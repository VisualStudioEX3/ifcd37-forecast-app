package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET relative humidity data.
 */
@Serializable
data class AemetRelativeHumidityData(
    /**
     * Max relative humidity.
     *
     * @return The percent of relative humidity.
     */
    @SerialName("maxima") val max: Int,

    /**
     * Min relative humidity.
     *
     * @return The percent of relative humidity.
     */
    @SerialName("minima") val min: Int,

    /**
     * Relative humidity levels by hour.
     */
    @SerialName("dato") val data: List<AemetRelativeHumidityDetailData>,
)

/**
 * AEMET relative humidity detail data.
 */
@Serializable
data class AemetRelativeHumidityDetailData(
    /**
     * Relative humidity expected percentage value.
     *
     * @return Percentage of relative humidity.
     */
    val value: Int,

    /**
     * Time of forecast relative humidity.
     *
     * @return Possible values for predictions by hour: 0..23.
     */
    @SerialName("hora") val hour: Int
)