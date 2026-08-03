package com.visualstudioex3.ifcd37weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.visualstudioex3.application.SecretsService
import com.visualstudioex3.application.SecretsServiceImplementation
import com.visualstudioex3.ifcd37weatherforecast.ui.theme.IFCD37WeatherForecastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IFCD37WeatherForecastTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val secretsService: SecretsService =
        SecretsServiceImplementation(LocalContext.current)
    val apiKey: String = remember {
        secretsService.getString("AEMET_API_KEY")
            ?: error("Secret key not found!")
    }

    Text(
        text = "AEMET_API_KEY: $apiKey",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IFCD37WeatherForecastTheme {
        Greeting("Android")
    }
}
