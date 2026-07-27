package org.example

import org.example.aemet.models.responses.AemetOpenDataResponse
import org.example.aemet.service.AemetApi
import retrofit2.HttpException

suspend fun main() {
    val response: AemetOpenDataResponse = try {
        AemetApi.endpoints.getDailyForecatsByCity(
            Secrets.getSecret("aemet_opendata_api_key"),
            "28058"
        )
    } catch (e: HttpException) {
        AemetApi.getErrorResponseBody(e)
    }

    println(response)
}