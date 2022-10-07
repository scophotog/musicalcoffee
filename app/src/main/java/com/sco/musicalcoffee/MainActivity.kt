package com.sco.musicalcoffee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val coffeeListFragment = CoffeeListFragment()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container, coffeeListFragment,
                CoffeeListFragment.TAG
            )
            .commit()
    }
}