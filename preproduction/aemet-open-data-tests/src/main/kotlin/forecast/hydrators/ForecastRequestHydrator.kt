package org.example.forecast.hydrators

import org.example.Secrets
import org.example.forecast.models.ForecastRequest

/**
 * Parameters model for [ForecastRequestHydrator] service.
 */
data class ForecastRequestHydratorParameters(
    /**
     * City code.
     *
     * The combination of ```CPRO``` and ```CMUN``` fields from **INE** ([www.ine.es](www.ine.es))
     * databases.
     * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
     */
    val cityCode: String,
)

/**
 * Forecast request hydrator.
 */
class ForecastRequestHydrator : IForecastRequestHydrator {
    override fun hydrate(
        parameters: ForecastRequestHydratorParameters
    ) = ForecastRequest(
        apiKey = Secrets.getSecret("aemet_opendata_api_key"),
        cityCode = parameters.cityCode
    )
}