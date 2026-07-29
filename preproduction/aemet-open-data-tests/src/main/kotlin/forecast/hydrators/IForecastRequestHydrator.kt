package org.example.forecast.hydrators

import org.example.forecast.models.ForecastRequest

/**
 * Contract for forecast request hydrator.
 */
interface IForecastRequestHydrator :
    IRequestHydrator<ForecastRequest, ForecastRequestHydratorParameters>