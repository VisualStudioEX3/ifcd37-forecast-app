package com.visualstudioex3.application.ports.output.weather.forecast.models

import com.visualstudioex3.application.entities.MunicipalityData

/**
 * Forecast request model.
 *
 * @param apiKey AEMET OpenData API key.
 * @param municipality Municipality.
 */
internal data class WeatherForecastRequest(
    val apiKey: String,
    val municipality: MunicipalityData
)
