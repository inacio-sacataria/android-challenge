package com.example.clima

import android.app.Application
import com.example.clima.network.ApiClient
import com.example.clima.network.ApiHelper
import com.example.clima.repositories.WeatherRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication :Application() {
    val weatherRepository = WeatherRepository(ApiHelper(ApiClient.apiService()))
}