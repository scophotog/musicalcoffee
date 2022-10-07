package com.sco.musicalcoffee

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.sco.di.FakeRepository
import com.sco.musicalcoffee.di.RepositoryModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
class CoffeeListFragmentHiltTest {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class TestModule {
        @Singleton
        @Binds
        abstract fun bindFakeRepository(
            fakeRepository: FakeRepository
        ): ICoffeeRepository
    }

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listOfCoffeeIsDisplayed() {
        onView(withId(R.id.coffee_list_recycler)).check(matches(isDisplayed()))
        assertRecyclerViewHasEntries(activityRule, R.id.coffee_list_recycler)
    }

    @Test
    fun listOfCoffeeHasAnItem() {
        onView(withId(R.id.coffee_list_recycler)).perform(
            RecyclerViewActions.scrollTo<CoffeeListAdapter.ViewHolder>(
                hasDescendant(withText("Cappuccino"))
            )
        )
    }

    @Test
    fun tappingCoffeeOpensDetails() {
        onView(withId(R.id.coffee_list_recycler)).perform(
            RecyclerViewActions.actionOnItem<CoffeeListAdapter.ViewHolder>(
                hasDescendant(withText("Cappuccino")), click()))
        onView(withId(R.id.coffee_detail_text)).check(matches(withText("Cappuccino")))
    }
}