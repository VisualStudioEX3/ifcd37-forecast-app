package com.visualstudioex3.application.ports.output.weather.forecast.usecases

import com.visualstudioex3.application.UseCase
import com.visualstudioex3.application.ports.input.weather.forecast.models.HourlyWeatherForecastData
import com.visualstudioex3.application.ports.output.weather.forecast.models.WeatherForecastRequest

/**
 * Contract for hourly weather forecast use case.
 */
internal interface HourlyWeatherForecastUseCase
    : UseCase<WeatherForecastRequest, List<HourlyWeatherForecastData>>
