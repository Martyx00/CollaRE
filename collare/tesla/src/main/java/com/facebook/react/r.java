package com.facebook.react;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ab;
import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.c;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.f;
import com.facebook.react.uimanager.g;
import com.facebook.react.uimanager.o;

/* compiled from: ReactRootView */
public class r extends SizeMonitoringFrameLayout implements ab, com.facebook.react.uimanager.common.a {

    /* renamed from: a  reason: collision with root package name */
    private k f3042a;

    /* renamed from: b  reason: collision with root package name */
    private String f3043b;

    /* renamed from: c  reason: collision with root package name */
    private Bundle f3044c;

    /* renamed from: d  reason: collision with root package name */
    private String f3045d;
    private a e;
    private b f;
    private int g;
    private boolean h;
    private boolean i;
    private g j;
    private final i k = new i(this);
    private boolean l = false;
    private int m = View.MeasureSpec.makeMeasureSpec(0, 0);
    private int n = View.MeasureSpec.makeMeasureSpec(0, 0);
    private int o = 1;

    /* compiled from: ReactRootView */
    public interface b {
        void a(r rVar);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    public r(Context context) {
        super(context);
        d();
    }

    private void d() {
        setClipChildren(false);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0049 A[Catch:{ all -> 0x0099 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058 A[Catch:{ all -> 0x0099 }, LOOP:1: B:18:0x0052->B:20:0x0058, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0080 A[Catch:{ all -> 0x0099 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r9, int r10) {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.r.onMeasure(int, int):void");
    }

    @Override // com.facebook.react.uimanager.common.a
    public int getWidthMeasureSpec() {
        if (this.l || getLayoutParams() == null || getLayoutParams().width <= 0) {
            return this.m;
        }
        return View.MeasureSpec.makeMeasureSpec(getLayoutParams().width, 1073741824);
    }

    @Override // com.facebook.react.uimanager.common.a
    public int getHeightMeasureSpec() {
        if (this.l || getLayoutParams() == null || getLayoutParams().height <= 0) {
            return this.n;
        }
        return View.MeasureSpec.makeMeasureSpec(getLayoutParams().height, 1073741824);
    }

    @Override // com.facebook.react.uimanager.ab
    public void a(MotionEvent motionEvent) {
        k kVar = this.f3042a;
        if (kVar == null || !this.h || kVar.j() == null) {
            com.facebook.common.e.a.c("ReactNative", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
        } else if (this.j == null) {
            com.facebook.common.e.a.c("ReactNative", "Unable to dispatch touch to JS before the dispatcher is available");
        } else {
            this.j.a(motionEvent, ((UIManagerModule) this.f3042a.j().getNativeModule(UIManagerModule.class)).getEventDispatcher());
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        b(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        b(motionEvent);
        super.onTouchEvent(motionEvent);
        return true;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (StackOverflowError e2) {
            a(e2);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        k kVar = this.f3042a;
        if (kVar == null || !this.h || kVar.j() == null) {
            com.facebook.common.e.a.c("ReactNative", "Unable to handle key event as the catalyst instance has not been attached");
            return super.dispatchKeyEvent(keyEvent);
        }
        this.k.a(keyEvent);
        return super.dispatchKeyEvent(keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i2, Rect rect) {
        k kVar = this.f3042a;
        if (kVar == null || !this.h || kVar.j() == null) {
            com.facebook.common.e.a.c("ReactNative", "Unable to handle focus changed event as the catalyst instance has not been attached");
            super.onFocusChanged(z, i2, rect);
            return;
        }
        this.k.a();
        super.onFocusChanged(z, i2, rect);
    }

    public void requestChildFocus(View view, View view2) {
        k kVar = this.f3042a;
        if (kVar == null || !this.h || kVar.j() == null) {
            com.facebook.common.e.a.c("ReactNative", "Unable to handle child focus changed event as the catalyst instance has not been attached");
            super.requestChildFocus(view, view2);
            return;
        }
        this.k.a(view2);
        super.requestChildFocus(view, view2);
    }

    private void b(MotionEvent motionEvent) {
        k kVar = this.f3042a;
        if (kVar == null || !this.h || kVar.j() == null) {
            com.facebook.common.e.a.c("ReactNative", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
        } else if (this.j == null) {
            com.facebook.common.e.a.c("ReactNative", "Unable to dispatch touch to JS before the dispatcher is available");
        } else {
            this.j.b(motionEvent, ((UIManagerModule) this.f3042a.j().getNativeModule(UIManagerModule.class)).getEventDispatcher());
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.h) {
            e();
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.h) {
            e();
        }
    }

    private void e() {
        getViewTreeObserver().removeOnGlobalLayoutListener(getCustomGlobalLayoutListener());
    }

    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (this.i) {
            this.i = false;
            if (this.f3043b != null) {
                ReactMarker.logMarker(ReactMarkerConstants.CONTENT_APPEARED, this.f3043b, this.g);
            }
        }
    }

    public void a(k kVar, String str, Bundle bundle) {
        a(kVar, str, bundle, null);
    }

    public void a(k kVar, String str, Bundle bundle, String str2) {
        com.facebook.systrace.a.a(0, "startReactApplication");
        try {
            UiThreadUtil.assertOnUiThread();
            com.facebook.i.a.a.a(this.f3042a == null, "This root view has already been attached to a catalyst instance manager");
            this.f3042a = kVar;
            this.f3043b = str;
            this.f3044c = bundle;
            this.f3045d = str2;
            if (!this.f3042a.d()) {
                this.f3042a.c();
            }
            g();
        } finally {
            com.facebook.systrace.a.b(0);
        }
    }

    private void f() {
        k kVar = this.f3042a;
        if (kVar == null) {
            com.facebook.common.e.a.c("ReactNative", "Unable to enable layout calculation for uninitialized ReactInstanceManager");
            return;
        }
        ReactContext j2 = kVar.j();
        if (j2 != null) {
            ((UIManagerModule) j2.getCatalystInstance().getNativeModule(UIManagerModule.class)).getUIImplementation().g(getRootViewTag());
        }
    }

    private void a(int i2, int i3) {
        k kVar = this.f3042a;
        if (kVar == null) {
            com.facebook.common.e.a.c("ReactNative", "Unable to update root layout specs for uninitialized ReactInstanceManager");
            return;
        }
        ReactContext j2 = kVar.j();
        if (j2 != null) {
            al.a(j2, getUIManagerType()).updateRootLayoutSpecs(getRootViewTag(), i2, i3);
        }
    }

    public void a() {
        k kVar = this.f3042a;
        if (kVar != null && this.h) {
            kVar.b(this);
            this.f3042a = null;
            this.h = false;
        }
        this.i = false;
    }

    public void b() {
        this.j = new g(this);
        b bVar = this.f;
        if (bVar != null) {
            bVar.a(this);
        }
    }

    public void setEventListener(b bVar) {
        this.f = bVar;
    }

    /* access modifiers changed from: package-private */
    public String getJSModuleName() {
        return (String) com.facebook.i.a.a.a(this.f3043b);
    }

    public Bundle getAppProperties() {
        return this.f3044c;
    }

    public String getInitialUITemplate() {
        return this.f3045d;
    }

    public void setAppProperties(Bundle bundle) {
        UiThreadUtil.assertOnUiThread();
        this.f3044c = bundle;
        if (getRootViewTag() != 0) {
            c();
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        com.facebook.systrace.a.a(0, "ReactRootView.runApplication");
        try {
            if (this.f3042a != null) {
                if (this.h) {
                    ReactContext j2 = this.f3042a.j();
                    if (j2 == null) {
                        com.facebook.systrace.a.b(0);
                        return;
                    }
                    CatalystInstance catalystInstance = j2.getCatalystInstance();
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("rootTag", (double) getRootViewTag());
                    Bundle appProperties = getAppProperties();
                    if (appProperties != null) {
                        writableNativeMap.putMap("initialProps", Arguments.fromBundle(appProperties));
                    }
                    if (getUIManagerType() == 2) {
                        writableNativeMap.putBoolean("fabric", true);
                    }
                    this.i = true;
                    ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).runApplication(getJSModuleName(), writableNativeMap);
                    com.facebook.systrace.a.b(0);
                }
            }
        } finally {
            com.facebook.systrace.a.b(0);
        }
    }

    private a getCustomGlobalLayoutListener() {
        if (this.e == null) {
            this.e = new a();
        }
        return this.e;
    }

    private void g() {
        com.facebook.systrace.a.a(0, "attachToReactInstanceManager");
        try {
            if (!this.h) {
                this.h = true;
                ((k) com.facebook.i.a.a.a(this.f3042a)).a(this);
                getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
                com.facebook.systrace.a.b(0);
            }
        } finally {
            com.facebook.systrace.a.b(0);
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.Object
    public void finalize() {
        super.finalize();
        com.facebook.i.a.a.a(!this.h, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }

    public int getRootViewTag() {
        return this.g;
    }

    public void setRootViewTag(int i2) {
        this.g = i2;
    }

    @Override // com.facebook.react.uimanager.ab
    public void a(Throwable th) {
        k kVar = this.f3042a;
        if (kVar == null || kVar.j() == null) {
            throw new RuntimeException(th);
        }
        this.f3042a.j().handleException(new f(th.getMessage(), this, th));
    }

    public void setIsFabric(boolean z) {
        this.o = z ? 2 : 1;
    }

    public int getUIManagerType() {
        return this.o;
    }

    public k getReactInstanceManager() {
        return this.f3042a;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, WritableMap writableMap) {
        k kVar = this.f3042a;
        if (kVar != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) kVar.j().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactRootView */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: b  reason: collision with root package name */
        private final Rect f3047b;

        /* renamed from: c  reason: collision with root package name */
        private final int f3048c;

        /* renamed from: d  reason: collision with root package name */
        private int f3049d = 0;
        private int e = 0;
        private DisplayMetrics f = new DisplayMetrics();
        private DisplayMetrics g = new DisplayMetrics();

        a() {
            c.a(r.this.getContext().getApplicationContext());
            this.f3047b = new Rect();
            this.f3048c = (int) o.a(60.0f);
        }

        public void onGlobalLayout() {
            if (r.this.f3042a != null && r.this.h && r.this.f3042a.j() != null) {
                a();
                b();
                c();
            }
        }

        private void a() {
            r.this.getRootView().getWindowVisibleDisplayFrame(this.f3047b);
            int i = c.a().heightPixels - this.f3047b.bottom;
            if (this.f3049d != i && i > this.f3048c) {
                this.f3049d = i;
                WritableMap createMap = Arguments.createMap();
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putDouble("screenY", (double) o.d((float) this.f3047b.bottom));
                createMap2.putDouble("screenX", (double) o.d((float) this.f3047b.left));
                createMap2.putDouble("width", (double) o.d((float) this.f3047b.width()));
                createMap2.putDouble("height", (double) o.d((float) this.f3049d));
                createMap.putMap("endCoordinates", createMap2);
                r.this.a("keyboardDidShow", createMap);
            } else if (this.f3049d != 0 && i <= this.f3048c) {
                this.f3049d = 0;
                r.this.a("keyboardDidHide", (WritableMap) null);
            }
        }

        private void b() {
            int rotation = ((WindowManager) r.this.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
            if (this.e != rotation) {
                this.e = rotation;
                a(rotation);
            }
        }

        private void c() {
            c.b(r.this.getContext());
            if (!a(this.f, c.a()) || !a(this.g, c.b())) {
                this.f.setTo(c.a());
                this.g.setTo(c.b());
                d();
            }
        }

        private boolean a(DisplayMetrics displayMetrics, DisplayMetrics displayMetrics2) {
            if (Build.VERSION.SDK_INT >= 17) {
                return displayMetrics.equals(displayMetrics2);
            }
            return displayMetrics.widthPixels == displayMetrics2.widthPixels && displayMetrics.heightPixels == displayMetrics2.heightPixels && displayMetrics.density == displayMetrics2.density && displayMetrics.densityDpi == displayMetrics2.densityDpi && displayMetrics.scaledDensity == displayMetrics2.scaledDensity && displayMetrics.xdpi == displayMetrics2.xdpi && displayMetrics.ydpi == displayMetrics2.ydpi;
        }

        private void a(int i) {
            String str;
            double d2;
            boolean z = true;
            switch (i) {
                case 0:
                    str = "portrait-primary";
                    d2 = 0.0d;
                    z = false;
                    break;
                case 1:
                    str = "landscape-primary";
                    d2 = -90.0d;
                    break;
                case 2:
                    str = "portrait-secondary";
                    d2 = 180.0d;
                    z = false;
                    break;
                case 3:
                    str = "landscape-secondary";
                    d2 = 90.0d;
                    break;
                default:
                    return;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putString("name", str);
            createMap.putDouble("rotationDegrees", d2);
            createMap.putBoolean("isLandscape", z);
            r.this.a("namedOrientationDidChange", createMap);
        }

        private void d() {
            ((DeviceInfoModule) r.this.f3042a.j().getNativeModule(DeviceInfoModule.class)).emitUpdateDimensionsEvent();
        }
    }
}
