package com.visualstudioex3.application

import com.visualstudioex3.application.ports.input.MunicipalityFinder
import com.visualstudioex3.application.ports.input.MunicipalityFinderImplementation
import com.visualstudioex3.application.ports.input.weather.forecast.usecases.WeatherForecastUseCase
import com.visualstudioex3.application.ports.input.weather.forecast.usecases.WeatherForecastUseCaseImplementation
import com.visualstudioex3.application.ports.output.weather.forecast.usecases.DailyWeatherForecastUseCase
import com.visualstudioex3.application.ports.output.weather.forecast.usecases.DailyWeatherForecastUseCaseImplementation
import com.visualstudioex3.application.ports.output.weather.forecast.usecases.HourlyWeatherForecastUseCase
import com.visualstudioex3.application.ports.output.weather.forecast.usecases.HourlyWeatherForecastUseCaseImplementation
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
     * [com.visualstudioex3.application.ports.input.MunicipalityFinderImplementation] service binder.
     *
     * @param impl The service implementation. It's resolved by Hilt.
     *
     * @return Returns a singleton instance of [com.visualstudioex3.application.ports.input.MunicipalityFinderImplementation] service.
     */
    @Binds
    @Singleton
    internal abstract fun bindMunicipalityFinder(
        impl: MunicipalityFinderImplementation
    ): MunicipalityFinder

    /**
     * [DailyWeatherForecastUseCase] service binder.
     *
     * @param impl The service implementation. It's resolved by Hilt.
     *
     * @return Returns an instance of [DailyWeatherForecastUseCase] service.
     */
    @Binds
    internal abstract fun bindDailyWeatherForecastUseCase(
        impl: DailyWeatherForecastUseCaseImplementation
    ) : DailyWeatherForecastUseCase

    /**
     * [HourlyWeatherForecastUseCase] service binder.
     *
     * @param impl The service implementation. It's resolved by Hilt.
     *
     * @return Returns an instance of [HourlyWeatherForecastUseCase] service.
     */
    @Binds
    internal abstract fun bindHourlyWeatherForecastUseCase(
        impl: HourlyWeatherForecastUseCaseImplementation
    ) : HourlyWeatherForecastUseCase

    /**
     * [WeatherForecastUseCase] service binder.
     *
     * @param impl The service implementation. It's resolved by Hilt.
     *
     * @return Returns an instance of [WeatherForecastUseCase] service.
     */
    @Binds
    internal abstract fun bindWeatherForecastUseCase(
        impl: WeatherForecastUseCaseImplementation
    ) : WeatherForecastUseCase
}
