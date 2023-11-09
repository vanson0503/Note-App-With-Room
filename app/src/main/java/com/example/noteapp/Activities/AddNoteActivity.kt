package com.example.noteapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.Model.Note
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.ViewModel.NoteViewModelFactory
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.databinding.ActivityMainBinding

class AddNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNoteBinding
    private val noteViewModel : NoteViewModel by lazy{
        ViewModelProvider(
            this,
            NoteViewModelFactory(application)
        ).get(NoteViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnAddNote.setOnClickListener {
            addNoteHandler()
        }
    }

    private fun addNoteHandler() {
        if(binding.edtTitle.text.toString().isNotEmpty()&&binding.edtDescription.text.toString().isNotEmpty()){
            val note = Note(title=binding.edtTitle.text.toString(), description = binding.edtDescription.text.toString())
            noteViewModel.insert(note)
            finish()
        }
        else{
            Toast.makeText(this, "Data is not empty!", Toast.LENGTH_SHORT).show()
        }
    }
}