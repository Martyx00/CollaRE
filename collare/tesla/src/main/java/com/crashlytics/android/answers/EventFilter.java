package com.crashlytics.android.answers;

/* access modifiers changed from: package-private */
public interface EventFilter {
    boolean skipEvent(SessionEvent sessionEvent);
}
