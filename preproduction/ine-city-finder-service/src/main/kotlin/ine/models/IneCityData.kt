package org.example.ine.models

/**
 * INE city data model.
 *
 * Contains the data to identify a city in Spain.
 *
 * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
 */
data class IneCityData(
    /**
     * Autonomous community code.
     */
    val autnomousCommunityCode: String,

    /**
     * State code.
     */
    val stateCode: String,

    /**
     * City code.
     */
    val cityCode: String,

    /**
     * Control digit.
     */
    val controlDigit: String,

    /**
     * City name.
     */
    val name: String,
)