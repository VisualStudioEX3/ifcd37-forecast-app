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
import org.example.forecast.requesthandlers.IHourlyForecastRequestHandler

/**
 * Daily forecast use case.
 */
class HourlyForecastUseCase(
    // TODO: Use IoC with Hilt to resolve dependency
    val requestHandler: IHourlyForecastRequestHandler = HourlyForecastRequestHandler()
) : IHourlyForecastUseCase {
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
        date = extractForecastDate(data),
        data = extractForecastDataDetails(data)
    )

    private fun extractForecastDataDetails(
        data: AemetHourlyCityWeatherPredictionDetailData
    ): List<HourlyForecastDataDetail> = processAndCacheWindData(data)
        .let { cachedWindData ->
            data.temperature // Extract available hours from one of the lists:
                .map {
                    extractForecastDataDetail(it.period, data, cachedWindData)
                }
        }

    private fun extractForecastDataDetail(
        hour: Int,
        data: AemetHourlyCityWeatherPredictionDetailData,
        cachedWindData: List<AemetWindData>
    ) = HourlyForecastDataDetail(
        hour = hour,
        skyState = extractSkyState(hour, data),
        temperature = extractTemperature(hour, data),
        rain = extractRain(hour, data),
        windChill = extractWindChild(hour, data),
        wind = extractWindFromCache(hour, cachedWindData),
        relativeHumidity = extractRelativeHumidity(hour, data),
    )

    private fun extractForecastDate(
        data: AemetHourlyCityWeatherPredictionDetailData
    ): LocalDate =
        data.date.date

    private fun extractSkyState(
        hour: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ) = ForecastSkyStates.entries
        .first {
            it.id == data.sky.first { e ->
                e.period.toInt() == hour
            }.value
        }

    private fun extractTemperature(
        hour: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ): Int = data.temperature.first {
        it.period == hour
    }.value

    private fun extractRain(
        hour: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ): Int = data.rain.first {
        it.period.toInt() == hour
    }.value

    private fun extractWindChild(
        hour: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ): Int = data.windChill.first {
        it.period == hour
    }.value

    private fun extractWindFromCache(
        hour: Int,
        cachedWindData: List<AemetWindData>
    ) = cachedWindData.first {
        it.period.toInt() == hour
    }.let {
        ForecastWindData(
            direction = ForecastWindDirections.entries
                .first { e ->
                    e.id == it.direction
                },
            speed = it.speed
        )
    }

    private fun processAndCacheWindData(
        data: AemetHourlyCityWeatherPredictionDetailData
    ): List<AemetWindData> = data.windAndMaxWindGust
        .filter { // Discard { value: 0, periodo: "" } objects:
            it.value == null &&
                    it.direction != null &&
                    it.speed != null
        }.map {
            AemetWindData(
                it.direction!!.first(),
                it.speed!!.first(),
                it.period.toString()
            )
        }

    private fun extractRelativeHumidity(
        hour: Int,
        data: AemetHourlyCityWeatherPredictionDetailData
    ): Int = data.relativeHumidity.first {
        it.period == hour
    }.value
}