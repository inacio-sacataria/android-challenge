package com.example.clima.responses

import com.example.clima.results.WeatherResult
import com.google.gson.annotations.SerializedName

data class WeatherSimpleResponse(
    @SerializedName("list") val list: List<WeatherResult>
)