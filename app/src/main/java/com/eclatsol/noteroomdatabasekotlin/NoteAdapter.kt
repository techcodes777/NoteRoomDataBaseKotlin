package com.eclatsol.noteroomdatabasekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val context: Context, private val iNoteClickListener: INoteClickListener) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    val list = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text)
        val deleteBtn: ImageView = itemView.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_add_data, parent, false)
        )
        viewHolder.deleteBtn.setOnClickListener {
            iNoteClickListener.itemClick(list[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.textView.text = list[position].text
    }

    fun updateList(note: List<Note>){
        list.clear()
        list.addAll(note)
        notifyDataSetChanged()
    }
}

interface INoteClickListener {
    fun itemClick(note: Note)
}