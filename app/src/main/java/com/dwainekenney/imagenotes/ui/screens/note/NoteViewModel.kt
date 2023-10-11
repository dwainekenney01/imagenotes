package com.dwainekenney.imagenotes.ui.screens.note

import androidx.lifecycle.ViewModel
import com.dwainekenney.imagenotes.domain.models.NoteModel
import com.dwainekenney.imagenotes.domain.repository.NotesRepository
import com.dwainekenney.imagenotes.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(initViewState())
    private val state get() = _viewState.value
    val viewState: StateFlow<NoteViewState> get() = _viewState

    private fun initViewState() = NoteViewState(
        note = null,
        isAutoSaveEnabled = settingsRepository.isAutoSaveEnabled()
    )

    data class NoteViewState(
        val note: NoteModel?,
        val isAutoSaveEnabled: Boolean
    )

    fun initViewModel(noteId: String?) {
        requireNotNull(noteId)
        _viewState.update { it.copy(note = notesRepository.getNote(noteId)) }
    }

    // Returns true if save was successful
    fun saveNote(): Boolean {
        return state.note?.let {
            notesRepository.saveNote(it.copy(lastEditedTimestamp = System.currentTimeMillis()))
            true
        } ?: false
    }

    fun deleteNote() {
        state.note?.let { notesRepository.deleteNote(it.id) }
    }

    fun updateNoteText(newText: String) {
        _viewState.update { it.copy(note = it.note?.copy(noteText = newText)) }
        if (state.isAutoSaveEnabled) {
            saveNote()
        }
    }

    fun updateImageUri(newUri: String) {

    }
}