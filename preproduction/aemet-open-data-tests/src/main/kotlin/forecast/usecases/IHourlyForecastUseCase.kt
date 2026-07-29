package org.example.forecast.usecases

import org.example.forecast.models.ForecastRequest
import org.example.forecast.models.HourlyForecastData

/**
 * Contract for hourly forecast use case.
 */
interface IHourlyForecastUseCase :
    IUseCase<ForecastRequest, List<HourlyForecastData>>