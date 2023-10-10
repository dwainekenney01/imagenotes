package com.dwainekenney.imagenotes.domain.repository

import com.dwainekenney.imagenotes.domain.storage.NotesStorage
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesStorage: NotesStorage
) {

    fun getAllNotes() = notesStorage.getAllNotes()
}