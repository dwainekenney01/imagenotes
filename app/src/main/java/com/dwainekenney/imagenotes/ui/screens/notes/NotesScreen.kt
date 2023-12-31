package com.dwainekenney.imagenotes.ui.screens.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dwainekenney.imagenotes.R
import com.dwainekenney.imagenotes.domain.models.NoteModel
import com.dwainekenney.imagenotes.ui.composables.MainScaffold
import com.dwainekenney.imagenotes.ui.composables.MainScaffoldRoute
import com.dwainekenney.imagenotes.ui.navigation.Routes

@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    navController: NavController
) {
    val (notes) = viewModel.viewState.collectAsState().value

    MainScaffold(
        scaffoldRoute = MainScaffoldRoute.NOTES,
        navController = navController,
        enableNewNoteCreation = true,
        createNewNote = viewModel::createNewNote
    ) {
        if (notes.isEmpty()) {
            NoNotesView()
        } else {
            LazyColumn {
                items(notes) {
                    NotePreview(
                        note = it,
                        onSelectNote = { navController.navigate(Routes.noteRoute(it.id)) }
                    )
                }
            }
        }
    }
}

@Composable
private fun NotePreview(
    note: NoteModel,
    onSelectNote: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectNote() }
            .padding(8.dp)
    ) {
        note.noteImageUri?.let {
            // TODO: Add image selection using Glide
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = note.firstLine.ifBlank { stringResource(id = R.string.empty_note) },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontStyle = if (note.firstLine.isBlank()) FontStyle.Italic else FontStyle.Normal
            )
            Text(
                text = stringResource(id = R.string.last_edited, note.lastEditedString),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight, 
            contentDescription = null
        )
    }
    Divider()
}

@Composable
private fun NoNotesView() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.no_notes)
        )
    }
}