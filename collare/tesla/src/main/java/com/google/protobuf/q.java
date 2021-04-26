package com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ExtensionRegistryLite */
public class q {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f4458a = false;

    /* renamed from: b  reason: collision with root package name */
    static final q f4459b = new q(true);

    /* renamed from: c  reason: collision with root package name */
    private static final Class<?> f4460c = b();

    /* renamed from: d  reason: collision with root package name */
    private final Map<Object, Object<?, ?>> f4461d;

    static Class<?> b() {
        try {
            return Class.forName("com.google.protobuf.m");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static boolean c() {
        return f4458a;
    }

    public static q d() {
        return p.b();
    }

    q() {
        this.f4461d = new HashMap();
    }

    q(q qVar) {
        if (qVar == f4459b) {
            this.f4461d = Collections.emptyMap();
        } else {
            this.f4461d = Collections.unmodifiableMap(qVar.f4461d);
        }
    }

    q(boolean z) {
        this.f4461d = Collections.emptyMap();
    }
}
