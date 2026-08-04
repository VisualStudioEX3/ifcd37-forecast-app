package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET relative humidity period data.
 */
@Serializable
data class AemetRelativeHumidityPeriodData(
    /**
     * Relative humidity expected percentage value.
     *
     * @return Percentage of relative humidity.
     */
    val value: Int,

    /**
     * Period of validity for the relative humidity.
     *
     * @return Possible values: 0..23.
     */
    @SerialName("periodo")
    val period: Int
)
