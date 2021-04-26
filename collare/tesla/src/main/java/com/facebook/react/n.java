package com.facebook.react;

import android.app.Application;
import com.facebook.i.a.a;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.e;
import com.facebook.react.uimanager.ak;
import java.util.List;

/* compiled from: ReactNativeHost */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    private final Application f3037a;

    /* renamed from: b  reason: collision with root package name */
    private k f3038b;

    /* access modifiers changed from: protected */
    public e d() {
        return null;
    }

    /* access modifiers changed from: protected */
    public JavaScriptExecutorFactory e() {
        return null;
    }

    /* access modifiers changed from: protected */
    public JSIModulePackage g() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String h() {
        return "index.android";
    }

    /* access modifiers changed from: protected */
    public String i() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String j() {
        return "index.android.bundle";
    }

    public abstract boolean k();

    /* access modifiers changed from: protected */
    public abstract List<o> l();

    protected n(Application application) {
        this.f3037a = application;
    }

    public k a() {
        if (this.f3038b == null) {
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_START);
            this.f3038b = c();
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_END);
        }
        return this.f3038b;
    }

    public boolean b() {
        return this.f3038b != null;
    }

    /* access modifiers changed from: protected */
    public k c() {
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_START);
        l a2 = k.a().a(this.f3037a).c(h()).a(k()).a(d()).a(e()).a(f()).a(g()).a(LifecycleState.BEFORE_CREATE);
        for (o oVar : l()) {
            a2.a(oVar);
        }
        String i = i();
        if (i != null) {
            a2.b(i);
        } else {
            a2.a((String) a.a(j()));
        }
        k a3 = a2.a();
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_END);
        return a3;
    }

    /* access modifiers changed from: protected */
    public ak f() {
        return new ak();
    }
}
