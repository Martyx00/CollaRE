package com.facebook.react.views.drawer.a;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: DrawerOpenedEvent */
public class b extends c<b> {
    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topDrawerOpened";
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public b(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), Arguments.createMap());
    }
}
