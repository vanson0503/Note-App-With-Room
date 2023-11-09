package com.example.noteapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.Database.Dao.NoteDao
import com.example.noteapp.Model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase :RoomDatabase(){
    abstract fun getNoteDao():NoteDao

    companion object{
        @Volatile
        private var instance:NoteDatabase?=null

        fun getInstance(context: Context):NoteDatabase{
            if(instance==null){
                instance = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "notes_database"
                ).build()
            }
            return instance!!
        }
    }
}