package com.sample.browserstack.samplecalculator;

import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor;
import androidx.test.runner.screenshot.Screenshot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Espresso tests to ensure that random operation result are
 * handled correctly and correct output is generated and the
 * editText box is updated correctly
 */


@MediumTest
@RunWith(AndroidJUnit4.class)
public class EnsureRandomTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testZeroMultiplication() throws IOException {
        onView(withId(R.id.buttonTwo)).perform(click());
        onView(withId(R.id.buttonMultiply)).perform(click());
        onView(withId(R.id.buttonZero)).perform(click());
        onView(withId(R.id.buttonEqual)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("0")));


        Set<BasicScreenCaptureProcessor> processors = new HashSet<>(Arrays.asList(new BrowserstackScreenCaptureProcessor()));
        Screenshot.capture().process(Collections.unmodifiableSet(processors));
    }

    @Test
    public void testZeroDivision() {
        onView(withId(R.id.buttonTwo)).perform(click());
        onView(withId(R.id.buttonDivide)).perform(click());
        onView(withId(R.id.buttonZero)).perform(click());
        onView(withId(R.id.buttonEqual)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("∞")));
    }

}
