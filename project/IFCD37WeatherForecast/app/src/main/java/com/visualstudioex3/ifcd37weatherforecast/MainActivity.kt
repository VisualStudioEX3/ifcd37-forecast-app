package com.visualstudioex3.ifcd37weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.visualstudioex3.application.ports.input.MunicipalityFinder
import com.visualstudioex3.ifcd37weatherforecast.ui.theme.IFCD37WeatherForecastTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var municipalityFinder: MunicipalityFinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IFCD37WeatherForecastTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Fuenla",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        val municipalities = remember {
            municipalityFinder.findMunicipalities(name)
        }

        for (municipality in municipalities) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "${municipality.name} " +
                            "(${municipality.province}, ${municipality.autonomousCommunity})",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
