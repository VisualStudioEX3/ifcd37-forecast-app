package org.example.aemet.service

import org.example.aemet.models.responses.AemetOpenDataResponse
import org.example.aemet.models.responses.IAemetForecastByCityResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * AEMET OpenData API for request specific forecasts by city.
 */
interface IAemetForecastByCityApiService {
    /**
     * Requests the daily forecast for a specific city.
     *
     * See [AEMET OpenData - Predicción por municipios diaria. Tiempo actual.](https://opendata.aemet.es/dist/index.html#tag/predicciones-especificas/GET/api/prediccion/especifica/municipio/diaria/{municipio})
     *
     * @param cityCode The combination of ```CPRO``` and ```CMUN``` fields from **INE** ([www.ine.es](www.ine.es))
     * databases.
     * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
     *
     * @return Returns [AemetOpenDataResponse] response with the result of the request.
     */
    @GET("api/prediccion/especifica/municipio/diaria/{municipio}")
    suspend fun getDailyForecatsByCity(
        @Path("municipio") cityCode: Int
    ): AemetOpenDataResponse

    /**
     * Requests the hourly forecast for a specific city.
     *
     * See [AEMET OpenData - Predicción por municipios horaria. Tiempo actual.](https://opendata.aemet.es/dist/index.html?#tag/predicciones-especificas/GET/api/prediccion/especifica/municipio/horaria/{municipio})
     *
     * @param cityCode The combination of ```CPRO``` and ```CMUN``` fields from **INE** ([www.ine.es](www.ine.es))
     * databases.
     * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
     *
     * @return Returns [AemetOpenDataResponse] response with the result of the request.
     */
    @GET("api/prediccion/especifica/municipio/horaria/{municipio}")
    suspend fun getHourlyForecatsByCity(
        @Path("municipio") cityCode: Int
    ): AemetOpenDataResponse

    /**
     * Requests data sourced by url in response.
     *
     * When a request is successful, [AemetOpenDataResponse] response return two urls,
     * [AemetOpenDataResponse.requestUrlData] with the temporal url pointed to the requested data, and
     * [AemetOpenDataResponse.requestUrlMetadata] with the temporal url pointed to the metadata that defined the
     * requested data structure and possible values. Each url ends with the id value to the pointed data (e.g.
     * ```https://opendata.aemet.es/opendata/sh/be16a275```).
     *
     * @param id Data id to request.
     *
     * @return Returns an [IAemetForecastByCityResponse] based object with the deserialized data.
     */
    @GET("sh/{id}")
    suspend fun <T : IAemetForecastByCityResponse> getResponseData(
        @Path("id") id: String
    ): T
}