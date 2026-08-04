package com.visualstudioex3.application.ports.input.weather.forecast.usecases

import com.visualstudioex3.application.UseCase
import com.visualstudioex3.application.entities.WeatherForecastData
import com.visualstudioex3.application.ports.input.weather.forecast.models.WeatherForecastRequest

/**
 * Contract for wather forecast use case.
 */
interface WeatherForecastUseCase
    : UseCase<WeatherForecastRequest, WeatherForecastData>
