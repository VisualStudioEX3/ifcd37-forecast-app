package org.example.forecast.usecases

import org.example.forecast.models.DailyForecastResponse
import org.example.forecast.models.ForecastRequest
import org.example.forecast.models.ForecastResponse

class ForecastUseCase(
    // TODO: Use IoC with Hilt to resolve dependency
    private val dailyForecastUseCase: DailyForecastUseCase = DailyForecastUseCase(),
    private val hourlyForecastUseCase: String = ""
) : IForecastUseCase {
    override suspend fun invoke(
        request: ForecastRequest
    ): ForecastResponse {
        val dailyForecast: DailyForecastResponse = dailyForecastUseCase.invoke(request)
        val hourlyForecast = emptyList<String>()

        return ForecastResponse(
            dataCreationDateTime = dailyForecast.dateTime,
            city = dailyForecast.cityName,
            daily = dailyForecast,
            hourly = hourlyForecast
        )
    }
}