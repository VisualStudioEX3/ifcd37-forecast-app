package com.visualstudioex3.ifcd37weatherforecast.navigation

import com.visualstudioex3.application.entities.Municipality
import kotlinx.serialization.Serializable

/**
 * Navigation routes.
 */
sealed class NavigationRoutes {
    /**
     * Municipality search screen route.
     */
    @Serializable
    data object MunicipalitySearchRoute: NavigationRoutes()

    /**
     * Weather forecast screen route.
     *
     * @param municipality Municipality to request the weather forecast.
     */
    @Serializable
    data class WeatherForecastRoute(
        val municipality: Municipality
    ): NavigationRoutes()
}
