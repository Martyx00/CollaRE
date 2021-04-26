package com.idehub.GoogleAnalyticsBridge;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.o;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: GoogleAnalyticsBridgePackage */
public class b implements o {

    /* renamed from: a  reason: collision with root package name */
    private String f4691a;

    public b(String str) {
        this.f4691a = str;
    }

    public b() {
        this(null);
    }

    @Override // com.facebook.react.o
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new GoogleAnalyticsBridge(reactApplicationContext, this.f4691a));
        arrayList.add(new GoogleTagManagerBridge(reactApplicationContext));
        return arrayList;
    }

    @Override // com.facebook.react.o
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new ViewManager[0]);
    }
}
