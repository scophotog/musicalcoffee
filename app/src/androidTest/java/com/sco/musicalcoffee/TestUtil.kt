package com.sco.musicalcoffee

import android.app.Activity
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import junit.framework.TestCase
import org.hamcrest.Matchers


/**
 * Helper function to check that the adapter actually has items.
 */
fun <A : Activity> assertRecyclerViewHasEntries(
    activityRule: ActivityScenarioRule<A>,
    @IdRes recyclerView: Int
) {
    activityRule.scenario.onActivity { activity ->
        val adapter = activity.findViewById<RecyclerView>(recyclerView).adapter
        TestCase.assertNotNull(adapter)
        ViewMatchers.assertThat(adapter!!.itemCount, Matchers.greaterThan(0))
    }
}