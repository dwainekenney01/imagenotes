package com.dwainekenney.imagenotes.domain.storage

import com.dwainekenney.imagenotes.domain.models.NoteModel

interface NotesStorage {
    fun getAllNotes(): List<NoteModel>
    fun getNote(id: String): NoteModel?
    fun saveNote(note: NoteModel)
    fun deleteNote(id: String)
}