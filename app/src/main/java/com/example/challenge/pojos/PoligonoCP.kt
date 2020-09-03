package com.example.challenge.pojos

import com.google.gson.annotations.SerializedName

data class PoligonoCP (
    @SerializedName("type") val type : String,
    @SerializedName("geometry") val geometry : Geometry
)

data class Geometry (
    @SerializedName("type") val type : String,
    @SerializedName("coordinates") val coordinates : List<List<List<Double>>>
)

data class LatLong (
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)