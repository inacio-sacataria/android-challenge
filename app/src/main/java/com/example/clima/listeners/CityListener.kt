package com.example.clima.listeners

import com.example.clima.results.WeatherResult


interface CityListener {
    fun onCityClicked(weatherResult: WeatherResult)
}