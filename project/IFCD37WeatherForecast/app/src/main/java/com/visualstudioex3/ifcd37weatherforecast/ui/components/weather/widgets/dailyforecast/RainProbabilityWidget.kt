package com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.dailyforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData

@Composable
fun RainProbabilityWidget(
    weatherForecast: DailyWeatherForecastData
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Cloud,
            contentDescription = "Cloud",
            Modifier.size(32.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text("${weatherForecast.rainProbability}%")
    }
}
