package com.dwainekenney.imagenotes.ui.screens.notes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.dwainekenney.imagenotes.domain.models.NoteModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    navController: NavController
) {
    val (notes) = viewModel.viewState.collectAsState().value

    LazyColumn {
        items(notes) {
            NotePreview(it)
        }
    }
}

@Composable
private fun NotePreview(note: NoteModel) {
    Text(text = note.noteText)
}