package com.example.challenge

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.challenge.databinding.ActivityMainBinding
import com.example.challenge.datasource.UtilsNetwork
import com.example.challenge.repository.InformationRepository
import com.example.challenge.repository.PoligonosRepository
import com.example.challenge.retrofit.ServiceCP
import com.example.challenge.retrofit.ServicePoligonos
import com.example.challenge.viewmodels.InformationViewModel
import com.example.challenge.viewmodels.InformationViewModelFactory
import com.example.challenge.viewmodels.PoligonosViewModel
import com.example.challenge.viewmodels.PoligonosViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var binding: ActivityMainBinding
    private lateinit var modelInformation: InformationViewModel
    private lateinit var modelPoligonos: PoligonosViewModel
    private var factoryInformation: InformationViewModelFactory? = null
    private var factoryPoligonos: PoligonosViewModelFactory? = null
    private var list: ArrayList<LatLng> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factoryInformation = InformationViewModelFactory(InformationRepository(ServiceCP().getApi()))
        modelInformation = ViewModelProvider(this, factoryInformation!!).get(InformationViewModel::class.java)

        factoryPoligonos = PoligonosViewModelFactory(PoligonosRepository(ServicePoligonos().getApi()))
        modelPoligonos = ViewModelProvider(this, factoryPoligonos!!).get(PoligonosViewModel::class.java)


        binding.txtCp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length == 5) {
                    binding.loader.visibility = View.VISIBLE
                    modelInformation.getInformation(s.toString())
                    modelPoligonos.getPoligonos(s.toString())
                    waitForResponse()
                } else {
                    binding.txtCp.error = "Unicamente numeros y 5 caracteres"
                    binding.txtCountry.setText("")
                    binding.txtCity.setText("")
                    binding.txtColony.setText("")
                    binding.txtEntity.setText("")
                    binding.txtMunicpy.setText("")
                    modelPoligonos.poligonosResponse.value = null
                    modelInformation.informationResponse.value = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun waitForResponse() {
        val support = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        modelInformation.informationNetworkState.observe(this, Observer {
            when (it.status) {
                UtilsNetwork.SUCCESS -> {
                    binding.loader.visibility = View.GONE
                    binding.txtCp.isEnabled = true
                }
                UtilsNetwork.FAILED -> {
                    binding.loader.visibility = View.GONE
                    binding.txtCp.isEnabled = true
                    Toast.makeText(this, "Ingrese un codigo postal valido", Toast.LENGTH_LONG)
                        .show()
                }
                else -> {
                    binding.loader.visibility = View.VISIBLE
                    binding.txtCp.isEnabled = false
                }
            }
        })

        modelPoligonos.poligonosNetworkState.observe(this, Observer {
            when (it.status) {
                UtilsNetwork.SUCCESS -> {
                    binding.loader.visibility = View.GONE
                    binding.txtCp.isEnabled = true
                }
                UtilsNetwork.FAILED -> {
                    binding.loader.visibility = View.GONE
                    binding.txtCp.isEnabled = true
                    Toast.makeText(this, "No existe poligono o algun problema en la comunicación", Toast.LENGTH_LONG)
                        .show()
                }
                else -> {
                    binding.loader.visibility = View.VISIBLE
                    binding.txtCp.isEnabled = false
                }
            }
        })

        modelInformation.informationResponse.observe(this, Observer {
            if (it != null) {
                binding.txtCountry.setText(getString(R.string.mx))
                binding.txtEntity.setText(it.federal_entity.name)
                binding.txtCity.setText(it.locality)
                binding.txtMunicpy.setText(it.municipality.name)
                binding.txtColony.setText(it.settlements[0].name)
                support.getMapAsync(this)
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        modelPoligonos.poligonosResponse.observe(this, Observer {
            if (it != null) {
                for (itemSuperior in it.geometry.coordinates) {
                    for (itemMedio in itemSuperior) {
                        list.add(LatLng(itemMedio[1], itemMedio[0]))
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(itemMedio[1], itemMedio[0])))
                    }
                }
                googleMap.setMinZoomPreference(14f)
                googleMap.addPolygon(
                    PolygonOptions()
                        .clickable(false)
                        .addAll(list)
                        .strokeColor(Color.GRAY)
                        .fillColor(Color.LTGRAY)
                        .strokeWidth(8f)
                )
            } else {
                googleMap.clear()
                list = arrayListOf()
            }
        })
    }
}