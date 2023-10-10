package com.dwainekenney.imagenotes.domain.repository

import com.dwainekenney.imagenotes.domain.models.NoteModel
import com.dwainekenney.imagenotes.domain.storage.NotesStorage
import javax.inject.Inject

// TODO: Add remote data source to sync notes with cloud
class NotesRepository @Inject constructor(
    private val notesStorage: NotesStorage
) {

    fun getAllNotes() = notesStorage.getAllNotes()

    fun getNote(id: String) = notesStorage.getNote(id)

    fun saveNote(note: NoteModel) = notesStorage.saveNote(note)

    fun deleteNote(id: String) = notesStorage.deleteNote(id)
}