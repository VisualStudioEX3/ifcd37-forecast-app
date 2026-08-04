package com.visualstudioex3.apdater.aemet.weather.forecast.municipality

import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetSourceData
import kotlinx.datetime.LocalDateTime

/**
 * Base interface contract for AEMET forecast by municipality response models.
 */
interface AemetWeatherForecastByMunicipalityResponse {
    /**
     * Source information about AEMET.
     */
    val source: AemetSourceData

    /**
     * Date of creation.
     *
     * @return [kotlinx.datetime.LocalDateTime] value from [String] formatted as "YYYY-MM-DDTHH-mm-ss".
     */
    val createdAt: LocalDateTime

    /**
     * Municipality name.
     */
    val municipality: String

    /**
     * Province name.
     */
    val province: String

    /**
     * Municipality code.
     *
     * The combination of ```CPRO``` and ```CMUN``` fields from **INE** ([www.ine.es](www.ine.es)) databases.
     * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
     */
    val municipalityCode: Int

    /**
     * API version.
     *
     * @return Decimal value like ```1.0```.
     */
    val version: Float
}
