package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetSourceData(
    @SerialName("productor") val producer: String,
    val web: String,
    @SerialName("enlace") val link: String,
    val language: String,
    val copyright: String,
    @SerialName("notaLegal") val legalNotes: String,
)