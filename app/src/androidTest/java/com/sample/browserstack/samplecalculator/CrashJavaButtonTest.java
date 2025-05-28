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
public class CrashJavaButtonTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void crashJavaAppUnderTest() {
        // this will throw the RuntimeException in Java,
        // test harness will catch it and the process will record the stack-trace
        onView(withId(R.id.buttonCrashJava)).perform(click());
    }
}
