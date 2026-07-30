package org.example.forecast.usecases

import kotlinx.coroutines.delay
import org.example.forecast.exceptions.TooManyRequestsException
import org.example.forecast.models.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Forecast use case.
 *
 * This use case requests the daily and hourly forecast data.
 */
class ForecastUseCase(
    // TODO: Use IoC with Hilt to resolve dependency
    private val locationFinder: String = "", // TODO: Implement location service based on INE database.
    private val dailyForecastUseCase: DailyForecastUseCase = DailyForecastUseCase(),
    private val hourlyForecastUseCase: HourlyForecastUseCase = HourlyForecastUseCase()
) : IForecastUseCase {
    // TODO: Resolve from settings
    private val maxRetries: Int = 3
    private val waitUntilNextRetry: Duration = 3000.milliseconds

    override suspend fun invoke(
        request: ForecastRequest
    ) = ForecastResponse(
        ForecastLocationData(
            "",
            ""
        ),
        tryRequestDailyForecast(request),
        tryRequestHourlyForecast(request)
    )

    /*
     * FYI: AEMET OpenData API should return HTTP status code 429 on first calls without a real reason (exceed the
     * request quota). In a way to solve this issue we implemented a system to try the request n times, with a wait,
     * until throw the TooManyRequestsException as real exception.
     */

    private suspend fun tryRequestDailyForecast(
        request: ForecastRequest
    ): List<DailyForecastData> {
        var times = 0

        while (true) {
            try {
                return dailyForecastUseCase.invoke(request)
            } catch (e: TooManyRequestsException) {
                if (times++ == maxRetries) {
                    throw e
                } else {
                    println("Retry #$times daily forecast request in $waitUntilNextRetry milliseconds...")
                    delay(waitUntilNextRetry)
                }
            }
        }
    }

    private suspend fun tryRequestHourlyForecast(
        request: ForecastRequest
    ): List<HourlyForecastData> {
        var times = 0

        while (true) {
            try {
                return hourlyForecastUseCase.invoke(request)
            } catch (e: TooManyRequestsException) {
                if (times++ == maxRetries) {
                    throw e
                } else {
                    println("Retry #$times hourly forecast request in $waitUntilNextRetry milliseconds...")
                    delay(waitUntilNextRetry)
                }
            }
        }
    }
}