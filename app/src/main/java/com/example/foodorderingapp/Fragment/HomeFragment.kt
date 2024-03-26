package com.example.foodorderingapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel

import com.example.foodorderingapp.R
import com.example.foodorderingapp.adapter.PouplarAdaptar
import com.example.foodorderingapp.databinding.FragmentHomeBinding
import com.example.foodorderingapp.databinding.FragmentMenuBottomSheetBinding
import com.example.foodorderingapp.menuBottomSheetFragment


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.viewAllMenu.setOnClickListener {
            val bottomSheetDialog = menuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"Test")
        }
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.r,ScaleTypes.CENTER_INSIDE))
        imageList.add(SlideModel(R.drawable.rr,ScaleTypes.CENTER_INSIDE))
        imageList.add(SlideModel(R.drawable.rrr,ScaleTypes.CENTER_INSIDE))


    val imageSlider = binding.imageSlider
    imageSlider.setImageList(imageList)
    imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object :ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemmessage = "Selected image $position"
                Toast.makeText(requireContext(),itemmessage,Toast.LENGTH_LONG).show()
            }
        })
        val foodName = listOf("Burger","Pizza","Sandwich","Noodles","Tacos","Tandoori Chicken","Shawarma")
        val Price = listOf("5$","7$","8$","9$","10%","60$","80$","70$")
        val popularFoodImage = listOf(R.drawable.burger,R.drawable.pizza,R.drawable.sandwich,R.drawable.noodles,R.drawable.tacos1,R.drawable.chicken,R.drawable.shwarma)
        val adaptar = PouplarAdaptar(foodName,Price,popularFoodImage,requireContext())
        binding.PopularRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.PopularRecyclerView.adapter=adaptar
}
    companion object{


    }    }

