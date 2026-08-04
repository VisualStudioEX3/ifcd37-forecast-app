package com.visualstudioex3.application.ports.input.weather.forecast.models

import com.visualstudioex3.application.values.weather.forecast.SkyStates
import kotlinx.datetime.LocalDate

/**
 * Hourly weather forecast data.
 */
data class HourlyWeatherForecastData(
    /**
     * Forecast date.
     */
    val date: LocalDate,

    /**
     * Forecast by hour.
     */
    val data: List<HourlyWeatherForecastDataDetail>
)

/**
 * Hourly weather forecast data detail.
 */
data class HourlyWeatherForecastDataDetail(
    /**
     * Forecast hour.
     */
    val hour: Int,

    /**
     * Sky state.
     */
    val skyState: SkyStates,

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
    val wind: WindData,

    /**
     * Relative humidity.
     *
     * @return Returns a value between 0 and 100.
     */
    val relativeHumidity: Int
)
