package com.facebook.react.bridge;

import android.os.Handler;
import android.os.Looper;

public class UiThreadUtil {
    private static Handler sMainHandler;

    public static boolean isOnUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void assertOnUiThread() {
        SoftAssertions.assertCondition(isOnUiThread(), "Expected to run on UI thread!");
    }

    public static void assertNotOnUiThread() {
        SoftAssertions.assertCondition(!isOnUiThread(), "Expected not to run on UI thread!");
    }

    public static void runOnUiThread(Runnable runnable) {
        synchronized (UiThreadUtil.class) {
            if (sMainHandler == null) {
                sMainHandler = new Handler(Looper.getMainLooper());
            }
        }
        sMainHandler.post(runnable);
    }
}
