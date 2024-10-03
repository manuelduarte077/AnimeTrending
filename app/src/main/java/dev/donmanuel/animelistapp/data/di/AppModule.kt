package dev.donmanuel.animelistapp.data.di

import dev.donmanuel.animelistapp.data.network.KitsuApi
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.donmanuel.animelistapp.data.repository.KitsuRepositoryImpl
import dev.donmanuel.animelistapp.domain.repository.KitsuRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	@Singleton
	fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

	@Singleton
	@Provides
	fun provideKitsuApi(moshi: Moshi): KitsuApi = Retrofit.Builder().baseUrl(KitsuApi.baseUrl)
		.addConverterFactory(MoshiConverterFactory.create(moshi))
		.addCallAdapterFactory(ApiResponseCallAdapterFactory.create()).build()
		.create(KitsuApi::class.java)

	@Singleton
	@Provides
	fun provideKitsuRepository(api: KitsuApi): KitsuRepository = KitsuRepositoryImpl(api = api)
}