package com.example.clima.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.clima.repositories.WeatherRepository
import com.example.clima.utils.Constants.API_KEY
import com.example.clima.utils.Constants.UNITS_METRIC
import com.example.clima.utils.Resource
import kotlinx.coroutines.Dispatchers

class WeatherDetailsActivityViewModel(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    fun getWeatherByCityId(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(weatherRepository.getWeatherByCityId(id, API_KEY, UNITS_METRIC)))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message))
        }
    }
}