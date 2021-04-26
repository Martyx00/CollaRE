package com.facebook.imagepipeline.n;

import android.content.ContentResolver;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Pair;
import com.facebook.common.d.f;
import com.facebook.common.e.a;
import com.facebook.common.g.g;
import com.facebook.common.g.h;
import com.facebook.common.g.i;
import com.facebook.imagepipeline.e.e;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: LocalExifThumbnailProducer */
public class z implements ax<d> {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f2452a;

    /* renamed from: b  reason: collision with root package name */
    private final h f2453b;

    /* renamed from: c  reason: collision with root package name */
    private final ContentResolver f2454c;

    public z(Executor executor, h hVar, ContentResolver contentResolver) {
        this.f2452a = executor;
        this.f2453b = hVar;
        this.f2454c = contentResolver;
    }

    @Override // com.facebook.imagepipeline.n.ax
    public boolean a(e eVar) {
        return ay.a(512, 512, eVar);
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        an c2 = alVar.c();
        String b2 = alVar.b();
        final b a2 = alVar.a();
        final AnonymousClass1 r7 = new ar<d>(kVar, c2, "LocalExifThumbnailProducer", b2) {
            /* class com.facebook.imagepipeline.n.z.AnonymousClass1 */

            /* access modifiers changed from: protected */
            /* renamed from: d */
            public d c() {
                ExifInterface a2 = z.this.a(a2.b());
                if (a2 == null || !a2.hasThumbnail()) {
                    return null;
                }
                return z.this.a((z) z.this.f2453b.a(a2.getThumbnail()), (g) a2);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void b(d dVar) {
                d.d(dVar);
            }

            /* access modifiers changed from: protected */
            /* renamed from: b */
            public Map<String, String> c(d dVar) {
                return f.a("createdThumbnail", Boolean.toString(dVar != null));
            }
        };
        alVar.a(new e() {
            /* class com.facebook.imagepipeline.n.z.AnonymousClass2 */

            @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
            public void a() {
                r7.a();
            }
        });
        this.f2452a.execute(r7);
    }

    /* access modifiers changed from: package-private */
    public ExifInterface a(Uri uri) {
        String a2 = com.facebook.common.k.f.a(this.f2454c, uri);
        try {
            if (a(a2)) {
                return new ExifInterface(a2);
            }
            return null;
        } catch (IOException unused) {
            return null;
        } catch (StackOverflowError unused2) {
            a.c(z.class, "StackOverflowError in ExifInterface constructor");
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private d a(g gVar, ExifInterface exifInterface) {
        Pair<Integer, Integer> a2 = com.facebook.h.a.a(new i(gVar));
        int a3 = a(exifInterface);
        int i = -1;
        int intValue = a2 != null ? ((Integer) a2.first).intValue() : -1;
        if (a2 != null) {
            i = ((Integer) a2.second).intValue();
        }
        com.facebook.common.h.a a4 = com.facebook.common.h.a.a(gVar);
        try {
            d dVar = new d(a4);
            com.facebook.common.h.a.c(a4);
            dVar.a(com.facebook.g.b.f1987a);
            dVar.c(a3);
            dVar.b(intValue);
            dVar.a(i);
            return dVar;
        } catch (Throwable th) {
            com.facebook.common.h.a.c(a4);
            throw th;
        }
    }

    private int a(ExifInterface exifInterface) {
        return com.facebook.h.b.a(Integer.parseInt(exifInterface.getAttribute("Orientation")));
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            return false;
        }
        return true;
    }
}
