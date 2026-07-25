package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetTemperatureData(
    @SerialName("maxima") val max: Int,
    @SerialName("minima") val min: Int,
    @SerialName("dato") val data: List<AemetTemperatureDetailData>,
)

@Serializable
data class AemetTemperatureDetailData(
    val value: Int,
    @SerialName("hora") val hour: Int
)