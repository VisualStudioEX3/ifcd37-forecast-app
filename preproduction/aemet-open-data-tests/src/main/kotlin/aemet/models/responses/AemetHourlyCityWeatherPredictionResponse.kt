package org.example.aemet.models.responses

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.aemet.models.data.*

@Serializable
data class AemetHourlyCityWeatherPredictionResponse(
    override val id: Int,
    override val version: Float,
    @SerialName("origen") override val source: AemetSourceData,
    @SerialName("elaborado") override val createdAt: LocalDateTime,
    @SerialName("nombre") override val city: String,
    @SerialName("provincia") override val state: String,
    @SerialName("prediccion") val data: AemetHourlyCityWeatherPredictionData,
) : IAemetCityWeatherPredictionResponse

@Serializable
data class AemetHourlyCityWeatherPredictionData(
    @SerialName("dia") val days: List<AemetHourlyCityWeatherPredictionDetailData>
)

@Serializable
data class AemetHourlyCityWeatherPredictionDetailData(
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