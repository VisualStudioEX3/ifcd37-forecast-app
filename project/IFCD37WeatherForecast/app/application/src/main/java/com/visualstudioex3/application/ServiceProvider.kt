package com.visualstudioex3.application

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Service provider.
 *
 * Serve provider functions for resolve dependencies with Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceProvider {
    /**
     * [SecretsService] service provider.
     *
     * @param context [Context] instance. It's resolved by Hilt.
     *
     * @return Returns a singleton instance of [SecretsService] service.
     */
    @Provides
    @Singleton
    fun provideSecretsService(
        @ApplicationContext context: Context
    ): SecretsService = SecretsServiceImplementation(context)
}
