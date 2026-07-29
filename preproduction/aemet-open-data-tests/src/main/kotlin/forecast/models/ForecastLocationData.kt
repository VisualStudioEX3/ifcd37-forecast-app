package org.example.forecast.models

import kotlinx.serialization.Serializable

/**
 * Forecast location.
 */
@Serializable
data class ForecastLocationData(
    /**
     * City name.
     */
    val city: String,

    /**
     * State name.
     */
    val state: String,
)
