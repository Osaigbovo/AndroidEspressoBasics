package com.sqisland.android.espresso.hello

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.sqisland.android.espresso.recyclerview.RecyclerActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecyclerActivityTest {


    @Rule
    @JvmField
    var activityRule = ActivityTestRule<RecyclerActivity>(RecyclerActivity::class.java)

}