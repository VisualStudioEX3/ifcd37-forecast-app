package org.example.forecast.usecases

import org.example.forecast.models.DailyForecastData
import org.example.forecast.models.ForecastRequest

/**
 * Contract for daily forecast use case.
 */
interface IDailyForecastUseCase :
    IUseCase<ForecastRequest, List<DailyForecastData>>