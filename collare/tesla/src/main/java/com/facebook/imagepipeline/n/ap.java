package com.facebook.imagepipeline.n;

import com.facebook.common.d.e;
import com.facebook.common.d.f;
import com.facebook.common.d.i;
import com.facebook.common.g.h;
import com.facebook.g.c;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.n.v;
import com.facebook.imagepipeline.o.b;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: ResizeAndRotateProducer */
public class ap implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private static final e<Integer> f2307a = e.a(2, 7, 4, 5);

    /* renamed from: b  reason: collision with root package name */
    private final Executor f2308b;

    /* renamed from: c  reason: collision with root package name */
    private final h f2309c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f2310d;
    private final ak<d> e;
    private final boolean f;

    static int a(float f2, float f3) {
        return (int) (f3 + (f2 * 8.0f));
    }

    private static boolean b(int i) {
        return i < 8;
    }

    public ap(Executor executor, h hVar, boolean z, ak<d> akVar, boolean z2) {
        this.f2308b = (Executor) i.a(executor);
        this.f2309c = (h) i.a(hVar);
        this.f2310d = z;
        this.e = (ak) i.a(akVar);
        this.f = z2;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        this.e.a(new a(kVar, alVar), alVar);
    }

    /* compiled from: ResizeAndRotateProducer */
    private class a extends n<d, d> {

        /* renamed from: b  reason: collision with root package name */
        private final al f2312b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f2313c = false;

        /* renamed from: d  reason: collision with root package name */
        private final v f2314d;

        public a(final k<d> kVar, al alVar) {
            super(kVar);
            this.f2312b = alVar;
            this.f2314d = new v(ap.this.f2308b, new v.a(ap.this) {
                /* class com.facebook.imagepipeline.n.ap.a.AnonymousClass1 */

                @Override // com.facebook.imagepipeline.n.v.a
                public void a(d dVar, int i) {
                    a.this.b((a) dVar, (d) i);
                }
            }, 100);
            this.f2312b.a(new e(ap.this) {
                /* class com.facebook.imagepipeline.n.ap.a.AnonymousClass2 */

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void c() {
                    if (a.this.f2312b.h()) {
                        a.this.f2314d.b();
                    }
                }

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void a() {
                    a.this.f2314d.a();
                    a.this.f2313c = true;
                    kVar.b();
                }
            });
        }

        /* access modifiers changed from: protected */
        public void a(d dVar, int i) {
            if (!this.f2313c) {
                boolean a2 = a(i);
                if (dVar != null) {
                    com.facebook.common.k.e c2 = ap.c(this.f2312b.a(), dVar, ap.this.f2310d);
                    if (!a2 && c2 == com.facebook.common.k.e.UNSET) {
                        return;
                    }
                    if (c2 != com.facebook.common.k.e.YES) {
                        if (!(this.f2312b.a().g().g() || dVar.f() == 0 || dVar.f() == -1)) {
                            dVar = a(dVar);
                            dVar.c(0);
                        }
                        d().b(dVar, i);
                    } else if (this.f2314d.a(dVar, i)) {
                        if (a2 || this.f2312b.h()) {
                            this.f2314d.b();
                        }
                    }
                } else if (a2) {
                    d().b(null, 1);
                }
            }
        }

        private d a(d dVar) {
            d a2 = d.a(dVar);
            dVar.close();
            return a2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x00ff A[Catch:{ all -> 0x010d }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void b(com.facebook.imagepipeline.j.d r17, int r18) {
            /*
            // Method dump skipped, instructions count: 277
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.ap.a.b(com.facebook.imagepipeline.j.d, int):void");
        }

        private Map<String, String> a(d dVar, b bVar, int i, int i2, int i3, int i4) {
            String str;
            String str2;
            if (!this.f2312b.c().b(this.f2312b.b())) {
                return null;
            }
            String str3 = dVar.h() + "x" + dVar.i();
            if (bVar.f() != null) {
                str = bVar.f().f2090a + "x" + bVar.f().f2091b;
            } else {
                str = "Unspecified";
            }
            if (i > 0) {
                str2 = i + "/8";
            } else {
                str2 = "";
            }
            HashMap hashMap = new HashMap();
            hashMap.put("Original size", str3);
            hashMap.put("Requested size", str);
            hashMap.put("Fraction", str2);
            hashMap.put("queueTime", String.valueOf(this.f2314d.c()));
            hashMap.put("downsampleEnumerator", Integer.toString(i2));
            hashMap.put("softwareEnumerator", Integer.toString(i3));
            hashMap.put("rotationAngle", Integer.toString(i4));
            return f.a(hashMap);
        }
    }

    /* access modifiers changed from: private */
    public static com.facebook.common.k.e c(b bVar, d dVar, boolean z) {
        if (dVar == null || dVar.e() == c.f1991a) {
            return com.facebook.common.k.e.UNSET;
        }
        if (dVar.e() != com.facebook.g.b.f1987a) {
            return com.facebook.common.k.e.NO;
        }
        return com.facebook.common.k.e.a(e(bVar.g(), dVar) || b(d(bVar, dVar, z)));
    }

    static float a(com.facebook.imagepipeline.e.e eVar, int i, int i2) {
        if (eVar == null) {
            return 1.0f;
        }
        float f2 = (float) i;
        float f3 = (float) i2;
        float max = Math.max(((float) eVar.f2090a) / f2, ((float) eVar.f2091b) / f3);
        if (f2 * max > eVar.f2092c) {
            max = eVar.f2092c / f2;
        }
        return f3 * max > eVar.f2092c ? eVar.f2092c / f3 : max;
    }

    /* access modifiers changed from: private */
    public static int d(b bVar, d dVar, boolean z) {
        com.facebook.imagepipeline.e.e f2;
        int i;
        int i2;
        if (!z || (f2 = bVar.f()) == null) {
            return 8;
        }
        int c2 = c(bVar.g(), dVar);
        boolean z2 = false;
        int d2 = f2307a.contains(Integer.valueOf(dVar.g())) ? d(bVar.g(), dVar) : 0;
        if (c2 == 90 || c2 == 270 || d2 == 5 || d2 == 7) {
            z2 = true;
        }
        if (z2) {
            i = dVar.i();
        } else {
            i = dVar.h();
        }
        if (z2) {
            i2 = dVar.h();
        } else {
            i2 = dVar.i();
        }
        int a2 = a(a(f2, i, i2), f2.f2093d);
        if (a2 > 8) {
            return 8;
        }
        if (a2 < 1) {
            return 1;
        }
        return a2;
    }

    /* access modifiers changed from: private */
    public static int c(com.facebook.imagepipeline.e.f fVar, d dVar) {
        if (!fVar.e()) {
            return 0;
        }
        int a2 = a(dVar);
        if (fVar.d()) {
            return a2;
        }
        return (a2 + fVar.f()) % 360;
    }

    /* access modifiers changed from: private */
    public static int d(com.facebook.imagepipeline.e.f fVar, d dVar) {
        int indexOf = f2307a.indexOf(Integer.valueOf(dVar.g()));
        if (indexOf >= 0) {
            int i = 0;
            if (!fVar.d()) {
                i = fVar.f();
            }
            e<Integer> eVar = f2307a;
            return eVar.get((indexOf + (i / 90)) % eVar.size()).intValue();
        }
        throw new IllegalArgumentException("Only accepts inverted exif orientations");
    }

    private static int a(d dVar) {
        int f2 = dVar.f();
        if (f2 == 90 || f2 == 180 || f2 == 270) {
            return dVar.f();
        }
        return 0;
    }

    private static boolean e(com.facebook.imagepipeline.e.f fVar, d dVar) {
        return !fVar.g() && (c(fVar, dVar) != 0 || f(fVar, dVar));
    }

    private static boolean f(com.facebook.imagepipeline.e.f fVar, d dVar) {
        if (fVar.e() && !fVar.g()) {
            return f2307a.contains(Integer.valueOf(dVar.g()));
        }
        dVar.d(0);
        return false;
    }

    static int a(int i) {
        return Math.max(1, 8 / i);
    }
}
