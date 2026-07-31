package org.example.forecast.data

/**
 * Forecast sky states.
 *
 * Values extracted from image filenames from HTML code from:
 * [AEMET - Interpretación: Predicción por municipios](https://www.aemet.es/es/eltiempo/prediccion/municipios/ayuda)
 *
 * @param id AEMET sky state code.
 * @param iconResourceId Icon that represent the sky state.
 * @param stringResourceId Localized sky state description.
 */
enum class ForecastSkyStates(
    val id: String,
    val iconResourceId: Int,
    val stringResourceId: Int
) {
    CloudlessSky("11", 0, 0), // Cielo despejado
    ClearNight("11n", 0, 0), // Cielo despejado noche
    PartlyCloudy("12", 0, 0), // Poco nuboso
    PartlyCloudyNight("12n", 0, 0), // Poco nuboso noche
    CloudyIntervals("13", 0, 0), // Intervalos nubosos
    CloudyIntervalsNight("13n", 0, 0), // Intervalos nubosos noche
    Cloudy("14", 0, 0), // Nuboso
    CloudyNight("14n", 0, 0), // Nuboso noche
    VeryCloudy("15", 0, 0),  // Muy nuboso
    CoveredSky("16", 0, 0), // Cubierto
    HightClouds("17", 0, 0), // Nubes altas
    HighCloudsNight("17n", 0, 0), // Nubes altas noche
    PartyCloudyWithLightRain("43", 0, 0), // Intervalos nubosos con lluvia escasa
    PartyCloudyWithLightRainNight("43n", 0, 0), // Intervalos nubosos con lluvia escasa noche
    CloudyWithLightRain("44", 0, 0), // Nuboso con lluvia escasa
    CloudyWithLightRainNight("44n", 0, 0), // Nuboso con lluvia escasa noche
    VeryCloudyWithLightRain("45", 0, 0), // Muy nuboso con lluvia escasa
    CoveredWithLightRain("46", 0, 0), // Cubierto con lluvia escasa
    CloudyIntervalsWithRain("23", 0, 0), // Intervalos nubosos con lluvia
    CloudyIntervalsWithRainNight("23n", 0, 0), // Intervalos nubosos con lluvia noche
    CloudyWithRain("24", 0, 0), // Nuboso con lluvia
    CloudyWithRainNight("24n", 0, 0), // Nuboso con lluvia noche
    VeryCloudyWithRain("25", 0, 0), // Muy nuboso con lluvia
    CoveredWithRain("26", 0, 0), // Cubierto con lluvia
    CloudyIntervalsWithLightSnow("71", 0, 0), // Intervalos nubosos con nieve escasa
    CloudyIntervalsWithLightSnowNight("71n", 0, 0), // Intervalos nubosos con nieve escasa noche
    CloudyWithLightSnow("72", 0, 0), // Nuboso con nieve escasa
    CloudyWithLightSnowNight("72n", 0, 0), // Nuboso con nieve escasa noche
    VeryCloudyWithLightSnow("73", 0, 0), // Muy nuboso con nieve escasa
    VeryCloudyWithLightSnowNight("74", 0, 0), // Cubierto con nieve escasa
    CloudyIntervalsWithSnow("33", 0, 0), // Intervalos nubosos con nieve
    CloudyIntervalsWithSnowNight("33n", 0, 0), // Intervalos nubosos con nieve noche
    CloudyWithSnow("34", 0, 0), // Nuboso con nieve
    CloudyWithSnowNight("34n", 0, 0), // Nuboso con nieve noche
    VeryCloudyWithSnow("35", 0, 0), // Muy nuboso con nieve
    CoveredWithSnow("36", 0, 0), // Cubierto con nieve
    ClodyIntervalsWithStorm("51", 0, 0), // Intervalos nubosos con tormenta
    ClodyIntervalsWithStormNight("51n", 0, 0), // Intervalos nubosos con tormenta noche
    CloudyWithStorm("52", 0, 0), // Nuboso con tormenta
    CloudyWithStormNight("52n", 0, 0), // Nuboso con tormenta noche
    VeryCloudyWithStorm("53", 0, 0), // Muy nuboso con tormenta
    CoveredWithStorm("54", 0, 0), // Cubierto con tormenta
    CloudyIntervalsWithStormAndRain("61", 0, 0), // Intervalos nubosos con tormenta y lluvia escasa
    CloudyIntervalsWithStormAndRainNight("61n", 0, 0), // Intervalos nubosos con tormenta y lluvia escasa noche
    CloudyWithStormAndLightRain("62", 0, 0), // Nuboso con tormenta y lluvia escasa
    CloudyWithStormAndLightRainNight("62n", 0, 0), // Nuboso con tormenta y lluvia escasa noche
    VeryCloudyWithStormAndLightRain("63", 0, 0), // Muy nuboso con tormenta y lluvia escasa
    CoveredWithStormAndLightRain("64", 0, 0), // Cubierto con tormenta y lluvia escasa
    Fog("81", 0, 0), // Niebla
    Mist("82", 0, 0), // Bruma
    Haze("83", 0, 0), // Calima
}