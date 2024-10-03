// MainActivity.kt
package dev.donmanuel.animelistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.donmanuel.animelistapp.ui.theme.AnimeListAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			AnimeListAppTheme {
				val navController = rememberNavController()

				enableEdgeToEdge(
					statusBarStyle = SystemBarStyle.dark(
						android.graphics.Color.TRANSPARENT
					)
				)

				AppNavigation(navController = navController)
			}
		}
	}
}