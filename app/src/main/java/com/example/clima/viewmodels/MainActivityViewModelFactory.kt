package com.example.clima.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clima.repositories.WeatherRepository

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(private val weatherRepository: WeatherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}