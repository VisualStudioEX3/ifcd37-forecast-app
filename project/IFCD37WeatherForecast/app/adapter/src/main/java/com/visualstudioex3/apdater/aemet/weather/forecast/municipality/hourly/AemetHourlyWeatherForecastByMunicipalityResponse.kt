package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.hourly

import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetRainData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetRelativeHumidityPeriodData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetSkyStateData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetSnowLevelData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetSourceData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetStormProbabilityData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetTemperaturePeriodData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetWindAndMaxWindGustData
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.AemetWeatherForecastByMunicipalityResponse
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET hourly weather forecast by municipality response.
 *
 * See: [AEMET OpenData: Predicción por municipios horaria. Tiempo actual.](https://opendata.aemet.es/dist/index.html#tag/predicciones-especificas/GET/api/prediccion/especifica/municipio/horaria/{municipio})
 */
@Serializable
data class AemetHourlyWeatherForecastByMunicipalityResponse(
    @SerialName("origen")
    override val source: AemetSourceData,
    @SerialName("elaborado")
    override val createdAt: LocalDateTime,
    @SerialName("nombre")
    override val municipality: String,
    @SerialName("provincia")
    override val province: String,
    /**
     * AEMET hourly forecast by city data.
     */
    @SerialName("prediccion")
    val data: AemetHourlyWeatherForecastByMunicipalityData,
    @SerialName("id")
    override val municipalityCode: Int,
    override val version: Float
) : AemetWeatherForecastByMunicipalityResponse

/**
 * Root object for AEMET hourly weather forecast by municipality data.
 *
 * See: [AemetHourlyWeatherForecastByMunicipalityResponse.data]
 */
@Serializable
data class AemetHourlyWeatherForecastByMunicipalityData(
    /**
     * List of predictions by hour.
     */
    @SerialName("dia")
    val day: List<AemetHourlyWeatherForecastByMunicipalityDataDetail>
)

/**
 * AEMET hourly weather forecast by municipality data detail model.
 *
 * See: [AemetHourlyWeatherForecastByMunicipalityResponse.data], [AemetHourlyWeatherForecastByMunicipalityData.day]
 */
@Serializable
data class AemetHourlyWeatherForecastByMunicipalityDataDetail(
    /**
     * Sky state data.
     */
    @SerialName("estadoCielo")
    val sky: List<AemetSkyStateData>,

    /**
     * Rain data.
     */
    @SerialName("precipitacion")
    val rain: List<AemetRainData>,

    /**
     * Rain probability data.
     */
    @SerialName("probPrecipitacion")
    val rainProbability: List<AemetRainData>,

    /**
     * Storm probability data.
     */
    @SerialName("probTormenta")
    val stormProbability: List<AemetStormProbabilityData>,

    /**
     * Snow data.
     */
    @SerialName("nieve")
    val snow: List<AemetSnowLevelData>,

    /**
     * Snow probability data.
     */
    @SerialName("probNieve")
    val snowProbability: List<AemetSnowLevelData>,

    /**
     * Temperature data.
     */
    @SerialName("temperatura")
    val temperature: List<AemetTemperaturePeriodData>,

    /**
     * Wind child data.
     */
    @SerialName("sensTermica")
    val windChill: List<AemetTemperaturePeriodData>,

    /**
     * Relative humidity data.
     */
    @SerialName("humedadRelativa")
    val relativeHumidity: List<AemetRelativeHumidityPeriodData>,

    /**
     * Wind and max wind gust data.
     *
     * See [AemetWindAndMaxWindGustData] for more information about this list and how is received the data.
     */
    @SerialName("vientoAndRachaMax")
    val windAndMaxWindGust: List<AemetWindAndMaxWindGustData>,

    /**
     * Date of the prediction.
     *
     * @return [LocalDateTime] value from [String] formatted as "YYYY-MM-DDTHH-mm-ss".
     */
    @SerialName("fecha")
    val date: LocalDateTime,

    /**
     * Estimated hour when the sunrise begin.
     */
    @SerialName("orto")
    val sunriseHour: LocalTime,

    /**
     * Estimated hour when the sunset begin.
     */
    @SerialName("ocaso")
    val sunsetHour: LocalTime,
)
