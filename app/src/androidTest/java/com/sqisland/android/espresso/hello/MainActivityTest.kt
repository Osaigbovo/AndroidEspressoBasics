package com.sqisland.android.espresso.hello

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) // Annotation - All the test in this class are Android tests
class MainActivityTest {

    // Activity Test Rule - which Activity to launch before running each test method.
    @Rule // Rule - launch MainActivity before each test method.
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)


    /**
     * Use onView() instead of findViewById().
     * onView waits for the App to be idle - the views have been rendered.
     * with findViewById() we lose synchronization and the test may send UI Events before the app is ready.
     * */
    @Test
    fun greet() {
        onView(withId(R.id.greeting)) // tell Espresso we are looking for a view
                .check(matches(withText(""))) // check that this view matches this particular condition.

        onView(withId(R.id.greet_button))
                .perform(click()) // FLuent API - several perform and check functions can be chained together a longer as they are on the same view.
                .check(matches(not(isEnabled()))) // not() comes from the Hamcrest library in Espresso

        onView(withId(R.id.greeting))
                .check(matches(withText(R.string.hello))) // no hardcoded string - better to use string resources - if string is updated.
    }

    /**
     * METHOD 1
     * isAssignableFrom(TextView::class.java) - searching for a TextView or its subclass.
     * withParent(isAssignableFrom(Toolbar::class.java)))) - the parent view should be a Toolbar
     *
     * allOf() - ViewMatcher
     * */
    @Test
    fun toolbarTitle() {
        onView(
                allOf(
                        isAssignableFrom(TextView::class.java),
                        withParent(isAssignableFrom(Toolbar::class.java))))
                .check(matches(withText(R.string.title)))
    }

    /*
    * METHOD 2
    * */
    @Test
    fun toolbarTitleCustomMatcher() {

        val title = InstrumentationRegistry
                .getInstrumentation()
                .targetContext
                .getString(R.string.title)

        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(title)))
    }

    /*
    * use the getTitle() instead to verify that our app has the expected Title.
    * Write a Custom Matcher by extending a bounded matcher.
    *
    * A Bounded matcher allows you to supply your own criteria.
    * Bounded means that it only applies to a specific case - here a toolbar.
    * */
    private fun withToolbarTitle(expectedTitle: CharSequence): Matcher<View> {
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
            override fun describeTo(description: Description?) { // describeTo is called when the matching fails.
                description?.appendText("with Toolbar title: " + expectedTitle)
            }

            override fun matchesSafely(toolbar: Toolbar?): Boolean {
                return expectedTitle == toolbar?.title
            }
        }

    }


}