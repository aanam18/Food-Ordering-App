package com.example.foodorderingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.CartItemBinding

class CartAdaptar(
    private val cartItems: MutableList<String>,
    private val CartItemPrice: MutableList<String>,
    private val cartImage: MutableList<Int>
) :
    RecyclerView.Adapter<CartAdaptar.CartViewHolder>() {

    private val itemQuantities = IntArray(cartItems.size) { 1 }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = cartItems.size
    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartFoodName.text = cartItems[position]
                cartItemPrice.text = CartItemPrice[position]
                cartiamge.setImageResource(cartImage[position])
                cartItemQauntity.text = quantity.toString()

                minusButton.setOnClickListener {
                    decreaseQuantity(position)


                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)

                }
                deleteButton.setOnClickListener {
                    val itemPoistion = adapterPosition
                    if (itemPoistion != RecyclerView.NO_POSITION) {
                        deleteItem(itemPoistion)
                    }

                }


            }
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.cartItemQauntity.text = itemQuantities[position].toString()
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.cartItemQauntity.text = itemQuantities[position].toString()
            }

        }

        private fun deleteItem(position: Int) {
            cartItems.removeAt(position)
            cartImage.removeAt(position)
            CartItemPrice.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)

        }

    }
}