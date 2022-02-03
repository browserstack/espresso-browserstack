package com.sample.browserstack.samplecalculator;

import android.view.LayoutInflater;
import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import com.facebook.testing.screenshot.Screenshot;
import com.facebook.testing.screenshot.ViewHelpers;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class STFATest {

  @Rule
  public ActivityTestRule<MainActivity> activityRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);

  private MainActivity mainActivity;

  @Before
  public void setUp() {
    mainActivity = activityRule.getActivity();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void doScreenshot() {
    /*
     * Create and set up your view some how. This might be inflating,
     * or creating from a view class. You might want to set properties
     * on the view.
     */
    LayoutInflater mLayoutInflater = mainActivity.getLayoutInflater();
    View view = mLayoutInflater.inflate(R.layout.activity_main, null, false);

    /*
     * Measure and layout the view. In this example we give an exact
     * width but all the height to be WRAP_CONTENT.
     */
    ViewHelpers.setupView(view)
        .setExactWidthDp(300)
        .layout();

    /*
     * Take the actual screenshot. At the end of this call the screenshot
     * is stored on the device, and the gradle plugin takes care of
     * pulling it and displaying it to you in nice ways.
     */
    Screenshot.snap(view)
        .record();
  }

}