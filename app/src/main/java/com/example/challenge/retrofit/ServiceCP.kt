package com.example.challenge.retrofit

import com.example.challenge.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceCP {

    companion object {
        const val BASE_URL_CP = "https://sepomex-wje6f4jeia-uc.a.run.app/api/"
    }

    private var retrofit: Retrofit? = null

    fun getApi(): Api {
        return getPoligonos()!!.create(
            Api::class.java
        )
    }

    fun getPoligonos(): Retrofit? {
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_CP)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}