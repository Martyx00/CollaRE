package com.facebook.react.views.scroll;

import android.support.v4.util.Pools;
import com.facebook.i.a.a;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import com.facebook.react.uimanager.o;

/* compiled from: ScrollEvent */
public class h extends c<h> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.a<h> f3397a = new Pools.a<>(3);

    /* renamed from: b  reason: collision with root package name */
    private int f3398b;

    /* renamed from: c  reason: collision with root package name */
    private int f3399c;

    /* renamed from: d  reason: collision with root package name */
    private double f3400d;
    private double e;
    private int f;
    private int g;
    private int h;
    private int i;
    private i j;

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public static h a(int i2, i iVar, int i3, int i4, float f2, float f3, int i5, int i6, int i7, int i8) {
        h a2 = f3397a.a();
        if (a2 == null) {
            a2 = new h();
        }
        a2.b(i2, iVar, i3, i4, f2, f3, i5, i6, i7, i8);
        return a2;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void c() {
        f3397a.a(this);
    }

    private h() {
    }

    private void b(int i2, i iVar, int i3, int i4, float f2, float f3, int i5, int i6, int i7, int i8) {
        super.a(i2);
        this.j = iVar;
        this.f3398b = i3;
        this.f3399c = i4;
        this.f3400d = (double) f2;
        this.e = (double) f3;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
    }

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return i.a((i) a.a(this.j));
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return this.j == i.SCROLL;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("top", 0.0d);
        createMap.putDouble("bottom", 0.0d);
        createMap.putDouble("left", 0.0d);
        createMap.putDouble("right", 0.0d);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("x", (double) o.d((float) this.f3398b));
        createMap2.putDouble("y", (double) o.d((float) this.f3399c));
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putDouble("width", (double) o.d((float) this.f));
        createMap3.putDouble("height", (double) o.d((float) this.g));
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putDouble("width", (double) o.d((float) this.h));
        createMap4.putDouble("height", (double) o.d((float) this.i));
        WritableMap createMap5 = Arguments.createMap();
        createMap5.putDouble("x", this.f3400d);
        createMap5.putDouble("y", this.e);
        WritableMap createMap6 = Arguments.createMap();
        createMap6.putMap("contentInset", createMap);
        createMap6.putMap("contentOffset", createMap2);
        createMap6.putMap("contentSize", createMap3);
        createMap6.putMap("layoutMeasurement", createMap4);
        createMap6.putMap("velocity", createMap5);
        createMap6.putInt("target", d());
        createMap6.putBoolean("responderIgnoreScroll", true);
        return createMap6;
    }
}
