package com.visualstudioex3.ifcd37weatherforecast.ui.components.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.visualstudioex3.application.entities.Municipality
import com.visualstudioex3.ifcd37weatherforecast.viewmodels.WeatherForecastUiState

@Composable
fun MunicipalityWidget(
    uiState: WeatherForecastUiState
) {
    val municipality: Municipality = uiState.weatherForecast!!.municipality

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Absolute.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.LocationOn,
            contentDescription = "Search",
            Modifier.size(48.dp)
        )
        Spacer(Modifier.width(4.dp))
        Column {
            Text(
                municipality.name,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                "${municipality.province}, ${municipality.autonomousCommunity}",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(Modifier.width(24.dp))
    }
}
