package com.krisna.diva.topdrakor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krisna.diva.topdrakor.databinding.ItemRowDrakorBinding

class ListDrakorAdapter(private val listDrakor: ArrayList<Drakor>) :
    RecyclerView.Adapter<ListDrakorAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_row_drakor, parent, false)
        val binding = ItemRowDrakorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, name, description, synopsis) = listDrakor[position]
        holder.binding.ivDrakorPhoto.setImageResource(photo)
        holder.binding.tvDrakorName.text = name
        holder.binding.tvDrakorDescription.text = description
        holder.binding.tvDrakorSynopsis.text = synopsis

        holder.itemView.setOnClickListener { onItemClickCallback?.invoke(listDrakor[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listDrakor.size
//
//    class ListViewHolder(itemView: View) :
//        RecyclerView.ViewHolder(itemView) {
//        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_drakor_photo)
//        val tvName: TextView = itemView.findViewById(R.id.tv_drakor_name)
//        val tvDescription: TextView = itemView.findViewById(R.id.tv_drakor_description)
//        val tvSynopsis: TextView = itemView.findViewById(R.id.tv_drakor_synopsis)
//    }
class ListViewHolder(var binding: ItemRowDrakorBinding) : RecyclerView.ViewHolder(binding.root)


    //        SETONCLICK DARI MAIN ACTIVITY
    private var onItemClickCallback: ((Drakor) -> Unit)? = null

    // Ubah parameter metode setOnItemClickCallback menjadi fungsi lambda
    fun setOnItemClickCallback(onItemClickCallback: (Drakor) -> Unit) {
        this.onItemClickCallback = onItemClickCallback
    }
}