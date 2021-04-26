package com.crashlytics.android.answers;

import com.crashlytics.android.answers.SessionEvent;
import io.a.a.a.a.d.e;
import io.a.a.a.a.g.b;

interface SessionAnalyticsManagerStrategy extends e {
    void deleteAllEvents();

    void processEvent(SessionEvent.Builder builder);

    void sendEvents();

    void setAnalyticsSettingsData(b bVar, String str);
}
