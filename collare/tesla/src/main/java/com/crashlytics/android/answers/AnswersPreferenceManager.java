package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import io.a.a.a.a.f.c;
import io.a.a.a.a.f.d;

/* access modifiers changed from: package-private */
public class AnswersPreferenceManager {
    static final String PREFKEY_ANALYTICS_LAUNCHED = "analytics_launched";
    static final String PREF_STORE_NAME = "settings";
    private final c prefStore;

    public static AnswersPreferenceManager build(Context context) {
        return new AnswersPreferenceManager(new d(context, PREF_STORE_NAME));
    }

    AnswersPreferenceManager(c cVar) {
        this.prefStore = cVar;
    }

    @SuppressLint({"CommitPrefEdits"})
    public void setAnalyticsLaunched() {
        c cVar = this.prefStore;
        cVar.a(cVar.b().putBoolean(PREFKEY_ANALYTICS_LAUNCHED, true));
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean hasAnalyticsLaunched() {
        return this.prefStore.a().getBoolean(PREFKEY_ANALYTICS_LAUNCHED, false);
    }
}
