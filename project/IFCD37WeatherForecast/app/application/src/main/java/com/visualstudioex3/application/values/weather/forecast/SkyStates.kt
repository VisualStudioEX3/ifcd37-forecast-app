package com.visualstudioex3.application.values.weather.forecast

/**
 * Sky states.
 *
 * Values extracted from image filenames from HTML code from:
 * [AEMET - Interpretación: Predicción por municipios](https://www.aemet.es/es/eltiempo/prediccion/municipios/ayuda)
 *
 * @param id AEMET sky state code.
 * @param iconResourceId Icon that represent the sky state.
 * @param stringResourceId Localized sky state description.
 */
enum class SkyStates(
    val id: String,
    val iconResourceId: Int,
    val stringResourceId: Int
) {
    // TODO: Document and fill resource id values for each element:
    CloudlessSky("11", 0, 0), // Cielo despejado
    ClearNight("11n", 0, 0), // Cielo despejado noche
    PartlyCloudy("12", 0, 0), // Poco nuboso
    PartlyCloudyNight("12n", 0, 0), // Poco nuboso noche
    CloudyIntervals("13", 0, 0), // Intervalos nubosos
    CloudyIntervalsNight("13n", 0, 0), // Intervalos nubosos noche
    Cloudy("14", 0, 0), // Nuboso
    CloudyNight("14n", 0, 0), // Nuboso noche
    VeryCloudy("15", 0, 0),  // Muy nuboso
    VeryCloudyNight("15n", 0, 0),  // Muy nuboso (noche, no documentado)
    CoveredSky("16", 0, 0), // Cubierto
    CoveredSkyNight("16n", 0, 0), // Cubierto (noche, no documentado)
    HightClouds("17", 0, 0), // Nubes altas
    HighCloudsNight("17n", 0, 0), // Nubes altas noche
    PartyCloudyWithLightRain("43", 0, 0), // Intervalos nubosos con lluvia escasa
    PartyCloudyWithLightRainNight("43n", 0, 0), // Intervalos nubosos con lluvia escasa noche
    CloudyWithLightRain("44", 0, 0), // Nuboso con lluvia escasa
    CloudyWithLightRainNight("44n", 0, 0), // Nuboso con lluvia escasa noche
    VeryCloudyWithLightRain("45", 0, 0), // Muy nuboso con lluvia escasa
    VeryCloudyWithLightRainNight("45n", 0, 0), // Muy nuboso con lluvia escasa (noche, no documentado)
    CoveredWithLightRain("46", 0, 0), // Cubierto con lluvia escasa
    CoveredWithLightRainNight("46n", 0, 0), // Cubierto con lluvia escasa (noche, no documentado)
    CloudyIntervalsWithRain("23", 0, 0), // Intervalos nubosos con lluvia
    CloudyIntervalsWithRainNight("23n", 0, 0), // Intervalos nubosos con lluvia noche
    CloudyWithRain("24", 0, 0), // Nuboso con lluvia
    CloudyWithRainNight("24n", 0, 0), // Nuboso con lluvia noche
    VeryCloudyWithRain("25", 0, 0), // Muy nuboso con lluvia
    VeryCloudyWithRainNight("25n", 0, 0), // Muy nuboso con lluvia (noche, no documentado)
    CoveredWithRain("26", 0, 0), // Cubierto con lluvia
    CoveredWithRainNight("26n", 0, 0), // Cubierto con lluvia (noche, no documentado)
    CloudyIntervalsWithLightSnow("71", 0, 0), // Intervalos nubosos con nieve escasa
    CloudyIntervalsWithLightSnowNight("71n", 0, 0), // Intervalos nubosos con nieve escasa noche
    CloudyWithLightSnow("72", 0, 0), // Nuboso con nieve escasa
    CloudyWithLightSnowNight("72n", 0, 0), // Nuboso con nieve escasa noche
    VeryCloudyWithLightSnow("73", 0, 0), // Muy nuboso con nieve escasa
    VeryCloudyWithLightSnowNight("73n", 0, 0), // Cubierto con nieve escasa (noche, no documentado)
    CoveredWithLightSnow("74", 0, 0), // Cubierto con nieve escasa
    CoveredWithLightSnowNight("74n", 0, 0), // Cubierto con nieve escasa (noche, no documentado)
    CloudyIntervalsWithSnow("33", 0, 0), // Intervalos nubosos con nieve
    CloudyIntervalsWithSnowNight("33n", 0, 0), // Intervalos nubosos con nieve noche
    CloudyWithSnow("34", 0, 0), // Nuboso con nieve
    CloudyWithSnowNight("34n", 0, 0), // Nuboso con nieve noche
    VeryCloudyWithSnow("35", 0, 0), // Muy nuboso con nieve
    VeryCloudyWithSnowNight("35n", 0, 0), // Muy nuboso con nieve (noche, no documentado)
    CoveredWithSnow("36", 0, 0), // Cubierto con nieve
    CoveredWithSnowNight("36n", 0, 0), // Cubierto con nieve (noche, no documentado)
    ClodyIntervalsWithStorm("51", 0, 0), // Intervalos nubosos con tormenta
    ClodyIntervalsWithStormNight("51n", 0, 0), // Intervalos nubosos con tormenta noche
    CloudyWithStorm("52", 0, 0), // Nuboso con tormenta
    CloudyWithStormNight("52n", 0, 0), // Nuboso con tormenta noche
    VeryCloudyWithStorm("53", 0, 0), // Muy nuboso con tormenta
    VeryCloudyWithStormNight("53n", 0, 0), // Muy nuboso con tormenta (noche, no documentado)
    CoveredWithStorm("54", 0, 0), // Cubierto con tormenta
    CoveredWithStormNight("54n", 0, 0), // Cubierto con tormenta (noche, no documentado)
    CloudyIntervalsWithStormAndRain("61", 0, 0), // Intervalos nubosos con tormenta y lluvia escasa
    CloudyIntervalsWithStormAndRainNight("61n", 0, 0), // Intervalos nubosos con tormenta y lluvia escasa noche
    CloudyWithStormAndLightRain("62", 0, 0), // Nuboso con tormenta y lluvia escasa
    CloudyWithStormAndLightRainNight("62n", 0, 0), // Nuboso con tormenta y lluvia escasa noche
    VeryCloudyWithStormAndLightRain("63", 0, 0), // Muy nuboso con tormenta y lluvia escasa
    VeryCloudyWithStormAndLightRainNight("63n", 0, 0), // Muy nuboso con tormenta y lluvia escasa (noche, no documentado)
    CoveredWithStormAndLightRain("64", 0, 0), // Cubierto con tormenta y lluvia escasa
    CoveredWithStormAndLightRainNight("64n", 0, 0), // Cubierto con tormenta y lluvia escasa (noche, no documentado)
    Fog("81", 0, 0), // Niebla
    FogNight("81n", 0, 0), // Niebla (noche, no documentado)
    Mist("82", 0, 0), // Bruma
    MistNight("82n", 0, 0), // Bruma (noche, no documentado)
    Haze("83", 0, 0), // Calima
    HazeNight("83n", 0, 0), // Calima (noche, no documentado)
}
