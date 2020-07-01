package com.classassignment2.tipcalculator


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TipCalculatorTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun tipCalculatorTest() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.costOfBillEditText),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                )
            )
        )
        appCompatEditText.perform(scrollTo(), replaceText("20.00"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.costOfBillEditText), withText("20.00"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                )
            )
        )
        appCompatEditText2.perform(pressImeActionButton())

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.peopleAmountSpinner),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        3
                    ),
                    1
                )
            )
        )
        appCompatSpinner.perform(scrollTo(), click())

        val appCompatTextView = onData(anything())
            .inAdapterView(
                allOf(
                    withClassName(`is`("com.android.internal.app.AlertController")),
                    childAtPosition(
                        withClassName(`is`("android.widget.FrameLayout")),
                        0
                    )
                )
            )
            .atPosition(1)
        appCompatTextView.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.outcomeTip), withText("$2.00"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        4
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.outcomePay), withText("$22.00"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        5
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        val textView4 = onView(
            allOf(
                withId(R.id.outcomePerPerson), withText("$11.00"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        6
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
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
