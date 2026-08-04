package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.daily

import com.visualstudioex3.apdater.aemet.AemetOpenDataRequest

/**
 * AEMET daily weather forecast by municipality request.
 */
interface AemetDailyWeatherForecastByMunicipalityRequest
    : AemetOpenDataRequest<AemetDailyWeatherForecastByMunicipalityRequestBody,
        AemetDailyWeatherForecastByMunicipalityResponse> {
}
