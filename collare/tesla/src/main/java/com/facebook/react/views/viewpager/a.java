package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: PageScrollEvent */
class a extends c<a> {

    /* renamed from: a  reason: collision with root package name */
    private final int f3578a;

    /* renamed from: b  reason: collision with root package name */
    private final float f3579b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topPageScroll";
    }

    protected a(int i, int i2, float f) {
        super(i);
        this.f3578a = i2;
        this.f3579b = (Float.isInfinite(f) || Float.isNaN(f)) ? BitmapDescriptorFactory.HUE_RED : f;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("position", this.f3578a);
        createMap.putDouble("offset", (double) this.f3579b);
        return createMap;
    }
}
