package id.ilmudata.mynote


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import id.ilmudata.mynote.databinding.ItemNoteBinding

//class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class MyHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)
{
    val myNote = binding.myNote
    val noteCreated = binding.noteCreated


    @SuppressLint("SimpleDateFormat")
    fun bind(item: Note, clickListener: OnItemClickListener)
    {
        val pattern = "dd MMM yyyy HH:mm:ss"
        val df: DateFormat = SimpleDateFormat(pattern)

        myNote.text = item.note
        noteCreated.text = df.format(item.created)

        itemView.setOnClickListener {
            clickListener.onItemClicked(item)
        }
        itemView.btnDelete.setOnClickListener{
            clickListener.onItemDeleteClicked(item)
        }

    }

}
class RecyclerAdapter(
    private var items: MutableList<Note>,
    private val itemClickListener: OnItemClickListener)
                    :RecyclerView.Adapter<MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyHolder {
        return MyHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
//        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(myHolder: MyHolder, position: Int) {
        val item = items[position]
        myHolder.bind(item, itemClickListener)
    }

}
interface OnItemClickListener{
    fun onItemClicked(item: Note)
    fun onItemDeleteClicked(item: Note)
}