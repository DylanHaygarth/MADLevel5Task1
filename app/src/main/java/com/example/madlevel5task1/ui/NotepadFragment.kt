package com.example.madlevel5task1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.madlevel5task1.R
import kotlinx.android.synthetic.main.fragment_notepad.*

class NotepadFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notepad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddNoteResult()
    }

    // observes the live data from the view model. Whenever the live data changes, the notepad is updated
    private fun observeAddNoteResult() {
        viewModel.note.observe(viewLifecycleOwner, androidx.lifecycle.Observer { note ->
            note?.let {
                tvNoteTitle.text = it.title
                tvLastUpdated.text = getString(R.string.date, it.date.toString())
                tvNoteText.text = it.text
            }
        })
    }
}