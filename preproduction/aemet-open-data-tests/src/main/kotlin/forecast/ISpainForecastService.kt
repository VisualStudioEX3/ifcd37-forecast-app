package org.example.forecast

import org.example.forecast.models.ForecastResponse

/**
 * Spain forecast service.
 *
 * Service to request forecast data from Spain locations.
 */
interface ISpainForecastService {
    /**
     * Gets forecast by city.
     *
     * @param cityCode City code. The combination of ```CPRO``` and ```CMUN``` fields from
     * **INE** ([www.ine.es](www.ine.es)) databases.
     * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
     *
     * @return Returns a [ForecastResponse] object with all data about the daily and hourly forescast for the requested
     * city.
     *
     * @throws IllegalStateException Throws if have any error requesting the data.
     */
    suspend fun getForecastByCity(
        cityCode: String
    ): ForecastResponse
}