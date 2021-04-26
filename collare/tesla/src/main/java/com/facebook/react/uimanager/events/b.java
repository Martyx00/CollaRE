package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.o;

/* compiled from: ContentSizeChangeEvent */
public class b extends c<b> {

    /* renamed from: a  reason: collision with root package name */
    private final int f3228a;

    /* renamed from: b  reason: collision with root package name */
    private final int f3229b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topContentSizeChange";
    }

    public b(int i, int i2, int i3) {
        super(i);
        this.f3228a = i2;
        this.f3229b = i3;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("width", (double) o.d((float) this.f3228a));
        createMap.putDouble("height", (double) o.d((float) this.f3229b));
        rCTEventEmitter.receiveEvent(d(), "topContentSizeChange", createMap);
    }
}
