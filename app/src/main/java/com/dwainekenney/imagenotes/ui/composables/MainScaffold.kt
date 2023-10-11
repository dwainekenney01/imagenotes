package com.dwainekenney.imagenotes.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.dwainekenney.imagenotes.R
import com.dwainekenney.imagenotes.ui.navigation.Routes

enum class MainScaffoldRoute(val titleRes: Int) {
    NOTES(R.string.notes_screen_title),
    SETTINGS(R.string.settings_screen_title)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    scaffoldRoute: MainScaffoldRoute,
    navController: NavController,
    enableNewNoteCreation: Boolean = false,
    createNewNote: (() -> String)? = null,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            NotesTopAppBar(
                title = stringResource(id = scaffoldRoute.titleRes)
            )
        },
        bottomBar = {
            NotesBottomAppBar(
                currentRoute = scaffoldRoute,
                onTapBottomIcon = {
                    val route = when (it) {
                        MainScaffoldRoute.NOTES -> Routes.notesRoute()
                        MainScaffoldRoute.SETTINGS -> Routes.settingsRoute()
                    }
                    navController.navigate(route)
                }
            )
        },
        floatingActionButton = {
            if (enableNewNoteCreation) {
                FloatingActionButton(
                    onClick = {
                        createNewNote?.let {
                            val newNoteId = it.invoke()
                            navController.navigate(Routes.noteRoute(newNoteId))
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotesTopAppBar(
    title: String
) {
    TopAppBar(
        title = {
            Text(text = title)
        }
    )
}

@Composable
private fun NotesBottomAppBar(
    currentRoute: MainScaffoldRoute,
    onTapBottomIcon: (MainScaffoldRoute) -> Unit
) {
    fun tapBottomIcon(iconRoute: MainScaffoldRoute) {
        if (iconRoute != currentRoute)
            onTapBottomIcon(iconRoute)
    }

    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { tapBottomIcon(MainScaffoldRoute.NOTES) }) {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = null,
                    tint = if (currentRoute == MainScaffoldRoute.NOTES) MaterialTheme.colorScheme.primary
                    else Color.LightGray
                )
            }
            IconButton(onClick = { tapBottomIcon(MainScaffoldRoute.SETTINGS) }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = if (currentRoute == MainScaffoldRoute.SETTINGS) MaterialTheme.colorScheme.primary
                    else Color.LightGray
                )
            }
        }
    }
}