package com.facebook.react.views.webview.a;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* compiled from: TopLoadingStartEvent */
public class c extends com.facebook.react.uimanager.events.c<c> {

    /* renamed from: a  reason: collision with root package name */
    private WritableMap f3601a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topLoadingStart";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public c(int i, WritableMap writableMap) {
        super(i);
        this.f3601a = writableMap;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), this.f3601a);
    }
}
