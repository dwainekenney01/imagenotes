package com.dwainekenney.imagenotes.domain.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.dwainekenney.imagenotes.di.SettingsSharedPrefs
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    @SettingsSharedPrefs private val sharedPreferences: SharedPreferences
) {

    fun setAutoSaveEnabled(enabled: Boolean) {
        sharedPreferences.edit {
            putBoolean(AUTO_SAVE_KEY, enabled)
        }
    }

    fun isAutoSaveEnabled(): Boolean {
        return sharedPreferences.getBoolean(AUTO_SAVE_KEY, true)
    }

    companion object {
        private const val AUTO_SAVE_KEY = "autoSave"
    }
}