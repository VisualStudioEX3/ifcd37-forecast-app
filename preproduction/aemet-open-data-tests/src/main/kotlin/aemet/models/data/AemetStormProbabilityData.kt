package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetStormProbabilityData(
    val value: Int,
    @SerialName("periodo") val period: String
)