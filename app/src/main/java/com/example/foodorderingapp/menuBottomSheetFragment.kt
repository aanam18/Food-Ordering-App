package com.example.foodorderingapp

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.foodorderingapp.adapter.MenuAdapter
import com.example.foodorderingapp.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class menuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMenuBottomSheetBinding.inflate(inflater, container,false)
        binding.buttonback.setOnClickListener {
            dismiss()
        }
        val menufoodname = listOf("Burger","Pizza","Noodles","Fried Rice")
        val menuItemPrice = listOf("$5","$3","$10","$2")
        val menuImage = listOf(
            R.drawable.menu2,R.drawable.menu3,R.drawable.menu5,R.drawable.menu4,
        )
        val adapter = MenuAdapter(ArrayList(menufoodname),ArrayList(menuItemPrice),ArrayList(menuImage),requireContext())
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter=adapter
        return binding.root


    }

    companion object {

    }
}