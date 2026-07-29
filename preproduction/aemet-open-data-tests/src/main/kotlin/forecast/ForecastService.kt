package org.example.forecast

import org.example.forecast.hydrators.ForecastRequestHydrator
import org.example.forecast.hydrators.ForecastRequestHydratorParameters
import org.example.forecast.models.ForecastRequest
import org.example.forecast.models.ForecastResponse
import org.example.forecast.usecases.ForecastUseCase

object ForecastService {
    // TODO: Use IoC with Hilt to resolve dependency
    private val hydrator = ForecastRequestHydrator()
    private val useCase = ForecastUseCase()

    suspend fun getForecastByCity(
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
            TODO("Implement error handling.")
        }
    }
}