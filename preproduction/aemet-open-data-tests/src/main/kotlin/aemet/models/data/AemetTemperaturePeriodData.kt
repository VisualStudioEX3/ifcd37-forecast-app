package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET temperature period data.
 */
@Serializable
data class AemetTemperaturePeriodData(
    /**
     * Temperature value.
     *
     * @return Temperature in celsius.
     */
    val value: Int,

    /**
     * Period of validity for the temperature.
     *
     * @return Possible values: 0..23.
     */
    @SerialName("periodo") val period: Int
)