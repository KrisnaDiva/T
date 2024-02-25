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
        val binding =
            ItemRowDrakorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ListViewHolder(var binding: ItemRowDrakorBinding) : RecyclerView.ViewHolder(binding.root)

    private var onItemClickCallback: ((Drakor) -> Unit)? = null

    fun setOnItemClickCallback(onItemClickCallback: (Drakor) -> Unit) {
        this.onItemClickCallback = onItemClickCallback
    }
}