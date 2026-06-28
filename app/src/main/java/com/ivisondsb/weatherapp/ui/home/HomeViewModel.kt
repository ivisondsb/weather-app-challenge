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
    private val _weatherList = MutableStateFlow<List<WeatherResponse>>(emptyList())
    val weatherList: StateFlow<List<WeatherResponse>> = _weatherList

    private val cities = listOf(
        Pair("Recife",                  Pair(-8.0476,  -34.8770)),
        Pair("Olinda",                  Pair(-8.0089,  -34.8553)),
        Pair("São Paulo",               Pair(-23.5505, -46.6333)),
        Pair("Rio de Janeiro",          Pair(-22.9068, -43.1729)),
        Pair("Brasília",                Pair(-15.7801, -47.9292)),
        Pair("Manaus",                  Pair(-3.1019,  -60.0250)),
        Pair("Belo Horizonte",          Pair(-19.9191, -43.9386)),
    )

    fun getAllWeather() {
        viewModelScope.launch {
            val results = cities.map { (_, coords) ->
                repository.getWeather(lat = coords.first, lon = coords.second)
            }
            _weatherList.value = results
        }
    }
}