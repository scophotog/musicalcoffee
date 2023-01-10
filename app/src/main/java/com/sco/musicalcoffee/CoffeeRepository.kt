package com.sco.musicalcoffee

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

interface ICoffeeRepository {
    suspend fun getCoffee(): List<Coffee>
}

class CoffeeRepository @Inject constructor() : ICoffeeRepository {

    companion object {
        var shouldRandomError = false
    }

    /**
     * Returns a list of coffee with a simulated loading delay and error
     */
    override suspend fun getCoffee(): List<Coffee> {
        // Simulate Network Delay
        val shouldLoadImmediately = true //System.currentTimeMillis() % 2L == 0L
        val randomDelay = (if (shouldLoadImmediately) 0 else (0..3).random()).seconds
        delay(randomDelay)
        // Simulate Network Error
        val randomError = if (shouldRandomError) (1..4).random() == 2 else false
        return if (randomError) listOf() else listOfCoffee()
    }

    private fun listOfCoffee(): List<Coffee> {
        // Simulate Unexpected Data
        val unexpectedData = if (shouldRandomError && (1..3).random() % 2 == 0) "Cat-puccino" else "Cappuccino"
        return listOf(
            Coffee("Espresso", R.drawable.espresso),
            Coffee(unexpectedData, R.drawable.cappuccino),
            Coffee("Americano", R.drawable.americano),
            Coffee("Doppio", R.drawable.doppio),
            Coffee("Latte", R.drawable.latte),
        )
    }
}