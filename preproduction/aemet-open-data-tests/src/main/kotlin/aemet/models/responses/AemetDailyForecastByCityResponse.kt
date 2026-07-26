package org.example.aemet.models.responses

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.aemet.models.data.*

/**
 * AEMET daily forecast by city response.
 *
 * See: [Predicción por municipios diaria. Tiempo actual.](https://opendata.aemet.es/dist/index.html#tag/predicciones-especificas/GET/api/prediccion/especifica/municipio/diaria/{municipio})
 */
@Serializable
data class AemetDailyForecastByCityResponse(
    @SerialName("origen") override val source: AemetSourceData,
    @SerialName("elaborado") override val createdAt: LocalDateTime,
    @SerialName("nombre") override val city: String,
    @SerialName("provincia") override val state: String,

    /**
     * AEMET daily forecast by city data.
     */
    @SerialName("prediccion") val data: AemetDailyCityWeatherPredictionData,
    @SerialName("id") override val cityCode: Int,
    override val version: Float
) : IAemetForecastByCityResponse

/**
 * Root object for AEMET daily forecast by city data.
 *
 * See: [AemetDailyForecastByCityResponse.data]
 */
@Serializable
data class AemetDailyCityWeatherPredictionData(
    /**
     * List of predictions by day.
     */
    @SerialName("dia") val day: List<AemetDailyCityWeatherPredictionDetailData>
)

/**
 * AEMET daily forecast by city data detail object.
 *
 * See: [AemetDailyForecastByCityResponse.data], [AemetDailyCityWeatherPredictionData.day]
 */
@Serializable
data class AemetDailyCityWeatherPredictionDetailData(
    /**
     * Rain probability data.
     */
    @SerialName("probPrecipitacion") val rainProbability: List<AemetRainData>,

    /**
     * Snow data.
     */
    @SerialName("cotaNieveProv") val snow: List<AemetSnowLevelData>,

    /**
     * Sky state data.
     */
    @SerialName("estadoCielo") val sky: List<AemetSkyStateData>,

    /**
     * Wind data.
     */
    @SerialName("viento") val wind: List<AemetWindData>,

    /**
     * Max wind gust data.
     */
    @SerialName("rachaMax") val maxWindGust: List<AemetWindGustData>,

    /**
     * Temperature data.
     */
    @SerialName("temperatura") val temperature: AemetTemperatureData,

    /**
     * Wind child data.
     */
    @SerialName("sensTermica") val windChill: AemetTemperatureData,

    /**
     * Relative humidity data.
     */
    @SerialName("humedadRelativa") val relativeHumidity: AemetRelativeHumidityData,

    /**
     * Max ultraviolet radiation index.
     */
    @SerialName("uvMax") val maxUvRadiation: Int,

    /**
     * Date of the prediction.
     *
     * @return [LocalDateTime] value from [String] formatted as "YYYY-MM-DDTHH-mm-ss".
     */
    @SerialName("fecha") val date: LocalDateTime
)