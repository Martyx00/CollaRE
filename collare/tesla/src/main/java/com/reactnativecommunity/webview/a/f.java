package com.reactnativecommunity.webview.a;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import com.google.android.gms.common.internal.ImagesContract;

/* compiled from: TopShouldStartLoadWithRequestEvent */
public class f extends c<e> {

    /* renamed from: a  reason: collision with root package name */
    private final String f4780a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topShouldStartLoadWithRequest";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public f(int i, String str) {
        super(i);
        this.f4780a = str;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(ImagesContract.URL, this.f4780a);
        createMap.putString("navigationType", "other");
        rCTEventEmitter.receiveEvent(d(), "topShouldStartLoadWithRequest", createMap);
    }
}
