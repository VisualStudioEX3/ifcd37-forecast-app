package com.visualstudioex3.ifcd37weatherforecast.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.visualstudioex3.ifcd37weatherforecast.screens.MunicipalityFinderNavigationRoute
import com.visualstudioex3.ifcd37weatherforecast.screens.MunicipalityFinderScreen
import com.visualstudioex3.ifcd37weatherforecast.screens.WeatherForecastNavigationRoute
import com.visualstudioex3.ifcd37weatherforecast.screens.WeatherForecastScreen

/**
 * Navigation manager.
 *
 * @param navController [NavHostController] instance.
 * @param modifier The [Modifier] to be applied to this layout node.
 */
@Composable
fun NavigationManager(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController,
        startDestination = MunicipalityFinderNavigationRoute,
        modifier = modifier.padding(16.dp)
    ) {
        composable<MunicipalityFinderNavigationRoute> {
            MunicipalityFinderScreen(navController)
        }
        composable<WeatherForecastNavigationRoute> {
            WeatherForecastScreen(navController)
        }
    }
}
