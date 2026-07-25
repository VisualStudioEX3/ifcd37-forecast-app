package org.example.aemet.models.data

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetHourlyCityWeatherData(
    override val id: Int,
    override val version: Float,
    @SerialName("origen") override val source: AemetSourceData,
    @SerialName("elaborado") override val createdAt: LocalDateTime,
    @SerialName("nombre") override val city: String,
    @SerialName("provincia") override val state: String,
    @SerialName("prediccion") val data: AemetHourlyWeatherData,
) : IAemetCityWeatherData

@Serializable
data class AemetHourlyWeatherData(
    @SerialName("dia") val days: List<AemetHourlyWeatherDayData>
)

@Serializable
data class AemetHourlyWeatherDayData(
    @SerialName("estadoCielo") val sky: List<AemetSkyStateData>,
    @SerialName("precipitacion") val rain: List<AemetRainData>,
    @SerialName("probPrecipitacion") val rainProbability: List<AemetRainData>,
    @SerialName("probTormenta") val stormProbability: List<AemetStormProbabilityData>,
    @SerialName("nieve") val snow: List<AemetSnowData>,
    @SerialName("probNieve") val snowProbability: List<AemetSnowData>,
    @SerialName("temperatura") val temperature: List<AemetTemperatureDetailData>,
    @SerialName("sensTermica") val windChill: List<AemetTemperatureDetailData>,
    @SerialName("humedadRelativa") val relativeHumidity: List<AemetRelativeHumidityDetailData>,
    @SerialName("vientoAndRachaMax") val windAndMaxWindGust: List<AemetWindAndMaxWindGustData>,
    @SerialName("fecha") val date: LocalDateTime,
    @SerialName("orto") val dawnHour: LocalTime,
    @SerialName("ocaso") val sunsetHour: LocalTime,
)