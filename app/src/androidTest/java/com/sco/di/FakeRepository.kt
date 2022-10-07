package com.sco.di

import com.sco.musicalcoffee.Coffee
import com.sco.musicalcoffee.ICoffeeRepository
import com.sco.musicalcoffee.R
import javax.inject.Inject

class FakeRepository @Inject constructor(): ICoffeeRepository {
    override suspend fun getCoffee(): List<Coffee> {
        return listOf(
            Coffee("Cappuccino", R.drawable.cappuccino),
            Coffee("I", R.drawable.cappuccino),
            Coffee("AM", R.drawable.cappuccino),
            Coffee("TEST", R.drawable.cappuccino),
            Coffee("DATA", R.drawable.cappuccino),
        )
    }
}