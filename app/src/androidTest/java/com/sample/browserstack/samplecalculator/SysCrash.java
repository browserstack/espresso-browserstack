package com.sample.browserstack.samplecalculator;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SysCrash {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void crashAppUnderTest() {
        // Will invoke nativeCrash() and produce a real SIGSEGV
        onView(withId(R.id.buttonCrash)).perform(click());

        // (no further assertions—test will fail when the process dies)
    }
}
