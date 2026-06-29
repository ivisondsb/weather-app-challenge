package com.ivisondsb.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ivisondsb.weatherapp.ui.home.HomeViewModel
import com.ivisondsb.weatherapp.ui.theme.WeatherAppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

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

    val viewModel: HomeViewModel = viewModel()
    val weatherList by viewModel.weatherList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllWeather()
    }

    fun getCurrentDate(): String {
        val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.ENGLISH)
        return LocalDate.now().format(formatter)
    }

    val currentDate = getCurrentDate()

    Column(
        modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Weather App", fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
                Text(currentDate, fontSize = 12.sp, color = Color.Gray)
            }
            Text("⛅", fontSize = 40.sp)
        }

        Spacer(Modifier.height(10.dp))

        LazyColumn(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(weatherList) { weather ->
                CityCard(
                    city = weather.name,
                    condition = weather.weather[0].description,
                    temp = weather.main.temp.toInt(),
                    tempMax = weather.main.tempMax.toInt(),
                    tempMin = weather.main.tempMin.toInt(),
                    icon = weather.weather[0].icon
                )
            }
        }
    }
}


@Composable
fun CityCard(
    city: String,
    condition: String,
    temp: Int,
    tempMax: Int,
    tempMin: Int,
    icon: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable { },
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(city, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(condition, fontSize = 11.sp, color = Color.Gray)
                Text("Máx: ${tempMax}°  Mín: ${tempMin}°", fontSize = 11.sp)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${icon}@2x.png",
                    contentDescription = condition,
                    modifier = Modifier.size(40.dp)
                )
            }
            Text("${temp}°", fontSize = 42.sp, fontWeight = FontWeight.Light)
        }
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

@Preview
@Composable
private fun CityCardPreview() {
    CityCard(
        city = "Recife",
        "Nublado",
        25,
        27,
        23,
        "https://openweathermap.org/img/wn/03d@2x.png"
    )
}