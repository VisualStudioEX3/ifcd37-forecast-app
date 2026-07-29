package org.example.forecast.requesthandlers

import kotlinx.serialization.json.Json
import org.example.NetworkUtils
import org.example.aemet.models.responses.AemetHourlyForecastByCityResponse
import org.example.aemet.models.responses.AemetOpenDataResponse
import org.example.aemet.service.AemetApi
import org.example.forecast.models.ForecastRequest
import retrofit2.HttpException

/**
 * Hourly forecast request handler.
 */
class HourlyForecastRequestHandler(
    // TODO: Use IoC with Hilt to resolve dependency
): IHourlyForecastRequestHandler {
    override suspend fun invoke(
        request: ForecastRequest
    ): AemetHourlyForecastByCityResponse =
        deserializeData(
            downloadData(
                launchRequest(
                    request
                )
            )
        )

    private suspend fun launchRequest(
        request: ForecastRequest
    ): AemetOpenDataResponse {
        try {
            return AemetApi.endpoints.getHourlyForecatsByCity(
                request.apiKey,
                request.cityCode
            )
        } catch (e: HttpException) {
            AemetApi.getErrorResponseBody(e).run {
                error(description)
            }
        }
    }

    private fun downloadData(
        response: AemetOpenDataResponse
    ): String = NetworkUtils.downloadResourceStringFromUrl(
        response.requestUrlData!!
    )

    private fun deserializeData(
        json: String
    ): AemetHourlyForecastByCityResponse =
        Json.decodeFromString<List<AemetHourlyForecastByCityResponse>>(
            json
        ).first()
}