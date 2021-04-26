package com.crashlytics.android.answers;

import android.app.Activity;
import android.os.Bundle;
import com.crashlytics.android.answers.SessionEvent;
import io.a.a.a.a;

/* access modifiers changed from: package-private */
public class AnswersLifecycleCallbacks extends a.b {
    private final SessionAnalyticsManager analyticsManager;
    private final BackgroundManager backgroundManager;

    @Override // io.a.a.a.a.b
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // io.a.a.a.a.b
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // io.a.a.a.a.b
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public AnswersLifecycleCallbacks(SessionAnalyticsManager sessionAnalyticsManager, BackgroundManager backgroundManager2) {
        this.analyticsManager = sessionAnalyticsManager;
        this.backgroundManager = backgroundManager2;
    }

    @Override // io.a.a.a.a.b
    public void onActivityStarted(Activity activity) {
        this.analyticsManager.onLifecycle(activity, SessionEvent.Type.START);
    }

    @Override // io.a.a.a.a.b
    public void onActivityResumed(Activity activity) {
        this.analyticsManager.onLifecycle(activity, SessionEvent.Type.RESUME);
        this.backgroundManager.onActivityResumed();
    }

    @Override // io.a.a.a.a.b
    public void onActivityPaused(Activity activity) {
        this.analyticsManager.onLifecycle(activity, SessionEvent.Type.PAUSE);
        this.backgroundManager.onActivityPaused();
    }

    @Override // io.a.a.a.a.b
    public void onActivityStopped(Activity activity) {
        this.analyticsManager.onLifecycle(activity, SessionEvent.Type.STOP);
    }
}
