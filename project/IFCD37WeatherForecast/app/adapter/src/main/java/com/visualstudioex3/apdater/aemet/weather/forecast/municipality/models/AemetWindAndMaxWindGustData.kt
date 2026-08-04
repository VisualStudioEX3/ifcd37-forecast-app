package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET wind and max wind gust data.
 *
 * This value is received as a pair of two different objects in the same list as follow:
 *
 * ```
 *     {
 *         "direccion": [
 *             "NO"
 *         ],
 *         "velocidad": [
 *             "22"
 *         ],
 *         "periodo": "21"
 *     },
 *     {
 *         "value": "33",
 *         "periodo": "21"
 *     }
 * ```
 *
 * To be serialized as a type we used a data class with all fields as optional (except for 'period' that always is
 * received) to fit the two combinations. 'period' field is used later as key to link the pair combinations.
 */
@Serializable
data class AemetWindAndMaxWindGustData(
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
    @SerialName("direccion")
    val direction: List<String>? = null,

    /**
     * Wind speed.
     *
     * @return Wind velocity in kilometers/hour.
     */
    @SerialName("velocidad")
    val speed: List<Int>? = null,

    /**
     * Time of forecast wind velocity.
     *
     * @return Possible values for predictions by hour: 0..23.
     */
    @SerialName("periodo")
    val period: Int,

    /**
     * Max wind gust value.
     *
     * @return Wind gust in kilometers/hour.
     */
    val value: Int? = null,
)
