package com.dwainekenney.imagenotes.ui.screens.settings

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
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(initViewState())
    val viewState: StateFlow<SettingsViewState> get() = _viewState

    private fun initViewState() = SettingsViewState(
        isAutoSaveEnabled = settingsRepository.isAutoSaveEnabled()
    )

    data class SettingsViewState(
        val isAutoSaveEnabled: Boolean
    )

    fun setAutoSaveEnabled(enabled: Boolean) {
        settingsRepository.setAutoSaveEnabled(enabled)
        _viewState.update { it.copy(isAutoSaveEnabled = enabled) }
    }
}