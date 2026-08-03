package com.visualstudioex3.application.di

import android.content.Context
import com.visualstudioex3.application.SecretsService
import com.visualstudioex3.application.SecretsServiceImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Application service provider.
 *
 * Serve provider functions for resolve dependencies with Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppServiceProvider {
    /**
     * [SecretsService] service provider.
     *
     * @param context [ApplicationContext] instance. Is resolved by Hilt.
     *
     * @return Returns a singleton instance of [SecretsService] service.
     */
    @Provides
    @Singleton
    fun provideSecretsService(
        @ApplicationContext context: Context
    ): SecretsService = SecretsServiceImplementation(context)
}
