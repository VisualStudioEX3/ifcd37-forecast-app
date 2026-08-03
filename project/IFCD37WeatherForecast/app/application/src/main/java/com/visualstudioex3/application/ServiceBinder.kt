package com.visualstudioex3.application

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Service binder.
 *
 * Serve bind functions for resolve dependencies with Hilt using inversion of control.
 */
@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceBinder {
    /**
     * [MunicipalityFinderImplementation] service binder.
     *
     * @param impl The service implementation. It's resolved by Hilt.
     *
     * @return Returns a singleton instance of [MunicipalityFinderImplementation] service.
     */
    @Binds
    @Singleton
    internal abstract fun bindMunicipalityFinder(
        impl: MunicipalityFinderImplementation
    ): MunicipalityFinder
}
