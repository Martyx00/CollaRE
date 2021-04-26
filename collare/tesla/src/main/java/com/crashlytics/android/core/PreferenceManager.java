package com.crashlytics.android.core;

import android.annotation.SuppressLint;
import io.a.a.a.a.f.c;
import io.a.a.a.a.f.d;

/* access modifiers changed from: package-private */
@SuppressLint({"CommitPrefEdits"})
public class PreferenceManager {
    static final String PREF_ALWAYS_SEND_REPORTS_KEY = "always_send_reports_opt_in";
    private static final String PREF_MIGRATION_COMPLETE = "preferences_migration_complete";
    private static final boolean SHOULD_ALWAYS_SEND_REPORTS_DEFAULT = false;
    private final CrashlyticsCore kit;
    private final c preferenceStore;

    public static PreferenceManager create(c cVar, CrashlyticsCore crashlyticsCore) {
        return new PreferenceManager(cVar, crashlyticsCore);
    }

    public PreferenceManager(c cVar, CrashlyticsCore crashlyticsCore) {
        this.preferenceStore = cVar;
        this.kit = crashlyticsCore;
    }

    /* access modifiers changed from: package-private */
    public void setShouldAlwaysSendReports(boolean z) {
        c cVar = this.preferenceStore;
        cVar.a(cVar.b().putBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, z));
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAlwaysSendReports() {
        if (!this.preferenceStore.a().contains(PREF_MIGRATION_COMPLETE)) {
            d dVar = new d(this.kit);
            if (!this.preferenceStore.a().contains(PREF_ALWAYS_SEND_REPORTS_KEY) && dVar.a().contains(PREF_ALWAYS_SEND_REPORTS_KEY)) {
                boolean z = dVar.a().getBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, false);
                c cVar = this.preferenceStore;
                cVar.a(cVar.b().putBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, z));
            }
            c cVar2 = this.preferenceStore;
            cVar2.a(cVar2.b().putBoolean(PREF_MIGRATION_COMPLETE, true));
        }
        return this.preferenceStore.a().getBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, false);
    }
}
