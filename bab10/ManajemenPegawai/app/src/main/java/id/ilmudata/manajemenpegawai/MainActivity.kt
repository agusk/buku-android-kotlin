package id.ilmudata.manajemenpegawai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*

import id.ilmudata.manajemenpegawai.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mode = 1 // 1 = new, 2 = update
    private var server = "http://10.0.2.2:3000"

    private lateinit  var queue: RequestQueue
    private lateinit var allPegawai: MutableLiveData<List<Pegawai>>

    private var currentPegawai: Pegawai = Pegawai(0,"","","","",0,)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PegawaiListAdapter(this)

        binding.vwPegawai.layoutManager = LinearLayoutManager(this)
        binding.vwPegawai.adapter = adapter

        adapter.onItemClick = { pegawai ->
            // rebinding controls
            currentPegawai = pegawai

            // fill data
            binding.txtNIP.setText(pegawai.nip)
            binding.txtAge.setText(pegawai.umur.toString())
            binding.txtEmail.setText(pegawai.email)
            binding.txtNama.setText(pegawai.nama)


            if(pegawai.jeniskelamin == "Wanita")
                binding.radioGroup.check(R.id.rdWoman)
            else
                binding.radioGroup.check(R.id.rdMan)

            mode = 2
        }

        // instance
        queue = Volley.newRequestQueue(this)
        allPegawai = MutableLiveData<List<Pegawai>>()
        allPegawai.observe(this, Observer { items ->
            items?.let { adapter.setDataSourcePegawai(it)}
        })
    }
    fun showAllData(v:View) {
        val url = "${server}/api/all"

        val jsonPegawaiList = JsonArrayRequest(
                Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val list = ArrayList<Pegawai>()
                for (i in 0 until response.length()){
                    Log.i("APP","response.getString(i)")
                    list.add(gson.fromJson(response.getString(i),Pegawai::class.java))
                }

                allPegawai.value = list

            },
            { error ->
                val msg = "Response is: ${error.toString()}"
                Log.e("APP", msg)
            }
        )

        queue.add(jsonPegawaiList)
    }

    private fun clearAll(){
        currentPegawai = Pegawai(0,"","","","",0,)
        binding.txtNIP.setText("")
        binding.txtAge.setText("")
        binding.txtEmail.setText("")
        binding.txtNama.setText("")
    }

    fun newData(v:View){
        mode = 1
        clearAll()
    }
    fun saveData(v:View){
        val gson = Gson()
        val sex: RadioButton = findViewById(binding.radioGroup.checkedRadioButtonId)

        val peg = Pegawai(
                id = currentPegawai.id,
                nip = binding.txtNIP.text.toString(),
                nama = binding.txtNama.text.toString(),
                email = binding.txtEmail.text.toString(),
                umur = binding.txtAge.text.toString().toInt(),
                jeniskelamin = sex.text.toString()
        )
        val jsonPeg = gson.toJson(peg)
        if(mode==1){
            // insert
            val url = "${server}/api/insert"
            val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, url, JSONObject(jsonPeg),
                { response ->
                    peg.id = response.getInt("id")
                    if(allPegawai.value!=null){
                        val list = allPegawai.value as ArrayList<Pegawai>
                        list.add(peg)
                        allPegawai.value = list
                    }else{
                        val list = ArrayList<Pegawai>()
                        list.add(peg)
                        allPegawai.value = list
                    }
                    mode = 2
                },
                { error ->
                    val msg = "Response is: ${error.toString()}"
                    Log.e("APP", msg)
                }
            )
            queue.add(jsonObjectRequest)

        }else{
            // update
            val url = "${server}/api/update"
            val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, url, JSONObject(jsonPeg),
                { _ ->
                    val list = allPegawai.value as ArrayList<Pegawai>
                    list.forEachIndexed { index, item ->
                        item.takeIf { it.id == peg.id}?.let {
                            list[index] = peg
                        }
                    }
                    allPegawai.value = list
                    mode = 2
                },
                { error ->
                    val msg = "Response is: ${error.toString()}"
                    Log.e("APP", msg)
                }
            )
            queue.add(jsonObjectRequest)
        }

    }
    fun deleteData(v:View) {

        if(currentPegawai.id>0) {
            val url = "${server}/api/delete"
            val json = JSONObject()
            json.put("id",currentPegawai.id)

            val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, url, json,
                { _ ->
                    val list = allPegawai.value as ArrayList<Pegawai>
                    list.remove(currentPegawai)
                    allPegawai.value = list

                    mode =1
                    clearAll()
                },
                { error ->
                    val msg = "Response is: ${error.toString()}"
                    Log.e("APP", msg)
                }
            )
            queue.add(jsonObjectRequest)

        }
    }
}