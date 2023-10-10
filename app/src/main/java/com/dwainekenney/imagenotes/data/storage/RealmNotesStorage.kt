package com.dwainekenney.imagenotes.data.storage

import com.dwainekenney.imagenotes.domain.models.NoteModel
import com.dwainekenney.imagenotes.domain.storage.NotesStorage
import javax.inject.Inject

class RealmNotesStorage @Inject constructor(

) : NotesStorage {
    override fun getAllNotes(): List<NoteModel> {
        return listOf(
            NoteModel("note text is very long there is a lot of text here sososososossososdf  sdfsdfa asdfa", null, 0, 10)
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