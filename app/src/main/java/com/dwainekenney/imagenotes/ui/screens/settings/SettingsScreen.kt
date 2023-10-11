package com.dwainekenney.imagenotes.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dwainekenney.imagenotes.R
import com.dwainekenney.imagenotes.ui.composables.MainScaffold
import com.dwainekenney.imagenotes.ui.composables.MainScaffoldRoute

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    navController: NavController
) {
    val (isAutoSaveEnabled) = viewModel.viewState.collectAsState().value

    MainScaffold(
        scaffoldRoute = MainScaffoldRoute.SETTINGS,
        navController = navController
    ) {
        Column {
            SwitchPreference(
                title = stringResource(id = R.string.auto_save_preference_title),
                enabled = isAutoSaveEnabled,
                setEnabled = viewModel::setAutoSaveEnabled
            )
        }
    }
}

@Composable
private fun SwitchPreference(
    title: String,
    enabled: Boolean,
    setEnabled: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title
        )
        Switch(
            checked = enabled,
            onCheckedChange = setEnabled
        )
    }
}