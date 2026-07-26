package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetSnowLevelData(
    val value: Int,
    @SerialName("periodo") val period: String
)