package com.visualstudioex3.apdater.aemet.weather.forecast.municipality.daily

import com.visualstudioex3.apdater.network.NetworkUtils
import com.visualstudioex3.apdater.network.exceptions.ForbiddenHttpRequestException
import com.visualstudioex3.apdater.network.exceptions.HttpRequestException
import com.visualstudioex3.apdater.network.exceptions.NotFoundHttpRequestException
import com.visualstudioex3.apdater.network.exceptions.TooManyRequestsHttpRequestException
import com.visualstudioex3.apdater.network.exceptions.UnauthorizedHttpRequestException
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

internal class AemetDailyWeatherForecastByMunicipalityRequestImplementation @Inject constructor(
    val retrofit: Retrofit
): AemetDailyWeatherForecastByMunicipalityRequest {
    override suspend fun invoke(
        requestBody: AemetDailyWeatherForecastByMunicipalityRequestBody
    ): AemetDailyWeatherForecastByMunicipalityResponse = try {
        retrofit.create<AemetDailyWeatherForecastByMunicipalityEndpoint>()
            .invoke(
                requestBody.apiKey,
                requestBody.municipalityCode
            ).let { response ->
                NetworkUtils.downloadResourceStringFromUrl(
                    response.requestUrlData!!
                )
            }.let { json ->
                Json.decodeFromString<List<AemetDailyWeatherForecastByMunicipalityResponse>>(
                    json
                ).first()
            }
        } catch (e: HttpException) {
            throw when (e.code()) {
                401 -> UnauthorizedHttpRequestException()
                403 -> ForbiddenHttpRequestException(e.message())
                404 -> NotFoundHttpRequestException(e.message())
                429 -> TooManyRequestsHttpRequestException(e.message())
                else -> HttpRequestException(e.code(), e.message())
            }
        }
}
