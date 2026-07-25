package org.example.aemet.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetOpenDataResponse(
    @SerialName("estado") val state: Int,
    @SerialName("descripcion") val description: String,
    @SerialName("datos") val requestUrlData: String?,
    @SerialName("metadatos") val requestUrlMetadata: String?,
)