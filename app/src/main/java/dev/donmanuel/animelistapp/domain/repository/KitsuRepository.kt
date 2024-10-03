package dev.donmanuel.animelistapp.domain.repository

import dev.donmanuel.animelistapp.domain.model.AnimeData

interface KitsuRepository {

	suspend fun getTrendingAnime(): List<AnimeData>

	suspend fun getAnime(id: Int): AnimeData?
}