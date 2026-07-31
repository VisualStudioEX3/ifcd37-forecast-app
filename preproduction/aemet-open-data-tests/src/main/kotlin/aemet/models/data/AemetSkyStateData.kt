package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET sky state data.
 */
@Serializable
data class AemetSkyStateData(
    /**
     * AEMET sky state code.
     *
     * @return Not found documented values on AEMET OpenData sources.
     */
    val value: String,

    /**
     * Period of validity for the sky state.
     *
     * @return Possible values for predictions by hour: 0..23.
     *
     * Possible values for predictions by days: "00-06",
     * "06-12", "12-18", "18-24", "00-12", "00-24", "12-24"
     */
    @SerialName("periodo") val period: String = "",

    /**
     * Sky state description.
     *
     * @return Short description string.
     */
    @SerialName("descripcion") val description: String,
)