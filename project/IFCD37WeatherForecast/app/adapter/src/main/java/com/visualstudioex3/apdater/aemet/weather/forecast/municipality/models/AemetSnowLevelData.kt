package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET snow prediction data.
 */
@Serializable
data class AemetSnowLevelData(
    /**
     * Snow level value.
     *
     * @return Snow level in meters. Empty string means not data for this period.
     */
    val value: String,

    /**
     * Period of validity for the snow level.
     *
     * @return Possible values for predictions by hour: 0..23.
     *
     * Possible values for predictions by days: "00-06",
     * "06-12", "12-18", "18-24", "00-12", "00-24", "12-24"
     */
    @SerialName("periodo")
    val period: String? = null
)
