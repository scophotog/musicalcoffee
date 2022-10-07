package com.sco.musicalcoffee.espresso

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

class CountingIdlingResource(private val resourceName: String) : IdlingResource {

    private val counter = AtomicInteger(0)

    private var resourceCallback:
            IdlingResource.ResourceCallback? = null

    override fun getName() = resourceName

    override fun isIdleNow() = counter.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if (counterVal == 0) {
            resourceCallback?.onTransitionToIdle()
        } else if (counterVal < 0) {
            throw IllegalStateException("Counter less than 0; should never happen")
        }
    }
}