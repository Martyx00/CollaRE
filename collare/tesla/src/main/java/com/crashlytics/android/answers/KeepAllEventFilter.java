package com.crashlytics.android.answers;

/* access modifiers changed from: package-private */
public class KeepAllEventFilter implements EventFilter {
    @Override // com.crashlytics.android.answers.EventFilter
    public boolean skipEvent(SessionEvent sessionEvent) {
        return false;
    }

    KeepAllEventFilter() {
    }
}
