package com.example.foodorderingapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R

import com.example.foodorderingapp.adapter.MenuAdapter
import com.example.foodorderingapp.databinding.FragmentSearchBinding



class SearchFragment : Fragment() {
private lateinit var binding:FragmentSearchBinding
private lateinit var adaptar : MenuAdapter

private val originalMenuFoodName = listOf("Burger","Pizza","Noodles","Tacos")
    private val OrignalMenuItemPrice = listOf("5$","8$","9$","60$")
    private val orignialMenuImage = listOf(
        R.drawable.burger,R.drawable.pizza,R.drawable.noodles,R.drawable.tacos1,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private val filteredMenuFoodName = mutableListOf<String>()
    private val filteredMenuItemPrice = mutableListOf<String>()
    private val filteredMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        adaptar= MenuAdapter(filteredMenuFoodName,filteredMenuItemPrice,filteredMenuImage,requireContext())
        binding.menuRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adaptar

        //setup for search view
//        setUpSearchView()
        //show all Menu items
        showAllMenuItems()
        return binding.root

    }

    private fun showAllMenuItems() {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        filteredMenuFoodName.addAll(originalMenuFoodName)
        filteredMenuItemPrice.addAll(OrignalMenuItemPrice)
        filteredMenuImage.addAll(orignialMenuImage)

        adaptar.notifyDataSetChanged()
    }

    private fun setUpSearchView() {

//        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String): Boolean {
//                filterMenuItem(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//               filterMenuItem(newText)
//                return true
//            }
//        })
//
    }

    private fun filterMenuItem(query: String) {
    filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()
        originalMenuFoodName.forEachIndexed{index, foodName ->
            if (foodName.contains(query, ignoreCase = true)){
                filteredMenuFoodName.add(foodName)
                filteredMenuItemPrice.add(OrignalMenuItemPrice[index])
                filteredMenuImage.add(orignialMenuImage[index])
            }
        }
        adaptar.notifyDataSetChanged()

    }

    companion object {

    }
}
