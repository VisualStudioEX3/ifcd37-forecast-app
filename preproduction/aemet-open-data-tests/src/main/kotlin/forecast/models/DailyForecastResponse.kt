package org.example.forecast.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.example.forecast.data.ForecastSkyStates
import org.example.forecast.data.ForecastWindDirections

/**
 * Daily forecast response model.
 */
@Serializable
data class DailyForecastResponse(
    /**
     * Date and time of forecast data preparation from source service.
     */
    val dateTime: LocalDateTime,

    /**
     * City name.
     */
    val cityName: String,

    /**
     * Forecast data by day.
     */
    val data: List<ForecastDayData>
)

/**
 * Forecast day data.
 */
@Serializable
data class ForecastDayData(
    /**
     * Forecast date.
     */
    val date: LocalDate,

    /**
     * Sky state.
     */
    val skyState: ForecastSkyStates,

    /**
     * Min and max temperatures.
     */
    val temperature: ForecastDayTemperatureData,

    /**
     * Rain probability.
     *
     * @return Returns a value between 0 and 100.
     */
    val rainProbability: Int,

    /**
     * Min and max wind chill temperatures.
     */
    val windChill: ForecastDayTemperatureData,

    /**
     * Wind direction and speed.
     */
    val wind: ForecastDayWindData,

    /**
     * Ultraviolet max radiation.
     */
    val uvMaxRadiation: Int,

    /**
     * Min and max relative humidity.
     */
    val relativeHumidity: ForecastRelativeHumidityData,
)

@Serializable
data class ForecastDayTemperatureData(
    val max: Int,
    val min: Int,
)


@Serializable
data class ForecastDayWindData(
    val direction: ForecastWindDirections,
    val speed: Int,
)

@Serializable
data class ForecastRelativeHumidityData(
    val max: Int,
    val min: Int,
)