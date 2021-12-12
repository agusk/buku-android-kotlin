package id.ilmudata.mynote

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import id.ilmudata.mynote.databinding.ActivityItemNoteBinding

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemNoteBinding
    private var note = Note(0,"", Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btn = intent.getStringExtra("btn")
        binding.btnAdd.text = btn
        note = intent.getSerializableExtra("note") as Note
        binding.txtNewNote.setText(note.note)

    }
    fun submit(v: View) {
        val returnIntent = Intent()
        note.note = binding.txtNewNote.text.toString()

        returnIntent.putExtra("btn",  binding.btnAdd.text)
        returnIntent.putExtra("note", note)

        setResult(RESULT_OK, returnIntent)

        finish()
    }
    fun cancelSubmit(v: View) {
        setResult(RESULT_CANCELED, null)
        finish()
    }
}