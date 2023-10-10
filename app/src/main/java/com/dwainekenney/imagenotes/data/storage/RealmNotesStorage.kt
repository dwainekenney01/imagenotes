package com.dwainekenney.imagenotes.data.storage

import com.dwainekenney.imagenotes.domain.models.NoteModel
import com.dwainekenney.imagenotes.domain.storage.NotesStorage
import javax.inject.Inject

class RealmNotesStorage @Inject constructor(

) : NotesStorage {
    override fun getAllNotes(): List<NoteModel> {
        return listOf(
            NoteModel("note", null, 0, 10)
        )
    }

    override fun getNote(id: String): NoteModel? {
        TODO("Not yet implemented")
    }

    override fun saveNote(note: NoteModel) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(id: String) {
        TODO("Not yet implemented")
    }
}