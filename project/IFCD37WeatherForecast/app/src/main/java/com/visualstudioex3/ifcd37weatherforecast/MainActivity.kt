package com.visualstudioex3.ifcd37weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.visualstudioex3.ifcd37weatherforecast.navigation.NavigationManager
import com.visualstudioex3.ifcd37weatherforecast.ui.theme.IFCD37WeatherForecastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IFCD37WeatherForecastTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationManager(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
