/*
 * MyTestBehaviorBehavior.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 10:19 AM
 * Last modified 10/24/18 10:19 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tampan.kucingapes.submission4kotlindicoding.activity.MainActivity

@RunWith(AndroidJUnit4::class)
class MyTestBehavior {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {

        // wait api load list
        Thread.sleep(3000)

        onView(withId(R.id.pager)).perform(swipeLeft())

        onView(
            allOf(
                childAtPosition(allOf(withId(R.id.list_match),
                    childAtPosition(withId(R.id.swipe_refresh), 0)), 1),
                isDisplayed()
            )
        ).perform(click())

        // wait api load detail
        Thread.sleep(2000)

        onView(withId(R.id.favorite_action)).perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withId(R.id.favorites_match)).perform(click())
        onView(withId(R.id.pager)).perform(swipeDown())

    }

    private fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}