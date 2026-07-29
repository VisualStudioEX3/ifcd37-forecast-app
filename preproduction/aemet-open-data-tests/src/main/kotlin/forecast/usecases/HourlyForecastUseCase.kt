package org.example.forecast.usecases

import kotlinx.datetime.LocalDate
import org.example.aemet.models.data.AemetWindData
import org.example.aemet.models.responses.AemetHourlyCityWeatherPredictionDetailData
import org.example.aemet.models.responses.AemetHourlyForecastByCityResponse
import org.example.forecast.data.ForecastSkyStates
import org.example.forecast.data.ForecastWindDirections
import org.example.forecast.models.ForecastRequest
import org.example.forecast.models.ForecastWindData
import org.example.forecast.models.HourlyForecastData
import org.example.forecast.models.HourlyForecastDataDetail
import org.example.forecast.requesthandlers.HourlyForecastRequestHandler

/**
 * Daily forecast use case.
 */
class HourlyForecastUseCase(
    // TODO: Use IoC with Hilt to resolve dependency
    val requestHandler: HourlyForecastRequestHandler = HourlyForecastRequestHandler()
) : IHourlyForecastUseCase {
    private var cacheWindData: List<AemetWindData>? = null

    override suspend fun invoke(
        request: ForecastRequest
    ): List<HourlyForecastData> =
        digest(
            requestHandler.invoke(
                request
            )
        )

    private fun digest(
        response: AemetHourlyForecastByCityResponse
    ): List<HourlyForecastData> = response.data.day.map {
        extractForecastData(it)
    }

    private fun extractForecastData(
        data: AemetHourlyCityWeatherPredictionDetailData
    ) = HourlyForecastData(
        date = extractDate(data),
        data = extractForecastDataDetails(data)
    )

    private fun extractForecastDataDetails(
        data: AemetHourlyCityWeatherPredictionDetailData
    ): List<HourlyForecastDataDetail> {
        val details = mutableListOf<HourlyForecastDataDetail>()

        repeat(data.temperature.size) { idx ->
            details.add(extractForecastDataDetail(idx, data))
        }

        return details
    }

    private fun extractForecastDataDetail(
        periodIndex: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ) = HourlyForecastDataDetail(
        hour = extractHour(periodIndex, data),
        skyState = extractSkyState(periodIndex, data),
        temperature = extractTemperature(periodIndex, data),
        rain = extractRain(periodIndex, data),
        windChill = extractWindChild(periodIndex, data),
        wind = extractWind(periodIndex, data),
        relativeHumidity = extractRelativeHumidity(periodIndex, data),
    )

    private fun extractDate(
        data: AemetHourlyCityWeatherPredictionDetailData
    ): LocalDate =
        data.date.date

    private fun extractHour(
        periodIndex: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ): Int = data.temperature[periodIndex].period

    private fun extractSkyState(
        periodIndex: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ) = ForecastSkyStates.valueOf(data.sky[periodIndex].value)

    private fun extractTemperature(
        periodIndex: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ): Int = data.temperature[periodIndex].value

    private fun extractRain(
        periodIndex: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ): Int = data.rain[periodIndex].value

    private fun extractWindChild(
        periodIndex: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ): Int = data.windChill[periodIndex].value

    private fun extractWind(
        periodIndex: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ) = getCachedWindData(data)[periodIndex].let {
        ForecastWindData(
            direction = ForecastWindDirections.valueOf(it.direction),
            speed = it.velocity
        )
    }

    private fun getCachedWindData(
        data: AemetHourlyCityWeatherPredictionDetailData
    ): List<AemetWindData> {
        if (cacheWindData == null) {
            cacheWindData = data.windAndMaxWindGust
                .filterNot {
                    it.value == null
                }.map {
                    AemetWindData(
                        it.direction!!.first(),
                        it.velocity!!.first(),
                        ""
                    )
                }
        }

        return cacheWindData!!
    }

    private fun extractRelativeHumidity(
        periodIndex: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ): Int = data.relativeHumidity[periodIndex].value
}