package com.crashlytics.android.answers;

import com.crashlytics.android.answers.SessionEvent;
import io.a.a.a.a.g.b;

class DisabledSessionAnalyticsManagerStrategy implements SessionAnalyticsManagerStrategy {
    @Override // io.a.a.a.a.d.e
    public void cancelTimeBasedFileRollOver() {
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void deleteAllEvents() {
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void processEvent(SessionEvent.Builder builder) {
    }

    @Override // io.a.a.a.a.d.e
    public boolean rollFileOver() {
        return false;
    }

    public void scheduleTimeBasedRollOverIfNeeded() {
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void sendEvents() {
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void setAnalyticsSettingsData(b bVar, String str) {
    }

    DisabledSessionAnalyticsManagerStrategy() {
    }
}
