package com.crashlytics.android.core;

import android.content.Context;
import android.util.Log;
import com.crashlytics.android.answers.AppMeasurementEventLogger;
import com.crashlytics.android.answers.EventLogger;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import io.a.a.a.a.b.g;
import io.a.a.a.a.b.n;
import io.a.a.a.a.b.q;
import io.a.a.a.a.b.r;
import io.a.a.a.a.c.d;
import io.a.a.a.a.c.m;
import io.a.a.a.a.e.e;
import io.a.a.a.a.f.b;
import io.a.a.a.a.g.t;
import io.a.a.a.c;
import io.a.a.a.i;
import io.a.a.a.l;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.net.ssl.HttpsURLConnection;

@d(a = {CrashlyticsNdkDataProvider.class})
public class CrashlyticsCore extends i<Void> {
    static final float CLS_DEFAULT_PROCESS_DELAY = 1.0f;
    static final String CRASHLYTICS_REQUIRE_BUILD_ID = "com.crashlytics.RequireBuildId";
    static final boolean CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT = true;
    static final String CRASH_MARKER_FILE_NAME = "crash_marker";
    static final int DEFAULT_MAIN_HANDLER_TIMEOUT_SEC = 4;
    private static final String INITIALIZATION_MARKER_FILE_NAME = "initialization_marker";
    static final int MAX_ATTRIBUTES = 64;
    static final int MAX_ATTRIBUTE_SIZE = 1024;
    private static final String MISSING_BUILD_ID_MSG = "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.";
    private static final String PREFERENCE_STORE_NAME = "com.crashlytics.android.core.CrashlyticsCore";
    public static final String TAG = "CrashlyticsCore";
    private final ConcurrentHashMap<String, String> attributes;
    private CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsController controller;
    private CrashlyticsFileMarker crashMarker;
    private CrashlyticsNdkDataProvider crashlyticsNdkDataProvider;
    private float delay;
    private boolean disabled;
    private e httpRequestFactory;
    private CrashlyticsFileMarker initializationMarker;
    private CrashlyticsListener listener;
    private final PinningInfoProvider pinningInfo;
    private final long startTime;
    private String userEmail;
    private String userId;
    private String userName;

    @Override // io.a.a.a.i
    public String getIdentifier() {
        return "com.crashlytics.sdk.android.crashlytics-core";
    }

    @Override // io.a.a.a.i
    public String getVersion() {
        return "2.6.4.27";
    }

    public static class Builder {
        private float delay = -1.0f;
        private boolean disabled = false;
        private CrashlyticsListener listener;
        private PinningInfoProvider pinningInfoProvider;

        public Builder delay(float f) {
            if (f <= BitmapDescriptorFactory.HUE_RED) {
                throw new IllegalArgumentException("delay must be greater than 0");
            } else if (this.delay <= BitmapDescriptorFactory.HUE_RED) {
                this.delay = f;
                return this;
            } else {
                throw new IllegalStateException("delay already set.");
            }
        }

        public Builder listener(CrashlyticsListener crashlyticsListener) {
            if (crashlyticsListener == null) {
                throw new IllegalArgumentException("listener must not be null.");
            } else if (this.listener == null) {
                this.listener = crashlyticsListener;
                return this;
            } else {
                throw new IllegalStateException("listener already set.");
            }
        }

        @Deprecated
        public Builder pinningInfo(PinningInfoProvider pinningInfoProvider2) {
            if (pinningInfoProvider2 == null) {
                throw new IllegalArgumentException("pinningInfoProvider must not be null.");
            } else if (this.pinningInfoProvider == null) {
                this.pinningInfoProvider = pinningInfoProvider2;
                return this;
            } else {
                throw new IllegalStateException("pinningInfoProvider already set.");
            }
        }

        public Builder disabled(boolean z) {
            this.disabled = z;
            return this;
        }

        public CrashlyticsCore build() {
            if (this.delay < BitmapDescriptorFactory.HUE_RED) {
                this.delay = CrashlyticsCore.CLS_DEFAULT_PROCESS_DELAY;
            }
            return new CrashlyticsCore(this.delay, this.listener, this.pinningInfoProvider, this.disabled);
        }
    }

    public CrashlyticsCore() {
        this(CLS_DEFAULT_PROCESS_DELAY, null, null, false);
    }

    CrashlyticsCore(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z) {
        this(f, crashlyticsListener, pinningInfoProvider, z, n.a("Crashlytics Exception Handler"));
    }

    CrashlyticsCore(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z, ExecutorService executorService) {
        this.userId = null;
        this.userEmail = null;
        this.userName = null;
        this.delay = f;
        this.listener = crashlyticsListener == null ? new NoOpListener() : crashlyticsListener;
        this.pinningInfo = pinningInfoProvider;
        this.disabled = z;
        this.backgroundWorker = new CrashlyticsBackgroundWorker(executorService);
        this.attributes = new ConcurrentHashMap<>();
        this.startTime = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.i
    public boolean onPreExecute() {
        return onPreExecute(super.getContext());
    }

    /* access modifiers changed from: package-private */
    public boolean onPreExecute(Context context) {
        String a2;
        if (!new q().c(context)) {
            c.g().a(TAG, "Crashlytics is disabled, because data collection is disabled by Firebase.");
            this.disabled = CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        }
        if (this.disabled || (a2 = new g().a(context)) == null) {
            return false;
        }
        String m = io.a.a.a.a.b.i.m(context);
        if (isBuildIdValid(m, io.a.a.a.a.b.i.a(context, CRASHLYTICS_REQUIRE_BUILD_ID, (boolean) CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT))) {
            try {
                l g = c.g();
                g.c(TAG, "Initializing Crashlytics " + getVersion());
                b bVar = new b(this);
                this.crashMarker = new CrashlyticsFileMarker(CRASH_MARKER_FILE_NAME, bVar);
                this.initializationMarker = new CrashlyticsFileMarker(INITIALIZATION_MARKER_FILE_NAME, bVar);
                PreferenceManager create = PreferenceManager.create(new io.a.a.a.a.f.d(getContext(), PREFERENCE_STORE_NAME), this);
                CrashlyticsPinningInfoProvider crashlyticsPinningInfoProvider = this.pinningInfo != null ? new CrashlyticsPinningInfoProvider(this.pinningInfo) : null;
                this.httpRequestFactory = new io.a.a.a.a.e.b(c.g());
                this.httpRequestFactory.a(crashlyticsPinningInfoProvider);
                r idManager = getIdManager();
                AppData create2 = AppData.create(context, idManager, a2, m);
                ManifestUnityVersionProvider manifestUnityVersionProvider = new ManifestUnityVersionProvider(context, create2.packageName);
                AppMeasurementEventListenerRegistrar instanceFrom = DefaultAppMeasurementEventListenerRegistrar.instanceFrom(this);
                EventLogger eventLogger = AppMeasurementEventLogger.getEventLogger(context);
                l g2 = c.g();
                g2.a(TAG, "Installer package name is: " + create2.installerPackageName);
                this.controller = new CrashlyticsController(this, this.backgroundWorker, this.httpRequestFactory, idManager, create, bVar, create2, manifestUnityVersionProvider, instanceFrom, eventLogger);
                boolean didPreviousInitializationFail = didPreviousInitializationFail();
                checkForPreviousCrash();
                this.controller.enableExceptionHandling(Thread.getDefaultUncaughtExceptionHandler(), new q().b(context));
                if (!didPreviousInitializationFail || !io.a.a.a.a.b.i.n(context)) {
                    c.g().a(TAG, "Exception handling initialization successful");
                    return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
                }
                c.g().a(TAG, "Crashlytics did not finish previous background initialization. Initializing synchronously.");
                finishInitSynchronously();
                return false;
            } catch (Exception e) {
                c.g().e(TAG, "Crashlytics was not started due to an exception during initialization", e);
                this.controller = null;
                return false;
            }
        } else {
            throw new m(MISSING_BUILD_ID_MSG);
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.i
    public Void doInBackground() {
        markInitializationStarted();
        this.controller.cleanInvalidTempFiles();
        try {
            this.controller.registerDevicePowerStateListener();
            t b2 = io.a.a.a.a.g.q.a().b();
            if (b2 == null) {
                c.g().d(TAG, "Received null settings, skipping report submission!");
                markInitializationComplete();
                return null;
            }
            this.controller.registerAnalyticsEventListener(b2);
            if (!b2.f6072d.f6046c) {
                c.g().a(TAG, "Collection of crash reports disabled in Crashlytics settings.");
                markInitializationComplete();
                return null;
            } else if (!new q().c(getContext())) {
                c.g().a(TAG, "Automatic collection of crash reports disabled by Firebase settings.");
                markInitializationComplete();
                return null;
            } else {
                CrashlyticsNdkData nativeCrashData = getNativeCrashData();
                if (nativeCrashData != null && !this.controller.finalizeNativeReport(nativeCrashData)) {
                    c.g().a(TAG, "Could not finalize previous NDK sessions.");
                }
                if (!this.controller.finalizeSessions(b2.f6070b)) {
                    c.g().a(TAG, "Could not finalize previous sessions.");
                }
                this.controller.submitAllReports(this.delay, b2);
                markInitializationComplete();
                return null;
            }
        } catch (Exception e) {
            c.g().e(TAG, "Crashlytics encountered a problem during asynchronous initialization.", e);
        } catch (Throwable th) {
            markInitializationComplete();
            throw th;
        }
    }

    public static CrashlyticsCore getInstance() {
        return (CrashlyticsCore) c.a(CrashlyticsCore.class);
    }

    public PinningInfoProvider getPinningInfoProvider() {
        if (!this.disabled) {
            return this.pinningInfo;
        }
        return null;
    }

    public void logException(Throwable th) {
        if (this.disabled || !ensureFabricWithCalled("prior to logging exceptions.")) {
            return;
        }
        if (th == null) {
            c.g().a(5, TAG, "Crashlytics is ignoring a request to log a null exception.");
        } else {
            this.controller.writeNonFatalException(Thread.currentThread(), th);
        }
    }

    public void log(String str) {
        doLog(3, TAG, str);
    }

    private void doLog(int i, String str, String str2) {
        if (!this.disabled && ensureFabricWithCalled("prior to logging messages.")) {
            this.controller.writeToLog(System.currentTimeMillis() - this.startTime, formatLogMessage(i, str, str2));
        }
    }

    public void log(int i, String str, String str2) {
        doLog(i, str, str2);
        c.g().a(i, "" + str, "" + str2, CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT);
    }

    public void setUserIdentifier(String str) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting user data.")) {
            this.userId = sanitizeAttribute(str);
            this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setUserName(String str) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting user data.")) {
            this.userName = sanitizeAttribute(str);
            this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setUserEmail(String str) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting user data.")) {
            this.userEmail = sanitizeAttribute(str);
            this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setString(String str, String str2) {
        String str3;
        if (this.disabled || !ensureFabricWithCalled("prior to setting keys.")) {
            return;
        }
        if (str == null) {
            Context context = getContext();
            if (context == null || !io.a.a.a.a.b.i.i(context)) {
                c.g().e(TAG, "Attempting to set custom attribute with null key, ignoring.", null);
                return;
            }
            throw new IllegalArgumentException("Custom attribute key must not be null.");
        }
        String sanitizeAttribute = sanitizeAttribute(str);
        if (this.attributes.size() < 64 || this.attributes.containsKey(sanitizeAttribute)) {
            if (str2 == null) {
                str3 = "";
            } else {
                str3 = sanitizeAttribute(str2);
            }
            this.attributes.put(sanitizeAttribute, str3);
            this.controller.cacheKeyData(this.attributes);
            return;
        }
        c.g().a(TAG, "Exceeded maximum number of custom attributes (64)");
    }

    public void setBool(String str, boolean z) {
        setString(str, Boolean.toString(z));
    }

    public void setDouble(String str, double d2) {
        setString(str, Double.toString(d2));
    }

    public void setFloat(String str, float f) {
        setString(str, Float.toString(f));
    }

    public void setInt(String str, int i) {
        setString(str, Integer.toString(i));
    }

    public void setLong(String str, long j) {
        setString(str, Long.toString(j));
    }

    public void crash() {
        new CrashTest().indexOutOfBounds();
    }

    public boolean verifyPinning(URL url) {
        try {
            return internalVerifyPinning(url);
        } catch (Exception e) {
            c.g().e(TAG, "Could not verify SSL pinning", e);
            return false;
        }
    }

    @Deprecated
    public synchronized void setListener(CrashlyticsListener crashlyticsListener) {
        c.g().d(TAG, "Use of setListener is deprecated.");
        if (crashlyticsListener != null) {
            this.listener = crashlyticsListener;
        } else {
            throw new IllegalArgumentException("listener must not be null.");
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    /* access modifiers changed from: package-private */
    public CrashlyticsController getController() {
        return this.controller;
    }

    /* access modifiers changed from: package-private */
    public String getUserIdentifier() {
        if (getIdManager().a()) {
            return this.userId;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getUserEmail() {
        if (getIdManager().a()) {
            return this.userEmail;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getUserName() {
        if (getIdManager().a()) {
            return this.userName;
        }
        return null;
    }

    private void finishInitSynchronously() {
        AnonymousClass1 r0 = new io.a.a.a.a.c.g<Void>() {
            /* class com.crashlytics.android.core.CrashlyticsCore.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                return CrashlyticsCore.this.doInBackground();
            }

            @Override // io.a.a.a.a.c.i, io.a.a.a.a.c.j
            public io.a.a.a.a.c.e getPriority() {
                return io.a.a.a.a.c.e.IMMEDIATE;
            }
        };
        for (io.a.a.a.a.c.l lVar : getDependencies()) {
            r0.addDependency(lVar);
        }
        Future submit = getFabric().e().submit(r0);
        c.g().a(TAG, "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            submit.get(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            c.g().e(TAG, "Crashlytics was interrupted during initialization.", e);
        } catch (ExecutionException e2) {
            c.g().e(TAG, "Problem encountered during Crashlytics initialization.", e2);
        } catch (TimeoutException e3) {
            c.g().e(TAG, "Crashlytics timed out during initialization.", e3);
        }
    }

    /* access modifiers changed from: package-private */
    public void markInitializationStarted() {
        this.backgroundWorker.submitAndWait(new Callable<Void>() {
            /* class com.crashlytics.android.core.CrashlyticsCore.AnonymousClass2 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                CrashlyticsCore.this.initializationMarker.create();
                c.g().a(CrashlyticsCore.TAG, "Initialization marker file created.");
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void markInitializationComplete() {
        this.backgroundWorker.submit(new Callable<Boolean>() {
            /* class com.crashlytics.android.core.CrashlyticsCore.AnonymousClass3 */

            @Override // java.util.concurrent.Callable
            public Boolean call() {
                try {
                    boolean remove = CrashlyticsCore.this.initializationMarker.remove();
                    l g = c.g();
                    g.a(CrashlyticsCore.TAG, "Initialization marker file removed: " + remove);
                    return Boolean.valueOf(remove);
                } catch (Exception e) {
                    c.g().e(CrashlyticsCore.TAG, "Problem encountered deleting Crashlytics initialization marker.", e);
                    return false;
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean didPreviousInitializationFail() {
        return this.initializationMarker.isPresent();
    }

    /* access modifiers changed from: package-private */
    public void setCrashlyticsNdkDataProvider(CrashlyticsNdkDataProvider crashlyticsNdkDataProvider2) {
        this.crashlyticsNdkDataProvider = crashlyticsNdkDataProvider2;
    }

    /* access modifiers changed from: package-private */
    public CrashlyticsNdkData getNativeCrashData() {
        CrashlyticsNdkDataProvider crashlyticsNdkDataProvider2 = this.crashlyticsNdkDataProvider;
        if (crashlyticsNdkDataProvider2 != null) {
            return crashlyticsNdkDataProvider2.getCrashlyticsNdkData();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean internalVerifyPinning(URL url) {
        if (getPinningInfoProvider() == null) {
            return false;
        }
        io.a.a.a.a.e.d a2 = this.httpRequestFactory.a(io.a.a.a.a.e.c.GET, url.toString());
        ((HttpsURLConnection) a2.a()).setInstanceFollowRedirects(false);
        a2.b();
        return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
    }

    private void checkForPreviousCrash() {
        if (Boolean.TRUE.equals((Boolean) this.backgroundWorker.submitAndWait(new CrashMarkerCheck(this.crashMarker)))) {
            try {
                this.listener.crashlyticsDidDetectCrashDuringPreviousExecution();
            } catch (Exception e) {
                c.g().e(TAG, "Exception thrown by CrashlyticsListener while notifying of previous crash.", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void createCrashMarker() {
        this.crashMarker.create();
    }

    private static String formatLogMessage(int i, String str, String str2) {
        return io.a.a.a.a.b.i.b(i) + "/" + str + " " + str2;
    }

    private static boolean ensureFabricWithCalled(String str) {
        CrashlyticsCore instance = getInstance();
        if (instance != null && instance.controller != null) {
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        }
        l g = c.g();
        g.e(TAG, "Crashlytics must be initialized by calling Fabric.with(Context) " + str, null);
        return false;
    }

    private static String sanitizeAttribute(String str) {
        if (str == null) {
            return str;
        }
        String trim = str.trim();
        return trim.length() > 1024 ? trim.substring(0, 1024) : trim;
    }

    /* access modifiers changed from: private */
    public static final class CrashMarkerCheck implements Callable<Boolean> {
        private final CrashlyticsFileMarker crashMarker;

        public CrashMarkerCheck(CrashlyticsFileMarker crashlyticsFileMarker) {
            this.crashMarker = crashlyticsFileMarker;
        }

        @Override // java.util.concurrent.Callable
        public Boolean call() {
            if (!this.crashMarker.isPresent()) {
                return Boolean.FALSE;
            }
            c.g().a(CrashlyticsCore.TAG, "Found previous crash marker.");
            this.crashMarker.remove();
            return Boolean.TRUE;
        }
    }

    private static final class NoOpListener implements CrashlyticsListener {
        @Override // com.crashlytics.android.core.CrashlyticsListener
        public void crashlyticsDidDetectCrashDuringPreviousExecution() {
        }

        private NoOpListener() {
        }
    }

    static boolean isBuildIdValid(String str, boolean z) {
        if (!z) {
            c.g().a(TAG, "Configured not to require a build ID.");
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        } else if (!io.a.a.a.a.b.i.d(str)) {
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        } else {
            Log.e(TAG, ".");
            Log.e(TAG, ".     |  | ");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".   \\ |  | /");
            Log.e(TAG, ".    \\    /");
            Log.e(TAG, ".     \\  /");
            Log.e(TAG, ".      \\/");
            Log.e(TAG, ".");
            Log.e(TAG, MISSING_BUILD_ID_MSG);
            Log.e(TAG, ".");
            Log.e(TAG, ".      /\\");
            Log.e(TAG, ".     /  \\");
            Log.e(TAG, ".    /    \\");
            Log.e(TAG, ".   / |  | \\");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".");
            return false;
        }
    }
}
