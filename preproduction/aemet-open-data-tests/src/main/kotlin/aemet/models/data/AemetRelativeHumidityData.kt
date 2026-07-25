package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetRelativeHumidityData(
    @SerialName("maxima") val max: Int,
    @SerialName("minima") val min: Int,
    @SerialName("dato") val data: List<AemetRelativeHumidityDetailData>,
)

@Serializable
data class AemetRelativeHumidityDetailData(
    val value: Int,
    @SerialName("hora") val hour: Int
)