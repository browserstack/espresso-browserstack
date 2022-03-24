package com.sample.browserstack.samplecalculator;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor;
import androidx.test.runner.screenshot.ScreenCapture;
import androidx.test.runner.screenshot.Screenshot;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class ScreenshotUtils {

    private final String methodName;
    private final String className;
    private final CustomScreencaptureProcessor customProcesssor;
    private final Pattern SCREENSHOT_NAME_VALIDATION = Pattern.compile("[a-zA-Z0-9_-]+");

    ScreenshotUtils() {
        StackTraceElement testClass = findTestClassTraceElement(Thread.currentThread().getStackTrace());
        className = testClass.getClassName().replaceAll("[^A-Za-z0-9._-]", "_");
        methodName = testClass.getMethodName();
        customProcesssor = new CustomScreencaptureProcessor();
    }

    public void captureScreenshot(String screenshotName) {
        // ScreenshotName failing to pass this regex matcher will be skipped.
        if (!SCREENSHOT_NAME_VALIDATION.matcher(screenshotName).matches()) {
            throw new IllegalArgumentException("ScreenshotName must match " + SCREENSHOT_NAME_VALIDATION.pattern() + ".");
        } else {
            ScreenCapture capture = Screenshot.capture();
            capture.setFormat(Bitmap.CompressFormat.PNG);
            capture.setName(screenshotName);

            try {
                customProcesssor.process(capture);
            } catch (IOException e) {
                throw new RuntimeException("Unable to capture screenshot.", e);
            }
        }
    }

    /*
        Determine the className and testName of the current test being executed from the stacktrace.
     */
    private StackTraceElement findTestClassTraceElement(StackTraceElement[] trace) {
        for(int i = trace.length - 1; i >= 0; --i) {
            StackTraceElement element = trace[i];
            if ("android.test.InstrumentationTestCase".equals(element.getClassName()) && "runMethod".equals(element.getMethodName())) {
                return extractStackElement(trace, i);
            }

            if ("org.junit.runners.model.FrameworkMethod$1".equals(element.getClassName()) && "runReflectiveCall".equals(element.getMethodName())) {
                return extractStackElement(trace, i);
            }

            if ("cucumber.runtime.model.CucumberFeature".equals(element.getClassName()) && "run".equals(element.getMethodName())) {
                return extractStackElement(trace, i);
            }
        }

        throw new IllegalArgumentException("Could not find test class!");
    }

    private StackTraceElement extractStackElement(StackTraceElement[] trace, int i) {
        int testClassTraceIndex = Build.VERSION.SDK_INT >= 23 ? i - 2 : i - 3;
        return trace[testClassTraceIndex];
    }

    private class CustomScreencaptureProcessor extends BasicScreenCaptureProcessor {
        CustomScreencaptureProcessor() {
//            Storage Directory: /storage/emulated/0/Android/data/<bundle_id>/files/screenshots
//            File screenshotDir = new File(String.valueOf(ApplicationProvider.getApplicationContext().getExternalFilesDir(null)), "screenshots");

//            Storage Directory: /storage/emulated/0/Download/screenshots
            File screenshotDir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), "screenshots");
            File classDir = new File(screenshotDir, className);
            mDefaultScreenshotPath = new File(classDir, methodName);
        }

        @Override
        protected String getFilename(String filename) {
            String screenshotName = System.currentTimeMillis() + "_" + filename;
            return screenshotName;
        }
    }
}
