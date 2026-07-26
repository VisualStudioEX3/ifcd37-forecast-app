package org.example.aemet.models.responses

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.aemet.models.data.*

/**
 * AEMET hourly forecast by city response.
 *
 * See: [AEMET OpenData: Predicción por municipios horaria. Tiempo actual.](https://opendata.aemet.es/dist/index.html#tag/predicciones-especificas/GET/api/prediccion/especifica/municipio/horaria/{municipio})
 */
@Serializable
data class AemetHourlyForecastByCityResponse(
    @SerialName("origen") override val source: AemetSourceData,
    @SerialName("elaborado") override val createdAt: LocalDateTime,
    @SerialName("nombre") override val city: String,
    @SerialName("provincia") override val state: String,

    /**
     * AEMET hourly forecast by city data.
     */
    @SerialName("prediccion") val data: AemetHourlyCityWeatherPredictionData,
    @SerialName("id") override val cityCode: Int,
    override val version: Float
) : IAemetForecastByCityResponse

/**
 * Root object for AEMET hourly forecast by city data.
 *
 * See: [AemetHourlyForecastByCityResponse.data]
 */
@Serializable
data class AemetHourlyCityWeatherPredictionData(
    /**
     * List of predictions by hour.
     */
    @SerialName("dia") val day: List<AemetHourlyCityWeatherPredictionDetailData>
)

/**
 * AEMET hourly forecast by city data detail object.
 *
 * See: [AemetHourlyForecastByCityResponse.data], [AemetHourlyCityWeatherPredictionData.day]
 */
@Serializable
data class AemetHourlyCityWeatherPredictionDetailData(
    /**
     * Sky state data.
     */
    @SerialName("estadoCielo") val sky: List<AemetSkyStateData>,

    /**
     * Rain data.
     */
    @SerialName("precipitacion") val rain: List<AemetRainData>,

    /**
     * Rain probability data.
     */
    @SerialName("probPrecipitacion") val rainProbability: List<AemetRainData>,

    /**
     * Storm probability data.
     */
    @SerialName("probTormenta") val stormProbability: List<AemetStormProbabilityData>,

    /**
     * Snow data.
     */
    @SerialName("nieve") val snow: List<AemetSnowLevelData>,

    /**
     * Snow probability data.
     */
    @SerialName("probNieve") val snowProbability: List<AemetSnowLevelData>,

    /**
     * Temperature data.
     */
    @SerialName("temperatura") val temperature: List<AemetTemperatureByHourData>,

    /**
     * Wind child data.
     */
    @SerialName("sensTermica") val windChill: List<AemetTemperatureByHourData>,

    /**
     * Relative humidity data.
     */
    @SerialName("humedadRelativa") val relativeHumidity: List<AemetRelativeHumidityDetailData>,

    /**
     * Wind and max wind gust data.
     *
     * See [AemetWindAndMaxWindGustData] for more information about this list and how is received the data.
     */
    @SerialName("vientoAndRachaMax") val windAndMaxWindGust: List<AemetWindAndMaxWindGustData>,

    /**
     * Date of the prediction.
     *
     * @return [LocalDateTime] value from [String] formatted as "YYYY-MM-DDTHH-mm-ss".
     */
    @SerialName("fecha") val date: LocalDateTime,

    /**
     * Estimated hour when the sunrise begin.
     */
    @SerialName("orto") val sunriseHour: LocalTime,

    /**
     * Estimated hour when the sunset begin.
     */
    @SerialName("ocaso") val sunsetHour: LocalTime,
)