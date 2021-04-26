package com.facebook.g;

/* compiled from: DefaultImageFormats */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static final c f1987a = new c("JPEG", "jpeg");

    /* renamed from: b  reason: collision with root package name */
    public static final c f1988b = new c("PNG", "png");

    /* renamed from: c  reason: collision with root package name */
    public static final c f1989c = new c("GIF", "gif");

    /* renamed from: d  reason: collision with root package name */
    public static final c f1990d = new c("BMP", "bmp");
    public static final c e = new c("WEBP_SIMPLE", "webp");
    public static final c f = new c("WEBP_LOSSLESS", "webp");
    public static final c g = new c("WEBP_EXTENDED", "webp");
    public static final c h = new c("WEBP_EXTENDED_WITH_ALPHA", "webp");
    public static final c i = new c("WEBP_ANIMATED", "webp");
    public static final c j = new c("HEIF", "heif");

    public static boolean a(c cVar) {
        return b(cVar) || cVar == i;
    }

    public static boolean b(c cVar) {
        return cVar == e || cVar == f || cVar == g || cVar == h;
    }
}
