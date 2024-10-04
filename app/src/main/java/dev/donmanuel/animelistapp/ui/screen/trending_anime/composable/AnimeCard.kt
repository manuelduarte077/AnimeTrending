@file:OptIn(ExperimentalSharedTransitionApi::class)

package dev.donmanuel.animelistapp.ui.screen.trending_anime.composable

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.donmanuel.animelistapp.domain.model.AnimeData

@Composable
fun SharedTransitionScope.AnimeCard(
	anime: AnimeData,
	onClick: () -> Unit,
	animatedVisibilityScope: AnimatedVisibilityScope,
	modifier: Modifier = Modifier
) {
	Card(onClick = onClick, modifier = modifier) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(10.dp),
			modifier = Modifier.padding(6.dp)
		) {
			AsyncImage(
				model = anime.attributes.posterImage.original,
				contentDescription = "anime image",
				modifier = Modifier
					.size(120.dp)
					.clip(RoundedCornerShape(8.dp))
					.sharedElement(
						rememberSharedContentState(key = anime.id),
						animatedVisibilityScope = animatedVisibilityScope
					),
				contentScale = ContentScale.Crop,
			)

			Column {
				Row(
					modifier = Modifier
						.background(
							Color(0xFFC4C7EB),
							shape = RoundedCornerShape(20.dp)
						)
						.padding(horizontal = 6.dp, vertical = 4.dp)
				) {
					Icon(
						imageVector = Icons.Rounded.Star,
						contentDescription = "rating",
						tint = Color.Yellow
					)
					Text(
						text = anime.attributes.averageRating.toString(),
						style = MaterialTheme.typography.titleMedium,
						textAlign = TextAlign.Center,
						fontWeight = FontWeight.SemiBold
					)
				}

				Text(
					text = anime.attributes.canonicalTitle.toString(),
					maxLines = 1,
					overflow = TextOverflow.Ellipsis,
					style = MaterialTheme.typography.bodyLarge
				)

				Text(
					text = anime.attributes.synopsis.toString(),
					maxLines = 2,
					overflow = TextOverflow.Ellipsis,
					style = MaterialTheme.typography.titleMedium
				)

				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 6.dp, vertical = 4.dp),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween,
				) {
					Text(
						text = "Episodes: ${anime.attributes.episodeCount}",
						style = MaterialTheme.typography.labelMedium
					)
					Text(
						text = " | ",
						style = MaterialTheme.typography.labelMedium
					)
					Text(
						text = "Status: ${anime.attributes.status}",
						style = MaterialTheme.typography.labelMedium
					)
				}

				Text(
					text = "Age Rating: ${anime.attributes.ageRatingGuide}",
					style = MaterialTheme.typography.labelMedium
				)
			}
		}
	}
}