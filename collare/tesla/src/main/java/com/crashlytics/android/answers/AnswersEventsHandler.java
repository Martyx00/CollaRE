package com.crashlytics.android.answers;

import android.content.Context;
import com.crashlytics.android.answers.SessionEvent;
import io.a.a.a.a.d.d;
import io.a.a.a.a.e.e;
import io.a.a.a.a.g.b;
import io.a.a.a.c;
import io.a.a.a.i;
import java.util.concurrent.ScheduledExecutorService;

/* access modifiers changed from: package-private */
public class AnswersEventsHandler implements d {
    private final Context context;
    final ScheduledExecutorService executor;
    private final AnswersFilesManagerProvider filesManagerProvider;
    private final FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter;
    private final i kit;
    private final SessionMetadataCollector metadataCollector;
    private final e requestFactory;
    SessionAnalyticsManagerStrategy strategy = new DisabledSessionAnalyticsManagerStrategy();

    public AnswersEventsHandler(i iVar, Context context2, AnswersFilesManagerProvider answersFilesManagerProvider, SessionMetadataCollector sessionMetadataCollector, e eVar, ScheduledExecutorService scheduledExecutorService, FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter2) {
        this.kit = iVar;
        this.context = context2;
        this.filesManagerProvider = answersFilesManagerProvider;
        this.metadataCollector = sessionMetadataCollector;
        this.requestFactory = eVar;
        this.executor = scheduledExecutorService;
        this.firebaseAnalyticsApiAdapter = firebaseAnalyticsApiAdapter2;
    }

    public void processEventAsync(SessionEvent.Builder builder) {
        processEvent(builder, false, false);
    }

    public void processEventAsyncAndFlush(SessionEvent.Builder builder) {
        processEvent(builder, false, true);
    }

    public void processEventSync(SessionEvent.Builder builder) {
        processEvent(builder, true, false);
    }

    public void setAnalyticsSettingsData(final b bVar, final String str) {
        executeAsync(new Runnable() {
            /* class com.crashlytics.android.answers.AnswersEventsHandler.AnonymousClass1 */

            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.setAnalyticsSettingsData(bVar, str);
                } catch (Exception e) {
                    c.g().e(Answers.TAG, "Failed to set analytics settings data", e);
                }
            }
        });
    }

    public void disable() {
        executeAsync(new Runnable() {
            /* class com.crashlytics.android.answers.AnswersEventsHandler.AnonymousClass2 */

            public void run() {
                try {
                    SessionAnalyticsManagerStrategy sessionAnalyticsManagerStrategy = AnswersEventsHandler.this.strategy;
                    AnswersEventsHandler.this.strategy = new DisabledSessionAnalyticsManagerStrategy();
                    sessionAnalyticsManagerStrategy.deleteAllEvents();
                } catch (Exception e) {
                    c.g().e(Answers.TAG, "Failed to disable events", e);
                }
            }
        });
    }

    @Override // io.a.a.a.a.d.d
    public void onRollOver(String str) {
        executeAsync(new Runnable() {
            /* class com.crashlytics.android.answers.AnswersEventsHandler.AnonymousClass3 */

            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.sendEvents();
                } catch (Exception e) {
                    c.g().e(Answers.TAG, "Failed to send events files", e);
                }
            }
        });
    }

    public void enable() {
        executeAsync(new Runnable() {
            /* class com.crashlytics.android.answers.AnswersEventsHandler.AnonymousClass4 */

            public void run() {
                try {
                    SessionEventMetadata metadata = AnswersEventsHandler.this.metadataCollector.getMetadata();
                    SessionAnalyticsFilesManager analyticsFilesManager = AnswersEventsHandler.this.filesManagerProvider.getAnalyticsFilesManager();
                    analyticsFilesManager.registerRollOverListener(AnswersEventsHandler.this);
                    AnswersEventsHandler.this.strategy = new EnabledSessionAnalyticsManagerStrategy(AnswersEventsHandler.this.kit, AnswersEventsHandler.this.context, AnswersEventsHandler.this.executor, analyticsFilesManager, AnswersEventsHandler.this.requestFactory, metadata, AnswersEventsHandler.this.firebaseAnalyticsApiAdapter);
                } catch (Exception e) {
                    c.g().e(Answers.TAG, "Failed to enable events", e);
                }
            }
        });
    }

    public void flushEvents() {
        executeAsync(new Runnable() {
            /* class com.crashlytics.android.answers.AnswersEventsHandler.AnonymousClass5 */

            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.rollFileOver();
                } catch (Exception e) {
                    c.g().e(Answers.TAG, "Failed to flush events", e);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void processEvent(final SessionEvent.Builder builder, boolean z, final boolean z2) {
        AnonymousClass6 r0 = new Runnable() {
            /* class com.crashlytics.android.answers.AnswersEventsHandler.AnonymousClass6 */

            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.processEvent(builder);
                    if (z2) {
                        AnswersEventsHandler.this.strategy.rollFileOver();
                    }
                } catch (Exception e) {
                    c.g().e(Answers.TAG, "Failed to process event", e);
                }
            }
        };
        if (z) {
            executeSync(r0);
        } else {
            executeAsync(r0);
        }
    }

    private void executeSync(Runnable runnable) {
        try {
            this.executor.submit(runnable).get();
        } catch (Exception e) {
            c.g().e(Answers.TAG, "Failed to run events task", e);
        }
    }

    private void executeAsync(Runnable runnable) {
        try {
            this.executor.submit(runnable);
        } catch (Exception e) {
            c.g().e(Answers.TAG, "Failed to submit events task", e);
        }
    }
}
