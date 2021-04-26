package com.google.protobuf;

/* compiled from: Android */
final class e {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?> f4179a = a("libcore.io.Memory");

    /* renamed from: b  reason: collision with root package name */
    private static final boolean f4180b = (a("org.robolectric.Robolectric") != null);

    static boolean a() {
        return f4179a != null && !f4180b;
    }

    private static <T> Class<T> a(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
