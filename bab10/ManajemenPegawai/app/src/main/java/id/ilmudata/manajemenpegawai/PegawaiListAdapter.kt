package id.ilmudata.manajemenpegawai

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import id.ilmudata.manajemenpegawai.databinding.ItemPegawaiBinding

class PegawaiListAdapter internal constructor(context: Context)  //
    : RecyclerView.Adapter<PegawaiListAdapter.PegawaiViewHolder>()  {

    private var items = emptyList<Pegawai>()
    // event
    var onItemClick: ((Pegawai) -> Unit)? = null

    inner class PegawaiViewHolder(val binding: ItemPegawaiBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            // initialize
            binding.layout .setOnClickListener {
                onItemClick?.invoke(items[adapterPosition]) // propagate
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PegawaiViewHolder {
        return PegawaiViewHolder(
            ItemPegawaiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: PegawaiViewHolder, position: Int) {
        val item = items[position]

        // binding
        holder.binding.itemNIP.text = "NIP: ${item.nip}"
        holder.binding.itemNama.text = item.nama
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setDataSourcePegawai(items: List<Pegawai>) {
        this.items = items
        notifyDataSetChanged()
    }

}