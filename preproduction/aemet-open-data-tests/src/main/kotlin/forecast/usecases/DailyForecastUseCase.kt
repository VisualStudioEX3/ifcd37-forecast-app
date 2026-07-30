package org.example.forecast.usecases

import kotlinx.datetime.LocalDate
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

    /*
     * Because sometimes AEMET API returns the full period list but some periods with empty values,
     * this function allow to look first for the expected "00-24" period data, and, if not was available,
     * look for the "12-24" period data, and last look for the first period with data.
    */
    private fun <TPeriod> getPeriodOrDefault(
        list: List<TPeriod>,
        predicate: (TPeriod) -> Pair<TPeriod, String>,
        filter: (TPeriod) -> Boolean = { true } // Is not empty string, zero value, etc...
    ): TPeriod = list.map {
        predicate(it)
    }.run {
        // First try, get first full day period 00-24h:
        firstOrNull { (value, period) ->
            period == "00-24" && filter(value)
        }
        // Second try, get second half-day period 12-24h:
        firstOrNull { (value, period) ->
            period == "12-24" && filter(value)
        }
        // Last try, get the first value with data.
        // FYI: Latest 3 day forecasts only had single element without period value.
        // They are the same period as 00-24h:
            ?: first { (value, _) ->
                filter(value)
            }
    }.let { (value, _) ->
        value
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
    ): ForecastSkyStates = getPeriodOrDefault(
        list = data.sky,
        predicate = {
            Pair(it, it.period)
        },
        filter = { (value, _, _) ->
            value.isNotEmpty()
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
    ): Int = getPeriodOrDefault(
        list = data.rainProbability,
        predicate = {
            Pair(it, it.period)
        }
    ).value
    
    private fun extractWindChild(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = data.windChill.let {
        ForecastMinMaxTemperatureData(it.min, it.max)
    }

    private fun extractWind(
        data: AemetDailyCityWeatherPredictionDetailData
    ): ForecastWindData = getPeriodOrDefault(
        list = data.wind,
        predicate = {
            Pair(it, it.period)
        },
        filter = { (direction, _, _) ->
            direction.isNotEmpty()
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