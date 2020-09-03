package com.example.challenge.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge.datasource.InformationDataSource
import com.example.challenge.datasource.NetworkState
import com.example.challenge.pojos.InfoCP
import com.example.challenge.repository.InformationRepository

class InformationViewModel(
    private val repository: InformationRepository
) : ViewModel() {

    private lateinit var informationDataSource: InformationDataSource

    var informationResponse = MutableLiveData<InfoCP>()
    var informationNetworkState = MutableLiveData<NetworkState>()

    fun getInformation(cp: String) {
        informationDataSource = repository.requestInfo()
        informationDataSource.requestInfo(cp)
        informationResponse = informationDataSource.informationCPResponse
        informationNetworkState = informationDataSource.networkState
    }
}