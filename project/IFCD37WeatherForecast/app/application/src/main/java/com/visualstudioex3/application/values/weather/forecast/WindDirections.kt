package com.visualstudioex3.application.values.weather.forecast

/**
 * Forecast wind directions.
 *
 * @param id AEMET wind direction code.
 * @param iconResourceId Icon that represent the wind direction.
 */
enum class WindDirections(
    val id: String,
    val iconResourceId: Int
) {
    // TODO: Fill resource id values for each element:
    /**
     * North (N).
     */
    North("N", 0),

    /**
     * North-east (NE).
     */
    NorthEast("NE", 0),

    /**
     * East (E).
     */
    East("E", 0),

    /**
     * South-east (SE).
     */
    SouthEast("SE", 0),

    /**
     * South (S).
     */
    South("S", 0),

    /**
     * South-west (SW).
     */
    SouthWest("SO", 0),

    /**
     * West (W).
     */
    West("O", 0),

    /**
     * North-west (NW).
     */
    NorthWest("NO", 0),

    /**
     * Calm. No wind.
     */
    Calm("C", 0),
}
