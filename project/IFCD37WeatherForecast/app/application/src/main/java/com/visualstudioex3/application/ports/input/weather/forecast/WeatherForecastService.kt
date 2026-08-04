package com.visualstudioex3.application.ports.input.weather.forecast

import com.visualstudioex3.application.entities.Municipality
import com.visualstudioex3.application.entities.WeatherForecast

/**
 * Weather forecast service.
 */
interface WeatherForecastService {
    /**
     * Gets the weather forecast for the given municipality.
     *
     * @param municipality The location to request the weather forecast.
     *
     * @return Returns a [WeatherForecast] object.
     */
    suspend fun getWeatherForecast(
        municipality: Municipality
    ): WeatherForecast
}
