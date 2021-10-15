package id.ilmudata.manajemenpegawai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_pegawai.view.*

class PegawaiListAdapter internal constructor(context: Context)  //
    : RecyclerView.Adapter<PegawaiListAdapter.PegawaiViewHolder>()  {

    private val inflater: LayoutInflater = LayoutInflater.from(context) //
    private var items = emptyList<Pegawai>()
    // event
    var onItemClick: ((Pegawai) -> Unit)? = null

    inner class PegawaiViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val itemNIP = item.itemNIP
        val itemNama = item.itemNama

        init {
            // initialize
            item.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition]) // propagate
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PegawaiViewHolder {
        val itemView = inflater.inflate(R.layout.item_pegawai,parent,false)  //

        return PegawaiViewHolder(itemView) //
    }

    override fun onBindViewHolder(holder: PegawaiViewHolder, position: Int) {
        val item = items[position]

        // binding
        holder.itemNIP.text = "NIP: ${item.nip}"
        holder.itemNama.text = item.nama
    }

    internal fun setDataSourcePegawai(items: List<Pegawai>) {
        this.items = items
        notifyDataSetChanged()
    }

}