package com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.dailyforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import com.visualstudioex3.application.ports.input.weather.forecast.models.MinMaxTemperatureData

@Composable
fun WindChillWidget(
    weatherForecast: DailyWeatherForecastData
) {
    val windChill: MinMaxTemperatureData = weatherForecast.windChill

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Accessibility,
            contentDescription = "Accessibility",
            Modifier.size(32.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text("${windChill.max}º/${windChill.min}º")
    }
}
