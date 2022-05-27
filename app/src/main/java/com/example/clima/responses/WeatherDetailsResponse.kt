package com.example.clima.responses

import android.os.Parcelable
import com.example.clima.models.*
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDetailsResponse(
    @SerializedName("coord") val coord: Coord,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val dt: Int,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
) : Parcelable {
    val cityAndCountry: String get() = "$name, ${sys.country}"
}