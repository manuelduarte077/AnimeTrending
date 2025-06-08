package dev.donmanuel.animelistapp.di

import dev.donmanuel.animelistapp.data.network.KitsuApi
import dev.donmanuel.animelistapp.data.repository.KitsuRepositoryImpl
import dev.donmanuel.animelistapp.domain.repository.KitsuRepository
import dev.donmanuel.animelistapp.ui.screen.anime.AnimeViewModel
import dev.donmanuel.animelistapp.ui.screen.trending_anime.TrendingAnimeViewModel
import dev.donmanuel.animelistapp.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class KoinModuleTest : KoinTest {

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
                // Usar un módulo de prueba que incluye el módulo de la app
                module {
                    // Incluir el módulo original
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
    fun `verify koin module`() {
        // No necesitamos llamar a checkModules aquí ya que estamos verificando
        // las instancias directamente en los otros métodos de prueba
        // y el módulo ya está iniciado en el método setup()
        assertNotNull(get<KitsuRepository>())
    }

    @Test
    fun `check moshi instance is provided`() {
        val moshi = get<com.squareup.moshi.Moshi>()
        assertNotNull(moshi)
    }

    @Test
    fun `check kitsuApi instance is provided`() {
        val kitsuApi = get<KitsuApi>()
        assertNotNull(kitsuApi)
    }

    @Test
    fun `check kitsuRepository instance is provided`() {
        val kitsuRepository = get<KitsuRepository>()
        assertNotNull(kitsuRepository)
        assertTrue(kitsuRepository is KitsuRepositoryImpl)
    }

    @Test
    fun `check trendingAnimeViewModel factory is provided`() {
        val viewModel = get<TrendingAnimeViewModel>()
        assertNotNull(viewModel)
    }

    @Test
    fun `check animeViewModel factory is provided`() {
        val viewModel = get<AnimeViewModel>()
        assertNotNull(viewModel)
    }
}
