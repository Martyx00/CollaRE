package com.facebook.react.views.drawer.a;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: DrawerClosedEvent */
public class a extends c<a> {
    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topDrawerClosed";
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public a(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), Arguments.createMap());
    }
}
