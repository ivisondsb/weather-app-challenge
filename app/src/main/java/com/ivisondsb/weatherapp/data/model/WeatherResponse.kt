package com.ivisondsb.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double
)

data class Weather(
    val description: String,
    val icon: String
)
