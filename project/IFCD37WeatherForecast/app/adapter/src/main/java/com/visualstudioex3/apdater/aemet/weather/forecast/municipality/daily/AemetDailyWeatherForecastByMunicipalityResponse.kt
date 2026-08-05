package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.daily

import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetRainData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetRelativeHumidityData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetSkyStateData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetSnowLevelData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetSourceData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetTemperatureData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetWindData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetWindGustData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.AemetWeatherForecastByMunicipalityResponse
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetRainProbabilityData
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET daily weather forecast by municipality response.
 *
 * See: [Predicción por municipios diaria. Tiempo actual.](https://opendata.aemet.es/dist/index.html#tag/predicciones-especificas/GET/api/prediccion/especifica/municipio/diaria/{municipio})
 */
@Serializable
data class AemetDailyWeatherForecastByMunicipalityResponse(
    @SerialName("origen")
    override val source: AemetSourceData,
    @SerialName("elaborado")
    override val createdAt: LocalDateTime,
    @SerialName("nombre")
    override val municipality: String,
    @SerialName("provincia")
    override val province: String,

    /**
     * AEMET daily weather forecast by municipality data.
     */
    @SerialName("prediccion")
    val data: AemetDailyWeatherForecastByMunicipalityData,
    @SerialName("id")
    override val municipalityCode: Int,
    override val version: Float
) : AemetWeatherForecastByMunicipalityResponse

/**
 * Root object for AEMET daily weather forecast by municipality data.
 *
 * See: [AemetDailyWeatherForecastByMunicipalityResponse.data]
 */
@Serializable
data class AemetDailyWeatherForecastByMunicipalityData(
    /**
     * List of predictions by day.
     */
    @SerialName("dia")
    val day: List<AemetDailyWeatherForecastByMunicipalityDataDetail>
)

/**
 * AEMET daily weather forecast by municipality data detail model.
 *
 * See: [AemetDailyWeatherForecastByMunicipalityResponse.data], [AemetDailyWeatherForecastByMunicipalityData.day]
 */
@Serializable
data class AemetDailyWeatherForecastByMunicipalityDataDetail(
    /**
     * Rain probability data.
     */
    @SerialName("probPrecipitacion")
    val rainProbability: List<AemetRainProbabilityData>,

    /**
     * Snow data.
     */
    @SerialName("cotaNieveProv")
    val snow: List<AemetSnowLevelData>,

    /**
     * Sky state data.
     */
    @SerialName("estadoCielo")
    val sky: List<AemetSkyStateData>,

    /**
     * Wind data.
     */
    @SerialName("viento")
    val wind: List<AemetWindData>,

    /**
     * Max wind gust data.
     */
    @SerialName("rachaMax")
    val maxWindGust: List<AemetWindGustData>,

    /**
     * Temperature data.
     */
    @SerialName("temperatura")
    val temperature: AemetTemperatureData,

    /**
     * Wind child data.
     */
    @SerialName("sensTermica")
    val windChill: AemetTemperatureData,

    /**
     * Relative humidity data.
     */
    @SerialName("humedadRelativa")
    val relativeHumidity: AemetRelativeHumidityData,

    /**
     * Max ultraviolet radiation index.
     */
    @SerialName("uvMax")
    val maxUvRadiation: Int? = null,

    /**
     * Date of the prediction.
     *
     * @return [LocalDateTime] value from [String] formatted as "YYYY-MM-DDTHH-mm-ss".
     */
    @SerialName("fecha")
    val date: LocalDateTime
)
