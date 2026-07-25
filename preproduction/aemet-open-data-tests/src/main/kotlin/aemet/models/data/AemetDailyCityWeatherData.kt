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
    @SerialName("probPrecipitacion") val rainProbability: List<AemetRainProbabilityData>,
    @SerialName("cotaNieveProv") val snow: List<AemetSnowData>,
    @SerialName("estadoCielo") val sky: List<AemetSkyStateData>,
    @SerialName("viento") val wind: List<AemetWindStateData>,
    @SerialName("rachaMax") val maxWindGust: List<AemetWindGustData>,
    @SerialName("temperatura") val temperature: AemetTemperatureData,
    @SerialName("sensTermica") val windChill: AemetTemperatureData,
    @SerialName("humedadRelativa") val relativeHumidity: AemetRelativeHumidityData,
    val uvMax: Int,
    @SerialName("fecha") val date: LocalDateTime
)

@Serializable
data class AemetRainProbabilityData(
    val value: Int,
    @SerialName("periodo") val period: String
)

@Serializable
data class AemetSnowData(
    val value: String,
    @SerialName("periodo") val period: String
)

@Serializable
data class AemetSkyStateData(
    val value: String,
    @SerialName("periodo") val period: String,
    @SerialName("descripcion") val description: String,
)

@Serializable
data class AemetWindStateData(
    @SerialName("direccion") val direction: String,
    @SerialName("velocidad") val velocity: Int,
    @SerialName("periodo") val period: String
)

@Serializable
data class AemetWindGustData(
    val value: String,
    @SerialName("periodo") val period: String
)

@Serializable
data class AemetTemperatureData(
    @SerialName("maxima") val max: Int,
    @SerialName("minima") val min: Int,
    @SerialName("dato") val data: List<AemetTemperatureDetailData>,
)

@Serializable
data class AemetTemperatureDetailData(
    val value: Int,
    @SerialName("hora") val hour: Int
)

@Serializable
data class AemetRelativeHumidityData(
    @SerialName("maxima") val max: Int,
    @SerialName("minima") val min: Int,
    @SerialName("dato") val data: List<AemetRelativeHumidityDetailData>,
)

@Serializable
data class AemetRelativeHumidityDetailData(
    val value: Int,
    @SerialName("hora") val hour: Int
)