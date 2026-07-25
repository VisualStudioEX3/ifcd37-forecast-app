package org.example.aemet.models.responses

import kotlinx.datetime.LocalDateTime
import org.example.aemet.models.data.AemetSourceData

interface IAemetCityWeatherPredictionResponse {
    val id: Int
    val version: Float
    val source: AemetSourceData
    val createdAt: LocalDateTime
    val city: String
    val state: String
}