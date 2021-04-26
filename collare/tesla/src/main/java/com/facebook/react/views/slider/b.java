package com.facebook.react.views.slider;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: ReactSliderEvent */
public class b extends c<b> {

    /* renamed from: a  reason: collision with root package name */
    private final double f3416a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f3417b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topChange";
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public b(int i, double d2, boolean z) {
        super(i);
        this.f3416a = d2;
        this.f3417b = z;
    }

    public double j() {
        return this.f3416a;
    }

    public boolean k() {
        return this.f3417b;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), l());
    }

    private WritableMap l() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", d());
        createMap.putDouble(FirebaseAnalytics.b.VALUE, j());
        createMap.putBoolean("fromUser", k());
        return createMap;
    }
}
