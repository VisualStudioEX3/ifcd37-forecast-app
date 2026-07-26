package org.example.aemet.models.responses

import kotlinx.datetime.LocalDateTime
import org.example.aemet.models.data.AemetSourceData

/**
 * Base interface contract for AEMET weather prediction response models.
 */
interface IAemetCityWeatherPredictionResponse {
    /**
     * Source information about AEMET.
     */
    val source: AemetSourceData

    /**
     * Date of creation.
     *
     * @return [LocalDateTime] value from [String] formatted as "YYYY-MM-DDTHH-mm-ss".
     */
    val createdAt: LocalDateTime

    /**
     * City name.
     */
    val city: String

    /**
     * State name.
     */
    val state: String

    /**
     * City code.
     *
     * Is the combination of CPRO and CMUN fields from INE ([www.ine.es](www.ine.es)) databases.
     *
     * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
     */
    val cityCode: Int

    /**
     * API version.
     *
     * @return Decimal value like 1.0 or 2.5.
     */
    val version: Float
}