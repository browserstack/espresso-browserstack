package com.sample.browserstack.samplecalculator;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;

import androidx.test.runner.screenshot.BasicScreenCaptureProcessor;
import androidx.test.runner.screenshot.ScreenCapture;
import androidx.test.runner.screenshot.Screenshot;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public final class NativeScreenshot {

    private static String methodName;
    private static String className;
    private static final Pattern SCREENSHOT_NAME_VALIDATION = Pattern.compile("[a-zA-Z0-9_-]+");

    private NativeScreenshot() {}

    /**
     * Captures screenshot using Android Screenshot library and stores in the filesystem.
     * Special Cases:
     * If the screenshotName contains spaces or does not pass validation, the corresponding
     * screenshot is not visible on BrowserStack's Dashboard.
     * If there is any runtime exception while capturing screenshot, the method throws
     * Exception and the test might fail if exception is not handled properly.
     * @param screenshotName    a screenshot identifier
     * @return path to the screenshot file
     */
    public static String capture(String screenshotName) {
        StackTraceElement testClass = findTestClassTraceElement(Thread.currentThread().getStackTrace());
        className = testClass.getClassName().replaceAll("[^A-Za-z0-9._-]", "_");
        methodName = testClass.getMethodName();
        EspressoScreenCaptureProcessor screenCaptureProcessor = new EspressoScreenCaptureProcessor();

        if (!SCREENSHOT_NAME_VALIDATION.matcher(screenshotName).matches()) {
            throw new IllegalArgumentException("ScreenshotName must match " + SCREENSHOT_NAME_VALIDATION.pattern() + ".");
        } else {
            ScreenCapture capture = Screenshot.capture();
            capture.setFormat(Bitmap.CompressFormat.PNG);
            capture.setName(screenshotName);

            try {
                return screenCaptureProcessor.process(capture);
            } catch (IOException e) {
                throw new RuntimeException("Unable to capture screenshot.", e);
            }
        }
    }

    /**
     * Extracts the currently executing test's trace element based on the test runner
     * or any framework being used.
     * @param trace stacktrace of the currently running test
     * @return StackTrace Element corresponding to the current test being executed.
     */
    private static StackTraceElement findTestClassTraceElement(StackTraceElement[] trace) {
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

    /**
     * Based on the test runner or framework being used, extracts the exact traceElement.
     * @param trace stacktrace of the currently running test
     * @param i a reference index
     * @return trace element based on the index passed
     */
    private static StackTraceElement extractStackElement(StackTraceElement[] trace, int i) {
        int testClassTraceIndex = Build.VERSION.SDK_INT >= 23 ? i - 2 : i - 3;
        return trace[testClassTraceIndex];
    }

    private static class EspressoScreenCaptureProcessor extends BasicScreenCaptureProcessor {
        private static final String SCREENSHOT = "screenshots";

        EspressoScreenCaptureProcessor() {
            File screenshotDir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), SCREENSHOT);
            File classDir = new File(screenshotDir, className);
            mDefaultScreenshotPath = new File(classDir, methodName);
        }

        /**
         * Converts the filename to a standard path to be stored on device.
         * Example: "post_addition" converts to "1648038895211_post_addition"
         * which is later suffixed by the file extension i.e. png.
         * @param filename  a screenshot identifier
         * @return custom filename format
         */
        @Override
        protected String getFilename(String filename) {
            return System.currentTimeMillis() + "_" + filename;
        }
    }
}
