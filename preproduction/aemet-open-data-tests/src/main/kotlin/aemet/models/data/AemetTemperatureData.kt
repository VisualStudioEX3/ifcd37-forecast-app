package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET temperature data.
 */
@Serializable
data class AemetTemperatureData(
    /**
     * Max temperature value.
     *
     * @return Max temperature in celsius.
     */
    @SerialName("maxima") val max: Int,

    /**
     * Min temperature value.
     *
     * @return Min temperature in celsius.
     */
    @SerialName("minima") val min: Int,

    /**
     * List of temperature details by hour.
     */
    @SerialName("dato") val data: List<AemetTemperatureDeatilData>,
)

/**
 * AEMET temperature detail data.
 */
@Serializable
data class AemetTemperatureDeatilData(
    /**
     * Temperature value.
     *
     * @return Temperature in celsius.
     */
    val value: Int,

    /**
     * Time of forecast temperature.
     *
     * @return Possible values: 0..23.
     */
    @SerialName("hora") val hour: Int
)