package com.facebook.react.views.webview.a;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: TopLoadingFinishEvent */
public class b extends c<b> {

    /* renamed from: a  reason: collision with root package name */
    private WritableMap f3600a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topLoadingFinish";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public b(int i, WritableMap writableMap) {
        super(i);
        this.f3600a = writableMap;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), this.f3600a);
    }
}
