package com.facebook.imagepipeline.nativecode;

/* compiled from: WebpTranscoderFactory */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f2460a = false;

    /* renamed from: b  reason: collision with root package name */
    private static b f2461b;

    static {
        try {
            f2461b = (b) Class.forName("com.facebook.imagepipeline.nativecode.WebpTranscoderImpl").newInstance();
            f2460a = true;
        } catch (Throwable unused) {
            f2460a = false;
        }
    }

    public static b a() {
        return f2461b;
    }
}
