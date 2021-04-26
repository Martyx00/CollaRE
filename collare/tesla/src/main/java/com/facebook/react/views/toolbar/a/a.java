package com.facebook.react.views.toolbar.a;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: ToolbarClickEvent */
public class a extends c<a> {

    /* renamed from: a  reason: collision with root package name */
    private final int f3545a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topSelect";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    public a(int i, int i2) {
        super(i);
        this.f3545a = i2;
    }

    public int j() {
        return this.f3545a;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("position", j());
        rCTEventEmitter.receiveEvent(d(), a(), writableNativeMap);
    }
}
