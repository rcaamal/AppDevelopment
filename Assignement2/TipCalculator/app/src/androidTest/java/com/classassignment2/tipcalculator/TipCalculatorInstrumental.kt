package com.classassignment2.tipcalculator


import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.not


@RunWith(AndroidJUnit4::class)
@LargeTest
class TipCalculatorInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun changeText_sameActivity() {

        onView(withId(R.id.costOfBillEditText))
        .perform(typeText("100.00"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.seekBarTip))
            .perform(scrollTo())
            .perform(click());
        onView(withId(R.id.seekBarTip)).perform(click(swipeLeft()))

        onView(withId(R.id.textViewTip))
            .check(matches(withText("50")))

        onView(withId(R.id.peopleAmountSpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.peopleAmountSpinner)).check(matches(withSpinnerText(containsString("2"))));

        onView(withId(R.id.outcomePerPerson))
       .check(matches(withText("$75.00")))

    }

    @Test
    fun changeText_sameActivity2() {

        onView(withId(R.id.costOfBillEditText))
            .perform(typeText("50.00"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.seekBarTip))
            .perform(scrollTo())
            .perform(click());
        onView(withId(R.id.seekBarTip)).perform(click(swipeLeft()))

        onView(withId(R.id.textViewTip))
            .check(matches(withText("50")))

        onView(withId(R.id.peopleAmountSpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.peopleAmountSpinner)).check(matches(withSpinnerText(containsString("2"))));

        onView(withId(R.id.outcomePerPerson))
            .check(matches(withText("$37.50")))

    }

    @Test
    fun changeText_sameActivity3() {

        onView(withId(R.id.costOfBillEditText))
            .perform(typeText("150.00"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.seekBarTip))
            .perform(scrollTo())
            .perform(click());
        onView(withId(R.id.seekBarTip)).perform(click(swipeLeft()))

        onView(withId(R.id.textViewTip))
            .check(matches(withText("50")))

        onView(withId(R.id.peopleAmountSpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.peopleAmountSpinner)).check(matches(withSpinnerText(containsString("2"))));

        onView(withId(R.id.outcomePerPerson))
            .perform(scrollTo())
            .check(matches(withText("$112.50")))

    }





}