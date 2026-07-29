package org.example.forecast.usecases

import org.example.forecast.models.*

/**
 * Forecast use case.
 *
 * This use case requests the daily and hourly forecast data,
 */
class ForecastUseCase(
    // TODO: Use IoC with Hilt to resolve dependency
    private val locationFinder: String, // TODO: Implement location service based on INE database.
    private val dailyForecastUseCase: DailyForecastUseCase = DailyForecastUseCase(),
    private val hourlyForecastUseCase: HourlyForecastUseCase = HourlyForecastUseCase()
) : IForecastUseCase {
    override suspend fun invoke(
        request: ForecastRequest
    ): ForecastResponse {
        val location = ForecastLocationData(
            "",
            ""
        )
        val dailyForecast: List<DailyForecastData> = dailyForecastUseCase.invoke(request)
        val hourlyForecast: List<HourlyForecastData> = hourlyForecastUseCase.invoke(request)

        return ForecastResponse(
            location,
            dailyForecast,
            hourlyForecast
        )
    }
}