package com.ivisondsb.weatherapp.data.repository

import com.ivisondsb.weatherapp.BuildConfig
import com.ivisondsb.weatherapp.data.model.WeatherResponse
import com.ivisondsb.weatherapp.data.remote.RetrofitInstance

class WeatherRepository {

    private val api = RetrofitInstance.api

    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        return api.getWeather(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.WEATHER_API_KEY
        )
    }
}
