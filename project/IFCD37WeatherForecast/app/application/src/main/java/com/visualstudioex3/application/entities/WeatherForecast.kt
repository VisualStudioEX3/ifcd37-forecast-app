package com.visualstudioex3.application.entities

import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import com.visualstudioex3.application.ports.input.weather.forecast.models.HourlyWeatherForecastData

/**
 * Weather forecast entity model.
 *
 * @param municipality Weather Forecast for municipality.
 * @param daily Daily weather forecast data.
 * @param hourly Hourly weather forecast data.
 */
data class WeatherForecast(
    val municipality: Municipality,
    val daily: List<DailyWeatherForecastData>,
    val hourly: List<HourlyWeatherForecastData>
)
