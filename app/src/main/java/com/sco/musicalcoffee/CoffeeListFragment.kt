package com.sco.musicalcoffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sco.musicalcoffee.databinding.LayoutCoffeeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeeListFragment : Fragment() {

    companion object {
        const val TAG = "CoffeeListFragment"
    }

    private val viewModel: CoffeeViewModel by viewModels()

    private var _binding: LayoutCoffeeListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LayoutCoffeeListBinding.inflate(inflater, container, false).apply {
            _binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                CoffeeListState.Error -> handleError()
                is CoffeeListState.Loaded -> handleDataLoaded(state.coffeeList) { item ->
                    onItemClick(
                        item
                    )
                }
                CoffeeListState.Loading -> handleLoading()
            }
        }
        binding.swipeContainer.apply {
            isEnabled = true
            setOnRefreshListener {
                viewModel.getCoffeeList()
            }
        }

        viewModel.getCoffeeList()
    }

    private fun handleError() {
        Toast.makeText(activity, "Loading Error", Toast.LENGTH_SHORT).show()
        binding.swipeContainer.isRefreshing = false
    }

    private fun handleDataLoaded(data: List<Coffee>, onItemClick: (item: Coffee) -> Unit) {
        binding.coffeeListRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CoffeeListAdapter(data) { item -> onItemClick(item) }
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        binding.swipeContainer.isRefreshing = false
    }

    private fun handleLoading() {
        binding.swipeContainer.isRefreshing = true
        binding.coffeeListRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CoffeeListAdapter(listOf()) { }
        }
    }

    private fun onItemClick(coffee: Coffee) {
        val fragment = CoffeeDetailFragment().apply {
            arguments = Bundle().apply {
                putString("coffee", coffee.drinkName)
                putInt("image", coffee.image)
            }
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment).addToBackStack(null).commit()
    }
}