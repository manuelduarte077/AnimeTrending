package dev.donmanuel.animelistapp.ui.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.PrivacyTip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.donmanuel.animelistapp.ui.theme.PurpleGrey80
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
	navigateBack: () -> Unit,
) {
	// States to manage theme and language switch
	val isDarkTheme = remember { mutableStateOf(false) }
	val isEnglish = remember { mutableStateOf(true) }
	val showBookmarksSheet = remember { mutableStateOf(false) }
	val showPrivacyPolicySheet = remember { mutableStateOf(false) }
	val coroutineScope = rememberCoroutineScope()

	val bookmarkList = listOf(
		"One Piece",
		"Naruto",
		"Dragon Ball",
		"Attack on Titan",
		"Death Note",
		"Tokyo Revengers",
		"Jujutsu Kaisen",
		"Black Clover",
		"Haikyuu",
		"Re:Zero",
		"Dr. Stone",
		"Vinland Saga",
		"Beastars",
		"Mob Psycho 100",
		"Kaguya-sama: Love is War",
		"Steins;Gate",
		"Code Geass",
		"Fullmetal Alchemist",
		"Your Lie in April",
		"Anohana",
		"Clannad",
		"Toradora",
	)

	// Sheet state
	val bottomSheetState = rememberModalBottomSheetState(
		skipPartiallyExpanded = true
	)

	Scaffold(topBar = {
		TopAppBar(
			title = {
				Text(
					text = "Settings",
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.Bold,
					textAlign = TextAlign.Center,
				)
			},
			navigationIcon = {
				IconButton(onClick = navigateBack) {
					Icon(
						imageVector = Icons.AutoMirrored.Filled.ArrowBack,
						contentDescription = "Back"
					)
				}
			},
		)
	}) { innerPadding ->
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
		) {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(16.dp),
				verticalArrangement = Arrangement.spacedBy(16.dp),
				horizontalAlignment = Alignment.Start
			) {
				// Change Theme option
				Text(
					text = "Change Theme",
					style = MaterialTheme.typography.bodyLarge,
					fontWeight = FontWeight.SemiBold
				)
				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Text(
						text = if (isDarkTheme.value) "Dark" else "Light",
						style = MaterialTheme.typography.titleMedium,
						fontWeight = FontWeight.Medium,
					)
					Switch(
						checked = isDarkTheme.value,
						onCheckedChange = { isDarkTheme.value = it })
				}

				HorizontalDivider()

				// Change Language option
				Text(
					text = "Change Language",
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.SemiBold
				)
				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Text(
						text = if (isEnglish.value) "English" else "Spanish",
						style = MaterialTheme.typography.titleMedium,
						fontWeight = FontWeight.Medium,
					)
					Switch(
						checked = isEnglish.value,
						onCheckedChange = { isEnglish.value = it },
					)
				}

				HorizontalDivider()

				// Bookmarks option
				Row(
					modifier = Modifier
						.padding(top = 16.dp)
						.clickable {
							coroutineScope.launch {
								showBookmarksSheet.value = true
							}
						},

					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Icon(
						imageVector = Icons.Rounded.Bookmarks,
						contentDescription = "Bookmarks",
						modifier = Modifier.padding(end = 8.dp),
					)
					Text(
						text = "Bookmarks",
						style = MaterialTheme.typography.titleMedium,
						fontWeight = FontWeight.SemiBold
					)
				}
				HorizontalDivider()

				// App Name
				Text(
					text = "App Name",
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.SemiBold
				)
				Text(
					text = "AnimeList App",
					style = MaterialTheme.typography.titleSmall,
					fontWeight = FontWeight.Medium,
					modifier = Modifier
						.fillMaxWidth()
						.padding(top = 8.dp)
				)
				HorizontalDivider()

				/// Privacy Policy
				Row(modifier = Modifier
					.padding(top = 16.dp)
					.clickable {
						coroutineScope.launch {
							showPrivacyPolicySheet.value = true
						}
					}) {
					Icon(
						imageVector = Icons.Rounded.PrivacyTip,
						contentDescription = "Bookmarks",
						modifier = Modifier.padding(end = 8.dp)
					)

					Text(
						text = "Privacy Policy",
						style = MaterialTheme.typography.titleMedium,
						fontWeight = FontWeight.SemiBold,
					)
				}

				HorizontalDivider()

				// App Version
				Text(
					text = "App Version",
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.Medium
				)
				Text(
					text = "1.0.0",
					style = MaterialTheme.typography.titleMedium,
					modifier = Modifier
						.fillMaxWidth()
						.padding(top = 8.dp)
				)

			}
		}

		// BottomSheet for bookmarks
		if (showBookmarksSheet.value) {
			ModalBottomSheet(
				modifier = Modifier
					.fillMaxSize()
					.padding(vertical = 16.dp),
				onDismissRequest = { showBookmarksSheet.value = false },
				sheetState = bottomSheetState,
			) {
				LazyColumn(
					modifier = Modifier
						.padding(16.dp)
						.fillMaxWidth()
				) {
					items(bookmarkList) { bookmark ->
						BookmarksCard(bookmark = bookmark)
						HorizontalDivider()
					}
				}
			}

		}
	}

	/// Privacy Policy Modal
	if (showPrivacyPolicySheet.value) {
		ModalBottomSheet(
			modifier = Modifier
				.fillMaxSize()
				.padding(vertical = 16.dp),
			onDismissRequest = { showPrivacyPolicySheet.value = false },
			sheetState = bottomSheetState,
			shape = MaterialTheme.shapes.small,
		) {
			Column(
				modifier = Modifier
					.padding(horizontal = 16.dp)
					.verticalScroll(rememberScrollState()),
				verticalArrangement = Arrangement.spacedBy(16.dp),
			) {
				Text(
					text = "This is the privacy policy",
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.SemiBold,
				)

				Spacer(modifier = Modifier.height(12.dp))
				Text(
					text = privacyPolicy,
					style = MaterialTheme.typography.labelSmall,
					fontWeight = FontWeight.Medium
				)
				Spacer(modifier = Modifier.height(16.dp))
			}
		}
	}
}


/// Bookmarks Card
@Composable
fun BookmarksCard(
	bookmark: String,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		/// Imagen

		Icon(
			imageVector = Icons.Rounded.Bookmarks,
			contentDescription = "Bookmark",
			tint = PurpleGrey80,
			modifier = Modifier
				.height(60.dp)
				.width(60.dp)

		)
		Spacer(modifier = Modifier.width(8.dp))
		Column {
			Text(
				text = bookmark,
				style = MaterialTheme.typography.titleMedium,
				fontWeight = FontWeight.Bold,
			)
			Text(
				text = "This is a description of the anime",
				style = MaterialTheme.typography.titleSmall,
				fontWeight = FontWeight.Medium
			)

			Spacer(modifier = Modifier.height(16.dp))

			Row(
				modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = "Episodes: 12",
					style = MaterialTheme.typography.titleSmall,
					fontWeight = FontWeight.SemiBold
				)
				Text(
					text = "Score: 9.5",
					style = MaterialTheme.typography.titleSmall,
					fontWeight = FontWeight.SemiBold
				)
			}
		}
	}
}


val privacyPolicy = """
    Privacy Policy

    Your privacy is important to us. It is AnimeList App's policy to respect your privacy regarding any information we may collect from you across our website, http://animelistapp.com, and other sites we own and operate.

    1. Information we collect
    We only collect information about you if we have a reason to do so – for example, to provide our services, to communicate with you, or to make our services better.

    2. How we use information
    We use the information we collect in various ways, including to:
    - Provide, operate, and maintain our website
    - Improve, personalize, and expand our website
    - Understand and analyze how you use our website
    - Develop new products, services, features, and functionality
    - Communicate with you, either directly or through one of our partners, including for customer service, to provide you with updates and other information relating to the website, and for marketing and promotional purposes

    3. Sharing of information
    We do not share your personal information with anyone except to comply with the law, develop our products, or protect our rights.

    4. Security of information
    We take the security of your personal information seriously and use reasonable electronic, personnel, and physical measures to protect it from loss, theft, alteration, or misuse.

    5. Changes to this policy
    We may update our Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page.

    6. Contact us
    If you have any questions about this Privacy Policy, please contact us at support@animelistapp.com.

    Effective date: January 1, 2024
""".trimIndent()



