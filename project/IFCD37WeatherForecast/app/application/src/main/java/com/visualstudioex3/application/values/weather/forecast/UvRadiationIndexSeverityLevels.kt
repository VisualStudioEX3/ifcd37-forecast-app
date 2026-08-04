package com.visualstudioex3.application.values.weather.forecast

/**
 * Forecast ultraviolet radiation index severity levels.
 *
 * Values extracted from:
 * [AEMET - Interpretación: Predicción por municipios](https://www.aemet.es/es/eltiempo/prediccion/municipios/ayuda)
 *
 * @param range AEMET ultraviolet radiation index level range.
 * @param iconResourceId Icon that represent the ultraviolet radiation index level.
 * @param stringResourceId Localized sky state description.
 */
enum class UvRadiationIndexSeverityLevels(
    val range: IntRange,
    val iconResourceId: Int,
    val stringResourceId: Int
) {
    // TODO: Fill resource id values for each element:
    /**
     * Unavailable data.
     */
    Unavailable(0..0, 0, 0),

    /**
     * Low ultraviolet radiation index level.
     */
    Low(1..2, 0, 0),

    /**
     * Moderate ultraviolet radiation index level.
     */
    Moderate(3..5, 0, 0),

    /**
     * High ultraviolet radiation index level.
     */
    High(6..7, 0, 0),

    /**
     * Very High ultraviolet radiation index level.
     */
    VeryHigh(8..10, 0, 0),

    /**
     * Extremely High ultraviolet radiation index level.
     */
    ExtremelyHigh(10..100, 0, 0),
}
