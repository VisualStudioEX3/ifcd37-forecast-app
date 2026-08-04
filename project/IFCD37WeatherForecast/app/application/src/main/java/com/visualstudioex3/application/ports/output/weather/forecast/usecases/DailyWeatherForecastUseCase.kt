package com.visualstudioex3.application.ports.output.weather.forecast.usecases

import com.visualstudioex3.application.UseCase
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import com.visualstudioex3.application.ports.output.weather.forecast.models.WeatherForecastRequest

/**
 * Contract for daily weather forecast use case.
 */
internal interface DailyWeatherForecastUseCase
    : UseCase<WeatherForecastRequest, List<DailyWeatherForecastData>>
