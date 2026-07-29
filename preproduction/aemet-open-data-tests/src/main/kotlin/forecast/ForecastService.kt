package org.example.forecast

import org.example.Secrets
import org.example.forecast.models.ForecastRequest
import org.example.forecast.models.ForecastResponse
import org.example.forecast.usecases.ForecastUseCase

object ForecastService {
    // TODO: Use IoC with Hilt to resolve dependency
    private val forecastUseCase = ForecastUseCase()

    suspend fun getForecastByCity(
        cityCode: String
    ): ForecastResponse {
        try {
            val apiKey: String = Secrets.getSecret("aemet_opendata_api_key")
            val request = ForecastRequest(apiKey, cityCode)

            return forecastUseCase.invoke(request)
        } catch (e: Exception) {
            TODO("Implement error handling.")
        }
    }
}