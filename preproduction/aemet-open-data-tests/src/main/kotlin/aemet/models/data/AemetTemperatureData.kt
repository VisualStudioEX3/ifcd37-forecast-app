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
    @SerialName("dato") val data: List<AemetTemperatureByHourData>,
)

@Serializable
data class AemetTemperatureByHourData(
    /**
     * Temperature value.
     *
     * @return Temperature in celsius.
     */
    val value: Int,

    /**
     * Time of forecast temperature.
     *
     * @return Possible values for predictions by hour: 0..23.
     *
     * Possible values for predictions by days: "00-06",
     * "06-12", "12-18", "18-24", "00-12", "00-24", "12-24"
     */
    @SerialName("hora") val hour: Byte
)