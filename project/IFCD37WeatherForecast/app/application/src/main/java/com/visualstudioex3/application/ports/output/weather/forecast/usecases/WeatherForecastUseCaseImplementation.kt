package com.visualstudioex3.application.ports.output.weather.forecast.usecases

import com.visualstudioex3.apdater.network.exceptions.TooManyRequestsHttpRequestException
import com.visualstudioex3.application.entities.WeatherForecast
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import com.visualstudioex3.application.ports.input.weather.forecast.models.HourlyWeatherForecastData
import com.visualstudioex3.application.ports.output.weather.forecast.models.WeatherForecastRequest
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

internal class WeatherForecastUseCaseImplementation @Inject constructor(
    val dailyUseCase: DailyWeatherForecastUseCase,
    val hourlyUseCase: HourlyWeatherForecastUseCase
) : WeatherForecastUseCase {
    // TODO: Resolve from settings
    private val maxRetries: Int = 5
    private val waitUntilNextRetry: Duration = 5000.milliseconds

    override suspend fun invoke(
        request: WeatherForecastRequest
    ) = WeatherForecast(
        request.municipality,
        daily = tryRequestDailyForecast(request),
        hourly = tryRequestHourlyForecast(request)
    )

    /*
     * FYI: AEMET OpenData API should return HTTP status code 429 on first calls without a real reason (exceed the
     * request quota). In a way to solve this issue we implemented a system to try the request n times, with a wait,
     * until throw the TooManyRequestsException as real exception.
     */

    private suspend fun tryRequestDailyForecast(
        request: WeatherForecastRequest
    ): List<DailyWeatherForecastData> {
        var times = 0

        while (true) {
            try {
                return dailyUseCase.invoke(request)
            } catch (e: TooManyRequestsHttpRequestException) {
                if (times++ == maxRetries) {
                    throw e
                } else {
                    // TODO: Implemente future Logger service.
                    println("($times/$maxRetries) Daily weather forecast request failed by HTTP Status 429. Retry in $waitUntilNextRetry...")
                    delay(waitUntilNextRetry)
                }
            }
        }
    }

    private suspend fun tryRequestHourlyForecast(
        request: WeatherForecastRequest
    ): List<HourlyWeatherForecastData> {
        var times = 0

        while (true) {
            try {
                return hourlyUseCase.invoke(request)
            } catch (e: TooManyRequestsHttpRequestException) {
                if (times++ == maxRetries) {
                    throw e
                } else {
                    // TODO: Implemente future Logger service.
                    println("($times/$maxRetries) Hourly weather forecast request failed by HTTP Status 429. Retry in $waitUntilNextRetry...")
                    delay(waitUntilNextRetry)
                }
            }
        }
    }
}
