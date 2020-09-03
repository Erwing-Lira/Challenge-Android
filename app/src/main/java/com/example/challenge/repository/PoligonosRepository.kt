package com.example.challenge.repository

import com.example.challenge.api.Api
import com.example.challenge.datasource.PoligonosDataSource

class PoligonosRepository(
    private val api: Api
) {
    fun getPoligonos(): PoligonosDataSource{
        return PoligonosDataSource(api)
    }
}