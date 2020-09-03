package com.example.challenge.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.challenge.api.Api
import com.example.challenge.pojos.InfoCP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformationDataSource(
    private val api: Api
) {

    var networkState = MutableLiveData<NetworkState>()
    var informationCPResponse = MutableLiveData<InfoCP>()

    fun requestInfo(cp: String) {
        networkState.value = NetworkState.LOADING

        api.getCPInfo(cp).enqueue(object : Callback<InfoCP>{
            override fun onFailure(call: Call<InfoCP>, t: Throwable) {
                networkState.value = NetworkState.ERROR
                Log.i("Mensaje Data Source Info", "${t}")
            }

            override fun onResponse(call: Call<InfoCP>, response: Response<InfoCP>) {
                if(response.isSuccessful){
                    networkState.value = NetworkState.LOADED
                    informationCPResponse.value = response.body()
                    Log.i("Mensaje Data Source Info", informationCPResponse.value.toString())
                } else {
                    networkState.value = NetworkState.ERROR
                    Log.i("Mensaje Data Source Info", "Pidio pero error")
                }
            }
        })
    }
}