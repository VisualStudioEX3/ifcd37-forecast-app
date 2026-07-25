package org.example.aemet.models.data

import kotlinx.datetime.LocalDateTime

interface IAemetCityWeatherData {
    val id: Int
    val version: Float
    val source: AemetSourceData
    val createdAt: LocalDateTime
    val city: String
    val state: String
}