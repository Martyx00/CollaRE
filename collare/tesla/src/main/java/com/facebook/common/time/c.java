package com.facebook.common.time;

/* compiled from: SystemClock */
public class c implements a {

    /* renamed from: a  reason: collision with root package name */
    private static final c f1798a = new c();

    private c() {
    }

    public static c b() {
        return f1798a;
    }

    @Override // com.facebook.common.time.a
    public long a() {
        return System.currentTimeMillis();
    }
}
