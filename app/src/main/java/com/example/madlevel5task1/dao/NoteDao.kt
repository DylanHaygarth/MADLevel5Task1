package com.example.madlevel5task1.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madlevel5task1.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM noteTable")
    fun getNotePad(): LiveData<Note?>

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}