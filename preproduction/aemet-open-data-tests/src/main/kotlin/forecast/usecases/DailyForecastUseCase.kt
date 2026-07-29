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
    ): ForecastSkyStates {
        val periodValue: String = data.sky
            .firstOrNull {
                it.period == "00-24" &&
                        it.value.isNotEmpty()
            }?.value
            ?: data.sky.first {
                it.value.isNotEmpty()
            }.value
        val skyState = ForecastSkyStates.valueOf(periodValue)

        return skyState
    }

    private fun extractTemperature(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = data.temperature.let {
        ForecastMinMaxTemperatureData(it.min, it.max)
    }

    private fun extractRainProbability(
        data: AemetDailyCityWeatherPredictionDetailData
    ): Int {
        val rainProbability: Int = data.rainProbability
            .first {
                it.period == "00-24"
            }.value

        return rainProbability
    }

    private fun extractWindChild(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = data.windChill.let {
        ForecastMinMaxTemperatureData(it.min, it.max)
    }

    private fun extractWind(
        data: AemetDailyCityWeatherPredictionDetailData
    ): ForecastWindData {
        val windPrediction = data.wind
            .first {
                it.period == "00-24"
            }

        return ForecastWindData(
            direction = ForecastWindDirections.valueOf(windPrediction.direction),
            speed = windPrediction.velocity
        )
    }

    private fun extractMaxUvRadiation(
        data: AemetDailyCityWeatherPredictionDetailData
    ): Int =
        data.maxUvRadiation!!

    private fun extractRelativeHumidity(
        data: AemetDailyCityWeatherPredictionDetailData
    ) = data.relativeHumidity.let {
        ForecastRelativeHumidityData(it.min, it.max)
    }
}