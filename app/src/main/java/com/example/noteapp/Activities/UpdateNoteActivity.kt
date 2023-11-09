package com.example.noteapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.Model.Note
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.ViewModel.NoteViewModelFactory
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateNoteBinding
    private val noteViewModel : NoteViewModel by lazy{
        ViewModelProvider(
            this,
            NoteViewModelFactory(application)
        ).get(NoteViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        noteViewModel.getNoteById(id).observe(this){
            note->
            binding.edtTitleUpdate.setText(note.title)
            binding.edtDescUpdate.setText(note.description)
        }
        
        binding.btnUpdate.setOnClickListener { 
            val title = binding.edtTitleUpdate.text.toString()
            val desc = binding.edtDescUpdate.text.toString()
            if(title.isNotEmpty()&&desc.isNotEmpty()){
                noteViewModel.update(Note(id,title,desc))
                finish()
            }else{
                Toast.makeText(this, "Data is not empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}