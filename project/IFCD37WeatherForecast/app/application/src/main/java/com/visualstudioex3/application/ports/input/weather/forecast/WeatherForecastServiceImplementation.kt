package com.visualstudioex3.application.ports.input.weather.forecast

import com.visualstudioex3.application.entities.Municipality
import com.visualstudioex3.application.entities.WeatherForecast
import com.visualstudioex3.application.exceptions.weather.forecast.WeatherForecastException
import com.visualstudioex3.application.ports.output.weather.forecast.models.WeatherForecastRequest
import com.visualstudioex3.application.ports.output.weather.forecast.usecases.WeatherForecastUseCase
import com.visualstudioex3.application.ports.output.SecretsService
import javax.inject.Inject

internal class WeatherForecastServiceImplementation @Inject constructor(
    val secrets: SecretsService,
    val useCase: WeatherForecastUseCase
) : WeatherForecastService {
    override suspend fun getWeatherForecast(
        municipality: Municipality
    ): WeatherForecast = try {
            useCase.invoke(WeatherForecastRequest(
                    apiKey = secrets.getString("aemet_opendata_api_key")
                        ?: error("AEMET API key not found!"),
                    municipality
                )
            )
        } catch (e: Exception) {
            throw WeatherForecastException(e)
        }
}
