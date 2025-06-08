package dev.donmanuel.animelistapp.data.network.dto

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class AnimeDataDtoTest {

    @Test
    fun `toModel converts AnimeDataDto to AnimeData correctly`() {
        // Given
        val meta = MetaDto(
            dimensions = DimensionsDto(
                tiny = SizeDto(width = 110, height = 156),
                small = SizeDto(width = 284, height = 402),
                large = SizeDto(width = 550, height = 780)
            )
        )
        
        val relationLinks = RelationLinksDto(
            self = "https://kitsu.io/api/edge/anime/1/relationships/genres",
            related = "https://kitsu.io/api/edge/anime/1/genres"
        )
        
        val emptyRelation = RelationDto(links = relationLinks)
        
        val animeDataDto = AnimeDataDto(
            id = "1",
            type = "anime",
            links = LinksDto("https://kitsu.io/api/edge/anime/1"),
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

        // When
        val animeData = animeDataDto.toModel()

        // Then
        assertEquals("1", animeData.id)
        assertEquals("Test Anime", animeData.attributes.canonicalTitle)
        assertEquals("Test synopsis", animeData.attributes.synopsis)
        assertEquals("test-anime", animeData.attributes.slug)
        assertEquals("tiny.jpg", animeData.attributes.posterImage.tiny)
        assertEquals("small.jpg", animeData.attributes.posterImage.small)
        assertEquals("medium.jpg", animeData.attributes.posterImage.medium)
        assertEquals("large.jpg", animeData.attributes.posterImage.large)
        assertEquals("original.jpg", animeData.attributes.posterImage.original)
        
        assertNotNull(animeData.attributes.coverImage)
        assertEquals("cover-tiny.jpg", animeData.attributes.coverImage.tiny)
        assertEquals("cover-small.jpg", animeData.attributes.coverImage.small)
        assertEquals("cover-large.jpg", animeData.attributes.coverImage.large)
        assertEquals("cover-original.jpg", animeData.attributes.coverImage.original)
        
        assertEquals(12, animeData.attributes.episodeCount)
        assertEquals(24, animeData.attributes.episodeLength)
        assertEquals("TV", animeData.attributes.showType)
        assertEquals("2023-01-01", animeData.attributes.startDate)
        assertEquals("2023-03-31", animeData.attributes.endDate)
        assertEquals("finished", animeData.attributes.status)
        assertEquals("Teens 13 or older", animeData.attributes.ageRatingGuide)
        assertEquals("85.5", animeData.attributes.averageRating)
        assertEquals("Test Anime", animeData.attributes.titles.en)
        assertEquals(1, animeData.attributes.abbreviatedTitles.size)
        assertEquals("TA", animeData.attributes.abbreviatedTitles[0])
    }

    @Test
    fun `toModel handles null values correctly`() {
        // Given
        val meta = MetaDto(
            dimensions = DimensionsDto(
                tiny = SizeDto(width = 110, height = 156),
                small = SizeDto(width = 284, height = 402),
                large = SizeDto(width = 550, height = 780)
            )
        )
        
        val relationLinks = RelationLinksDto(
            self = "https://kitsu.io/api/edge/anime/1/relationships/genres",
            related = "https://kitsu.io/api/edge/anime/1/genres"
        )
        
        val emptyRelation = RelationDto(links = relationLinks)
        
        val animeDataDto = AnimeDataDto(
            id = "1",
            type = "anime",
            links = LinksDto("https://kitsu.io/api/edge/anime/1"),
            attributes = AttributesDto(
                createdAt = "2023-01-01T00:00:00.000Z",
                updatedAt = "2023-01-02T00:00:00.000Z",
                slug = null,
                synopsis = null,
                coverImageTopOffset = 0,
                titles = TitlesDto(
                    en = null,
                    en_jp = null,
                    ja_jp = null
                ),
                canonicalTitle = "Test Anime",
                abbreviatedTitles = emptyList(),
                averageRating = null,
                ratingFrequencies = emptyMap(),
                userCount = null,
                favoritesCount = null,
                startDate = null,
                endDate = null,
                popularityRank = null,
                ratingRank = null,
                ageRating = null,
                ageRatingGuide = null,
                subtype = "unknown",
                status = "unknown",
                tba = null,
                posterImage = PosterImageDto(
                    tiny = "",
                    small = "",
                    medium = "",
                    large = "",
                    original = "",
                    meta = null
                ),
                coverImage = CoverImageDto(
                    tiny = "",
                    small = "",
                    large = "",
                    original = "",
                    meta = null
                ),
                episodeCount = null,
                episodeLength = null,
                youtubeVideoId = null,
                showType = null,
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

        // When
        val animeData = animeDataDto.toModel()

        // Then
        assertEquals("1", animeData.id)
        assertEquals("Test Anime", animeData.attributes.canonicalTitle)
        assertEquals(null, animeData.attributes.synopsis)
        assertEquals(null, animeData.attributes.slug)
        assertEquals("", animeData.attributes.posterImage.tiny)
        assertEquals(null, animeData.attributes.episodeCount)
        assertEquals(null, animeData.attributes.episodeLength)
        assertEquals(null, animeData.attributes.showType)
        assertEquals(null, animeData.attributes.startDate)
        assertEquals(null, animeData.attributes.endDate)
        assertEquals("unknown", animeData.attributes.status)
        assertEquals(null, animeData.attributes.ageRating)
        assertEquals(null, animeData.attributes.ageRatingGuide)
        assertEquals(null, animeData.attributes.averageRating)
        assertEquals(null, animeData.attributes.titles.en)
        assertEquals(0, animeData.attributes.abbreviatedTitles.size)
    }
}
