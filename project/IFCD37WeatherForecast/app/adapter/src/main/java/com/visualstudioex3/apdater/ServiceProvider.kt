package com.visualstudioex3.apdater

import android.content.Context
import com.visualstudioex3.apdater.inedb.DataFrameExcelImporter
import com.visualstudioex3.apdater.inedb.DataFrameExcelImporterImplementation
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
     * [DataFrameExcelImporter] service provider.
     *
     * @param context [Context] instance. It's resolved by Hilt.
     *
     * @return Returns a singleton instance of [DataFrameExcelImporter] service.
     */
    @Provides
    @Singleton
    fun provideDataFrameExcelImporter(
        @ApplicationContext context: Context
    ): DataFrameExcelImporter = DataFrameExcelImporterImplementation(context)
}
