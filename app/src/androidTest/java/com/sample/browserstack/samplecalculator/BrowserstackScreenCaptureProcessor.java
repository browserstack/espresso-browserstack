package com.sample.browserstack.samplecalculator;

import android.os.Environment;

import androidx.test.runner.screenshot.BasicScreenCaptureProcessor;

import java.io.File;

public class BrowserstackScreenCaptureProcessor extends BasicScreenCaptureProcessor {
  public BrowserstackScreenCaptureProcessor() {
    mTag = "BrowserstackScreenCaptureProcessor";
    mDefaultFilenamePrefix = "browserstackScreenshot";
    mDefaultScreenshotPath = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));
  }
}
