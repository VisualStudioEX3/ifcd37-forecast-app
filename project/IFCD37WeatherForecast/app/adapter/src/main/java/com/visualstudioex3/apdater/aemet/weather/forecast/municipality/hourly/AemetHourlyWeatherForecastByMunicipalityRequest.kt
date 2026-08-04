package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.hourly

import com.visualstudioex3.apdater.aemet.AemetOpenDataRequest

/**
 * AEMET hourly weather forecast by municipality request.
 */
interface AemetHourlyWeatherForecastByMunicipalityRequest
    : AemetOpenDataRequest<AemetHourlyWeatherForecastByMunicipalityRequestBody,
        AemetHourlyWeatherForecastByMunicipalityResponse> {
}
