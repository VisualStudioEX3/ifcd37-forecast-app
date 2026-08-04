package com.visualstudioex3.application.ports.output.weather.forecast.usecases

import com.visualstudioex3.application.UseCase
import com.visualstudioex3.application.entities.WeatherForecast
import com.visualstudioex3.application.ports.output.weather.forecast.models.WeatherForecastRequest

/**
 * Contract for wather forecast use case.
 */
internal interface WeatherForecastUseCase
    : UseCase<WeatherForecastRequest, WeatherForecast>
