package com.sample.browserstack.samplecalculator;

import androidx.test.filters.MediumTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Espresso tests to ensure that simple operations result in
 * correct output when the number & operations buttons are clicked
 */

@MediumTest
@RunWith(AndroidJUnit4.class)
public class EnsureOperationTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        // ActivityScenarioRule handles activity initialization automatically
        // No need to manually get activity reference in modern testing
    }

    @Test
    public void ensureAdditionWorks() {
        onView(withId(R.id.buttonOne)).perform(click());
        onView(withId(R.id.buttonTwo)).perform(click());
        onView(withId(R.id.buttonAdd)).perform(click());
        onView(withId(R.id.buttonTwo)).perform(click());
        onView(withId(R.id.buttonOne)).perform(click());
        onView(withId(R.id.buttonEqual)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("33")));
        NativeScreenshot.capture("post_addition");
    }

    @Test
    public void ensureSubtractionWorks() {
        onView(withId(R.id.buttonTwo)).perform(click());
        onView(withId(R.id.buttonTwo)).perform(click());
        onView(withId(R.id.buttonSubtract)).perform(click());
        onView(withId(R.id.buttonOne)).perform(click());
        onView(withId(R.id.buttonOne)).perform(click());
        onView(withId(R.id.buttonEqual)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("11")));
        NativeScreenshot.capture("post_subtraction");
    }

    @Test
    public void ensureMultiplicationWorks() {
        onView(withId(R.id.buttonOne)).perform(click());
        onView(withId(R.id.buttonTwo)).perform(click());
        onView(withId(R.id.buttonMultiply)).perform(click());
        onView(withId(R.id.buttonFive)).perform(click());
        onView(withId(R.id.buttonEqual)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("60")));

        NativeScreenshot.capture("post_multiplication");
    }

    @Test
    public void ensureDivisionWorks() {
        onView(withId(R.id.buttonOne)).perform(click());
        onView(withId(R.id.buttonTwo)).perform(click());
        onView(withId(R.id.buttonDivide)).perform(click());
        onView(withId(R.id.buttonThree)).perform(click());
        onView(withId(R.id.buttonEqual)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("4")));

        NativeScreenshot.capture("post_division");
    }
}
