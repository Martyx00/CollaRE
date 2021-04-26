package com.facebook.react.views.switchview;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: ReactSwitchEvent */
class b extends c<b> {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f3427a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topChange";
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public b(int i, boolean z) {
        super(i);
        this.f3427a = z;
    }

    public boolean j() {
        return this.f3427a;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), k());
    }

    private WritableMap k() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", d());
        createMap.putBoolean(FirebaseAnalytics.b.VALUE, j());
        return createMap;
    }
}
