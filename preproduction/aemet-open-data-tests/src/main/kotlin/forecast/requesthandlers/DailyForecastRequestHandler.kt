package org.example.forecast.requesthandlers

import kotlinx.serialization.json.Json
import org.example.NetworkUtils
import org.example.aemet.models.responses.AemetDailyForecastByCityResponse
import org.example.aemet.models.responses.AemetOpenDataResponse
import org.example.aemet.service.AemetApi
import org.example.forecast.models.ForecastRequest
import retrofit2.HttpException

/**
 * Daily forecast request handler.
 */
class DailyForecastRequestHandler(
    // TODO: Use IoC with Hilt to resolve dependency
): IDailyForecastRequestHandler {
    override suspend fun invoke(
        request: ForecastRequest
    ): AemetDailyForecastByCityResponse =
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
            return AemetApi.endpoints.getDailyForecatsByCity(
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
    ): AemetDailyForecastByCityResponse =
        Json.decodeFromString<List<AemetDailyForecastByCityResponse>>(
            json
        ).first()
}