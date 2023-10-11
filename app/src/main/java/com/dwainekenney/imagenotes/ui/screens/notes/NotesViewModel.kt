package com.dwainekenney.imagenotes.ui.screens.notes

import androidx.lifecycle.ViewModel
import com.dwainekenney.imagenotes.domain.models.NoteModel
import com.dwainekenney.imagenotes.domain.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(initViewState())
    val viewState: StateFlow<NotesViewState> get() = _viewState

    private fun initViewState() = NotesViewState(
        notes = notesRepository.getAllNotes()
    )

    data class NotesViewState(
        val notes: List<NoteModel>
    )

    // Returns the id of the newly-created note
    fun createNewNote(): String {
        return NoteModel().let {
            notesRepository.saveNote(it)
            it.id
        }
    }

    fun refresh() {
        _viewState.update { it.copy(notes = notesRepository.getAllNotes()) }
    }
}