package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET rain probability data.
 */
@Serializable
data class AemetRainData(
    /**
     * Rain probability expected percentage value.
     *
     * @return Percentage of rain probability.
     */
    val value: Int,

    /**
     * Period of validity for the rain probability.
     *
     * @return Possible values for predictions by hour: 0..23.
     *
     * Possible values for predictions by days: "00-06",
     * "06-12", "12-18", "18-24", "00-12", "00-24", "12-24"
     */
    @SerialName("periodo")
    val period: String = ""
)
