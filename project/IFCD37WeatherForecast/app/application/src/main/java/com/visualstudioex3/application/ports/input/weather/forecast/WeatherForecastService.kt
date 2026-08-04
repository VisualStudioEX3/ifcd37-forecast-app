package com.visualstudioex3.application.ports.input.weather.forecast

import com.visualstudioex3.application.entities.MunicipalityData
import com.visualstudioex3.application.entities.WeatherForecastData

/**
 * Weather forecast service.
 */
interface WeatherForecastService {
    /**
     * Gets the weather forecast for the given municipality.
     *
     * @param municipality The location to request the weather forecast.
     *
     * @return Returns a [WeatherForecastData] object.
     */
    suspend fun getWeatherForecast(
        municipality: MunicipalityData
    ): WeatherForecastData
}
