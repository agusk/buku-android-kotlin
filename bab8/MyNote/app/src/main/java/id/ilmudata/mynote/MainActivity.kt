package id.ilmudata.mynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var adapter: RecyclerAdapter
    val notes = mutableListOf<Note>(
        Note("Catatan 1", Date()),
        Note("Catatan 2",Date()),
        Note("Catatan 3",Date()),
        Note("Catatan 4",Date()),
        Note("Catatan 5",Date()))

    override fun onItemClicked(note: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("btn","Edit")
        intent.putExtra("note",note)

        startActivityForResult(intent,1)
    }

    override fun onItemDeleteClicked(item: Note) {
        notes.remove(item)
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesList = findViewById<RecyclerView>(R.id.myNote)
        notesList.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(notes,this)
        notesList.adapter = adapter
    }

    fun addNewNote(v: View) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("btn","Tambah")
        intent.putExtra("note",Note("", Date()))

        startActivityForResult(intent,1)
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
                    notes.set(index, note)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

}