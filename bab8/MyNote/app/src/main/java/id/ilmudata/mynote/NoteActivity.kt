package id.ilmudata.mynote

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item_note.*
import java.util.*

class NoteActivity : AppCompatActivity() {
    var note = Note("", Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_note)

        val btn = intent.getStringExtra("btn")
        btnAdd.text = btn
        note = intent.getSerializableExtra("note") as Note
        txtNewNote.setText(note.note)

    }
    fun submit(v: View) {
        val returnIntent = Intent()
        note.note = txtNewNote.text.toString()

        returnIntent.putExtra("btn",  btnAdd.text)
        returnIntent.putExtra("note", note)

        setResult(RESULT_OK, returnIntent)

        finish()
    }
    fun cancelSubmit(v: View) {
        setResult(RESULT_CANCELED, null)
        finish()
    }
}