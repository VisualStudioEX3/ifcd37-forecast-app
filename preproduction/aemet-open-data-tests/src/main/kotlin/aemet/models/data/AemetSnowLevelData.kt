package org.example.aemet.models.data

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
     * @return Snow level in meters.
     */
    val value: Int,

    /**
     * Period of validity for the snow level.
     *
     * @return Possible values for predictions by hour: 0..23.
     *
     * Possible values for predictions by days: "00-06",
     * "06-12", "12-18", "18-24", "00-12", "00-24", "12-24"
     */
    @SerialName("periodo") val period: String
)