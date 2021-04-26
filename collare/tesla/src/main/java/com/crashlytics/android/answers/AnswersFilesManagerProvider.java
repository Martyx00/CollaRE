package com.crashlytics.android.answers;

import android.content.Context;
import android.os.Looper;
import io.a.a.a.a.b.v;
import io.a.a.a.a.d.g;
import io.a.a.a.a.f.a;

/* access modifiers changed from: package-private */
public class AnswersFilesManagerProvider {
    static final String SESSION_ANALYTICS_FILE_NAME = "session_analytics.tap";
    static final String SESSION_ANALYTICS_TO_SEND_DIR = "session_analytics_to_send";
    final Context context;
    final a fileStore;

    public AnswersFilesManagerProvider(Context context2, a aVar) {
        this.context = context2;
        this.fileStore = aVar;
    }

    public SessionAnalyticsFilesManager getAnalyticsFilesManager() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return new SessionAnalyticsFilesManager(this.context, new SessionEventTransform(), new v(), new g(this.context, this.fileStore.a(), SESSION_ANALYTICS_FILE_NAME, SESSION_ANALYTICS_TO_SEND_DIR));
        }
        throw new IllegalStateException("AnswersFilesManagerProvider cannot be called on the main thread");
    }
}
