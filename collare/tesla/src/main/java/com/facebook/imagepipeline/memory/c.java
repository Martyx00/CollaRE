package com.facebook.imagepipeline.memory;

/* compiled from: BitmapCounterProvider */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2201a = b();

    /* renamed from: b  reason: collision with root package name */
    private static final Class<?> f2202b = c.class;

    /* renamed from: c  reason: collision with root package name */
    private static int f2203c = 384;

    /* renamed from: d  reason: collision with root package name */
    private static volatile b f2204d;

    private static int b() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (((long) min) > 16777216) {
            return (min / 4) * 3;
        }
        return min / 2;
    }

    public static b a() {
        if (f2204d == null) {
            synchronized (c.class) {
                if (f2204d == null) {
                    f2204d = new b(f2203c, f2201a);
                }
            }
        }
        return f2204d;
    }
}
