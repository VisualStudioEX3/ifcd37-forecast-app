package com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.dailyforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import com.visualstudioex3.application.ports.input.weather.forecast.models.MinMaxTemperatureData

@Composable
fun TemperatureWidget(
    weatherForecast: DailyWeatherForecastData
) {
    val temperature: MinMaxTemperatureData = weatherForecast.temperature

    Column {
        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TemperatureWidgetData(temperature.max, "max")
            VerticalDivider(Modifier
                .padding(horizontal = 16.dp)
                .height(100.dp)
            )
            TemperatureWidgetData(temperature.min, "min")
        }
    }
}

@Composable
private fun TemperatureWidgetData(
    temperature: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "${temperature}º",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
