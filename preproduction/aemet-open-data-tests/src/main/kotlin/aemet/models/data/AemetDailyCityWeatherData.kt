package org.example.aemet.models.data

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetDailyCityWeatherData(
    override val id: Int,
    override val version: Float,
    @SerialName("origen") override val source: AemetSourceData,
    @SerialName("elaborado") override val createdAt: LocalDateTime,
    @SerialName("nombre") override val city: String,
    @SerialName("provincia") override val state: String,
    @SerialName("prediccion") val data: AemetDailyWeatherData,
) : IAemetCityWeatherData


@Serializable
data class AemetDailyWeatherData(
    @SerialName("dia") val days: List<AemetDailyWeatherDayData>
)

@Serializable
data class AemetDailyWeatherDayData(
    @SerialName("probPrecipitacion") val rainProbability: List<AemetRainData>,
    @SerialName("cotaNieveProv") val snow: List<AemetSnowData>,
    @SerialName("estadoCielo") val sky: List<AemetSkyStateData>,
    @SerialName("viento") val wind: List<AemetWindData>,
    @SerialName("rachaMax") val maxWindGust: List<AemetWindGustData>,
    @SerialName("temperatura") val temperature: AemetTemperatureData,
    @SerialName("sensTermica") val windChill: AemetTemperatureData,
    @SerialName("humedadRelativa") val relativeHumidity: AemetRelativeHumidityData,
    val uvMax: Int,
    @SerialName("fecha") val date: LocalDateTime
)