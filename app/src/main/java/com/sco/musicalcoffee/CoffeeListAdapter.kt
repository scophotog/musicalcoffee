package com.sco.musicalcoffee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sco.musicalcoffee.databinding.LayoutCoffeeItemBinding

class CoffeeListAdapter(
    private val data: List<Coffee>,
    private val onItemClick: (text: Coffee) -> Unit
) :
    RecyclerView.Adapter<CoffeeListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutCoffeeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutCoffeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(data[position]) {
                binding.coffeeNameText.text = this.drinkName
                binding.coffeeImage.setImageResource(this.image)
                binding.root.setOnClickListener { onItemClick(this) }
            }
        }
    }

    override fun getItemCount() = data.size
}