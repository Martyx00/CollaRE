package com.facebook.imagepipeline.p;

/* compiled from: FrescoSystrace */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final a f2478a = new C0052b();

    /* renamed from: b  reason: collision with root package name */
    private static volatile c f2479b = null;

    /* compiled from: FrescoSystrace */
    public interface a {
    }

    /* compiled from: FrescoSystrace */
    public interface c {
        void a();

        void a(String str);
    }

    private b() {
    }

    public static void a(String str) {
        b().a(str);
    }

    public static void a() {
        b().a();
    }

    private static c b() {
        if (f2479b == null) {
            synchronized (b.class) {
                if (f2479b == null) {
                    f2479b = new a();
                }
            }
        }
        return f2479b;
    }

    /* renamed from: com.facebook.imagepipeline.p.b$b  reason: collision with other inner class name */
    /* compiled from: FrescoSystrace */
    private static final class C0052b implements a {
        private C0052b() {
        }
    }
}
