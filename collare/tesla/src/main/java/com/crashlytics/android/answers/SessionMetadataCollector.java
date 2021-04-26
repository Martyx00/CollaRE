package com.crashlytics.android.answers;

import android.content.Context;
import io.a.a.a.a.b.i;
import io.a.a.a.a.b.r;
import java.util.Map;
import java.util.UUID;

/* access modifiers changed from: package-private */
public class SessionMetadataCollector {
    private final Context context;
    private final r idManager;
    private final String versionCode;
    private final String versionName;

    public SessionMetadataCollector(Context context2, r rVar, String str, String str2) {
        this.context = context2;
        this.idManager = rVar;
        this.versionCode = str;
        this.versionName = str2;
    }

    public SessionEventMetadata getMetadata() {
        Map<r.a, String> h = this.idManager.h();
        return new SessionEventMetadata(this.idManager.c(), UUID.randomUUID().toString(), this.idManager.b(), this.idManager.j(), h.get(r.a.FONT_TOKEN), i.m(this.context), this.idManager.d(), this.idManager.g(), this.versionCode, this.versionName);
    }
}
