package com.sco.musicalcoffee

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sco.musicalcoffee.espresso.TestIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CoffeeListFragmentIdlingResourceTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var idlingResource: IdlingResource

    @Before
    fun setup() {
        idlingResource = TestIdlingResource.countingIdlingResource
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun shutDown() {
        if (::idlingResource.isInitialized) {
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }

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