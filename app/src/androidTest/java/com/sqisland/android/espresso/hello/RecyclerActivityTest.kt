package com.sqisland.android.espresso.hello

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.sqisland.android.espresso.recyclerview.RecyclerActivity
import com.sqisland.android.espresso.recyclerview.TextViewHolder
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecyclerActivityTest {


    @Rule
    @JvmField
    var activityRule = ActivityTestRule<RecyclerActivity>(RecyclerActivity::class.java)

    @Test
    fun clickItem() {

        onView(withId(R.id.footer))
                .check(matches(not(isDisplayed())))

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<TextViewHolder>(27, click()))

        onView(withId(R.id.footer))
                .check(matches(withText("27")))
                .check(matches(isDisplayed()))
    }

}