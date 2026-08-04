package com.visualstudioex3.application.ports.output.weather.forecast.usecases

import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.daily.AemetDailyWeatherForecastByMunicipalityDataDetail
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.daily.AemetDailyWeatherForecastByMunicipalityRequest
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.daily.AemetDailyWeatherForecastByMunicipalityRequestBody
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.daily.AemetDailyWeatherForecastByMunicipalityResponse
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import com.visualstudioex3.application.ports.input.weather.forecast.models.MinMaxRelativeHumidityData
import com.visualstudioex3.application.ports.input.weather.forecast.models.MinMaxTemperatureData
import com.visualstudioex3.application.ports.input.weather.forecast.models.UvRadiationData
import com.visualstudioex3.application.ports.output.weather.forecast.models.WeatherForecastRequest
import com.visualstudioex3.application.ports.input.weather.forecast.models.WindData
import com.visualstudioex3.application.values.weather.forecast.SkyStates
import com.visualstudioex3.application.values.weather.forecast.UvRadiationIndexSeverityLevels
import com.visualstudioex3.application.values.weather.forecast.WindDirections
import kotlinx.datetime.LocalDate
import javax.inject.Inject

internal class DailyWeatherForecastUseCaseImplementation @Inject constructor(
    val apiRequest: AemetDailyWeatherForecastByMunicipalityRequest
) : DailyWeatherForecastUseCase {
    override suspend fun invoke(
        request: WeatherForecastRequest
    ): List<DailyWeatherForecastData> = request.let {
        AemetDailyWeatherForecastByMunicipalityRequestBody(
            apiKey = it.apiKey,
            municipalityCode = it.municipality.code
        )
    }.let { requestBody ->
        apiRequest.invoke(requestBody)
    }.let { response ->
        digest(response)
    }

    private fun digest(
        response: AemetDailyWeatherForecastByMunicipalityResponse
    ): List<DailyWeatherForecastData> = response.data.day
        .map {
            extractForecastData(it)
        }

    /*
     * FYI: AEMET OpenData API returns all periods in the response despite the issue where more of them doesn't had data
     * because are behind the current forecast time elaboration. In addition, the latest forecast days return some
     * periods instead, and the last 3 days only return one period but without period value.
     *
     * This function solve this issue filtering one of the lists organized by periods, in this case used the sky states
     * list, removing all periods without data, and trying to get the first one by period. In case of not found one
     * valid period, we assumed that was one of the last 3 forecast days and then used empty string as period value.
     *
     * This value is used later to get the data from sky states, rain probabilities and wind lists.
     */
    private fun getValidPeriod(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail
    ): String = data.sky
        .filter {
            // Expected daily forecast periods based on results analized during 48h:
            listOf(
                // Full day period.
                // Should be the default period for daily forecasts and for the latest partial forecast days:
                "00-24",
                // First half-day period:
                "00-12",
                // Second half-day period.
                // Second default period and expected one from 12:00 PM:
                "12-24",
                // Last quarter-day period.
                // Third default period and should be the expected one from 18h until midnight:
                "18-24",
                // Full day period for the last 3 forecast days, where the period list only returns one single period
                // data but with period value empty:
                ""
            ).contains(it.period) &&
                    it.value.isNotEmpty() // Discard periods with empty values.
        }.map {
            it.period
        }.firstOrNull() ?: ""

    private fun extractForecastData(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail
    ) = getValidPeriod(data)
        .let { period ->
            DailyWeatherForecastData(
                date = extractDate(data),
                skyState = extractSkyState(data, period),
                temperature = extractTemperature(data),
                rainProbability = extractRainProbability(data, period),
                windChill = extractWindChild(data),
                wind = extractWind(data, period),
                uvMaxRadiation = extractMaxUvRadiation(data),
                relativeHumidity = extractRelativeHumidity(data),
            )
        }

    private fun extractDate(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail
    ): LocalDate =
        data.date.date

    private fun extractSkyState(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail,
        period: String
    ): SkyStates = data.sky
        .first {
            it.period == period
        }.let { (value, _, _) ->
            SkyStates.entries
                .first {
                    it.id == value
                }
        }

    private fun extractTemperature(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail
    ) = data.temperature.let {
        MinMaxTemperatureData(it.min, it.max)
    }

    private fun extractRainProbability(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail,
        period: String
    ): Int = data.rainProbability
        .first {
            it.period == period
        }.value

    private fun extractWindChild(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail
    ) = data.windChill.let {
        MinMaxTemperatureData(it.min, it.max)
    }

    private fun extractWind(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail,
        period: String
    ): WindData = data.wind
        .first {
            it.period == period
        }.let { (direction, speed, _) ->
            WindData(
                direction = WindDirections.entries
                    .first {
                        it.id == direction
                    },
                speed = speed
            )
        }

    private fun extractMaxUvRadiation(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail
    ) = (data.maxUvRadiation ?: 0)
        .let { maxUvIndex ->
            UvRadiationData(
                maxIndex = maxUvIndex,
                severityLevel = UvRadiationIndexSeverityLevels.entries
                    .first {
                        it.range.contains(maxUvIndex)
                    }
            )
        }

    private fun extractRelativeHumidity(
        data: AemetDailyWeatherForecastByMunicipalityDataDetail
    ) = data.relativeHumidity.let {
        MinMaxRelativeHumidityData(it.min, it.max)
    }
}
