package com.facebook.react.views.picker.a;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: PickerItemSelectEvent */
public class a extends c<a> {

    /* renamed from: a  reason: collision with root package name */
    private final int f3366a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topSelect";
    }

    public a(int i, int i2) {
        super(i);
        this.f3366a = i2;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("position", this.f3366a);
        return createMap;
    }
}
