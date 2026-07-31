package org.example.forecast.requesthandlers

import org.example.aemet.models.responses.AemetHourlyForecastByCityResponse
import org.example.forecast.models.ForecastRequest

/**
 * Contract for hourly forecast request handler.
 */
interface IHourlyForecastRequestHandler:
    IForecastRequestHandler<ForecastRequest, AemetHourlyForecastByCityResponse>
