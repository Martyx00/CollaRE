package com.facebook.react.modules.fresco;

import com.facebook.imagepipeline.o.b;
import com.facebook.imagepipeline.o.c;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: ReactNetworkImageRequest */
public class a extends b {

    /* renamed from: a  reason: collision with root package name */
    private final ReadableMap f2879a;

    public static a a(c cVar, ReadableMap readableMap) {
        return new a(cVar, readableMap);
    }

    protected a(c cVar, ReadableMap readableMap) {
        super(cVar);
        this.f2879a = readableMap;
    }

    public ReadableMap s() {
        return this.f2879a;
    }
}
