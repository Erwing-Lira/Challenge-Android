package com.example.challenge.datasource

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.challenge.api.Api
import com.example.challenge.pojos.PoligonoCP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PoligonosDataSource (
    private val api: Api
){
    val networkState = MutableLiveData<NetworkState>()
    val poligonosResponse = MutableLiveData<PoligonoCP>()

    fun requestPoligonos(cp: String) {
        networkState.value = NetworkState.LOADING

        api.getPoligonos(cp).enqueue(object : Callback<PoligonoCP> {
            override fun onFailure(call: Call<PoligonoCP>, t: Throwable) {
                networkState.value = NetworkState.ERROR
                Log.i("Mensaje Data Source Poligonos", "${t}")
            }

            override fun onResponse(call: Call<PoligonoCP>, response: Response<PoligonoCP>) {
                if (response.isSuccessful) {
                    networkState.value = NetworkState.LOADED
                    poligonosResponse.value = response.body()
                    Log.i("Mensaje Data Source Poligonos", poligonosResponse.value.toString())
                } else {
                    networkState.value = NetworkState.ERROR
                    Log.i("Mensaje Data Source Poligonos", "Pedido pero error")
                }
            }

        })
    }
}