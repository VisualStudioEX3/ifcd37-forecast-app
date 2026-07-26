package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET wind data.
 */
@Serializable
data class AemetWindData(
    /**
     * Wind direction.
     *
     * @return Possible values:
     * - "N/Norte"
     * - "NE/Nordeste"
     * - "E/Este"
     * - "SE/Sudeste"
     * - "S/Sur"
     * - "SO/Suroeste"
     * - "O/Oeste"
     * - "NO/Noroeste"
     * - "C/Calma"
     */
    @SerialName("direccion") val direction: String,

    /**
     * Wind velocity.
     *
     * @return Wind velocity in kilometers/hour.
     */
    @SerialName("velocidad") val velocity: Int,

    /**
     * Time of forecast wind velocity.
     *
     * @return Possible values for predictions by hour: 0..23.
     *
     * Possible values for predictions by days: "00-06",
     * "06-12", "12-18", "18-24", "00-12", "00-24", "12-24"
     */
    @SerialName("periodo") val period: String
)