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
    private val dailyForecastUseCase: IDailyForecastUseCase = DailyForecastUseCase(),
    private val hourlyForecastUseCase: IHourlyForecastUseCase = HourlyForecastUseCase()
) : IForecastUseCase {
    // TODO: Resolve from settings
    private val maxRetries: Int = 5
    private val waitUntilNextRetry: Duration = 5000.milliseconds

    override suspend fun invoke(
        request: ForecastRequest
    ) = ForecastResponse( // TODO: Resolve this with location finder service
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
                    println("($times/$maxRetries) Daily forecast request failed by HTTP Status 429. Retry in $waitUntilNextRetry...")
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
                    println("($times/$maxRetries) Hourly forecast request failed by HTTP Status 429. Retry in $waitUntilNextRetry...")
                    delay(waitUntilNextRetry)
                }
            }
        }
    }
}