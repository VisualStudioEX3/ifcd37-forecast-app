package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET storm probability data.
 */
@Serializable
data class AemetStormProbabilityData(
    /**
     * Storm probability percentage value.
     *
     * @return Storm probability percentage.
     */
    val value: Int,

    /**
     * Period of validity for the storm probability.
     *
     * @return Possible values for predictions by hour: 0..23.
     *
     * Possible values for predictions by days: "00-06",
     * "06-12", "12-18", "18-24", "00-12", "00-24", "12-24"
     */
    @SerialName("periodo") val period: String
)