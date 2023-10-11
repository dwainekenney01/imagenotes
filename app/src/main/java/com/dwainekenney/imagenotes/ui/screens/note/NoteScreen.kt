package com.dwainekenney.imagenotes.ui.screens.note

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dwainekenney.imagenotes.R
import com.dwainekenney.imagenotes.domain.models.NoteModel
import com.dwainekenney.imagenotes.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: NoteViewModel,
    navController: NavController
) {
    val (note, isAutoSaveEnabled) = viewModel.viewState.collectAsState().value

    Scaffold(
        topBar = {
            EditNoteTopAppBar(
                closeNote = {
                    navController.navigate(Routes.notesRoute()) {
                        popUpTo(Routes.notesRoute()) {
                            inclusive = true
                        }
                    }
                },
                deleteNote = viewModel::deleteNote,
                saveNote = if (isAutoSaveEnabled.not()) viewModel::saveNote else null,
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            note?.let {
                EditNoteContent(
                    note = note,
                    onNoteTextChanged = viewModel::updateNoteText,
                    onImageUriUpdated = viewModel::updateImageUri
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditNoteTopAppBar(
    closeNote: () -> Unit,
    deleteNote: () -> Unit,
    saveNote: (() -> Boolean)? // If saveNote is null, save button will not display
) {
    val context = LocalContext.current

    TopAppBar(
        title = { Text(text = stringResource(id = R.string.editing_note_screen_title)) },
        navigationIcon = {
            IconButton(onClick = closeNote) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            saveNote?.let {
                IconButton(onClick = {
                    if (saveNote()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.note_saved),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
            }
            IconButton(onClick = {
                deleteNote()
                closeNote()
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ColumnScope.EditNoteContent(
    note: NoteModel,
    onNoteTextChanged: (String) -> Unit,
    onImageUriUpdated: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .weight(1f),
        value = note.noteText,
        onValueChange = onNoteTextChanged
    )
}