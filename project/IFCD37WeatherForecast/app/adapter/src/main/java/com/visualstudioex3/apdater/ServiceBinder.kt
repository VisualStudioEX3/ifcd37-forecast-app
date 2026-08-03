package com.visualstudioex3.apdater

import com.visualstudioex3.apdater.inedb.repositories.IneAutonomousCommunityRepository
import com.visualstudioex3.apdater.inedb.repositories.IneAutonomousCommunityRepositoryImplementation
import com.visualstudioex3.apdater.inedb.repositories.IneMunicipalityRepository
import com.visualstudioex3.apdater.inedb.repositories.IneMunicipalityRepositoryImplementation
import com.visualstudioex3.apdater.inedb.repositories.IneProvinceRepository
import com.visualstudioex3.apdater.inedb.repositories.IneProvinceRepositoryImplementation
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
     * [IneMunicipalityRepository] service binder.
     *
     * @param impl The service implementation. It's resolved by Hilt.
     *
     * @return Returns a singleton instance of [IneMunicipalityRepository] service.
     */
    @Binds
    @Singleton
    internal abstract fun bindIneMunicipalityRepository(
        impl: IneMunicipalityRepositoryImplementation
    ): IneMunicipalityRepository

    /**
     * [IneProvinceRepository] service binder.
     *
     * @param impl The service implementation. It's resolved by Hilt.
     *
     * @return Returns a singleton instance of [IneProvinceRepository] service.
     */
    @Binds
    @Singleton
    internal abstract fun bindIneProvinceRepository(
        impl: IneProvinceRepositoryImplementation
    ): IneProvinceRepository

    /**
     * [IneAutonomousCommunityRepository] service binder.
     *
     * @param impl The service implementation. It's resolved by Hilt.
     *
     * @return Returns a singleton instance of [IneAutonomousCommunityRepository] service.
     */
    @Binds
    @Singleton
    internal abstract fun bindIneAutonomousCommunityRepository(
        impl: IneAutonomousCommunityRepositoryImplementation
    ): IneAutonomousCommunityRepository
}
