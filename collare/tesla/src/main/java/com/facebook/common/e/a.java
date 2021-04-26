package com.facebook.common.e;

/* compiled from: FLog */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static c f1749a = b.a();

    public static boolean a(int i) {
        return f1749a.a(i);
    }

    public static void a(Class<?> cls, String str) {
        if (f1749a.a(2)) {
            f1749a.a(a(cls), str);
        }
    }

    public static void a(Class<?> cls, String str, Object obj) {
        if (f1749a.a(2)) {
            f1749a.a(a(cls), a(str, obj));
        }
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2) {
        if (f1749a.a(2)) {
            f1749a.a(a(cls), a(str, obj, obj2));
        }
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (a(2)) {
            a(cls, a(str, obj, obj2, obj3));
        }
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (f1749a.a(2)) {
            f1749a.a(a(cls), a(str, obj, obj2, obj3, obj4));
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        if (f1749a.a(2)) {
            f1749a.a(str, a(str2, objArr));
        }
    }

    public static void a(Class<?> cls, String str, Object... objArr) {
        if (f1749a.a(2)) {
            f1749a.a(a(cls), a(str, objArr));
        }
    }

    public static void a(String str, String str2) {
        if (f1749a.a(3)) {
            f1749a.b(str, str2);
        }
    }

    public static void b(Class<?> cls, String str, Object obj) {
        if (f1749a.a(3)) {
            f1749a.b(a(cls), a(str, obj));
        }
    }

    public static void b(String str, String str2) {
        if (f1749a.a(4)) {
            f1749a.c(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f1749a.a(4)) {
            f1749a.a(str, str2, th);
        }
    }

    public static void c(String str, String str2) {
        if (f1749a.a(5)) {
            f1749a.d(str, str2);
        }
    }

    public static void b(Class<?> cls, String str) {
        if (f1749a.a(5)) {
            f1749a.d(a(cls), str);
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        if (f1749a.a(5)) {
            f1749a.d(str, a(str2, objArr));
        }
    }

    public static void a(String str, Throwable th, String str2, Object... objArr) {
        if (f1749a.a(5)) {
            f1749a.b(str, a(str2, objArr), th);
        }
    }

    public static void b(Class<?> cls, String str, Object... objArr) {
        if (f1749a.a(5)) {
            f1749a.d(a(cls), a(str, objArr));
        }
    }

    public static void a(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (a(5)) {
            a(cls, a(str, objArr), th);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (f1749a.a(5)) {
            f1749a.b(str, str2, th);
        }
    }

    public static void a(Class<?> cls, String str, Throwable th) {
        if (f1749a.a(5)) {
            f1749a.b(a(cls), str, th);
        }
    }

    public static void d(String str, String str2) {
        if (f1749a.a(6)) {
            f1749a.e(str, str2);
        }
    }

    public static void c(Class<?> cls, String str) {
        if (f1749a.a(6)) {
            f1749a.e(a(cls), str);
        }
    }

    public static void c(String str, String str2, Object... objArr) {
        if (f1749a.a(6)) {
            f1749a.e(str, a(str2, objArr));
        }
    }

    public static void c(Class<?> cls, String str, Object... objArr) {
        if (f1749a.a(6)) {
            f1749a.e(a(cls), a(str, objArr));
        }
    }

    public static void b(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (f1749a.a(6)) {
            f1749a.c(a(cls), a(str, objArr), th);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (f1749a.a(6)) {
            f1749a.c(str, str2, th);
        }
    }

    public static void b(Class<?> cls, String str, Throwable th) {
        if (f1749a.a(6)) {
            f1749a.c(a(cls), str, th);
        }
    }

    public static void e(String str, String str2) {
        if (f1749a.a(6)) {
            f1749a.f(str, str2);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (f1749a.a(6)) {
            f1749a.f(str, a(str2, objArr));
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (f1749a.a(6)) {
            f1749a.d(str, str2, th);
        }
    }

    public static void c(Class<?> cls, String str, Throwable th) {
        if (f1749a.a(6)) {
            f1749a.d(a(cls), str, th);
        }
    }

    private static String a(String str, Object... objArr) {
        return String.format(null, str, objArr);
    }

    private static String a(Class<?> cls) {
        return cls.getSimpleName();
    }
}
