package org.example.forecast.requesthandlers

import kotlinx.serialization.json.Json
import org.example.NetworkUtils
import org.example.aemet.models.responses.AemetDailyForecastByCityResponse
import org.example.aemet.models.responses.AemetOpenDataResponse
import org.example.aemet.service.AemetApi
import org.example.forecast.exceptions.*
import org.example.forecast.models.ForecastRequest
import retrofit2.HttpException

/**
 * Daily forecast request handler.
 */
class DailyForecastRequestHandler : IDailyForecastRequestHandler {
    override suspend fun invoke(
        request: ForecastRequest
    ): AemetDailyForecastByCityResponse =
        deserializeData(
            downloadData(
                sendRequest(
                    request
                )
            )
        )

    private suspend fun sendRequest(
        request: ForecastRequest
    ): AemetOpenDataResponse =
        try {
            AemetApi.endpoints.getDailyForecatsByCity(
                request.apiKey,
                request.cityCode
            )
        } catch (e: HttpException) {
            AemetApi.getErrorResponseBody(e).run {
                throw when (e.code()) {
                    401 -> UnauthorizedException(e.message())
                    403 -> ForbiddenException(e.message())
                    404 -> NotFoundException(e.message())
                    429 -> TooManyRequestsException(e.message())
                    else -> HttpRequestException(e.code(), e.message())
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