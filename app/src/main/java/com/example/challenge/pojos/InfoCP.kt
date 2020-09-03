package com.example.challenge.pojos

import com.google.gson.annotations.SerializedName

data class InfoCP (
    @SerializedName("zip_code") val zip_code : Int,
    @SerializedName("locality") val locality : String,
    @SerializedName("federal_entity") val federal_entity : Federal_entity,
    @SerializedName("settlements") val settlements : List<Settlements>,
    @SerializedName("municipality") val municipality : Municipality
)

data class Federal_entity (
    @SerializedName("key") val key : Int,
    @SerializedName("name") val name : String,
    @SerializedName("code") val code : String
)

data class Settlements (
    @SerializedName("key") val key : Int,
    @SerializedName("name") val name : String,
    @SerializedName("zone_type") val zone_type : String,
    @SerializedName("settlement_type") val settlement_type : Settlement_type
)

data class Settlement_type (
    @SerializedName("key") val key : Int,
    @SerializedName("name") val name : String
)

data class Municipality (
    @SerializedName("key") val key : Int,
    @SerializedName("name") val name : String
)