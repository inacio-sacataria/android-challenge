package com.example.clima.network

import com.example.clima.responses.WeatherDetailsResponse
import com.example.clima.responses.WeatherSimpleResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("group")
    suspend fun getWeatherByCityId(
        @Query("id") cityId: String,
        @Query("appid") clientApiKey: String,
        @Query("units") units: String
    ): WeatherSimpleResponse

    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") clientApiKey: String,
        @Query("units") units: String
    ): WeatherDetailsResponse
}