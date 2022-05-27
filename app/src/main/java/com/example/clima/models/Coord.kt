package com.example.clima.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Coord(
    @SerializedName("long") val long: Double,
    @SerializedName("lat") val lat: Double
) : Parcelable