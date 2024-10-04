@file:OptIn(ExperimentalSharedTransitionApi::class)

package dev.donmanuel.animelistapp.ui.screen.trending_anime

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.donmanuel.animelistapp.SettingsRoute
import dev.donmanuel.animelistapp.ui.screen.trending_anime.composable.AnimeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.TrendingAnimeScreen(
	navController: NavHostController,
	onAnimeClick: (String, String) -> Unit,
	animatedVisibilityScope: AnimatedVisibilityScope,
	viewModel: TrendingAnimeViewModel = hiltViewModel()
) {
	val animeData by viewModel.animeData.collectAsStateWithLifecycle()

	Scaffold(topBar = {
		TopAppBar(title = {
			Text(
				text = "Anime List",
				style = MaterialTheme.typography.titleLarge,
				fontWeight = FontWeight.Bold,
				textAlign = TextAlign.Center,
			)
		}, actions = {
			IconButton(onClick = {
				navController.navigate(SettingsRoute)
			}) {
				Icon(imageVector = Icons.Rounded.Settings, contentDescription = "Settings")
			}

		})

	}) { innerPadding ->
		AnimatedContent(
			targetState = animeData.isEmpty(), label = "..."
		) { isEmpty ->
			if (isEmpty) {
				Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
					CircularProgressIndicator()
				}
			} else {
				LazyColumn(
					contentPadding = PaddingValues(
						top = innerPadding.calculateTopPadding() + 10.dp,
						start = 20.dp,
						end = 20.dp,
						bottom = innerPadding.calculateBottomPadding() + 10.dp,
					), verticalArrangement = Arrangement.spacedBy(16.dp)
				) {
					items(animeData) { anime ->
						AnimeCard(
							anime = anime, onClick = {
								onAnimeClick(anime.attributes.posterImage.original, anime.id)
							}, animatedVisibilityScope = animatedVisibilityScope
						)
					}
				}

			}
		}
	}
}

