package com.example.challenge.retrofit

import com.example.challenge.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServicePoligonos {

    companion object {
        const val BASE_URL_POLIGON = "https://poligonos-wje6f4jeia-uc.a.run.app/"
    }

    private var retrofit: Retrofit? = null

    fun getApi(): Api {
        return getCPInf()!!.create(
            Api::class.java
        )
    }

    fun getCPInf(): Retrofit? {
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_POLIGON)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}