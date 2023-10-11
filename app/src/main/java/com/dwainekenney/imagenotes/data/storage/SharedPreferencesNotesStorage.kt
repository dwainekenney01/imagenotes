package com.dwainekenney.imagenotes.data.storage

import android.content.SharedPreferences
import androidx.core.content.edit
import com.dwainekenney.imagenotes.di.NotesSharedPrefs
import com.dwainekenney.imagenotes.domain.models.NoteModel
import com.dwainekenney.imagenotes.domain.storage.NotesStorage
import com.google.gson.Gson
import java.lang.Exception
import javax.inject.Inject

// TODO: Migrate to Realm
class SharedPreferencesNotesStorage @Inject constructor(
    @NotesSharedPrefs private val sharedPreferences: SharedPreferences
) : NotesStorage {
    override fun getAllNotes(): List<NoteModel> {
        return sharedPreferences.all.mapNotNull {
            try {
                Gson().fromJson(it.value as String, NoteModel::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }

    override fun getNote(id: String): NoteModel? {
        return try {
            sharedPreferences.getString(id, null)?.let {
                Gson().fromJson(it, NoteModel::class.java)
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun saveNote(note: NoteModel) {
        sharedPreferences.edit {
            putString(note.id, Gson().toJson(note))
        }
    }

    override fun deleteNote(id: String) {
        sharedPreferences.edit {
            remove(id)
        }
    }
}