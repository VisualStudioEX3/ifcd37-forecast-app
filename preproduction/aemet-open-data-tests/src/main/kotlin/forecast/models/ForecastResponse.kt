package org.example.forecast.models

import kotlinx.serialization.Serializable

/**
 * Forecast response object.
 */
@Serializable
data class ForecastResponse(
    /**
     * Forecast location.
     */
    val location: ForecastLocationData,

    /**
     * Daily forecast data.
     */
    val daily: List<DailyForecastData>,

    /**
     * Hourly forecast data.
     */
    val hourly: List<HourlyForecastData>,
)