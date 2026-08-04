package com.visualstudioex3.application.ports.output.weather.forecast.usecases

import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.hourly.AemetHourlyWeatherForecastByMunicipalityDataDetail
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.hourly.AemetHourlyWeatherForecastByMunicipalityRequest
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.hourly.AemetHourlyWeatherForecastByMunicipalityRequestBody
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.hourly.AemetHourlyWeatherForecastByMunicipalityResponse
import com.visualstudioex3.apdater.aemet.weather.forecast.municipality.models.AemetWindData
import com.visualstudioex3.application.ports.input.weather.forecast.models.HourlyWeatherForecastData
import com.visualstudioex3.application.ports.input.weather.forecast.models.HourlyWeatherForecastDataDetail
import com.visualstudioex3.application.ports.output.weather.forecast.models.WeatherForecastRequest
import com.visualstudioex3.application.ports.input.weather.forecast.models.WindData
import com.visualstudioex3.application.values.weather.forecast.SkyStates
import com.visualstudioex3.application.values.weather.forecast.WindDirections
import kotlinx.datetime.LocalDate
import javax.inject.Inject

internal class HourlyWeatherForecastUseCaseImplementation @Inject constructor(
    val apiRequest: AemetHourlyWeatherForecastByMunicipalityRequest
) : HourlyWeatherForecastUseCase {
    override suspend fun invoke(
        request: WeatherForecastRequest
    ): List<HourlyWeatherForecastData> = request.let {
        AemetHourlyWeatherForecastByMunicipalityRequestBody(
            apiKey = it.apiKey,
            municipalityCode = it.municipality.code
        )
    }.let { requestBody ->
        apiRequest.invoke(requestBody)
    }.let { response ->
        digest(response)
    }

    private fun digest(
        response: AemetHourlyWeatherForecastByMunicipalityResponse
    ): List<HourlyWeatherForecastData> = response.data.day.map {
        extractForecastData(it)
    }

    private fun extractForecastData(
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail
    ) = HourlyWeatherForecastData(
        date = extractForecastDate(data),
        data = extractForecastDataDetails(data)
    )

    private fun extractForecastDataDetails(
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail
    ): List<HourlyWeatherForecastDataDetail> = processAndCacheWindData(data)
        .let { cachedWindData ->
            data.temperature // Extract available hours from one of the lists:
                .map {
                    extractForecastDataDetail(it.period, data, cachedWindData)
                }
        }

    private fun extractForecastDataDetail(
        hour: Int,
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail,
        cachedWindData: List<AemetWindData>
    ) = HourlyWeatherForecastDataDetail(
        hour = hour,
        skyState = extractSkyState(hour, data),
        temperature = extractTemperature(hour, data),
        rain = extractRain(hour, data),
        windChill = extractWindChild(hour, data),
        wind = extractWindFromCache(hour, cachedWindData),
        relativeHumidity = extractRelativeHumidity(hour, data),
    )

    private fun extractForecastDate(
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail
    ): LocalDate =
        data.date.date

    private fun extractSkyState(
        hour: Int,
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail
    ) = SkyStates.entries
        .first {
            it.id == data.sky.first { e ->
                e.period.toInt() == hour
            }.value
        }

    private fun extractTemperature(
        hour: Int,
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail
    ): Int = data.temperature.first {
        it.period == hour
    }.value

    private fun extractRain(
        hour: Int,
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail
    ): Int = data.rain.first {
        it.period.toInt() == hour
    }.value

    private fun extractWindChild(
        hour: Int,
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail
    ): Int = data.windChill.first {
        it.period == hour
    }.value

    private fun extractWindFromCache(
        hour: Int,
        cachedWindData: List<AemetWindData>
    ) = cachedWindData.first {
        it.period.toInt() == hour
    }.let {
        WindData(
            direction = WindDirections.entries
                .first { e ->
                    e.id == it.direction
                },
            speed = it.speed
        )
    }

    private fun processAndCacheWindData(
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail
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
        data: AemetHourlyWeatherForecastByMunicipalityDataDetail
    ): Int = data.relativeHumidity.first {
        it.period == hour
    }.value
}
