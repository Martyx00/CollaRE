package com.facebook.imagepipeline.c;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.common.g.g;
import com.facebook.common.h.a;
import com.facebook.g.b;
import com.facebook.imagepipeline.j.d;

@TargetApi(11)
/* compiled from: HoneycombBitmapFactory */
public class e extends f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2023a = "e";

    /* renamed from: b  reason: collision with root package name */
    private final b f2024b;

    /* renamed from: c  reason: collision with root package name */
    private final com.facebook.imagepipeline.l.e f2025c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f2026d;

    public e(b bVar, com.facebook.imagepipeline.l.e eVar) {
        this.f2024b = bVar;
        this.f2025c = eVar;
    }

    /* JADX INFO: finally extract failed */
    @Override // com.facebook.imagepipeline.c.f
    @TargetApi(12)
    public a<Bitmap> a(int i, int i2, Bitmap.Config config) {
        if (this.f2026d) {
            return c(i, i2, config);
        }
        a<g> a2 = this.f2024b.a((short) i, (short) i2);
        try {
            d dVar = new d(a2);
            dVar.a(b.f1987a);
            try {
                a<Bitmap> a3 = this.f2025c.a(dVar, config, null, a2.a().a());
                if (!a3.a().isMutable()) {
                    a.c(a3);
                    this.f2026d = true;
                    com.facebook.common.e.a.e(f2023a, "Immutable bitmap returned by decoder");
                    a<Bitmap> c2 = c(i, i2, config);
                    d.d(dVar);
                    return c2;
                }
                a3.a().setHasAlpha(true);
                a3.a().eraseColor(0);
                d.d(dVar);
                a2.close();
                return a3;
            } catch (Throwable th) {
                d.d(dVar);
                throw th;
            }
        } finally {
            a2.close();
        }
    }

    private static a<Bitmap> c(int i, int i2, Bitmap.Config config) {
        return a.a(Bitmap.createBitmap(i, i2, config), g.a());
    }
}
