package org.example.forecast.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.example.forecast.data.ForecastSkyStates
import org.example.forecast.data.ForecastUvRadiationIndexSeverityLevels
import org.example.forecast.data.ForecastWindDirections

/**
 * Daily forecast data.
 */
@Serializable
data class DailyForecastData(
    /**
     * Forecast date.
     */
    val date: LocalDate,

    /**
     * Sky state.
     */
    val skyState: ForecastSkyStates,

    /**
     * Min/max temperatures.
     */
    val temperature: ForecastMinMaxTemperatureData,

    /**
     * Rain probability.
     *
     * @return Returns a value between 0 and 100.
     */
    val rainProbability: Int,

    /**
     * Min/max wind chill temperatures.
     */
    val windChill: ForecastMinMaxTemperatureData,

    /**
     * Wind direction and speed.
     */
    val wind: ForecastWindData,

    /**
     * Ultraviolet max radiation index level.
     */
    val uvMaxRadiation: ForecastUvRadiationData,

    /**
     * Min/max relative humidity.
     */
    val relativeHumidity: ForecastRelativeHumidityData,
)

/**
 * Forecast min/max temperature data.
 */
@Serializable
data class ForecastMinMaxTemperatureData(
    /**
     * Min temperature.
     *
     * @return Returns a value in celsius degrees.
     */
    val min: Int,

    /**
     * Max temperature.
     *
     * @return Returns a value in celsius degrees.
     */
    val max: Int,
)

/**
 * Forecast wind data.
 */
@Serializable
data class ForecastWindData(
    /**
     * Wind direction.
     */
    val direction: ForecastWindDirections,

    /**
     * Wind speed.
     *
     * @return Returns a value in kilometers by hour (Km/h).
     */
    val speed: Int,
)

/**
 * Forecast min/max relative humidity.
 */
@Serializable
data class ForecastRelativeHumidityData(
    /**
     * Min relative humidity.
     *
     * @return Returns a value between 0 and 100.
     */
    val min: Int,

    /**
     * Max relative humidity.
     *
     * @return Returns a value between 0 and 100.
     */
    val max: Int,
)

/**
 * Forecast ultraviolet radiation data.
 */
@Serializable
data class ForecastUvRadiationData(
    /**
     * Ultraviolet max radiation index.
     */
    val maxIndex: Int,

    /**
     * Ultraviolet radiation index severity level.
     */
    val severityLevel: ForecastUvRadiationIndexSeverityLevels
)