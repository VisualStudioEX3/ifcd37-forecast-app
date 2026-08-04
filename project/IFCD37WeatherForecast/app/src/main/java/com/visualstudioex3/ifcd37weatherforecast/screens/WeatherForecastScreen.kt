package com.visualstudioex3.ifcd37weatherforecast.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.visualstudioex3.application.entities.Municipality
import com.visualstudioex3.ifcd37weatherforecast.ui.components.HeaderTitle
import com.visualstudioex3.ifcd37weatherforecast.viewmodels.WeatherForecastUiState
import com.visualstudioex3.ifcd37weatherforecast.viewmodels.WeatherForecastViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

/**
 * Navigation route object for [WeatherForecastNavigationRoute] screen.
 */
@Serializable
object WeatherForecastNavigationRoute

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

    LaunchedEffect(navController) {
        viewModel.viewModelScope.launch {
            viewModel.getWeatherForecast()
        }
    }

    Column(Modifier.fillMaxSize()) {
        HeaderTitle("Weather Forecast")

        if (uiState.loading) {
            Text("Loading...")
        } else
        {
            if (uiState.error) {
                Text("Error.")
            } else {
                Text(uiState.weatherForecast.toString())
            }
        }
    }
}
