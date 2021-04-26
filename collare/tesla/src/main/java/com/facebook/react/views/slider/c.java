package com.facebook.react.views.slider;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: ReactSlidingCompleteEvent */
public class c extends com.facebook.react.uimanager.events.c<c> {

    /* renamed from: a  reason: collision with root package name */
    private final double f3418a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topSlidingComplete";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public c(int i, double d2) {
        super(i);
        this.f3418a = d2;
    }

    public double j() {
        return this.f3418a;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), k());
    }

    private WritableMap k() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", d());
        createMap.putDouble(FirebaseAnalytics.b.VALUE, j());
        return createMap;
    }
}
