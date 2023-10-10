package com.dwainekenney.imagenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dwainekenney.imagenotes.ui.navigation.Routes
import com.dwainekenney.imagenotes.ui.screens.notes.NotesScreen
import com.dwainekenney.imagenotes.ui.screens.notes.NotesViewModel
import com.dwainekenney.imagenotes.ui.screens.settings.SettingsScreen
import com.dwainekenney.imagenotes.ui.screens.settings.SettingsViewModel
import com.dwainekenney.imagenotes.ui.theme.ImageNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageNotesTheme {
                MainScreen()
            }
        }
    }

    @Composable
    private fun MainScreen() {
        val navController = rememberNavController()

        MainNavigation(navController = navController)
    }

    @Composable
    private fun MainNavigation(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Routes.notesRoute()
        ) {
            composable(
                route = Routes.notesRoute()
            ) {
                val notesViewModel: NotesViewModel by viewModels()
                NotesScreen(viewModel = notesViewModel, navController = navController)
            }
            composable(
                route = Routes.noteRoute(),
                arguments = listOf(
                    navArgument(Routes.ID_ARG_KEY) {
                        type = NavType.StringType
                    }
                )
            ) {

            }
            composable(
                route = Routes.settingsRoute()
            ) {
                val settingsViewModel: SettingsViewModel by viewModels()
                SettingsScreen(viewModel = settingsViewModel, navController = navController)
            }
        }
    }
}