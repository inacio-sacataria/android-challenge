package com.example.clima.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.clima.repositories.WeatherRepository
import com.example.clima.utils.Constants.API_KEY
import com.example.clima.utils.Constants.CITIES
import com.example.clima.utils.Constants.MY_CITY_NAME
import com.example.clima.utils.Constants.UNITS_METRIC
import com.example.clima.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainActivityViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getMyCityWeather() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(
                Resource.success(
                    weatherRepository.getWeatherByCityName(
                        MY_CITY_NAME,
                        API_KEY,
                        UNITS_METRIC
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(null, e.message))
        }
    }

    fun getEuropeanCitiesWeather() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(
                Resource.success(
                    weatherRepository.getWeatherByCityId(
                        CITIES,
                        API_KEY,
                        UNITS_METRIC
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(null, e.message))
        }
    }
}