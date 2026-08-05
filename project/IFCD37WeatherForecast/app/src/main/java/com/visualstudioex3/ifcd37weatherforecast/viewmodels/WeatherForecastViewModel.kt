package com.visualstudioex3.ifcd37weatherforecast.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualstudioex3.application.entities.Municipality
import com.visualstudioex3.application.entities.WeatherForecast
import com.visualstudioex3.application.ports.input.weather.forecast.WeatherForecastService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * UI state for [WeatherForecastViewModel] viewmodel.
 *
 * @param loading Gets if the viewmodel is loading requested weather forecast data.
 * @param weatherForecast The weather forecast data requested.
 * @param success Gets if the weather forecast request failed.
 */
data class WeatherForecastUiState(
    val loading: Boolean = false,
    val weatherForecast: WeatherForecast? = null,
    val success: Boolean = false
)

/**
 * Weather forecast viewmodel.
 *
 * @param weatherForecastService [WeatherForecastService] service. It's resolved by Hilt.
 */
@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val weatherForecastService: WeatherForecastService
): ViewModel() {
    private val _uiState = MutableStateFlow(WeatherForecastUiState())

    /**
     * UI state for this viewmodel.
     */
    val uiState: StateFlow<WeatherForecastUiState> = _uiState.asStateFlow()

    init {
        val municipality = savedStateHandle.get<Municipality>("municipality")
            ?: error("Expected Municipality object from SavedStateHandle.")

        getWeatherForecast(municipality)
    }

    /**
     * Gets the weather forecast for the given municipality.
     */
    fun getWeatherForecast(
        municipality: Municipality
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var result: WeatherForecast? = null

                _uiState.update { currentState ->
                    currentState.copy(
                        loading = true,
                        weatherForecast = null,
                        success = false
                    )
                }

                try {
                    result = weatherForecastService.getWeatherForecast(municipality)
                } catch (e: Exception) {
                    Log.e("weather_forecast", "$e")
                } finally {
                    _uiState.update { currentState ->
                        currentState.copy(
                            loading = false,
                            weatherForecast = result,
                            success = result != null
                        )
                    }
                }
            }
        }
    }
}
