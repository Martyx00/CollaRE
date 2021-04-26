package com.facebook.react;

import android.app.Activity;
import android.app.Application;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.a.a;
import com.facebook.react.devsupport.e;
import com.facebook.react.modules.core.b;
import com.facebook.react.uimanager.ak;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: ReactInstanceManagerBuilder */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private final List<o> f2780a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private String f2781b;

    /* renamed from: c  reason: collision with root package name */
    private JSBundleLoader f2782c;

    /* renamed from: d  reason: collision with root package name */
    private String f2783d;
    private NotThreadSafeBridgeIdleDebugListener e;
    private Application f;
    private boolean g;
    private LifecycleState h;
    private ak i;
    private NativeModuleCallExceptionHandler j;
    private Activity k;
    private b l;
    private e m;
    private boolean n;
    private a o;
    private JavaScriptExecutorFactory p;
    private int q = 1;
    private int r = -1;
    private JSIModulePackage s;
    private Map<String, Object> t;

    l() {
    }

    public l a(ak akVar) {
        this.i = akVar;
        return this;
    }

    public l a(JSIModulePackage jSIModulePackage) {
        this.s = jSIModulePackage;
        return this;
    }

    public l a(JavaScriptExecutorFactory javaScriptExecutorFactory) {
        this.p = javaScriptExecutorFactory;
        return this;
    }

    public l a(String str) {
        String str2;
        if (str == null) {
            str2 = null;
        } else {
            str2 = "assets://" + str;
        }
        this.f2781b = str2;
        this.f2782c = null;
        return this;
    }

    public l b(String str) {
        if (!str.startsWith("assets://")) {
            return a(JSBundleLoader.createFileLoader(str));
        }
        this.f2781b = str;
        this.f2782c = null;
        return this;
    }

    public l a(JSBundleLoader jSBundleLoader) {
        this.f2782c = jSBundleLoader;
        this.f2781b = null;
        return this;
    }

    public l c(String str) {
        this.f2783d = str;
        return this;
    }

    public l a(o oVar) {
        this.f2780a.add(oVar);
        return this;
    }

    public l a(Application application) {
        this.f = application;
        return this;
    }

    public l a(boolean z) {
        this.g = z;
        return this;
    }

    public l a(LifecycleState lifecycleState) {
        this.h = lifecycleState;
        return this;
    }

    public l a(e eVar) {
        this.m = eVar;
        return this;
    }

    public k a() {
        String str;
        com.facebook.i.a.a.a(this.f, "Application property has not been set with this builder");
        boolean z = true;
        com.facebook.i.a.a.a((!this.g && this.f2781b == null && this.f2782c == null) ? false : true, "JS Bundle File or Asset URL has to be provided when dev support is disabled");
        if (this.f2783d == null && this.f2781b == null && this.f2782c == null) {
            z = false;
        }
        com.facebook.i.a.a.a(z, "Either MainModulePath or JS Bundle File needs to be provided");
        if (this.i == null) {
            this.i = new ak();
        }
        String packageName = this.f.getPackageName();
        String a2 = com.facebook.react.modules.systeminfo.a.a();
        Application application = this.f;
        Activity activity = this.k;
        b bVar = this.l;
        JavaScriptExecutorFactory javaScriptExecutorFactory = this.p;
        if (javaScriptExecutorFactory == null) {
            javaScriptExecutorFactory = new com.facebook.react.jscexecutor.a(packageName, a2);
        }
        return new k(application, activity, bVar, javaScriptExecutorFactory, (this.f2782c != null || (str = this.f2781b) == null) ? this.f2782c : JSBundleLoader.createAssetLoader(this.f, str, false), this.f2783d, this.f2780a, this.g, this.e, (LifecycleState) com.facebook.i.a.a.a(this.h, "Initial lifecycle state was not set"), this.i, this.j, this.m, this.n, this.o, this.q, this.r, this.s, this.t);
    }
}
