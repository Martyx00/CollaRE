package com.google.protobuf;

/* access modifiers changed from: package-private */
/* compiled from: ExtensionRegistryFactory */
public final class p {

    /* renamed from: a  reason: collision with root package name */
    static final Class<?> f4457a = a();

    static Class<?> a() {
        try {
            return Class.forName("com.google.protobuf.o");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static q b() {
        if (f4457a != null) {
            try {
                return a("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return q.f4459b;
    }

    private static final q a(String str) {
        return (q) f4457a.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }
}
