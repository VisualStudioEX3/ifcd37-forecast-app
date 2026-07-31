package org.example.forecast.data

/**
 * Forecast wind directions.
 *
 * @param id AEMET wind direction code.
 * @param iconResourceId Icon that represent the wind direction.
 */
enum class ForecastWindDirections(
    val id: String,
    val iconResourceId: Int
) {
    North("N", 0),
    NortEast("NE", 0),
    East("E", 0),
    SouthEast("SE", 0),
    South("S", 0),
    SouthWest("SO", 0),
    West("O", 0),
    NorthWest("NO", 0),
    Calm("C", 0),
}