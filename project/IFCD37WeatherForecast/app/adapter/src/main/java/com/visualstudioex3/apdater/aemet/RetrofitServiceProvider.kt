package com.visualstudioex3.apdater.aemet

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

/**
 * Service provider for Retrofit.
 *
 * Serve provider functions for resolve Retrofit dependencies with Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitServiceProvider {
    /**
     * [Retrofit] service provider.
     *
     * @return Returns a singleton instance of [Retrofit] service.
     */
    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://opendata.aemet.es/opendata/")
        .build()
}
