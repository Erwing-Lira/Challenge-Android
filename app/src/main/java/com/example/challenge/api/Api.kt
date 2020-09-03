package com.example.challenge.api

import com.example.challenge.pojos.InfoCP
import com.example.challenge.pojos.PoligonoCP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("zip-codes/{cp}")
    fun getCPInfo(
        @Path("cp") cp: String
    ): Call<InfoCP>

    @GET("zip-codes/{cp}")
    fun getPoligonos(
        @Path("cp") cp: String
    ): Call<PoligonoCP>
}