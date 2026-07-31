package org.example.forecast.usecases

import kotlinx.datetime.LocalDate
import org.example.aemet.models.responses.AemetDailyCityWeatherPredictionDetailData
import org.example.aemet.models.responses.AemetDailyForecastByCityResponse
import org.example.forecast.data.ForecastSkyStates
import org.example.forecast.data.ForecastUvRadiationIndexSeverityLevels
import org.example.forecast.data.ForecastWindDirections
import org.example.forecast.models.*
import org.example.forecast.requesthandlers.DailyForecastRequestHandler

/**
 * Daily forecast use case.
 */
class DailyForecastUseCase(
    // TODO: Use IoC with Hilt to resolve dependency
    val requestHandler: DailyForecastRequestHandler = DailyForecastRequestHandler()
) : IDailyForecastUseCase {
    override suspend fun invoke(
        request: ForecastRequest
    ): List<DailyForecastData> =
        digest(
            requestHandler.invoke(
                request
            )
        )

    private fun digest(
        response: AemetDailyForecastByCityResponse
    ): List<DailyForecastData> = response.data.day
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
        data: AemetDailyCityWeatherPredictionDetailData
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
        data: AemetDailyCityWeatherPredictionDetailData
    ) = getValidPeriod(data)
        .let { period ->
            DailyForecastData(
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
        data: AemetDailyCityWeatherPredictionDetailData
    ): LocalDate =
        data.date.date

    private fun extractSkyState(
        data: AemetDailyCityWeatherPredictionDetailData,
        period: String
    ): ForecastSkyStates = data.sky
        .first {
            it.period == period
        }.let { (value, _, _) ->
            ForecastSkyStates.entries
                .first {
                    it.id == value
                }
        }

    private fun extractTemperature(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = data.temperature.let {
        ForecastMinMaxTemperatureData(it.min, it.max)
    }

    private fun extractRainProbability(
        data: AemetDailyCityWeatherPredictionDetailData,
        period: String
    ): Int = data.rainProbability
        .first {
            it.period == period
        }.value

    private fun extractWindChild(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = data.windChill.let {
        ForecastMinMaxTemperatureData(it.min, it.max)
    }

    private fun extractWind(
        data: AemetDailyCityWeatherPredictionDetailData,
        period: String
    ): ForecastWindData = data.wind
        .first {
            it.period == period
        }.let { (direction, speed, _) ->
            ForecastWindData(
                direction = ForecastWindDirections.entries
                    .first {
                        it.id == direction
                    },
                speed = speed
            )
        }

    private fun extractMaxUvRadiation(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = (data.maxUvRadiation ?: 0)
        .let { maxUvIndex ->
            ForecastUvRadiationData(
                maxIndex = maxUvIndex,
                severityLevel = ForecastUvRadiationIndexSeverityLevels.entries
                    .first {
                        it.range.contains(maxUvIndex)
                    }
            )
        }

    private fun extractRelativeHumidity(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = data.relativeHumidity.let {
        ForecastRelativeHumidityData(it.min, it.max)
    }
}