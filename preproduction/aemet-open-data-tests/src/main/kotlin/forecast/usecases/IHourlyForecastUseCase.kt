package org.example.forecast.usecases

import org.example.forecast.models.ForecastRequest
import org.example.forecast.models.HourlyForecastResponse

/**
 * Contract for hourly forecast use case.
 */
interface IHourlyForecastUseCase : IUseCase<ForecastRequest, HourlyForecastResponse>