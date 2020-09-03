package com.longbit.moda.shopping.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.longbit.moda.R
import com.longbit.moda.databinding.FragmentShoppingCartBinding
import com.longbit.moda.shopping.adapters.AdapterShoppingCart
import com.longbit.moda.shopping.pojos.Producto
import com.longbit.moda.shopping.pojos.ShoppingCart

class ShoppingCartFragment :
    Fragment(),
    AdapterShoppingCart.OnClickListenerHelperMinus,
    AdapterShoppingCart.OnClickListenerHelperAdd
{
    private lateinit var binding: FragmentShoppingCartBinding
    private var adaptador = AdapterShoppingCart(emptyList(), this, this)
    private val list: ArrayList<ShoppingCart> = arrayListOf(
        ShoppingCart(
            Producto(1, "Sudadera", 20.57, "https://cdn.shopify.com/s/files/1/0414/4557/products/CAMISA_MEZCLILLA_NINO_PAPA_1024x1024.png?v=1558979387", 7,
                "season", "Hombre", "Novedades", "Algodon", "Negro", "Cuidado con el perro", "Grande", "", "Pato", listOf("Nuevo", "Mujer", "Vintage", "Casual", "Elegante", "Estilo", "Tradicional", "Urbano", "Otoño")),
                "#aaa",
            "CH",
            1
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)

        binding.roundedButtonContinue.labelRoundedButton.text = getString(R.string.message_continue)

        adaptador.populateList(list)

        if (adaptador.itemCount == 0) {
            binding.recyclerShoppingCart.visibility = View.GONE
            binding.containerOptionsShopping.visibility = View.GONE
            binding.txtEmptyCart.visibility = View.VISIBLE
        } else {
            binding.recyclerShoppingCart.visibility = View.VISIBLE
            binding.containerOptionsShopping.visibility = View.VISIBLE
            binding.txtEmptyCart.visibility = View.GONE
        }

        binding.recyclerShoppingCart.adapter = adaptador

        return binding.root
  