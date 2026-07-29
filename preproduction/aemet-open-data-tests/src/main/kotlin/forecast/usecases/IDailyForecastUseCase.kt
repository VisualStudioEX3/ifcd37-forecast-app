package org.example.forecast.usecases

import org.example.forecast.models.DailyForecastResponse
import org.example.forecast.models.ForecastRequest

/**
 * Contract for daily forecast use case.
 */
interface IDailyForecastUseCase : IUseCase<ForecastRequest, DailyForecastResponse>