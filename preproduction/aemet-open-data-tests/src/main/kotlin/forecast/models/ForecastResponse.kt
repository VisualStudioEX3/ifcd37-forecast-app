package org.example.forecast.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Forecast response object.
 */
@Serializable
data class ForecastResponse(
    /**
     * City name.
     */
    val city: String,

    /**
     * Data creation date and time.
     *
     * Shows when was created the forecast data in remote service.
     */
    val dataCreationDateTime: LocalDateTime,

    /**
     * Daily forecast data.
     */
    val daily: DailyForecastResponse,

    /**
     * Hourly forecast data.
     */
    val hourly: List<String>,
)