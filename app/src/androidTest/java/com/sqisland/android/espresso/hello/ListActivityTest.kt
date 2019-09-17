package com.sqisland.android.espresso.hello

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<ListActivity>(ListActivity::class.java)

    @Test
    fun clickItem() {

        onView(withId(R.id.footer))
                .check(matches(not(isDisplayed()))) // The footer is initially not displayed.

        onData(withValue(27)) // Specify which AdapterView we want this to match, when there are several ListView displayed
                .inAdapterView(withId(R.id.list))
                .perform(click())

        onView(withId(R.id.footer))
                .check(matches(withText("27")))
                .check(matches(isDisplayed()))
    }

    private fun withValue(value: Int): Matcher<Any> {

        return object : BoundedMatcher<Any, ListActivity.Item>(ListActivity.Item::class.java) {

            override fun describeTo(description: Description?) {
                description?.appendText("has value " + value)
            }

            override fun matchesSafely(item: ListActivity.Item?): Boolean {
                return item.toString() == value.toString()
            }

        }

    }

}