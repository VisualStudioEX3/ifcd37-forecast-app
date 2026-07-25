package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetSkyStateData(
    val value: String,
    @SerialName("periodo") val period: String,
    @SerialName("descripcion") val description: String,
)