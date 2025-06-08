package dev.donmanuel.animelistapp.di

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.donmanuel.animelistapp.data.network.KitsuApi
import dev.donmanuel.animelistapp.data.repository.KitsuRepositoryImpl
import dev.donmanuel.animelistapp.domain.repository.KitsuRepository
import dev.donmanuel.animelistapp.ui.screen.anime.AnimeViewModel
import dev.donmanuel.animelistapp.ui.screen.trending_anime.TrendingAnimeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Definir el módulo principal de la aplicación
val appModule = module {
    // Singleton para Moshi
    single { 
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build() 
    }

    // Singleton para KitsuApi
    single { 
        Retrofit.Builder()
            .baseUrl(KitsuApi.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(KitsuApi::class.java)
    }

    // Singleton para KitsuRepository
    single<KitsuRepository> { 
        KitsuRepositoryImpl(api = get()) 
    }

    // ViewModel para TrendingAnimeScreen
    viewModel { 
        TrendingAnimeViewModel(repository = get()) 
    }

    // ViewModel para AnimeScreen
    viewModel { 
        AnimeViewModel(api = get()) 
    }
}
