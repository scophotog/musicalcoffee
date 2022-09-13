package com.sco.musicalcoffee

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class CoffeeRepository {

    companion object {
        var shouldRandomError = false
        var shouldLoadImmediately = true
    }

    /**
     * Returns a list of coffee with a simulated loading delay and error
     */
    suspend fun getCoffee(): List<Coffee> {
        val randomTime = if (shouldLoadImmediately) 0 else (1..3).random()
        delay(randomTime.seconds)
        val randomError = if (shouldRandomError) (1..3).random() % 2 == 0 else false
        return if (randomError) listOf() else listOfCoffee()
    }

    private fun listOfCoffee(): List<Coffee> {
        return listOf(
            Coffee("Espresso", R.drawable.espresso),
            Coffee("Cappuccino", R.drawable.cappuccino),
            Coffee("Americano", R.drawable.americano),
            Coffee("Doppio", R.drawable.doppio),
            Coffee("Latte", R.drawable.latte),
        )
    }
}