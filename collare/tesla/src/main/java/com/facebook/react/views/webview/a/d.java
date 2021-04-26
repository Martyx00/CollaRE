package com.facebook.react.views.webview.a;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: TopMessageEvent */
public class d extends c<d> {

    /* renamed from: a  reason: collision with root package name */
    private final String f3602a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topMessage";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public d(int i, String str) {
        super(i);
        this.f3602a = str;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("data", this.f3602a);
        rCTEventEmitter.receiveEvent(d(), "topMessage", createMap);
    }
}
