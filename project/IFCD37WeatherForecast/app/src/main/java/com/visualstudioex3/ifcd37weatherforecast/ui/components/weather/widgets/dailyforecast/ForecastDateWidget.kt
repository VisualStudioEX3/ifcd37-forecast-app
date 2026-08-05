package com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.dailyforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import kotlinx.datetime.LocalDate

@Composable
fun ForecastDateWidget(
    weatherForecast: DailyWeatherForecastData
) {
    val date: LocalDate = weatherForecast.date

    Column {
        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Today,
                contentDescription = "Today",
                Modifier.size(32.dp)
            )
            Spacer(Modifier.width(4.dp))
            Column {
                Text(
                    "${date.dayOfWeek.name} ${date.day}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    date.month.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(Modifier.width(24.dp))
        }
    }
}
