package org.example.forecast.usecases

import org.example.forecast.models.ForecastRequest
import org.example.forecast.models.ForecastResponse

/**
 * Contract for forecast use case.
 */
interface IForecastUseCase : IUseCase<ForecastRequest, ForecastResponse>