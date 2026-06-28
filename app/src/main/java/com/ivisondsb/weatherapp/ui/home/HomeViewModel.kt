package com.ivisondsb.weatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivisondsb.weatherapp.data.model.WeatherResponse
import com.ivisondsb.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = WeatherRepository()

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weather.value = repository.getWeather(lat, lon)
        }
    }
}