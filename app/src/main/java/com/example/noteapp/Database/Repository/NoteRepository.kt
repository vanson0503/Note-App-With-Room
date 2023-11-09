package com.example.noteapp.Database.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.noteapp.Database.Dao.NoteDao
import com.example.noteapp.Database.NoteDatabase
import com.example.noteapp.Model.Note

class NoteRepository(app:Application) {
    private val noteDao:NoteDao

    init {
        val noteDatabase = NoteDatabase.getInstance(app)
        noteDao = noteDatabase.getNoteDao()
    }

    suspend fun insert(note: Note) = noteDao.insert(note)
    suspend fun update(note: Note) = noteDao.update(note)
    suspend fun delete(note: Note) = noteDao.delete(note)

    fun getAllNotes():LiveData<List<Note>> = noteDao.getAllNotes()
    fun getNoteById(id:Int):LiveData<Note> = noteDao.getNoteById(id)
    fun searchNote(text:String):LiveData<List<Note>> = noteDao.searchNotes(text)

}