package id.ilmudata.manajemenpegawai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mode = 1 // 1 = new, 2 = update
    private var server = "http://10.0.2.2:3000"

    private lateinit  var queue: RequestQueue
    private lateinit var allPegawai: MutableLiveData<List<Pegawai>>

    private var txtNIP: EditText? = null
    private var txtNama: EditText? = null
    private var txtEmail: EditText? = null
    private var rdGroup: RadioGroup? = null
    private var txtAge: EditText? = null

    private var currentPegawai: Pegawai = Pegawai(0,"","","","",0,)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init
        txtNIP = findViewById(R.id.txtNIP)
        txtNama = findViewById(R.id.txtNama)
        txtEmail = findViewById(R.id.txtEmail)
        txtAge = findViewById(R.id.txtAge)
        rdGroup = findViewById(R.id.radioGroup)

        val adapter = PegawaiListAdapter(this)

        vwPegawai.layoutManager = LinearLayoutManager(this)
        vwPegawai.adapter = adapter

        adapter.onItemClick = { pegawai ->
            // rebinding controls
            currentPegawai = pegawai

            // fill data
            txtNIP?.setText(pegawai.nip)
            txtAge?.setText(pegawai.umur.toString())
            txtEmail?.setText(pegawai.email)
            txtNama?.setText(pegawai.nama)


            if(pegawai.jeniskelamin == "Wanita")
                rdGroup?.check(R.id.rdWoman)
            else
                rdGroup?.check(R.id.rdMan)

            mode = 2
        }

        // instance
        queue = Volley.newRequestQueue(this)
        allPegawai = MutableLiveData<List<Pegawai>>()
        allPegawai.observe(this, Observer { items ->
            items?.let { adapter.setDataSourcePegawai(it)}
        })
    }
    fun showAllData(view: View) {
        val url = "${server}/api/all"

        val jsonPegawaiList = JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->
                    val gson = Gson()
                    var list = ArrayList<Pegawai>()
                    for (i in 0 until response.length()){
                        Log.i("APP","response.getString(i)")
                        list.add(gson.fromJson(response.getString(i),Pegawai::class.java))
                    }

                    allPegawai.value = list

                },
                Response.ErrorListener { error ->
                    val msg = "Response is: ${error.toString()}"
                    Log.e("APP", msg)
                    //txtResult?.setText("Response is: ${error.toString()}")
                }
        )

        queue.add(jsonPegawaiList)
    }

    fun clearAll(){
        currentPegawai = Pegawai(0,"","","","",0,)
        txtNIP?.setText("")
        txtAge?.setText("")
        txtEmail?.setText("")
        txtNama?.setText("")
    }

    fun newData(view: View){
        mode = 1
        clearAll()
    }
    fun saveData(view: View){
        val gson = Gson()
        val sex: RadioButton = findViewById(radioGroup.checkedRadioButtonId)

        var peg = Pegawai(
                id = currentPegawai.id,
                nip = txtNIP?.text.toString(),
                nama = txtNama?.text.toString(),
                email = txtEmail?.text.toString(),
                umur = txtAge?.text.toString().toInt(),
                jeniskelamin = sex.text.toString()
        )
        var jsonPeg = gson.toJson(peg)
        if(mode==1){
            // insert
            val url = "${server}/api/insert"
            val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, url, JSONObject(jsonPeg),
                    Response.Listener { response ->
                        peg.id = response.getInt("id")
                        var list = allPegawai.value as ArrayList<Pegawai>
                        list.add(peg)
                        allPegawai.value = list
                        mode = 2
                    },
                    Response.ErrorListener { error ->
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
                    Response.Listener { response ->
                        var list = allPegawai.value as ArrayList<Pegawai>
                        list.forEachIndexed { index, item ->
                            item.takeIf { it.id == peg.id}?.let {
                                list[index] = peg
                            }
                        }
                        allPegawai.value = list
                        mode = 2
                    },
                    Response.ErrorListener { error ->
                        val msg = "Response is: ${error.toString()}"
                        Log.e("APP", msg)
                    }
            )
            queue.add(jsonObjectRequest)
        }

    }
    fun deleteData(view: View) {

        if(currentPegawai!=null) {
            val url = "${server}/api/delete"
            var json = JSONObject()
            json.put("id",currentPegawai.id)

            val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, url, json,
                    Response.Listener { response ->
                        var list = allPegawai.value as ArrayList<Pegawai>
                        list.remove(currentPegawai)
                        allPegawai.value = list

                        mode =1
                        clearAll()
                    },
                    Response.ErrorListener { error ->
                        val msg = "Response is: ${error.toString()}"
                        Log.e("APP", msg)
                    }
            )
            queue.add(jsonObjectRequest)

        }
    }
}