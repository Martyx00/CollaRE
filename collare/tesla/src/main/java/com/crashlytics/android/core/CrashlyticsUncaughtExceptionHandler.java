package com.crashlytics.android.core;

import io.a.a.a.a.g.t;
import io.a.a.a.c;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

/* access modifiers changed from: package-private */
public class CrashlyticsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final boolean firebaseCrashlyticsClientFlag;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);
    private final SettingsDataProvider settingsDataProvider;

    /* access modifiers changed from: package-private */
    public interface CrashListener {
        void onUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable th, boolean z);
    }

    /* access modifiers changed from: package-private */
    public interface SettingsDataProvider {
        t getSettingsData();
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener2, SettingsDataProvider settingsDataProvider2, boolean z, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.crashListener = crashListener2;
        this.settingsDataProvider = settingsDataProvider2;
        this.firebaseCrashlyticsClientFlag = z;
        this.defaultHandler = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        this.isHandlingException.set(true);
        try {
            this.crashListener.onUncaughtException(this.settingsDataProvider, thread, th, this.firebaseCrashlyticsClientFlag);
        } catch (Exception e) {
            c.g().e(CrashlyticsCore.TAG, "An error occurred in the uncaught exception handler", e);
        } catch (Throwable th2) {
            c.g().a(CrashlyticsCore.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.defaultHandler.uncaughtException(thread, th);
            this.isHandlingException.set(false);
            throw th2;
        }
        c.g().a(CrashlyticsCore.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
        this.defaultHandler.uncaughtException(thread, th);
        this.isHandlingException.set(false);
    }

    /* access modifiers changed from: package-private */
    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }
}
