package com.visualstudioex3.ifcd37weatherforecast.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.visualstudioex3.application.ports.input.weather.forecast.models.DailyWeatherForecastData
import com.visualstudioex3.ifcd37weatherforecast.ui.components.AnimatedCircularProgress
import com.visualstudioex3.ifcd37weatherforecast.ui.components.HeaderTitle
import com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.MunicipalityWidget
import com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.SkyStateWidget
import com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.WindStateWidget
import com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.dailyforecast.ForecastDateWidget
import com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.dailyforecast.MaxMinRelativeHumidityWidget
import com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.dailyforecast.MaxMinTemperatureWidget
import com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.dailyforecast.MaxMinWindChillWidget
import com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.dailyforecast.RainProbabilityWidget
import com.visualstudioex3.ifcd37weatherforecast.ui.components.weather.widgets.dailyforecast.UvMaxRadiationWidget
import com.visualstudioex3.ifcd37weatherforecast.viewmodels.WeatherForecastUiState
import com.visualstudioex3.ifcd37weatherforecast.viewmodels.WeatherForecastViewModel

/**
 * Weather forecast screen.
 *
 * @param navController Navigation controller.
 * @param viewModel [WeatherForecastViewModel] view model. It's resolved by Hilt.
 */
@Composable
fun WeatherForecastScreen(
    navController: NavHostController,
    viewModel: WeatherForecastViewModel = hiltViewModel()
) {
    val uiState: WeatherForecastUiState by viewModel.uiState.collectAsState()

    Column(Modifier.fillMaxSize()) {
        HeaderTitle("Weather Forecast")

        if (uiState.loading) {
            AnimatedCircularProgress()
        } else {
            if (uiState.success) {
                MunicipalityWidget(uiState)

                // Shows only today forecast:
                val today: DailyWeatherForecastData = uiState.weatherForecast!!.daily.first()

                SkyStateWidget(today)
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ForecastDateWidget(today)
                    Spacer(Modifier.width(16.dp))
                    MaxMinTemperatureWidget(today)
                }
                Spacer(Modifier.height(16.dp))
                Card(
                    Modifier.fillMaxWidth()
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                64.dp,
                                alignment = Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RainProbabilityWidget(today)
                            MaxMinWindChillWidget(today)
                            WindStateWidget(today)
                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                64.dp,
                                alignment = Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            UvMaxRadiationWidget(today)
                            MaxMinRelativeHumidityWidget(today)
                        }
                    }
                }
            } else {
                Text("Error!")
            }
        }
    }
}
