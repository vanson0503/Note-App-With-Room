package com.example.noteapp.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.Database.Repository.NoteRepository
import com.example.noteapp.Model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(app:Application):ViewModel() {
    private val noteRepository = NoteRepository(app)

    fun insert(note: Note) = GlobalScope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }
    fun update(note: Note) = GlobalScope.launch(Dispatchers.IO) {
        noteRepository.update(note)
    }
    fun delete(note: Note) = GlobalScope.launch(Dispatchers.IO) {
        noteRepository.delete(note)
    }
    fun getAllNotes():LiveData<List<Note>> = noteRepository.getAllNotes()
    fun getNoteById(id:Int):LiveData<Note> = noteRepository.getNoteById(id)
    fun searchNote(text:String):LiveData<List<Note>> = noteRepository.searchNote(text)
}