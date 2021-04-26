package com.crashlytics.android.answers;

import android.content.Context;
import com.crashlytics.android.answers.SessionEvent;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import io.a.a.a.a.b.g;
import io.a.a.a.a.d.f;
import io.a.a.a.a.e.e;
import io.a.a.a.a.g.b;
import io.a.a.a.c;
import io.a.a.a.i;
import io.a.a.a.l;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class EnabledSessionAnalyticsManagerStrategy implements SessionAnalyticsManagerStrategy {
    static final int UNDEFINED_ROLLOVER_INTERVAL_SECONDS = -1;
    g apiKey = new g();
    private final Context context;
    boolean customEventsEnabled = true;
    EventFilter eventFilter = new KeepAllEventFilter();
    private final ScheduledExecutorService executorService;
    private final SessionAnalyticsFilesManager filesManager;
    f filesSender;
    private final FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter;
    boolean forwardToFirebaseAnalyticsEnabled = false;
    private final e httpRequestFactory;
    boolean includePurchaseEventsInForwardedEvents = false;
    private final i kit;
    final SessionEventMetadata metadata;
    boolean predefinedEventsEnabled = true;
    private final AtomicReference<ScheduledFuture<?>> rolloverFutureRef = new AtomicReference<>();
    volatile int rolloverIntervalSeconds = -1;

    public EnabledSessionAnalyticsManagerStrategy(i iVar, Context context2, ScheduledExecutorService scheduledExecutorService, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, e eVar, SessionEventMetadata sessionEventMetadata, FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter2) {
        this.kit = iVar;
        this.context = context2;
        this.executorService = scheduledExecutorService;
        this.filesManager = sessionAnalyticsFilesManager;
        this.httpRequestFactory = eVar;
        this.metadata = sessionEventMetadata;
        this.firebaseAnalyticsApiAdapter = firebaseAnalyticsApiAdapter2;
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void setAnalyticsSettingsData(b bVar, String str) {
        this.filesSender = AnswersRetryFilesSender.build(new SessionAnalyticsFilesSender(this.kit, str, bVar.f6022a, this.httpRequestFactory, this.apiKey.a(this.context)));
        this.filesManager.setAnalyticsSettingsData(bVar);
        this.forwardToFirebaseAnalyticsEnabled = bVar.f;
        this.includePurchaseEventsInForwardedEvents = bVar.g;
        l g = c.g();
        StringBuilder sb = new StringBuilder();
        sb.append("Firebase analytics forwarding ");
        sb.append(this.forwardToFirebaseAnalyticsEnabled ? "enabled" : "disabled");
        g.a(Answers.TAG, sb.toString());
        l g2 = c.g();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Firebase analytics including purchase events ");
        sb2.append(this.includePurchaseEventsInForwardedEvents ? "enabled" : "disabled");
        g2.a(Answers.TAG, sb2.toString());
        this.customEventsEnabled = bVar.h;
        l g3 = c.g();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Custom event tracking ");
        sb3.append(this.customEventsEnabled ? "enabled" : "disabled");
        g3.a(Answers.TAG, sb3.toString());
        this.predefinedEventsEnabled = bVar.i;
        l g4 = c.g();
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Predefined event tracking ");
        sb4.append(this.predefinedEventsEnabled ? "enabled" : "disabled");
        g4.a(Answers.TAG, sb4.toString());
        if (bVar.k > 1) {
            c.g().a(Answers.TAG, "Event sampling enabled");
            this.eventFilter = new SamplingEventFilter(bVar.k);
        }
        this.rolloverIntervalSeconds = bVar.f6023b;
        scheduleTimeBasedFileRollOver(0, (long) this.rolloverIntervalSeconds);
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void processEvent(SessionEvent.Builder builder) {
        SessionEvent build = builder.build(this.metadata);
        if (!this.customEventsEnabled && SessionEvent.Type.CUSTOM.equals(build.type)) {
            l g = c.g();
            g.a(Answers.TAG, "Custom events tracking disabled - skipping event: " + build);
        } else if (!this.predefinedEventsEnabled && SessionEvent.Type.PREDEFINED.equals(build.type)) {
            l g2 = c.g();
            g2.a(Answers.TAG, "Predefined events tracking disabled - skipping event: " + build);
        } else if (this.eventFilter.skipEvent(build)) {
            l g3 = c.g();
            g3.a(Answers.TAG, "Skipping filtered event: " + build);
        } else {
            try {
                this.filesManager.writeEvent(build);
            } catch (IOException e) {
                l g4 = c.g();
                g4.e(Answers.TAG, "Failed to write event: " + build, e);
            }
            scheduleTimeBasedRollOverIfNeeded();
            boolean z = SessionEvent.Type.CUSTOM.equals(build.type) || SessionEvent.Type.PREDEFINED.equals(build.type);
            boolean equals = ProductAction.ACTION_PURCHASE.equals(build.predefinedType);
            if (this.forwardToFirebaseAnalyticsEnabled && z) {
                if (!equals || this.includePurchaseEventsInForwardedEvents) {
                    try {
                        this.firebaseAnalyticsApiAdapter.processEvent(build);
                    } catch (Exception e2) {
                        l g5 = c.g();
                        g5.e(Answers.TAG, "Failed to map event to Firebase: " + build, e2);
                    }
                }
            }
        }
    }

    public void scheduleTimeBasedRollOverIfNeeded() {
        if (this.rolloverIntervalSeconds != -1) {
            scheduleTimeBasedFileRollOver((long) this.rolloverIntervalSeconds, (long) this.rolloverIntervalSeconds);
        }
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void sendEvents() {
        if (this.filesSender == null) {
            io.a.a.a.a.b.i.a(this.context, "skipping files send because we don't yet know the target endpoint");
            return;
        }
        io.a.a.a.a.b.i.a(this.context, "Sending all files");
        List<File> batchOfFilesToSend = this.filesManager.getBatchOfFilesToSend();
        int i = 0;
        while (true) {
            try {
                if (batchOfFilesToSend.size() <= 0) {
                    break;
                }
                io.a.a.a.a.b.i.a(this.context, String.format(Locale.US, "attempt to send batch of %d files", Integer.valueOf(batchOfFilesToSend.size())));
                boolean send = this.filesSender.send(batchOfFilesToSend);
                if (send) {
                    i += batchOfFilesToSend.size();
                    this.filesManager.deleteSentFiles(batchOfFilesToSend);
                }
                if (!send) {
                    break;
                }
                batchOfFilesToSend = this.filesManager.getBatchOfFilesToSend();
            } catch (Exception e) {
                Context context2 = this.context;
                io.a.a.a.a.b.i.a(context2, "Failed to send batch of analytics files to server: " + e.getMessage(), e);
            }
        }
        if (i == 0) {
            this.filesManager.deleteOldestInRollOverIfOverMax();
        }
    }

    @Override // io.a.a.a.a.d.e
    public void cancelTimeBasedFileRollOver() {
        if (this.rolloverFutureRef.get() != null) {
            io.a.a.a.a.b.i.a(this.context, "Cancelling time-based rollover because no events are currently being generated.");
            this.rolloverFutureRef.get().cancel(false);
            this.rolloverFutureRef.set(null);
        }
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void deleteAllEvents() {
        this.filesManager.deleteAllEventsFiles();
    }

    @Override // io.a.a.a.a.d.e
    public boolean rollFileOver() {
        try {
            return this.filesManager.rollFileOver();
        } catch (IOException e) {
            io.a.a.a.a.b.i.a(this.context, "Failed to roll file over.", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void scheduleTimeBasedFileRollOver(long j, long j2) {
        if (this.rolloverFutureRef.get() == null) {
            io.a.a.a.a.d.i iVar = new io.a.a.a.a.d.i(this.context, this);
            Context context2 = this.context;
            io.a.a.a.a.b.i.a(context2, "Scheduling time based file roll over every " + j2 + " seconds");
            try {
                this.rolloverFutureRef.set(this.executorService.scheduleAtFixedRate(iVar, j, j2, TimeUnit.SECONDS));
            } catch (RejectedExecutionException e) {
                io.a.a.a.a.b.i.a(this.context, "Failed to schedule time based file roll over", e);
            }
        }
    }
}
