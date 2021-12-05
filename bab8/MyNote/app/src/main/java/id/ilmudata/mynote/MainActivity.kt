package id.ilmudata.mynote

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import id.ilmudata.mynote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerAdapter
    private val notes = mutableListOf<Note>(
        Note("Catatan 1", Date()),
        Note("Catatan 2",Date()),
        Note("Catatan 3",Date()),
        Note("Catatan 4",Date()),
        Note("Catatan 5",Date()))

    override fun onItemClicked(item: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("btn","Edit")
        intent.putExtra("note",item)

        //startActivityForResult(intent,1)
        getResult.launch(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemDeleteClicked(item: Note) {
        notes.remove(item)
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notesList = findViewById<RecyclerView>(R.id.myNote)
        notesList.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(notes,this)
        notesList.adapter = adapter
    }

    fun addNewNote(v: View) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("btn","Tambah")
        intent.putExtra("note",Note("", Date()))

        getResult.launch(intent)
        //startActivityForResult(intent,1)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if(it.resultCode == Activity.RESULT_OK){
                val note: Note = it.data?.getSerializableExtra("note") as Note

                if(it.data?.getStringExtra("btn")== "Tambah"){
                    notes.add(note)
                    adapter.notifyDataSetChanged()
                }else{
                    val index = notes.indexOf(notes.first { e->e.created == note.created })
                    if(index>=0){
                        notes[index] = note
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode==RESULT_OK){
            var note: Note = data?.getSerializableExtra("note") as Note
            if(data?.getStringExtra("btn")== "Tambah"){
                notes.add(note)
                adapter.notifyDataSetChanged()
            }else{
                val index = notes.indexOf(notes.first { e->e.created == note.created })
                if(index>=0){
                    notes[index] = note
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

}