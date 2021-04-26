package com.crashlytics.android.answers;

import android.content.Context;
import io.a.a.a.a.b.k;
import io.a.a.a.a.d.b;
import io.a.a.a.a.d.c;
import java.util.UUID;

/* access modifiers changed from: package-private */
public class SessionAnalyticsFilesManager extends b<SessionEvent> {
    private static final String SESSION_ANALYTICS_TO_SEND_FILE_EXTENSION = ".tap";
    private static final String SESSION_ANALYTICS_TO_SEND_FILE_PREFIX = "sa";
    private io.a.a.a.a.g.b analyticsSettingsData;

    SessionAnalyticsFilesManager(Context context, SessionEventTransform sessionEventTransform, k kVar, c cVar) {
        super(context, sessionEventTransform, kVar, cVar, 100);
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.a.d.b
    public String generateUniqueRollOverFileName() {
        UUID randomUUID = UUID.randomUUID();
        return SESSION_ANALYTICS_TO_SEND_FILE_PREFIX + b.ROLL_OVER_FILE_NAME_SEPARATOR + randomUUID.toString() + b.ROLL_OVER_FILE_NAME_SEPARATOR + this.currentTimeProvider.a() + SESSION_ANALYTICS_TO_SEND_FILE_EXTENSION;
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.a.d.b
    public int getMaxFilesToKeep() {
        io.a.a.a.a.g.b bVar = this.analyticsSettingsData;
        return bVar == null ? super.getMaxFilesToKeep() : bVar.e;
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.a.d.b
    public int getMaxByteSizePerFile() {
        io.a.a.a.a.g.b bVar = this.analyticsSettingsData;
        return bVar == null ? super.getMaxByteSizePerFile() : bVar.f6024c;
    }

    /* access modifiers changed from: package-private */
    public void setAnalyticsSettingsData(io.a.a.a.a.g.b bVar) {
        this.analyticsSettingsData = bVar;
    }
}
