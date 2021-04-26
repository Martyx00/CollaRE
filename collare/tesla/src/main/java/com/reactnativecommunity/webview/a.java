package com.reactnativecommunity.webview;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.o;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: RNCWebViewPackage */
public class a implements o {

    /* renamed from: a  reason: collision with root package name */
    private RNCWebViewManager f4768a;

    /* renamed from: b  reason: collision with root package name */
    private RNCWebViewModule f4769b;

    @Override // com.facebook.react.o
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        this.f4769b = new RNCWebViewModule(reactApplicationContext);
        this.f4769b.setPackage(this);
        arrayList.add(this.f4769b);
        return arrayList;
    }

    @Override // com.facebook.react.o
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        this.f4768a = new RNCWebViewManager();
        this.f4768a.setPackage(this);
        return Arrays.asList(this.f4768a);
    }

    public RNCWebViewModule a() {
        return this.f4769b;
    }
}
