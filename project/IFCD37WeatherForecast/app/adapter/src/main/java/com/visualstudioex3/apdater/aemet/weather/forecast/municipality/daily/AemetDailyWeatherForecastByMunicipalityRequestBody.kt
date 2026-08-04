package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.daily

/**
 * AEMET daily weather forecast by municipality request body.
 *
 * @param apiKey AEMET OpenData API key.
 * @param municipalityCode Municipality code. The combination of ```CPRO``` and ```CMUN``` fields
 * from **INE** ([www.ine.es](www.ine.es)) databases.
 * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
 */
data class AemetDailyWeatherForecastByMunicipalityRequestBody(
    val apiKey: String,
    val municipalityCode: String
)
