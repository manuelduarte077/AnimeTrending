package dev.donmanuel.animelistapp.di

import dev.donmanuel.animelistapp.data.network.KitsuApi
import dev.donmanuel.animelistapp.domain.repository.KitsuRepository
import dev.donmanuel.animelistapp.ui.screen.anime.AnimeViewModel
import dev.donmanuel.animelistapp.ui.screen.trending_anime.TrendingAnimeViewModel
import dev.donmanuel.animelistapp.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class KoinAppTest : KoinTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    fun setup() {
        stopKoin() // Asegurarse de que no hay una instancia de Koin activa
        startKoin {
            modules(
                // Módulo de prueba que reemplaza algunas dependencias con mocks
                module {
                    // Mantener el módulo original
                    includes(appModule)
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `verify all dependencies are correctly injected`() {
        // Mock KitsuApi para evitar llamadas reales a la red
        declareMock<KitsuApi>()

        // Verificar que todas las dependencias principales se pueden resolver
        val repository = get<KitsuRepository>()
        assertNotNull("Repository should be injected", repository)

        val trendingViewModel = get<TrendingAnimeViewModel>()
        assertNotNull("TrendingAnimeViewModel should be injected", trendingViewModel)

        val animeViewModel = get<AnimeViewModel>()
        assertNotNull("AnimeViewModel should be injected", animeViewModel)
    }
}
