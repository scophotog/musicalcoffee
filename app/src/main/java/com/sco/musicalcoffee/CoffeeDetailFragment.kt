package com.sco.musicalcoffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.sco.musicalcoffee.databinding.LayoutCoffeeDetailBinding

class CoffeeDetailFragment : Fragment() {

    private var _binding: LayoutCoffeeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LayoutCoffeeDetailBinding.inflate(inflater, container, false).apply {
            _binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.coffeeDetailText.text = arguments?.getString("coffee") ?: "Unknown Coffee"
        binding.coffeeDetailImage.setImageResource(
            arguments?.getInt("image") ?: R.drawable.ic_launcher_background
        )
        binding.coffeeDetailImage.setOnClickListener { onItemClick() }
    }

    private fun onItemClick() {
        val directions = CoffeeDetailFragmentDirections.actionCoffeeDetailFragmentToCoffeeDialog()
        val options = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_up)
            .setExitAnim(R.anim.slide_down)
            .build()
        findNavController().navigate(directions, options)
    }
}