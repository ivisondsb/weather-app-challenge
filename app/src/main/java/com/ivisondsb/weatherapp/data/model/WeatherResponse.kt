package com.ivisondsb.weatherapp.data.model

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double
)

data class Weather(
    val description: String,
    val icon: String
)
