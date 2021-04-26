package com.facebook.react.uimanager;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: OnLayoutEvent */
public class n extends c<n> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.a<n> f3285a = new Pools.a<>(20);

    /* renamed from: b  reason: collision with root package name */
    private int f3286b;

    /* renamed from: c  reason: collision with root package name */
    private int f3287c;

    /* renamed from: d  reason: collision with root package name */
    private int f3288d;
    private int e;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topLayout";
    }

    public static n a(int i, int i2, int i3, int i4, int i5) {
        n a2 = f3285a.a();
        if (a2 == null) {
            a2 = new n();
        }
        a2.b(i, i2, i3, i4, i5);
        return a2;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void c() {
        f3285a.a(this);
    }

    private n() {
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, int i3, int i4, int i5) {
        super.a(i);
        this.f3286b = i2;
        this.f3287c = i3;
        this.f3288d = i4;
        this.e = i5;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("x", (double) o.d((float) this.f3286b));
        createMap.putDouble("y", (double) o.d((float) this.f3287c));
        createMap.putDouble("width", (double) o.d((float) this.f3288d));
        createMap.putDouble("height", (double) o.d((float) this.e));
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("layout", createMap);
        createMap2.putInt("target", d());
        rCTEventEmitter.receiveEvent(d(), a(), createMap2);
    }
}
