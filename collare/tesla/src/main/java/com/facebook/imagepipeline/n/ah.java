package com.facebook.imagepipeline.n;

import android.net.Uri;
import com.facebook.common.g.h;
import com.facebook.common.g.j;
import com.facebook.imagepipeline.d.e;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import com.facebook.imagepipeline.o.c;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PartialDiskCacheProducer */
public class ah implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final e f2269a;

    /* renamed from: b  reason: collision with root package name */
    private final f f2270b;

    /* renamed from: c  reason: collision with root package name */
    private final h f2271c;

    /* renamed from: d  reason: collision with root package name */
    private final com.facebook.common.g.a f2272d;
    private final ak<d> e;

    public ah(e eVar, f fVar, h hVar, com.facebook.common.g.a aVar, ak<d> akVar) {
        this.f2269a = eVar;
        this.f2270b = fVar;
        this.f2271c = hVar;
        this.f2272d = aVar;
        this.e = akVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        b a2 = alVar.a();
        if (!a2.n()) {
            this.e.a(kVar, alVar);
            return;
        }
        alVar.c().a(alVar.b(), "PartialDiskCacheProducer");
        com.facebook.b.a.d a3 = this.f2270b.a(a2, a(a2), alVar.d());
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f2269a.a(a3, atomicBoolean).a((a.f<d, TContinuationResult>) a(kVar, alVar, a3));
        a(atomicBoolean, alVar);
    }

    private a.f<d, Void> a(final k<d> kVar, final al alVar, final com.facebook.b.a.d dVar) {
        final String b2 = alVar.b();
        final an c2 = alVar.c();
        return new a.f<d, Void>() {
            /* class com.facebook.imagepipeline.n.ah.AnonymousClass1 */

            /* renamed from: b */
            public Void a(a.h<d> hVar) {
                if (ah.b(hVar)) {
                    c2.b(b2, "PartialDiskCacheProducer", null);
                    kVar.b();
                } else if (hVar.d()) {
                    c2.a(b2, "PartialDiskCacheProducer", hVar.f(), null);
                    ah.this.a((ah) kVar, (k) alVar, (al) dVar, (com.facebook.b.a.d) null);
                } else {
                    d e2 = hVar.e();
                    if (e2 != null) {
                        an anVar = c2;
                        String str = b2;
                        anVar.a(str, "PartialDiskCacheProducer", ah.a(anVar, str, true, e2.l()));
                        com.facebook.imagepipeline.e.a b2 = com.facebook.imagepipeline.e.a.b(e2.l() - 1);
                        e2.a(b2);
                        int l = e2.l();
                        b a2 = alVar.a();
                        if (b2.a(a2.h())) {
                            c2.a(b2, "PartialDiskCacheProducer", true);
                            kVar.b(e2, 9);
                        } else {
                            kVar.b(e2, 8);
                            ah.this.a((ah) kVar, (k) new aq(c.a(a2).a(com.facebook.imagepipeline.e.a.a(l - 1)).o(), alVar), (al) dVar, (com.facebook.b.a.d) e2);
                        }
                    } else {
                        an anVar2 = c2;
                        String str2 = b2;
                        anVar2.a(str2, "PartialDiskCacheProducer", ah.a(anVar2, str2, false, 0));
                        ah.this.a((ah) kVar, (k) alVar, (al) dVar, (com.facebook.b.a.d) e2);
                    }
                }
                return null;
            }
        };
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(k<d> kVar, al alVar, com.facebook.b.a.d dVar, d dVar2) {
        this.e.a(new a(kVar, this.f2269a, dVar, this.f2271c, this.f2272d, dVar2), alVar);
    }

    /* access modifiers changed from: private */
    public static boolean b(a.h<?> hVar) {
        return hVar.c() || (hVar.d() && (hVar.f() instanceof CancellationException));
    }

    static Map<String, String> a(an anVar, String str, boolean z, int i) {
        if (!anVar.b(str)) {
            return null;
        }
        if (z) {
            return com.facebook.common.d.f.a("cached_value_found", String.valueOf(z), "encodedImageSize", String.valueOf(i));
        }
        return com.facebook.common.d.f.a("cached_value_found", String.valueOf(z));
    }

    private void a(final AtomicBoolean atomicBoolean, al alVar) {
        alVar.a(new e() {
            /* class com.facebook.imagepipeline.n.ah.AnonymousClass2 */

            @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
            public void a() {
                atomicBoolean.set(true);
            }
        });
    }

    private static Uri a(b bVar) {
        return bVar.b().buildUpon().appendQueryParameter("fresco_partial", "true").build();
    }

    /* access modifiers changed from: private */
    /* compiled from: PartialDiskCacheProducer */
    public static class a extends n<d, d> {

        /* renamed from: a  reason: collision with root package name */
        private final e f2279a;

        /* renamed from: b  reason: collision with root package name */
        private final com.facebook.b.a.d f2280b;

        /* renamed from: c  reason: collision with root package name */
        private final h f2281c;

        /* renamed from: d  reason: collision with root package name */
        private final com.facebook.common.g.a f2282d;
        private final d e;

        private a(k<d> kVar, e eVar, com.facebook.b.a.d dVar, h hVar, com.facebook.common.g.a aVar, d dVar2) {
            super(kVar);
            this.f2279a = eVar;
            this.f2280b = dVar;
            this.f2281c = hVar;
            this.f2282d = aVar;
            this.e = dVar2;
        }

        public void a(d dVar, int i) {
            if (!b(i)) {
                if (this.e != null && dVar.k() != null) {
                    try {
                        a(a(this.e, dVar));
                    } catch (IOException e2) {
                        com.facebook.common.e.a.c("PartialDiskCacheProducer", "Error while merging image data", e2);
                        d().b(e2);
                    } catch (Throwable th) {
                        dVar.close();
                        this.e.close();
                        throw th;
                    }
                    dVar.close();
                    this.e.close();
                    this.f2279a.c(this.f2280b);
                } else if (!b(i, 8) || !a(i) || dVar.e() == com.facebook.g.c.f1991a) {
                    d().b(dVar, i);
                } else {
                    this.f2279a.a(this.f2280b, dVar);
                    d().b(dVar, i);
                }
            }
        }

        private j a(d dVar, d dVar2) {
            j a2 = this.f2281c.a(dVar2.l() + dVar2.k().f2076a);
            a(dVar.d(), a2, dVar2.k().f2076a);
            a(dVar2.d(), a2, dVar2.l());
            return a2;
        }

        private void a(InputStream inputStream, OutputStream outputStream, int i) {
            byte[] bArr = (byte[]) this.f2282d.a(16384);
            int i2 = i;
            while (i2 > 0) {
                try {
                    int read = inputStream.read(bArr, 0, Math.min(16384, i2));
                    if (read < 0) {
                        break;
                    } else if (read > 0) {
                        outputStream.write(bArr, 0, read);
                        i2 -= read;
                    }
                } catch (Throwable th) {
                    this.f2282d.a(bArr);
                    throw th;
                }
            }
            this.f2282d.a(bArr);
            if (i2 > 0) {
                throw new IOException(String.format(null, "Failed to read %d bytes - finished %d short", Integer.valueOf(i), Integer.valueOf(i2)));
            }
        }

        private void a(j jVar) {
            d dVar;
            Throwable th;
            com.facebook.common.h.a a2 = com.facebook.common.h.a.a(jVar.a());
            try {
                dVar = new d(a2);
                try {
                    dVar.m();
                    d().b(dVar, 1);
                    d.d(dVar);
                    com.facebook.common.h.a.c(a2);
                } catch (Throwable th2) {
                    th = th2;
                    d.d(dVar);
                    com.facebook.common.h.a.c(a2);
                    throw th;
                }
            } catch (Throwable th3) {
                dVar = null;
                th = th3;
                d.d(dVar);
                com.facebook.common.h.a.c(a2);
                throw th;
            }
        }
    }
}
