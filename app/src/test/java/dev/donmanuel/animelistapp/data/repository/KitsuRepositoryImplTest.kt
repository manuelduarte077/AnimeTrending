package dev.donmanuel.animelistapp.data.repository

import com.skydoves.sandwich.ApiResponse
import dev.donmanuel.animelistapp.data.network.KitsuApi
import dev.donmanuel.animelistapp.data.network.dto.AnimeDataDto
import dev.donmanuel.animelistapp.data.network.dto.AnimeResponseDto
import dev.donmanuel.animelistapp.data.network.dto.AttributesDto
import dev.donmanuel.animelistapp.data.network.dto.CoverImageDto
import dev.donmanuel.animelistapp.data.network.dto.DimensionsDto
import dev.donmanuel.animelistapp.data.network.dto.LinksDto
import dev.donmanuel.animelistapp.data.network.dto.MetaDto
import dev.donmanuel.animelistapp.data.network.dto.PosterImageDto
import dev.donmanuel.animelistapp.data.network.dto.RelationDto
import dev.donmanuel.animelistapp.data.network.dto.RelationLinksDto
import dev.donmanuel.animelistapp.data.network.dto.RelationshipsDto
import dev.donmanuel.animelistapp.data.network.dto.SizeDto
import dev.donmanuel.animelistapp.data.network.dto.TitlesDto
import dev.donmanuel.animelistapp.data.network.dto.TrendingAnimeListDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class KitsuRepositoryImplTest {

    private lateinit var kitsuApi: KitsuApi
    private lateinit var repository: KitsuRepositoryImpl

    @Before
    fun setup() {
        kitsuApi = mockk()
        repository = KitsuRepositoryImpl(kitsuApi)
    }

    @Test
    fun `getTrendingAnime returns list of anime data when API call is successful`() = runTest {
        // Given
        val mockAnimeDataDto = createMockAnimeDataDto("1")
        val mockTrendingAnimeListDto = TrendingAnimeListDto(listOf(mockAnimeDataDto))
        coEvery { kitsuApi.getTrendingAnime() } returns ApiResponse.Success(mockTrendingAnimeListDto)

        // When
        val result = repository.getTrendingAnime()

        // Then
        assertEquals(1, result.size)
        assertEquals("1", result[0].id)
        assertEquals("Test Anime", result[0].attributes.canonicalTitle)
    }

    @Test
    fun `getTrendingAnime returns empty list when API call fails`() = runTest {
        // Given
        coEvery { kitsuApi.getTrendingAnime() } returns ApiResponse.Failure.Error(
            Exception("Network error")
        )

        // When
        val result = repository.getTrendingAnime()

        // Then
        assertEquals(0, result.size)
    }

    @Test
    fun `getAnime returns anime data when API call is successful`() = runTest {
        // Given
        val mockAnimeDataDto = createMockAnimeDataDto("1")
        val mockAnimeDto = AnimeResponseDto(mockAnimeDataDto)
        coEvery { kitsuApi.getAnime(1) } returns ApiResponse.Success(mockAnimeDto)

        // When
        val result = repository.getAnime(1)

        // Then
        assertEquals("1", result?.id)
        assertEquals("Test Anime", result?.attributes?.canonicalTitle)
    }

    @Test
    fun `getAnime returns null when API call fails`() = runTest {
        // Given
        coEvery { kitsuApi.getAnime(1) } returns ApiResponse.Failure.Error(
            Exception("Network error")
        )

        // When
        val result = repository.getAnime(1)

        // Then
        assertNull(result)
    }

    private fun createMockAnimeDataDto(id: String): AnimeDataDto {
        val meta = MetaDto(
            dimensions = DimensionsDto(
                tiny = SizeDto(width = 110, height = 156),
                small = SizeDto(width = 284, height = 402),
                large = SizeDto(width = 550, height = 780)
            )
        )
        
        val relationLinks = RelationLinksDto(
            self = "https://kitsu.io/api/edge/anime/$id/relationships/genres",
            related = "https://kitsu.io/api/edge/anime/$id/genres"
        )
        
        val emptyRelation = RelationDto(links = relationLinks)
        
        return AnimeDataDto(
            id = id,
            type = "anime",
            links = LinksDto("https://kitsu.io/api/edge/anime/$id"),
            attributes = AttributesDto(
                createdAt = "2023-01-01T00:00:00.000Z",
                updatedAt = "2023-01-02T00:00:00.000Z",
                slug = "test-anime",
                synopsis = "Test synopsis",
                coverImageTopOffset = 0,
                titles = TitlesDto(
                    en = "Test Anime",
                    en_jp = "Test Anime JP",
                    ja_jp = "テストアニメ"
                ),
                canonicalTitle = "Test Anime",
                abbreviatedTitles = listOf("TA"),
                averageRating = "85.5",
                ratingFrequencies = mapOf("10" to "100", "20" to "50"),
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
                tba = null,
                posterImage = PosterImageDto(
                    tiny = "tiny.jpg",
                    small = "small.jpg",
                    medium = "medium.jpg",
                    large = "large.jpg",
                    original = "original.jpg",
                    meta = meta
                ),
                coverImage = CoverImageDto(
                    tiny = "cover-tiny.jpg",
                    small = "cover-small.jpg",
                    large = "cover-large.jpg",
                    original = "cover-original.jpg",
                    meta = meta
                ),
                episodeCount = 12,
                episodeLength = 24,
                youtubeVideoId = "abc123",
                showType = "TV",
                nsfw = false
            ),
            relationships = RelationshipsDto(
                genres = emptyRelation,
                categories = emptyRelation,
                castings = emptyRelation,
                installments = emptyRelation,
                mappings = emptyRelation,
                reviews = emptyRelation,
                mediaRelationships = emptyRelation,
                episodes = emptyRelation,
                streamingLinks = emptyRelation,
                animeProductions = emptyRelation,
                animeCharacters = emptyRelation,
                animeStaff = emptyRelation
            )
        )
    }
}
