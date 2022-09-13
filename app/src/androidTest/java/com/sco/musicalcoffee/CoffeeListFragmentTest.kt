package com.sco.musicalcoffee

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertNotNull
import org.hamcrest.Matchers.greaterThan
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoffeeListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listOfCoffeeIsDisplayed() {
        onView(withId(R.id.coffee_list_recycler)).check(matches(isDisplayed()))
        assertRecyclerViewHasEntries(R.id.coffee_list_recycler)
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

    /**
     * Helper function to check that the adapter actually has items.
     */
    private fun assertRecyclerViewHasEntries(@IdRes recyclerView: Int) {
        activityRule.scenario.onActivity { activity ->
            val adapter = activity.findViewById<RecyclerView>(recyclerView).adapter
            assertNotNull(adapter)
            assertThat(adapter!!.itemCount, greaterThan(0))
        }
    }
}