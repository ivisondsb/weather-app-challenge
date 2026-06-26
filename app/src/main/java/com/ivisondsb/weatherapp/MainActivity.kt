package com.ivisondsb.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivisondsb.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun WeatherApp(modifier: Modifier = Modifier) {
    Column(modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Text("It's time... ⛅")
    }
}

@Preview
@Composable
fun WeatherAppPreview() {
    WeatherAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            WeatherApp(modifier = Modifier.padding(innerPadding))
        }
    }
}