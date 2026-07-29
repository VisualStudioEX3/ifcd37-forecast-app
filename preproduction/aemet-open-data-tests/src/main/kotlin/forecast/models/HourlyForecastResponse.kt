package org.example.forecast.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.example.forecast.data.ForecastSkyStates
import org.example.forecast.data.ForecastWindDirections

@Serializable
data class HourlyForecastResponse(
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
    val data: List<ForecastHourData>
)

@Serializable
data class ForecastHourData(
    val foo: String
)