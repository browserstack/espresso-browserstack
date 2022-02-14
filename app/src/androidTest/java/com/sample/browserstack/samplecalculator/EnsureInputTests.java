package com.sample.browserstack.samplecalculator;

import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.squareup.spoon.Spoon;

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
 * Espresso tests to ensure that editText box is updated appropriately
 * whenever buttons are clicked
 */

@SmallTest
@RunWith(AndroidJUnit4.class)
public class EnsureInputTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = activityRule.getActivity();
    }

    @Test
    public void ensureSingleInputIsHandled() {
        Spoon.screenshot(mainActivity, "initial_state");
        onView(withId(R.id.buttonOne)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("1")));
        Spoon.screenshot(mainActivity, "post_multiple_btn_click");
    }

    @Test
    public void ensureMultipleInputIsHandled() {
        Spoon.screenshot(mainActivity, "initial_state");
        onView(withId(R.id.buttonOne)).perform(click());
        onView(withId(R.id.buttonTwo)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("12")));
        Spoon.screenshot(mainActivity, "post_multiple_btn_click");
    }
}
