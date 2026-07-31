package org.example

import org.example.forecast.ISpainForecastService
import org.example.forecast.SpainForecastService
import org.example.forecast.models.DailyForecastData
import org.example.forecast.models.ForecastResponse
import org.example.forecast.models.HourlyForecastData
import org.example.forecast.models.HourlyForecastDataDetail

suspend fun main() {
    val service: ISpainForecastService = SpainForecastService()
    val cityCode: String = requestCityCode()
    val response: ForecastResponse = service.getForecastByCity(cityCode)

    printForecast(response)
}

fun requestCityCode(): String {
    print("Enter a valid INE code for a city (CPRO + CMUN, example: '28058' for Fuenlabrada, Comunidad de Madrid): ")

    return readln()
}

fun printForecast(response: ForecastResponse) {
    printTodayForecast(response)
    printNowForecast(response)
}

fun printTodayForecast(response: ForecastResponse) {
    val today: DailyForecastData = response.daily.first()

    println("Forecast for today ${today.date}:")
    println("- Sky state: ${today.skyState}")
    println("- Temperature: (max: ${today.temperature.max}º, min: ${today.temperature.min}º)")
    println("- Rain probability: ${today.rainProbability}%")
    println("- Wind chill: (max: ${today.windChill.max}º, min: ${today.windChill.min}º)")
    println("- Wind: (Direction: ${today.wind.direction}, speed: ${today.wind.speed}km/h)")
    println("- Ultraviolet max radiation: ${today.uvMaxRadiation.maxIndex} (${today.uvMaxRadiation.severityLevel})")
    println("- Relative humidity: (max: ${today.relativeHumidity.max}mm, min: ${today.relativeHumidity.min}mm)")
}

fun printNowForecast(response: ForecastResponse) {
    val today: HourlyForecastData = response.hourly.first()
    val now: HourlyForecastDataDetail = today.data.first {
        it.hour == TimeUtils.now().hour
    }

    println("Forecast for today ${today.date} at ${now.hour}:00h:")
    println("- Sky state: ${now.skyState}")
    println("- Temperature: ${now.temperature}º")
    println("- Rain: ${now.rain}mm")
    println("- Wind chill: ${now.windChill}º")
    println("- Wind: (Direction: ${now.wind.direction}, speed: ${now.wind.speed}km/h)")
    println("- Relative humidity: ${now.relativeHumidity}mm)")
}
