package com.visualstudioex3.application.ports.input.weather.forecast.models

import com.visualstudioex3.application.values.weather.forecast.SkyStates
import com.visualstudioex3.application.values.weather.forecast.UvRadiationIndexSeverityLevels
import com.visualstudioex3.application.values.weather.forecast.WindDirections
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

/**
 * Daily weather forecast data.
 */
data class DailyWeatherForecastData(
    /**
     * Forecast date.
     */
    val date: LocalDate,

    /**
     * Sky state.
     */
    val skyState: SkyStates,

    /**
     * Min/max temperatures.
     */
    val temperature: MinMaxTemperatureData,

    /**
     * Rain probability.
     *
     * @return Returns a value between 0 and 100.
     */
    val rainProbability: Int,

    /**
     * Min/max wind chill temperatures.
     */
    val windChill: MinMaxTemperatureData,

    /**
     * Wind direction and speed.
     */
    val wind: WindData,

    /**
     * Ultraviolet max radiation index level.
     */
    val uvMaxRadiation: UvRadiationData,

    /**
     * Min/max relative humidity.
     */
    val relativeHumidity: MinMaxRelativeHumidityData,
)

/**
 * Min/max temperature data.
 */
data class MinMaxTemperatureData(
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
 * Wind data.
 */
data class WindData(
    /**
     * Wind direction.
     */
    val direction: WindDirections,

    /**
     * Wind speed.
     *
     * @return Returns a value in kilometers by hour (Km/h).
     */
    val speed: Int,
)

/**
 * Min/max relative humidity.
 */
data class MinMaxRelativeHumidityData(
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
 * Ultraviolet radiation data.
 */
data class UvRadiationData(
    /**
     * Ultraviolet max radiation index.
     */
    val maxIndex: Int,

    /**
     * Ultraviolet radiation index severity level.
     */
    val severityLevel: UvRadiationIndexSeverityLevels
)
