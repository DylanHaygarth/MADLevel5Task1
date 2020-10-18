package com.example.madlevel5task1.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.madlevel5task1.repository.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository =  NoteRepository(application.applicationContext)

    val note = noteRepository.getNotePad()
}
