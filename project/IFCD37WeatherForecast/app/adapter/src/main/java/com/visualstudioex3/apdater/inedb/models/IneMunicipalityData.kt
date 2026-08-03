package com.visualstudioex3.apdater.inedb.models

/**
 * INE Municipality data model.
 *
 * Contains the data to identify a municipality in Spain.
 *
 * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
 */
data class IneMunicipalityData(
    /**
     * Autonomous community code.
     */
    val autnomousCommunityCode: String,

    /**
     * Province code.
     */
    val provinceCode: String,

    /**
     * Municipality code.
     */
    val municipalityCode: String,

    /**
     * Control digit.
     */
    val controlDigit: String,

    /**
     * Municipality name.
     */
    val name: String,
)
