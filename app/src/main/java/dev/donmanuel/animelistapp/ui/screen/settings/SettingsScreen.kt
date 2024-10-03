package dev.donmanuel.animelistapp.ui.screen.settings

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
	navigateBack: () -> Unit,
) {
	Scaffold(topBar = {
		TopAppBar(title = {
			Text(
				text = "Settings",
				style = MaterialTheme.typography.titleLarge,
				fontWeight = FontWeight.Bold,
				textAlign = TextAlign.Center,
			)
		}, actions = {
			IconButton(onClick = navigateBack) {
				Icon(imageVector = Icons.Rounded.Settings, contentDescription = "Back")
			}
		})
	}) { innerPadding ->
		// Settings content
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
		) {
			Text(
				text = "Settings",
				style = MaterialTheme.typography.titleLarge,
				fontWeight = FontWeight.Bold,
				textAlign = TextAlign.Center,
			)

			// Add settings content here
		}
	}
}