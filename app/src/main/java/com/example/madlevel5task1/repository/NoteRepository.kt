package com.example.madlevel5task1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task1.dao.NoteDao
import com.example.madlevel5task1.database.NoteRoomDatabase
import com.example.madlevel5task1.model.Note

class NoteRepository(context: Context) {
    private var noteDao: NoteDao

    init {
        val noteRoomDatabase = NoteRoomDatabase.getDatabase(context)
        noteDao = noteRoomDatabase!!.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return noteDao?.getAllNotes() ?: MutableLiveData(emptyList())
    }

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}