package com.crashlytics.android.core;

/* access modifiers changed from: package-private */
public class TrimmedThrowableData {
    public final TrimmedThrowableData cause;
    public final String className;
    public final String localizedMessage;
    public final StackTraceElement[] stacktrace;

    public TrimmedThrowableData(Throwable th, StackTraceTrimmingStrategy stackTraceTrimmingStrategy) {
        this.localizedMessage = th.getLocalizedMessage();
        this.className = th.getClass().getName();
        this.stacktrace = stackTraceTrimmingStrategy.getTrimmedStackTrace(th.getStackTrace());
        Throwable cause2 = th.getCause();
        this.cause = cause2 != null ? new TrimmedThrowableData(cause2, stackTraceTrimmingStrategy) : null;
    }
}
