package org.example.forecast.models

import kotlinx.serialization.Serializable

/**
 * Forecast request model.
 */
@Serializable
data class ForecastRequest(
    /**
     * AEMET OpenData API key.
     */
    val apiKey: String,

    /**
     * City code.
     *
     * The combination of ```CPRO``` and ```CMUN``` fields from **INE** ([www.ine.es](www.ine.es))
     * databases.
     * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
     */
    val cityCode: String
)
