package com.visualstudioex3.application.exceptions.weather.forecast

/**
 * Weather forecast exception.
 *
 * @param e Inner exception.
 */
class WeatherForecastException(
    e: Exception
): RuntimeException(
    "Error requesting weather forecast: $e"
) {
}
