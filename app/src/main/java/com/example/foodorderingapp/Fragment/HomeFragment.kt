package com.example.foodorderingapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel

import com.example.foodorderingapp.R
import com.example.foodorderingapp.adapter.MenuAdapter
import com.example.foodorderingapp.databinding.FragmentHomeBinding
import com.example.foodorderingapp.menuBottomSheetFragment
import com.example.foodorderingapp.model.MenuItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<MenuItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewAllMenu.setOnClickListener {
            val bottomSheetDialog = menuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager, "Test")
        }

        //Retreive and display popular menu Items
        retrieveAndDisplayPopularItem()

        return binding.root


    }

    private fun retrieveAndDisplayPopularItem() {
        //get reference to the database
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")
        menuItems = mutableListOf()

        // Retrieve menu Items from the database
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    val menuItem = foodSnapshot.getValue(MenuItem::class.java)
                    menuItem?.let { menuItems.add(it) }
                }
                //Display Random popular Item
                randomPopularItems()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun randomPopularItems() {
        //create a shuffled list of menu Items
        val index = menuItems.indices.toList().shuffled()
        val numItemToShow = 6
        val subSetMenuItems = index.take(numItemToShow).map { menuItems[it] }

        setPopularItemAdapter(subSetMenuItems)
    }

    private fun setPopularItemAdapter(subSetMenuItems: List<MenuItem>) {
        val adaptar = MenuAdapter(subSetMenuItems, requireContext())
        binding.PopularRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.PopularRecyclerView.adapter = adaptar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.r, ScaleTypes.CENTER_INSIDE))
        imageList.add(SlideModel(R.drawable.rr, ScaleTypes.CENTER_INSIDE))
        imageList.add(SlideModel(R.drawable.rrr, ScaleTypes.CENTER_INSIDE))


        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemmessage = "Selected image $position"
                Toast.makeText(requireContext(), itemmessage, Toast.LENGTH_LONG).show()
            }
        })


    }


}

