package com.example.noteapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Model.Note
import com.example.noteapp.R
import com.example.noteapp.Utils.util

class NoteAdapter(
    val notes:List<Note>,
    val onDeleteNote: (note: Note) -> Unit,
    val onUpdateNote: (note: Note) -> Unit
):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.txtTitle)
        val itemNoteView = itemView.findViewById<CardView>(R.id.itemNoteView)
        val btnDeleteNote = itemView.findViewById<ConstraintLayout>(R.id.btnDeleteNote)
        val btnDelete = itemView.findViewById<ImageView>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val flag = false
        holder.apply {
            title.setBackgroundColor(Color.parseColor(util.color[position%6]))
            title.setText(notes[position].title)
            itemNoteView.setOnLongClickListener { view ->
                if (btnDeleteNote.visibility == View.GONE) {
                    btnDeleteNote.visibility = View.VISIBLE
                    //title.visibility = View.GONE
                }
                else if (btnDeleteNote.visibility == View.VISIBLE) {
                    btnDeleteNote.visibility = View.GONE
                    //title.visibility = View.GONE
                }
                true // Để xác nhận rằng bạn đã xử lý sự kiện long click
            }

            btnDelete.setOnClickListener {
                onDeleteNote(notes[position])
            }
            itemNoteView.setOnClickListener{
                onUpdateNote(notes[position])
            }


        }
    }
}