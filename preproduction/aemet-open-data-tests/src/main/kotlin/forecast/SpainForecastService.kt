package org.example.forecast

import org.example.forecast.hydrators.ForecastRequestHydrator
import org.example.forecast.hydrators.ForecastRequestHydratorParameters
import org.example.forecast.hydrators.IForecastRequestHydrator
import org.example.forecast.models.ForecastRequest
import org.example.forecast.models.ForecastResponse
import org.example.forecast.usecases.ForecastUseCase
import org.example.forecast.usecases.IForecastUseCase

class SpainForecastService(
    // TODO: Use IoC with Hilt to resolve dependency
    private val hydrator: IForecastRequestHydrator = ForecastRequestHydrator(),
    private val useCase: IForecastUseCase = ForecastUseCase()
) : ISpainForecastService {
    override suspend fun getForecastByCity(
        cityCode: String
    ): ForecastResponse {
        try {
            val request: ForecastRequest = hydrator.hydrate(
                ForecastRequestHydratorParameters(
                    cityCode
                )
            )
            val response: ForecastResponse = useCase.invoke(request)

            return response
        } catch (e: Exception) {
            error("Error fetching forecast data: ${e.message}")
        }
    }
}