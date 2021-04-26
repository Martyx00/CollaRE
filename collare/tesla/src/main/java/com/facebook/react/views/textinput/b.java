package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: ReactContentSizeChangedEvent */
public class b extends c<e> {

    /* renamed from: a  reason: collision with root package name */
    private float f3511a;

    /* renamed from: b  reason: collision with root package name */
    private float f3512b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topContentSizeChange";
    }

    public b(int i, float f, float f2) {
        super(i);
        this.f3511a = f;
        this.f3512b = f2;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("width", (double) this.f3511a);
        createMap2.putDouble("height", (double) this.f3512b);
        createMap.putMap("contentSize", createMap2);
        createMap.putInt("target", d());
        return createMap;
    }
}
