package com.example.noteapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.Adapter.NoteAdapter
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.ViewModel.NoteViewModelFactory
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val noteViewModel : NoteViewModel by lazy{
        ViewModelProvider(
            this,
            NoteViewModelFactory(application)
        ).get(NoteViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //noteViewModel = NoteViewModel(application)
        init()

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this,AddNoteActivity::class.java))
        }
        noteViewModel.getAllNotes().observe(this){
            notes->
            binding.rcvNotesList.adapter = NoteAdapter(notes,{
                note->
                noteViewModel.delete(note)
            },{note->
                val intent = Intent(this,UpdateNoteActivity::class.java)
                    .apply {
                        putExtra("id",note.id)
                    }
                startActivity(intent)
            })
        }
        binding.btnSearch.setOnClickListener {
            val intent = Intent(this,SearchNoteActivity::class.java)
            startActivity(intent)
        }

    }
    fun init(){
        binding.rcvNotesList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        if(noteViewModel.getAllNotes().value?.size==0){
            binding.noItemDisplay.visibility = View.VISIBLE
            binding.rcvNotesList.visibility = View.GONE
        }
        else{
            binding.noItemDisplay.visibility = View.GONE
            binding.rcvNotesList.visibility = View.VISIBLE
        }
    }
}