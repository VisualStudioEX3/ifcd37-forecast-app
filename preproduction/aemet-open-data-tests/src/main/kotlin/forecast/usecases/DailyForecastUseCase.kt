package org.example.forecast.usecases

import kotlinx.datetime.LocalDate
import org.example.TimeUtils
import org.example.aemet.models.responses.AemetDailyCityWeatherPredictionDetailData
import org.example.aemet.models.responses.AemetDailyForecastByCityResponse
import org.example.forecast.data.ForecastSkyStates
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

    private fun <TPeriod> getPeriodOrSingle(
        list: List<TPeriod>,
        keySelector: (TPeriod) -> String
    ): TPeriod = list
        .associateBy(keySelector)
        .let {
            when (TimeUtils.now().hour) {
                in 0..11 -> it["00-24"] ?: it["00-12"]
                in 12..17 -> it["12-24"] ?: it["00-24"]
                else -> it["18-24"] ?: it["00-24"]
            } ?: it.entries.single().value
        }

    private fun extractForecastData(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = DailyForecastData(
        date = extractDate(data),
        skyState = extractSkyState(data),
        temperature = extractTemperature(data),
        rainProbability = extractRainProbability(data),
        windChill = extractWindChild(data),
        wind = extractWind(data),
        uvMaxRadiation = extractMaxUvRadiation(data),
        relativeHumidity = extractRelativeHumidity(data),
    )

    private fun extractDate(
        data: AemetDailyCityWeatherPredictionDetailData
    ): LocalDate =
        data.date.date

    private fun extractSkyState(
        data: AemetDailyCityWeatherPredictionDetailData
    ): ForecastSkyStates = getPeriodOrSingle(
        list = data.sky,
        keySelector = {
            it.period
        }
    ).let { (value, _, _) ->
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
        data: AemetDailyCityWeatherPredictionDetailData
    ): Int = getPeriodOrSingle(
        list = data.rainProbability,
        keySelector = {
            it.period
        }
    ).value

    private fun extractWindChild(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = data.windChill.let {
        ForecastMinMaxTemperatureData(it.min, it.max)
    }

    private fun extractWind(
        data: AemetDailyCityWeatherPredictionDetailData
    ): ForecastWindData = getPeriodOrSingle(
        list = data.wind,
        keySelector = {
            it.period
        }
    ).let { (direction, speed, _) ->
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
    ): Int =
        data.maxUvRadiation ?: 0

    private fun extractRelativeHumidity(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = data.relativeHumidity.let {
        ForecastRelativeHumidityData(it.min, it.max)
    }
}