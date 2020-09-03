package com.example.challenge.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge.datasource.NetworkState
import com.example.challenge.datasource.PoligonosDataSource
import com.example.challenge.pojos.PoligonoCP
import com.example.challenge.repository.PoligonosRepository

class PoligonosViewModel(
    private val repository: PoligonosRepository
) : ViewModel(){

    private lateinit var poligonosDataSource: PoligonosDataSource

    var poligonosNetworkState = MutableLiveData<NetworkState>()
    var poligonosResponse = MutableLiveData<PoligonoCP>()

    fun getPoligonos(cp: String){
        poligonosDataSource = repository.getPoligonos()
        poligonosDataSource.requestPoligonos(cp)
        poligonosResponse = poligonosDataSource.poligonosResponse
        poligonosNetworkState = poligonosDataSource.networkState
    }
}