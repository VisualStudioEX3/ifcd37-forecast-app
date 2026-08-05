package com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import com.visualstudioex3.application.ports.input.weather.forecast.models.WindData

@Composable
fun WindStateWidget(
    weatherForecast: DailyWeatherForecastData
) {
    val wind: WindData = weatherForecast.wind

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Icon(
            Icons.Default.WindPower,
            contentDescription = "WindPower",
            Modifier.size(32.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text("${wind.direction.id} - ${wind.speed} km/h")
    }
}
