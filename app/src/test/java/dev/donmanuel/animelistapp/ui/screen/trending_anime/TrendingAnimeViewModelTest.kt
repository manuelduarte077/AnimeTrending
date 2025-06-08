package dev.donmanuel.animelistapp.ui.screen.trending_anime

import app.cash.turbine.test
import dev.donmanuel.animelistapp.domain.model.AnimeData
import dev.donmanuel.animelistapp.domain.model.Attributes
import dev.donmanuel.animelistapp.domain.model.CoverImage
import dev.donmanuel.animelistapp.domain.model.PosterImage
import dev.donmanuel.animelistapp.domain.model.Titles
import dev.donmanuel.animelistapp.domain.repository.KitsuRepository
import dev.donmanuel.animelistapp.util.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TrendingAnimeViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var repository: KitsuRepository
    private lateinit var viewModel: TrendingAnimeViewModel

    @Before
    fun setup() {
        repository = mockk()
    }

    @Test
    fun `init fetches trending anime from repository`() = runTest {
        // Given
        val mockAnimeList = listOf(createMockAnimeData("1"), createMockAnimeData("2"))
        coEvery { repository.getTrendingAnime() } returns mockAnimeList

        // When
        viewModel = TrendingAnimeViewModel(repository)
        coroutineTestRule.testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.animeData.test {
            val emittedList = awaitItem()
            assertEquals(2, emittedList.size)
            assertEquals("1", emittedList[0].id)
            assertEquals("2", emittedList[1].id)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `init handles empty list from repository`() = runTest {
        // Given
        coEvery { repository.getTrendingAnime() } returns emptyList()

        // When
        viewModel = TrendingAnimeViewModel(repository)
        coroutineTestRule.testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.animeData.test {
            val emittedList = awaitItem()
            assertEquals(0, emittedList.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun createMockAnimeData(id: String): AnimeData {
        return AnimeData(
            id = id,
            attributes = Attributes(
                createdAt = "2023-01-01",
                updatedAt = "2023-01-02",
                slug = "test-anime-$id",
                synopsis = "Test synopsis for anime $id",
                titles = Titles(en = "Test Anime $id"),
                canonicalTitle = "Test Anime $id",
                abbreviatedTitles = listOf("TA$id"),
                averageRating = "85.5",
                userCount = 1000,
                favoritesCount = 500,
                startDate = "2023-01-01",
                endDate = "2023-03-31",
                popularityRank = 10,
                ratingRank = 15,
                ageRating = "PG",
                ageRatingGuide = "Teens 13 or older",
                subtype = "TV",
                status = "finished",
                posterImage = PosterImage(
                    tiny = "tiny.jpg",
                    small = "small.jpg",
                    medium = "medium.jpg",
                    large = "large.jpg",
                    original = "original.jpg"
                ),
                coverImage = CoverImage(
                    tiny = "cover-tiny.jpg",
                    small = "cover-small.jpg",
                    large = "cover-large.jpg",
                    original = "cover-original.jpg"
                ),
                episodeCount = 12,
                episodeLength = 24,
                showType = "TV"
            )
        )
    }
}
