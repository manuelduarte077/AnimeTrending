package dev.donmanuel.animelistapp.ui.screen.anime

import app.cash.turbine.test
import dev.donmanuel.animelistapp.domain.model.AnimeData
import dev.donmanuel.animelistapp.domain.model.Attributes
import dev.donmanuel.animelistapp.domain.model.CoverImage
import dev.donmanuel.animelistapp.domain.model.PosterImage
import dev.donmanuel.animelistapp.domain.model.Titles
import dev.donmanuel.animelistapp.domain.repository.KitsuRepository
import dev.donmanuel.animelistapp.util.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AnimeViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var repository: KitsuRepository
    private lateinit var viewModel: AnimeViewModel

    @Before
    fun setup() {
        repository = mockk()
        viewModel = AnimeViewModel(repository)
    }

    @Test
    fun `fetchAnime updates state with anime data when repository returns data`() = runTest {
        // Given
        val animeId = 1
        val mockAnimeData = createMockAnimeData("1")
        coEvery { repository.getAnime(animeId) } returns mockAnimeData

        // When
        viewModel.fetchAnime(animeId)
        coroutineTestRule.testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.anime.test {
            val emittedAnime = awaitItem()
            assertEquals("1", emittedAnime?.id)
            assertEquals("Test Anime 1", emittedAnime?.attributes?.canonicalTitle)
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { repository.getAnime(animeId) }
    }

    @Test
    fun `fetchAnime updates state with null when repository returns null`() = runTest {
        // Given
        val animeId = 1
        coEvery { repository.getAnime(animeId) } returns null

        // When
        viewModel.fetchAnime(animeId)
        coroutineTestRule.testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.anime.test {
            val emittedAnime = awaitItem()
            assertNull(emittedAnime)
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { repository.getAnime(animeId) }
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
