package org.example.forecast.requesthandlers

import org.example.aemet.models.responses.AemetDailyForecastByCityResponse
import org.example.forecast.models.ForecastRequest

/**
 * Contract for daily forecast request handler.
 */
interface IDailyForecastRequestHandler:
    IForecastRequestHandler<ForecastRequest, AemetDailyForecastByCityResponse>
