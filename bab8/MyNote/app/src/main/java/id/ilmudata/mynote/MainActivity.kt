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
        Note(1,"Catatan 1", Date()),
        Note(2,"Catatan 2",Date()),
        Note(3,"Catatan 3",Date()),
        Note(4,"Catatan 4",Date()),
        Note(5,"Catatan 5",Date()))

    override fun onItemClicked(item: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("btn","Edit")
        intent.putExtra("note",item)

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

        binding.myNote.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(notes,this)
        binding.myNote.adapter = adapter
    }

    fun addNewNote(v: View) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("btn","Tambah")
        intent.putExtra("note",Note(0,"", Date()))

        getResult.launch(intent)
    }
    private fun getNewNoteId(): Int {
        var id = 0
        notes.forEach { item ->
            if(item.id>id)
                id = item.id
        }
        return id+1
    }

    @SuppressLint("NotifyDataSetChanged")
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if(it.resultCode == Activity.RESULT_OK){
                val note: Note = it.data?.getSerializableExtra("note") as Note

                if(note.id==0){
                    note.id = getNewNoteId()
                    notes.add(note)
                    adapter.notifyDataSetChanged()
                }else{
                    val index = notes.indexOf(notes.first { e->e.id == note.id })
                    if(index>=0){
                        notes[index] = note
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }

}