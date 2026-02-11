package com.sample.browserstack.samplecalculator;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.util.Log;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.browserstack.accessibility.AccessibilityUtils;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void myBeforeEach() throws Exception{
        Log.d("TRANSFORMATION_LOGS", "From @Before");
    }

    @Test
    public void testCalculatorBasicOperation() {
        android.util.Log.d("TRANSFORMATION_LOGS", "IN TEST");
        Espresso.onView(withId(R.id.buttonTwo)).perform(click());
        Espresso.onView(withId(R.id.buttonAdd)).perform(click());
        Espresso.onView(withId(R.id.buttonThree)).perform(click());
        Espresso.onView(withId(R.id.buttonEqual)).perform(click());
    }

    @Test
    public void exceptionFailedTest() throws Exception {
        Espresso.onView(withId(R.id.buttonFive)).perform(click());
        Espresso.onView(withId(R.id.buttonMultiply)).perform(click());
        Espresso.onView(withId(R.id.buttonTwo)).perform(click());
        Espresso.onView(withId(R.id.buttonEqual)).perform(click());
        throw new Exception("EXCEPTION FROM TEST");
    }

    @Test
    @Category(ExampleInstrumentedTest.class)
    public void assertionFailedTest() throws Exception {
        Log.d("TEST_LOGS", "testStarted");
        
        Espresso.onView(withId(R.id.buttonOne)).perform(click());
        Espresso.onView(withId(R.id.buttonAdd)).perform(click());
        Espresso.onView(withId(R.id.buttonOne)).perform(click());
        Espresso.onView(withId(R.id.buttonEqual)).perform(click());

        Log.d("PERFORM_SCAN_LOGS", "beforeScan");
        AccessibilityUtils.performEspressoAppAccessibilityScan("");
        Log.d("PERFORM_SCAN_LOGS", "afterScan");

        Thread.sleep(10000);

        Log.d("A11Y_RESULTS_SUMMARY", AccessibilityUtils.getResultsSummary().toString());
        Log.d("A11Y_RESULTS", AccessibilityUtils.getResults().toString());

        Assert.assertTrue(false);
        Thread.sleep(2000);

        Log.d("TEST_LOGS", "testEnded");
    }

    @Test
    @Category(MainActivity.class)
    public void testTags() throws InterruptedException{
        Log.d("TEST_LOGS", "testStarted");
        
        Assert.assertTrue(true);
        Thread.sleep(10000);

        Log.d("A11Y_RESULTS_SUMMARY", AccessibilityUtils.getResultsSummary().toString());
        Log.d("A11Y_RESULTS", AccessibilityUtils.getResults().toString());
        
        Log.d("TEST_LOGS", "testEnded");
    }

    @Test
    @Ignore
    public void testIgnored() throws InterruptedException {
        Log.d("TRANSFORMATION_LOGS", "testIgnored");
        Thread.sleep(2000);
    }

    @Test
    @Ignore("IGNORE MESSAGE")
    public void testIgnoredWithMessage() throws InterruptedException {
        Log.d("TRANSFORMATION_LOGS", "testIgnoredWithMessage");
        Thread.sleep(2000);
    }

    @Test
    public void testAssumptionFailure() throws InterruptedException {
        Assume.assumeTrue(false);
        Thread.sleep(2000);
    }
}
