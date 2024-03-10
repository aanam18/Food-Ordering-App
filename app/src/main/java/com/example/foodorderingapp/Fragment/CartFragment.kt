package com.example.foodorderingapp.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.CongratsBottomSheetFragment
import com.example.foodorderingapp.PayOutActivity
import com.example.foodorderingapp.R
import com.example.foodorderingapp.adapter.CartAdaptar
import com.example.foodorderingapp.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val cartfoodname = listOf("Burger", "Pizza", "Noodles", "Fried Rice")
        val cartItemPrice = listOf("$5", "$3", "$10", "$2")
        val cartImage = listOf(
            R.drawable.menu2, R.drawable.menu3, R.drawable.menu5, R.drawable.menu4,
        )
        val adapter =
            CartAdaptar(ArrayList(cartfoodname), ArrayList(cartItemPrice), ArrayList(cartImage))
        binding.cartRecylclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecylclerView.adapter = adapter
        binding.proceedButton.setOnClickListener {
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            startActivity(intent)
        }


        return binding.root

    }

    companion object {

    }

}