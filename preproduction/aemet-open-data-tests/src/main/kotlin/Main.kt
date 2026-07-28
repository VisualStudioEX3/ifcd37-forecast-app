package org.example

import kotlinx.serialization.json.Json
import org.example.aemet.models.responses.AemetDailyForecastByCityResponse
import org.example.aemet.models.responses.AemetHourlyForecastByCityResponse
import org.example.aemet.service.AemetApi
import retrofit2.HttpException

suspend fun main() {
    val apiKey: String = Secrets.getSecret("aemet_opendata_api_key")
    val cityCode = "28058"

    requestDailyForecast(apiKey, cityCode)
    requestHourlyForecast(apiKey, cityCode)
}

suspend fun requestDailyForecast(apiKey: String, cityCode: String) {
    try {
        AemetApi.endpoints.getDailyForecatsByCity(apiKey, cityCode).also {
            try {
                println("Daily forecast:")
                println(it)

                val json: String = NetworkUtils.downloadResourceStringFromUrl(
                    it.requestUrlData!!
                )

                println(json)

                val forecast: AemetDailyForecastByCityResponse =
                    Json.decodeFromString<List<AemetDailyForecastByCityResponse>>(json)
                        .first()

                println(forecast)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    } catch (e: HttpException) {
        println(AemetApi.getErrorResponseBody(e))
    }
}

suspend fun requestHourlyForecast(apiKey: String, cityCode: String) {
    try {
        AemetApi.endpoints.getHourlyForecatsByCity(apiKey, cityCode).also {
            try {
                println("Hourly forecast:")
                println(it)

                val json: String = NetworkUtils.downloadResourceStringFromUrl(
                    it.requestUrlData!!
                )

                println(json)

                val forecast: AemetHourlyForecastByCityResponse =
                    Json.decodeFromString<List<AemetHourlyForecastByCityResponse>>(json)
                        .first()

                println(forecast)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    } catch (e: HttpException) {
        println(AemetApi.getErrorResponseBody(e))
    }
}