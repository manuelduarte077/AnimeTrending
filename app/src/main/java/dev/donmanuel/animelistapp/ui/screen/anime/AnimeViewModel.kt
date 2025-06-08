package dev.donmanuel.animelistapp.ui.screen.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.animelistapp.domain.model.AnimeData
import dev.donmanuel.animelistapp.domain.repository.KitsuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeViewModel(
    private val api: KitsuRepository
) : ViewModel() {

    private var _anime = MutableStateFlow<AnimeData?>(null)
    val anime = _anime.asStateFlow()

    fun fetchAnime(id: Int) {
        viewModelScope.launch {
            _anime.update { api.getAnime(id) }
        }
    }
}