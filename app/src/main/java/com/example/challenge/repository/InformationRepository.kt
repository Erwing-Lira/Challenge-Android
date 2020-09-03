package com.example.challenge.repository

import com.example.challenge.api.Api
import com.example.challenge.datasource.InformationDataSource

class InformationRepository(
    private val api: Api
) {
    fun requestInfo(): InformationDataSource {
        return InformationDataSource(api)
    }
}