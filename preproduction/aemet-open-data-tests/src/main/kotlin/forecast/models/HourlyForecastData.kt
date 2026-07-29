package org.example.forecast.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.example.forecast.data.ForecastSkyStates

/**
 * Hourly forecast data.
 */
@Serializable
data class HourlyForecastData(
    /**
     * Forecast date.
     */
    val date: LocalDate,

    /**
     * Forecast by hour.
     */
    val data: List<HourlyForecastDataDetail>
)

/**
 * Hourly forecast data detail.
 */
@Serializable
data class HourlyForecastDataDetail(
    /**
     * Forecast hour.
     */
    val hour: Int,

    /**
     * Sky state.
     */
    val skyState: ForecastSkyStates,

    /**
     * Temperature.
     *
     * @return Returns a value in celsius degrees.
     */
    val temperature: Int,

    /**
     * Rain.
     *
     * @return Returns a value in mm.
     */
    val rain: Int,

    /**
     * Wind chill.
     *
     * @return Returns a value in celsius degrees.
     */
    val windChill: Int,

    /**
     * Wind direction and speed.
     */
    val wind: ForecastWindData,

    /**
     * Relative humidity.
     *
     * @return Returns a value between 0 and 100.
     */
    val relativeHumidity: Int
)