package com.facebook.react.views.webview.a;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: TopLoadingErrorEvent */
public class a extends c<a> {

    /* renamed from: a  reason: collision with root package name */
    private WritableMap f3599a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topLoadingError";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public a(int i, WritableMap writableMap) {
        super(i);
        this.f3599a = writableMap;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), this.f3599a);
    }
}
