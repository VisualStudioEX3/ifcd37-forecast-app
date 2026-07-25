package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetWindData(
    @SerialName("direccion") val direction: String,
    @SerialName("velocidad") val velocity: Int,
    @SerialName("periodo") val period: String
)