// Navigation.kt
package dev.donmanuel.animelistapp

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.donmanuel.animelistapp.ui.screen.anime.AnimeScreen
import dev.donmanuel.animelistapp.ui.screen.settings.SettingsScreen
import dev.donmanuel.animelistapp.ui.screen.trending_anime.TrendingAnimeScreen
import kotlinx.serialization.Serializable

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun AppNavigation(navController: NavHostController) {
	SharedTransitionLayout {
		NavHost(navController = navController, startDestination = TrendingAnimeRoute) {

			// Trending Anime Screen
			composable<TrendingAnimeRoute> {
				TrendingAnimeScreen(
					animatedVisibilityScope = this,
					onAnimeClick = { coverUrl, id ->
						navController.navigate(
							AnimeRoute(coverUrl = coverUrl, id = id)
						)
					},
					navController = navController,
				)
			}

			// Anime Screen
			composable<AnimeRoute> {
				val args = it.toRoute<AnimeRoute>()

				AnimeScreen(
					animatedVisibilityScope = this, id = args.id.toInt(), coverImage = args.coverUrl
				) {
					navController.popBackStack()
				}
			}

			// Settings Screen
			composable<SettingsRoute> {
				SettingsScreen {
					navController.popBackStack()
				}
			}
		}
	}
}

// Define routes
@Serializable
data object TrendingAnimeRoute

@Serializable
data class AnimeRoute(val coverUrl: String, val id: String)

@Serializable
data object SettingsRoute