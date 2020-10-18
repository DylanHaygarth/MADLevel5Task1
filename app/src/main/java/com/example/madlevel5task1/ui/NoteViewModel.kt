package com.example.madlevel5task1.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task1.model.Note
import com.example.madlevel5task1.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val noteRepository =  NoteRepository(application.applicationContext)

    val note = noteRepository.getNotePad()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun updateNote(title: String, text: String) {
        //if there is an existing note, take that id to update it instead of adding a new one
        val newNote = Note(
            id = note.value?.id,
            title = title,
            date = Date(),
            text = text
        )

        if(isNoteValid(newNote)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    noteRepository.updateNote(newNote)
                }
                success.value = true
            }
        }
    }

    // checks whether note had a non-empty title
    private fun isNoteValid(note: Note): Boolean {
        return when {
            note.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }

}
