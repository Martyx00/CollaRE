package com.facebook.react.fabric;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.fabric.jsi.Binding;
import com.facebook.react.fabric.jsi.EventBeatManager;
import com.facebook.react.fabric.jsi.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.c;
import com.facebook.react.fabric.mounting.mountitems.BatchMountItem;
import com.facebook.react.fabric.mounting.mountitems.b;
import com.facebook.react.fabric.mounting.mountitems.e;
import com.facebook.react.fabric.mounting.mountitems.f;
import com.facebook.react.fabric.mounting.mountitems.g;
import com.facebook.react.fabric.mounting.mountitems.h;
import com.facebook.react.fabric.mounting.mountitems.i;
import com.facebook.react.fabric.mounting.mountitems.j;
import com.facebook.react.fabric.mounting.mountitems.k;
import com.facebook.react.modules.core.e;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.as;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.events.d;
import com.facebook.react.uimanager.v;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.view.ReactViewManager;
import com.facebook.yoga.YogaMeasureMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressLint({"MissingNativeLoadLibrary"})
/* compiled from: FabricUIManager */
public class a implements LifecycleEventListener, UIManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2685a = "a";

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, String> f2686b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private Binding f2687c;

    /* renamed from: d  reason: collision with root package name */
    private final ReactApplicationContext f2688d;
    private final c e;
    private final d f;
    private final ConcurrentHashMap<Integer, af> g;
    private final EventBeatManager h;
    private final Object i;
    private final Object j;
    private List<e> k;
    private List<e> l;
    private final C0054a m;
    private boolean n;
    private long o;
    private long p;
    private long q;
    private long r;
    private long s;
    private long t;
    private long u;

    @Override // com.facebook.react.bridge.UIManager
    public void clearJSResponder() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public void profileNextBatch() {
    }

    @Override // com.facebook.react.bridge.UIManager
    public void setJSResponder(int i2, boolean z) {
    }

    static {
        com.facebook.react.fabric.jsi.a.a();
        f2686b.put("View", ReactViewManager.REACT_CLASS);
        f2686b.put("Image", ReactImageManager.REACT_CLASS);
        f2686b.put("ScrollView", ReactScrollViewManager.REACT_CLASS);
        f2686b.put("ReactPerformanceLoggerFlag", "ReactPerformanceLoggerFlag");
        f2686b.put("Paragraph", ReactTextViewManager.REACT_CLASS);
        f2686b.put("Text", "RCText");
        f2686b.put("RawText", ReactRawTextManager.REACT_CLASS);
        f2686b.put("ActivityIndicatorView", ReactProgressBarViewManager.REACT_CLASS);
        f2686b.put("ShimmeringView", "RKShimmeringView");
        f2686b.put("TemplateView", "RCTTemplateView");
    }

    @Override // com.facebook.react.bridge.UIManager
    public <T extends SizeMonitoringFrameLayout & com.facebook.react.uimanager.common.a> int addRootView(T t2, WritableMap writableMap, String str) {
        int a2 = v.a();
        af afVar = new af(this.f2688d, t2.getContext());
        this.e.a(a2, t2);
        this.g.put(Integer.valueOf(a2), afVar);
        this.f2687c.startSurface(a2, (NativeMap) writableMap);
        T t3 = t2;
        updateRootLayoutSpecs(a2, t3.getWidthMeasureSpec(), t3.getHeightMeasureSpec());
        if (str != null) {
            this.f2687c.renderTemplateToSurface(a2, str);
        }
        return a2;
    }

    @com.facebook.j.a.a
    public void onRequestEventBeat() {
        this.f.a();
    }

    @Override // com.facebook.react.bridge.UIManager
    public void removeRootView(int i2) {
        this.e.a(i2);
        this.g.remove(Integer.valueOf(i2));
    }

    @com.facebook.j.a.a
    private e createMountItem(String str, int i2, int i3, boolean z) {
        String str2 = f2686b.get(str);
        if (str2 != null) {
            af afVar = this.g.get(Integer.valueOf(i2));
            if (afVar != null) {
                return new com.facebook.react.fabric.mounting.mountitems.a(afVar, str2, i3, z);
            }
            throw new IllegalArgumentException("Unable to find ReactContext for root: " + i2);
        }
        throw new IllegalArgumentException("Unable to find component with name " + str);
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void initialize() {
        this.f.a(2, new FabricEventEmitter(this));
        this.f.a(this.h);
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void onCatalystInstanceDestroy() {
        this.f.b(this.h);
        this.f.a(2);
        this.f2687c.a();
        as.a();
    }

    @com.facebook.j.a.a
    private void preallocateView(int i2, String str) {
        if (!UiThreadUtil.isOnUiThread()) {
            synchronized (this.j) {
                String str2 = f2686b.get(str);
                com.facebook.i.a.a.a(str2);
                this.l.add(new f((af) com.facebook.i.a.a.a(this.g.get(Integer.valueOf(i2))), i2, str2));
            }
        }
    }

    @com.facebook.j.a.a
    private e removeMountItem(int i2, int i3, int i4) {
        return new g(i2, i3, i4);
    }

    @com.facebook.j.a.a
    private e insertMountItem(int i2, int i3, int i4) {
        return new com.facebook.react.fabric.mounting.mountitems.d(i2, i3, i4);
    }

    @com.facebook.j.a.a
    private e deleteMountItem(int i2) {
        return new b(i2);
    }

    @com.facebook.j.a.a
    private e updateLayoutMountItem(int i2, int i3, int i4, int i5, int i6) {
        return new i(i2, i3, i4, i5, i6);
    }

    @com.facebook.j.a.a
    private e updatePropsMountItem(int i2, ReadableNativeMap readableNativeMap) {
        return new k(i2, readableNativeMap);
    }

    @com.facebook.j.a.a
    private e updateLocalDataMountItem(int i2, ReadableNativeMap readableNativeMap) {
        return new j(i2, readableNativeMap);
    }

    @com.facebook.j.a.a
    private e updateEventEmitterMountItem(int i2, Object obj) {
        return new h(i2, (EventEmitterWrapper) obj);
    }

    @com.facebook.j.a.a
    private e createBatchMountItem(e[] eVarArr, int i2) {
        return new BatchMountItem(eVarArr, i2);
    }

    @com.facebook.j.a.a
    private long measure(String str, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, int i2, int i3, int i4, int i5) {
        c cVar = this.e;
        ReactApplicationContext reactApplicationContext = this.f2688d;
        float f2 = (float) i2;
        float f3 = (float) i3;
        float a2 = com.facebook.react.fabric.mounting.b.a(f2, f3);
        YogaMeasureMode b2 = com.facebook.react.fabric.mounting.b.b(f2, f3);
        float f4 = (float) i4;
        float f5 = (float) i5;
        return cVar.a(reactApplicationContext, str, readableNativeMap, readableNativeMap2, a2, b2, com.facebook.react.fabric.mounting.b.a(f4, f5), com.facebook.react.fabric.mounting.b.b(f4, f5));
    }

    @com.facebook.j.a.a
    private void scheduleMountItems(e eVar, long j2, long j3, long j4) {
        this.s = j2;
        this.t = j3;
        this.u = SystemClock.uptimeMillis() - j4;
        this.r = SystemClock.uptimeMillis();
        synchronized (this.i) {
            this.k.add(eVar);
        }
        if (UiThreadUtil.isOnUiThread()) {
            a();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a() {
        List<e> list;
        List<e> list2;
        if (!this.n) {
            com.facebook.common.e.a.c("ReactNative", "Not flushing pending UI operations because of previously thrown Exception");
            return;
        }
        try {
            synchronized (this.j) {
                list = this.l;
                this.l = new ArrayList();
            }
            this.o = SystemClock.uptimeMillis();
            synchronized (this.i) {
                list2 = this.k;
                this.k = new ArrayList();
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            com.facebook.systrace.a.a(0, "FabricUIManager::premountViews (" + list.size() + " batches)");
            for (e eVar : list) {
                eVar.a(this.e);
            }
            this.q = SystemClock.uptimeMillis() - uptimeMillis;
            com.facebook.systrace.a.b(0);
            com.facebook.systrace.a.a(0, "FabricUIManager::mountViews (" + list2.size() + " batches)");
            long uptimeMillis2 = SystemClock.uptimeMillis();
            for (e eVar2 : list2) {
                eVar2.a(this.e);
            }
            this.p = SystemClock.uptimeMillis() - uptimeMillis2;
            com.facebook.systrace.a.b(0);
        } catch (Exception e2) {
            com.facebook.common.e.a.c("ReactNative", "Exception thrown when executing UIFrameGuarded", e2);
            this.n = false;
            throw e2;
        }
    }

    @Override // com.facebook.react.bridge.UIManager
    public void updateRootLayoutSpecs(final int i2, final int i3, final int i4) {
        ReactApplicationContext reactApplicationContext = this.f2688d;
        reactApplicationContext.runOnJSQueueThread(new GuardedRunnable(reactApplicationContext) {
            /* class com.facebook.react.fabric.a.AnonymousClass1 */

            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                a.this.f2687c.setConstraints(i2, com.facebook.react.fabric.mounting.b.a(i3), com.facebook.react.fabric.mounting.b.b(i3), com.facebook.react.fabric.mounting.b.a(i4), com.facebook.react.fabric.mounting.b.b(i4));
            }
        });
    }

    public void a(int i2, String str, WritableMap writableMap) {
        EventEmitterWrapper c2 = this.e.c(i2);
        if (c2 == null) {
            String str2 = f2685a;
            com.facebook.common.e.a.a(str2, "Unable to invoke event: " + str + " for reactTag: " + i2);
            return;
        }
        c2.a(str, writableMap);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        com.facebook.react.modules.core.e.b().a(e.a.DISPATCH_UI, this.m);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        com.facebook.react.modules.core.e.b().b(e.a.DISPATCH_UI, this.m);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void dispatchCommand(int i2, int i3, ReadableArray readableArray) {
        synchronized (this.i) {
            this.k.add(new com.facebook.react.fabric.mounting.mountitems.c(i2, i3, readableArray));
        }
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public Map<String, Long> getPerformanceCounters() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.s));
        hashMap.put("LayoutTime", Long.valueOf(this.t));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.r));
        hashMap.put("RunStartTime", Long.valueOf(this.o));
        hashMap.put("BatchedExecutionTime", Long.valueOf(this.p));
        hashMap.put("NonBatchedExecutionTime", Long.valueOf(this.q));
        hashMap.put("FinishFabricTransactionTime", Long.valueOf(this.u));
        return hashMap;
    }

    /* access modifiers changed from: private */
    /* renamed from: com.facebook.react.fabric.a$a  reason: collision with other inner class name */
    /* compiled from: FabricUIManager */
    public class C0054a extends b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ a f2693a;

        @Override // com.facebook.react.fabric.b
        public void a(long j) {
            if (!this.f2693a.n) {
                com.facebook.common.e.a.c("ReactNative", "Not flushing pending UI operations because of previously thrown Exception");
                return;
            }
            try {
                this.f2693a.a();
                com.facebook.react.modules.core.e.b().a(e.a.DISPATCH_UI, this.f2693a.m);
            } catch (Exception e) {
                com.facebook.common.e.a.a("ReactNative", "Exception thrown when executing UIFrameGuarded", e);
                this.f2693a.n = false;
                throw e;
            } catch (Throwable th) {
                com.facebook.react.modules.core.e.b().a(e.a.DISPATCH_UI, this.f2693a.m);
                throw th;
            }
        }
    }
}
