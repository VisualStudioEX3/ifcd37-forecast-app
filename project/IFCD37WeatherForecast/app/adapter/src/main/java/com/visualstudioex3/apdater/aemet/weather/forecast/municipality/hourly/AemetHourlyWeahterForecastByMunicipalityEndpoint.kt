package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.hourly

import com.visualstudioex3.apdater.aemet.AemetOpenDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * AEMET OpenData API endpoint for request hourly weather forecasts by municipality.
 */
internal interface AemetHourlyWeahterForecastByMunicipalityEndpoint {
    /**
     * Invokes the request.
     *
     * See [AEMET OpenData - Predicción por municipios horaria. Tiempo actual.](https://opendata.aemet.es/dist/index.html?#tag/predicciones-especificas/GET/api/prediccion/especifica/municipio/horaria/{municipio})
     *
     * @param apiKey AEMET OpenData API key.
     * @param municipalityCode The combination of "CPRO" and "CMUN" fields from **INE** ([www.ine.es](www.ine.es))
     * databases.
     * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
     *
     * @return Returns [AemetOpenDataResponse] response with the result of the request.
     */
    @GET("api/prediccion/especifica/municipio/horaria/{municipio}")
    suspend fun invoke(
        @Header("api_key") apiKey: String,
        @Path("municipio") municipalityCode: String
    ): AemetOpenDataResponse
}
