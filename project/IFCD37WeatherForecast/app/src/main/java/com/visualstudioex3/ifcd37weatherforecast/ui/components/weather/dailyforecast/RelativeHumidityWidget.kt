package com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.dailyforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import com.visualstudioex3.application.ports.input.weather.forecast.models.MinMaxRelativeHumidityData

@Composable
fun RelativeHumidityWidget(
    weatherForecast: DailyWeatherForecastData
) {
    val relativeHumidity: MinMaxRelativeHumidityData = weatherForecast.relativeHumidity

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.WaterDrop,
            contentDescription = "WaterDrop",
            Modifier.size(32.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text("${relativeHumidity.max}%/${relativeHumidity.min}%")
    }
}
