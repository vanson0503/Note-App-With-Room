package com.example.noteapp.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.Model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note : Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes():LiveData<List<Note>>

    @Query("SELECT * FROM notes_table WHERE id=:id")
    fun getNoteById(id:Int):LiveData<Note>

    @Query("SELECT * FROM notes_table WHERE title LIKE '%' || :text || '%' OR description LIKE '%' || :text || '%' ")
    fun searchNotes(text: String): LiveData<List<Note>>


}