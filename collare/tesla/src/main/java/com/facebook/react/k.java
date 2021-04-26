package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.g.t;
import android.util.Log;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NativeDeltaClient;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.d;
import com.facebook.react.devsupport.e;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.fabric.ReactFabric;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ak;
import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.c;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: ReactInstanceManager */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2754a = "k";

    /* renamed from: b  reason: collision with root package name */
    private final Set<r> f2755b = Collections.synchronizedSet(new HashSet());

    /* renamed from: c  reason: collision with root package name */
    private volatile LifecycleState f2756c;

    /* renamed from: d  reason: collision with root package name */
    private a f2757d;
    private volatile Thread e;
    private final JavaScriptExecutorFactory f;
    private final JSBundleLoader g;
    private final String h;
    private final List<o> i;
    private final com.facebook.react.devsupport.a.b j;
    private final boolean k;
    private final NotThreadSafeBridgeIdleDebugListener l;
    private final Object m = new Object();
    private volatile ReactContext n;
    private final Context o;
    private com.facebook.react.modules.core.b p;
    private Activity q;
    private final Collection<b> r = Collections.synchronizedSet(new HashSet());
    private volatile boolean s = false;
    private volatile Boolean t = false;
    private final d u;
    private final NativeModuleCallExceptionHandler v;
    private final JSIModulePackage w;
    private List<ViewManager> x;

    /* compiled from: ReactInstanceManager */
    public interface b {
        void a(ReactContext reactContext);
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactInstanceManager */
    public class a {

        /* renamed from: b  reason: collision with root package name */
        private final JavaScriptExecutorFactory f2778b;

        /* renamed from: c  reason: collision with root package name */
        private final JSBundleLoader f2779c;

        public a(JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
            this.f2778b = (JavaScriptExecutorFactory) com.facebook.i.a.a.a(javaScriptExecutorFactory);
            this.f2779c = (JSBundleLoader) com.facebook.i.a.a.a(jSBundleLoader);
        }

        public JavaScriptExecutorFactory a() {
            return this.f2778b;
        }

        public JSBundleLoader b() {
            return this.f2779c;
        }
    }

    public static l a() {
        return new l();
    }

    k(Context context, Activity activity, com.facebook.react.modules.core.b bVar, JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader, String str, List<o> list, boolean z, NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener, LifecycleState lifecycleState, ak akVar, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler, e eVar, boolean z2, com.facebook.react.devsupport.a.a aVar, int i2, int i3, JSIModulePackage jSIModulePackage, Map<String, Object> map) {
        Log.d("ReactNative", "ReactInstanceManager.ctor()");
        a(context);
        c.a(context);
        this.o = context;
        this.q = activity;
        this.p = bVar;
        this.f = javaScriptExecutorFactory;
        this.g = jSBundleLoader;
        this.h = str;
        this.i = new ArrayList();
        this.k = z;
        com.facebook.systrace.a.a(0, "ReactInstanceManager.initDevSupportManager");
        this.j = com.facebook.react.devsupport.a.a(context, k(), this.h, z, eVar, aVar, i2, map);
        com.facebook.systrace.a.b(0);
        this.l = notThreadSafeBridgeIdleDebugListener;
        this.f2756c = lifecycleState;
        this.u = new d(context);
        this.v = nativeModuleCallExceptionHandler;
        synchronized (this.i) {
            com.facebook.d.b.c.a().a(com.facebook.d.c.a.f1806c, "RNCore: Use Split Packages");
            this.i.add(new a(this, new com.facebook.react.modules.core.b() {
                /* class com.facebook.react.k.AnonymousClass1 */

                @Override // com.facebook.react.modules.core.b
                public void k() {
                    k.this.n();
                }
            }, akVar, z2, i3));
            if (this.k) {
                this.i.add(new b());
            }
            this.i.addAll(list);
        }
        this.w = jSIModulePackage;
        com.facebook.react.modules.core.e.a();
        if (this.k) {
            this.j.c();
        }
    }

    private d k() {
        return new d() {
            /* class com.facebook.react.k.AnonymousClass2 */
        };
    }

    public com.facebook.react.devsupport.a.b b() {
        return this.j;
    }

    private static void a(Context context) {
        SoLoader.a(context, false);
    }

    public void c() {
        Log.d("ReactNative", "ReactInstanceManager.createReactContextInBackground()");
        com.facebook.i.a.a.a(!this.s, "createReactContextInBackground should only be called when creating the react application for the first time. When reloading JS, e.g. from a new file, explicitlyuse recreateReactContextInBackground");
        this.s = true;
        l();
    }

    private void l() {
        Log.d("ReactNative", "ReactInstanceManager.recreateReactContextInBackgroundInner()");
        com.facebook.d.b.c.a().a(com.facebook.d.c.a.f1806c, "RNCore: recreateReactContextInBackground");
        UiThreadUtil.assertOnUiThread();
        if (this.k && this.h != null) {
            final com.facebook.react.modules.debug.a.a e2 = this.j.e();
            if (this.j.h() && !e2.b()) {
                a((NativeDeltaClient) null);
                return;
            } else if (!com.facebook.systrace.a.a(0)) {
                if (this.g == null) {
                    this.j.i();
                    return;
                } else {
                    this.j.a(new com.facebook.react.devsupport.a.c() {
                        /* class com.facebook.react.k.AnonymousClass3 */
                    });
                    return;
                }
            }
        }
        m();
    }

    private void m() {
        Log.d("ReactNative", "ReactInstanceManager.recreateReactContextInBackgroundFromBundleLoader()");
        com.facebook.d.b.c.a().a(com.facebook.d.c.a.f1806c, "RNCore: load from BundleLoader");
        a(this.f, this.g);
    }

    public boolean d() {
        return this.s;
    }

    public void e() {
        UiThreadUtil.assertOnUiThread();
        ReactContext reactContext = this.n;
        if (reactContext == null) {
            com.facebook.common.e.a.c("ReactNative", "Instance detached from instance manager");
            n();
            return;
        }
        ((DeviceEventManagerModule) reactContext.getNativeModule(DeviceEventManagerModule.class)).emitHardwareBackPressed();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void n() {
        UiThreadUtil.assertOnUiThread();
        com.facebook.react.modules.core.b bVar = this.p;
        if (bVar != null) {
            bVar.k();
        }
    }

    public void a(Intent intent) {
        UiThreadUtil.assertOnUiThread();
        ReactContext j2 = j();
        if (j2 == null) {
            com.facebook.common.e.a.c("ReactNative", "Instance detached from instance manager");
            return;
        }
        String action = intent.getAction();
        Uri data = intent.getData();
        if ("android.intent.action.VIEW".equals(action) && data != null) {
            ((DeviceEventManagerModule) j2.getNativeModule(DeviceEventManagerModule.class)).emitNewIntentReceived(data);
        }
        j2.onNewIntent(this.q, intent);
    }

    public void f() {
        UiThreadUtil.assertOnUiThread();
        this.p = null;
        if (this.k) {
            this.j.a(false);
        }
        o();
    }

    public void a(Activity activity) {
        com.facebook.i.a.a.a(this.q);
        boolean z = activity == this.q;
        com.facebook.i.a.a.a(z, "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + this.q.getClass().getSimpleName() + " Paused activity: " + activity.getClass().getSimpleName());
        f();
    }

    public void a(Activity activity, com.facebook.react.modules.core.b bVar) {
        UiThreadUtil.assertOnUiThread();
        this.p = bVar;
        b(activity);
    }

    public void b(Activity activity) {
        UiThreadUtil.assertOnUiThread();
        this.q = activity;
        if (this.k) {
            final View decorView = this.q.getWindow().getDecorView();
            if (!t.u(decorView)) {
                decorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    /* class com.facebook.react.k.AnonymousClass4 */

                    public void onViewDetachedFromWindow(View view) {
                    }

                    public void onViewAttachedToWindow(View view) {
                        decorView.removeOnAttachStateChangeListener(this);
                        k.this.j.a(true);
                    }
                });
            } else {
                this.j.a(true);
            }
        }
        a(false);
    }

    public void g() {
        UiThreadUtil.assertOnUiThread();
        if (this.k) {
            this.j.a(false);
        }
        p();
        this.q = null;
    }

    public void c(Activity activity) {
        if (activity == this.q) {
            g();
        }
    }

    private synchronized void a(boolean z) {
        ReactContext j2 = j();
        if (j2 != null && (z || this.f2756c == LifecycleState.BEFORE_RESUME || this.f2756c == LifecycleState.BEFORE_CREATE)) {
            j2.onHostResume(this.q);
        }
        this.f2756c = LifecycleState.RESUMED;
    }

    private synchronized void o() {
        ReactContext j2 = j();
        if (j2 != null) {
            if (this.f2756c == LifecycleState.BEFORE_CREATE) {
                j2.onHostResume(this.q);
                j2.onHostPause();
            } else if (this.f2756c == LifecycleState.RESUMED) {
                j2.onHostPause();
            }
        }
        this.f2756c = LifecycleState.BEFORE_RESUME;
    }

    private synchronized void p() {
        ReactContext j2 = j();
        if (j2 != null) {
            if (this.f2756c == LifecycleState.RESUMED) {
                j2.onHostPause();
                this.f2756c = LifecycleState.BEFORE_RESUME;
            }
            if (this.f2756c == LifecycleState.BEFORE_RESUME) {
                j2.onHostDestroy();
            }
        }
        this.f2756c = LifecycleState.BEFORE_CREATE;
    }

    private synchronized void q() {
        if (this.f2756c == LifecycleState.RESUMED) {
            a(true);
        }
    }

    public void a(Activity activity, int i2, int i3, Intent intent) {
        ReactContext j2 = j();
        if (j2 != null) {
            j2.onActivityResult(activity, i2, i3, intent);
        }
    }

    public void h() {
        UiThreadUtil.assertOnUiThread();
        this.j.b();
    }

    public void a(r rVar) {
        UiThreadUtil.assertOnUiThread();
        this.f2755b.add(rVar);
        rVar.removeAllViews();
        rVar.setId(-1);
        ReactContext j2 = j();
        if (this.e == null && j2 != null) {
            c(rVar);
        }
    }

    public void b(r rVar) {
        UiThreadUtil.assertOnUiThread();
        synchronized (this.f2755b) {
            if (this.f2755b.contains(rVar)) {
                ReactContext j2 = j();
                this.f2755b.remove(rVar);
                if (j2 != null && j2.hasActiveCatalystInstance()) {
                    a(rVar, j2.getCatalystInstance());
                }
            }
        }
    }

    public List<ViewManager> a(ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_START);
        com.facebook.systrace.a.a(0, "createAllViewManagers");
        try {
            if (this.x == null) {
                synchronized (this.i) {
                    if (this.x == null) {
                        this.x = new ArrayList();
                        for (o oVar : this.i) {
                            this.x.addAll(oVar.createViewManagers(reactApplicationContext));
                        }
                        return this.x;
                    }
                }
            }
            List<ViewManager> list = this.x;
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
            return list;
        } finally {
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = r6.i.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        if (r0.hasNext() == false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        r4 = r0.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        if ((r4 instanceof com.facebook.react.t) == false) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        r4 = ((com.facebook.react.t) r4).a(r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        if (r4 == null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0035, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0038, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        r3 = r6.i;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.uimanager.ViewManager a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.m
            monitor-enter(r0)
            com.facebook.react.bridge.ReactContext r1 = r6.j()     // Catch:{ all -> 0x003e }
            com.facebook.react.bridge.ReactApplicationContext r1 = (com.facebook.react.bridge.ReactApplicationContext) r1     // Catch:{ all -> 0x003e }
            r2 = 0
            if (r1 == 0) goto L_0x003c
            boolean r3 = r1.hasActiveCatalystInstance()     // Catch:{ all -> 0x003e }
            if (r3 != 0) goto L_0x0013
            goto L_0x003c
        L_0x0013:
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            java.util.List<com.facebook.react.o> r3 = r6.i
            monitor-enter(r3)
            java.util.List<com.facebook.react.o> r0 = r6.i     // Catch:{ all -> 0x0039 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0039 }
        L_0x001d:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x0039 }
            if (r4 == 0) goto L_0x0037
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x0039 }
            com.facebook.react.o r4 = (com.facebook.react.o) r4     // Catch:{ all -> 0x0039 }
            boolean r5 = r4 instanceof com.facebook.react.t     // Catch:{ all -> 0x0039 }
            if (r5 == 0) goto L_0x001d
            com.facebook.react.t r4 = (com.facebook.react.t) r4     // Catch:{ all -> 0x0039 }
            com.facebook.react.uimanager.ViewManager r4 = r4.a(r1, r7)     // Catch:{ all -> 0x0039 }
            if (r4 == 0) goto L_0x001d
            monitor-exit(r3)     // Catch:{ all -> 0x0039 }
            return r4
        L_0x0037:
            monitor-exit(r3)     // Catch:{ all -> 0x0039 }
            return r2
        L_0x0039:
            r7 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0039 }
            throw r7
        L_0x003c:
            monitor-exit(r0)
            return r2
        L_0x003e:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.k.a(java.lang.String):com.facebook.react.uimanager.ViewManager");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = new java.util.HashSet();
        r5 = r10.i.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        if (r5.hasNext() == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        r6 = r5.next();
        com.facebook.systrace.b.a(0, "ReactInstanceManager.getViewManagerName").a("Package", r6.getClass().getSimpleName()).a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        if ((r6 instanceof com.facebook.react.t) == false) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004f, code lost:
        r6 = ((com.facebook.react.t) r6).a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        if (r6 == null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0057, code lost:
        r0.addAll(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005a, code lost:
        com.facebook.systrace.b.a(0).a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0062, code lost:
        com.facebook.systrace.a.b(0);
        r1 = new java.util.ArrayList(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006a, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r4 = r10.i;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> i() {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.k.i():java.util.List");
    }

    public ReactContext j() {
        ReactContext reactContext;
        synchronized (this.m) {
            reactContext = this.n;
        }
        return reactContext;
    }

    private void a(NativeDeltaClient nativeDeltaClient) {
        JSBundleLoader jSBundleLoader;
        Log.d("ReactNative", "ReactInstanceManager.onJSBundleLoadedFromServer()");
        if (nativeDeltaClient == null) {
            jSBundleLoader = JSBundleLoader.createCachedBundleFromNetworkLoader(this.j.f(), this.j.g());
        } else {
            jSBundleLoader = JSBundleLoader.createDeltaFromNetworkLoader(this.j.f(), nativeDeltaClient);
        }
        a(this.f, jSBundleLoader);
    }

    private void a(JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
        Log.d("ReactNative", "ReactInstanceManager.recreateReactContextInBackground()");
        UiThreadUtil.assertOnUiThread();
        a aVar = new a(javaScriptExecutorFactory, jSBundleLoader);
        if (this.e == null) {
            a(aVar);
        } else {
            this.f2757d = aVar;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(final a aVar) {
        Log.d("ReactNative", "ReactInstanceManager.runCreateReactContextOnNewThread()");
        UiThreadUtil.assertOnUiThread();
        synchronized (this.f2755b) {
            synchronized (this.m) {
                if (this.n != null) {
                    a(this.n);
                    this.n = null;
                }
            }
        }
        this.e = new Thread(null, new Runnable() {
            /* class com.facebook.react.k.AnonymousClass5 */

            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x000c */
            /* JADX WARNING: Removed duplicated region for block: B:2:0x000c A[LOOP:0: B:2:0x000c->B:16:0x000c, LOOP_START, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                // Method dump skipped, instructions count: 114
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.k.AnonymousClass5.run():void");
            }
        }, "create_react_context");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_CONTEXT_THREAD_START);
        this.e.start();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(final ReactApplicationContext reactApplicationContext) {
        Log.d("ReactNative", "ReactInstanceManager.setupReactContext()");
        ReactMarker.logMarker(ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_END);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_START);
        com.facebook.systrace.a.a(0, "setupReactContext");
        synchronized (this.f2755b) {
            synchronized (this.m) {
                this.n = (ReactContext) com.facebook.i.a.a.a(reactApplicationContext);
            }
            CatalystInstance catalystInstance = (CatalystInstance) com.facebook.i.a.a.a(reactApplicationContext.getCatalystInstance());
            catalystInstance.initialize();
            this.j.a(reactApplicationContext);
            this.u.a(catalystInstance);
            q();
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_START);
            for (r rVar : this.f2755b) {
                c(rVar);
            }
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_END);
        }
        final b[] bVarArr = (b[]) this.r.toArray(new b[this.r.size()]);
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.k.AnonymousClass6 */

            public void run() {
                for (b bVar : bVarArr) {
                    bVar.a(reactApplicationContext);
                }
            }
        });
        com.facebook.systrace.a.b(0);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_END);
        reactApplicationContext.runOnJSQueueThread(new Runnable() {
            /* class com.facebook.react.k.AnonymousClass7 */

            public void run() {
                Process.setThreadPriority(0);
                ReactMarker.logMarker(ReactMarkerConstants.CHANGE_THREAD_PRIORITY, "js_default");
            }
        });
        reactApplicationContext.runOnNativeModulesQueueThread(new Runnable() {
            /* class com.facebook.react.k.AnonymousClass8 */

            public void run() {
                Process.setThreadPriority(0);
            }
        });
    }

    private void c(final r rVar) {
        WritableMap writableMap;
        Log.d("ReactNative", "ReactInstanceManager.attachRootViewToInstance()");
        com.facebook.systrace.a.a(0, "attachRootViewToInstance");
        UIManager a2 = al.a(this.n, rVar.getUIManagerType());
        Bundle appProperties = rVar.getAppProperties();
        if (appProperties == null) {
            writableMap = new WritableNativeMap();
        } else {
            writableMap = Arguments.fromBundle(appProperties);
        }
        final int addRootView = a2.addRootView(rVar, writableMap, rVar.getInitialUITemplate());
        rVar.setRootViewTag(addRootView);
        rVar.c();
        com.facebook.systrace.a.a(0, "pre_rootView.onAttachedToReactInstance", addRootView);
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.k.AnonymousClass9 */

            public void run() {
                com.facebook.systrace.a.b(0, "pre_rootView.onAttachedToReactInstance", addRootView);
                rVar.b();
            }
        });
        com.facebook.systrace.a.b(0);
    }

    private void a(r rVar, CatalystInstance catalystInstance) {
        Log.d("ReactNative", "ReactInstanceManager.detachViewFromInstance()");
        UiThreadUtil.assertOnUiThread();
        if (rVar.getUIManagerType() == 2) {
            ((ReactFabric) catalystInstance.getJSModule(ReactFabric.class)).unmountComponentAtNode(rVar.getId());
        } else {
            ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).unmountApplicationComponentAtRootTag(rVar.getId());
        }
    }

    private void a(ReactContext reactContext) {
        Log.d("ReactNative", "ReactInstanceManager.tearDownReactContext()");
        UiThreadUtil.assertOnUiThread();
        if (this.f2756c == LifecycleState.RESUMED) {
            reactContext.onHostPause();
        }
        synchronized (this.f2755b) {
            for (r rVar : this.f2755b) {
                rVar.removeAllViews();
                rVar.setId(-1);
            }
        }
        reactContext.destroy();
        this.j.b(reactContext);
        this.u.b(reactContext.getCatalystInstance());
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ReactApplicationContext a(JavaScriptExecutor javaScriptExecutor, JSBundleLoader jSBundleLoader) {
        Log.d("ReactNative", "ReactInstanceManager.createReactContext()");
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_REACT_CONTEXT_START, javaScriptExecutor.getName());
        ReactApplicationContext reactApplicationContext = new ReactApplicationContext(this.o);
        NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler = this.v;
        if (nativeModuleCallExceptionHandler == null) {
            nativeModuleCallExceptionHandler = this.j;
        }
        reactApplicationContext.setNativeModuleCallExceptionHandler(nativeModuleCallExceptionHandler);
        CatalystInstanceImpl.Builder nativeModuleCallExceptionHandler2 = new CatalystInstanceImpl.Builder().setReactQueueConfigurationSpec(ReactQueueConfigurationSpec.createDefault()).setJSExecutor(javaScriptExecutor).setRegistry(a(reactApplicationContext, this.i, false)).setJSBundleLoader(jSBundleLoader).setNativeModuleCallExceptionHandler(nativeModuleCallExceptionHandler);
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_START);
        com.facebook.systrace.a.a(0, "createCatalystInstance");
        try {
            CatalystInstanceImpl build = nativeModuleCallExceptionHandler2.build();
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            JSIModulePackage jSIModulePackage = this.w;
            if (jSIModulePackage != null) {
                build.addJSIModules(jSIModulePackage.getJSIModules(reactApplicationContext, build.getJavaScriptContextHolder()));
            }
            NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = this.l;
            if (notThreadSafeBridgeIdleDebugListener != null) {
                build.addBridgeIdleDebugListener(notThreadSafeBridgeIdleDebugListener);
            }
            if (com.facebook.systrace.a.a(0)) {
                build.setGlobalVariable("__RCTProfileIsProfiling", "true");
            }
            ReactMarker.logMarker(ReactMarkerConstants.PRE_RUN_JS_BUNDLE_START);
            com.facebook.systrace.a.a(0, "runJSBundle");
            build.runJSBundle();
            com.facebook.systrace.a.b(0);
            reactApplicationContext.initializeWithInstance(build);
            return reactApplicationContext;
        } catch (Throwable th) {
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            throw th;
        }
    }

    private NativeModuleRegistry a(ReactApplicationContext reactApplicationContext, List<o> list, boolean z) {
        e eVar = new e(reactApplicationContext, this);
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_START);
        synchronized (this.i) {
            for (o oVar : list) {
                if (!z || !this.i.contains(oVar)) {
                    com.facebook.systrace.a.a(0, "createAndProcessCustomReactPackage");
                    if (z) {
                        try {
                            this.i.add(oVar);
                        } catch (Throwable th) {
                            com.facebook.systrace.a.b(0);
                            throw th;
                        }
                    }
                    a(oVar, eVar);
                    com.facebook.systrace.a.b(0);
                }
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_END);
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_START);
        com.facebook.systrace.a.a(0, "buildNativeModuleRegistry");
        try {
            return eVar.a();
        } finally {
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_END);
        }
    }

    private void a(o oVar, e eVar) {
        com.facebook.systrace.b.a(0, "processPackage").a("className", oVar.getClass().getSimpleName()).a();
        boolean z = oVar instanceof q;
        if (z) {
            ((q) oVar).b();
        }
        eVar.a(oVar);
        if (z) {
            ((q) oVar).c();
        }
        com.facebook.systrace.b.a(0).a();
    }
}
