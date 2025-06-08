package dev.donmanuel.animelistapp

import android.app.Application
import dev.donmanuel.animelistapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AnimeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Inicializar Koin
        startKoin {
            // Usar androidLogger para depuración
            androidLogger()
            // Proporcionar el contexto de Android
            androidContext(this@AnimeApp)
            // Cargar los módulos de Koin
            modules(appModule)
        }
    }
}