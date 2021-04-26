package com.crashlytics.android.core;

/* access modifiers changed from: package-private */
public interface StackTraceTrimmingStrategy {
    StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr);
}
