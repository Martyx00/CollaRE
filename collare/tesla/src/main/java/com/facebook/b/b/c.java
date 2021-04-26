package com.facebook.b.b;

import android.content.Context;
import com.facebook.b.a.g;
import com.facebook.b.a.h;
import com.facebook.common.a.b;
import com.facebook.common.d.i;
import com.facebook.common.d.l;
import java.io.File;

/* compiled from: DiskCacheConfig */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final int f1671a;

    /* renamed from: b  reason: collision with root package name */
    private final String f1672b;

    /* renamed from: c  reason: collision with root package name */
    private final l<File> f1673c;

    /* renamed from: d  reason: collision with root package name */
    private final long f1674d;
    private final long e;
    private final long f;
    private final h g;
    private final com.facebook.b.a.a h;
    private final com.facebook.b.a.c i;
    private final b j;
    private final Context k;
    private final boolean l;

    private c(a aVar) {
        com.facebook.b.a.a aVar2;
        com.facebook.b.a.c cVar;
        b bVar;
        this.f1671a = aVar.f1675a;
        this.f1672b = (String) i.a(aVar.f1676b);
        this.f1673c = (l) i.a(aVar.f1677c);
        this.f1674d = aVar.f1678d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = (h) i.a(aVar.g);
        if (aVar.h == null) {
            aVar2 = g.a();
        } else {
            aVar2 = aVar.h;
        }
        this.h = aVar2;
        if (aVar.i == null) {
            cVar = h.b();
        } else {
            cVar = aVar.i;
        }
        this.i = cVar;
        if (aVar.j == null) {
            bVar = com.facebook.common.a.c.a();
        } else {
            bVar = aVar.j;
        }
        this.j = bVar;
        this.k = aVar.l;
        this.l = aVar.k;
    }

    public int a() {
        return this.f1671a;
    }

    public String b() {
        return this.f1672b;
    }

    public l<File> c() {
        return this.f1673c;
    }

    public long d() {
        return this.f1674d;
    }

    public long e() {
        return this.e;
    }

    public long f() {
        return this.f;
    }

    public h g() {
        return this.g;
    }

    public com.facebook.b.a.a h() {
        return this.h;
    }

    public com.facebook.b.a.c i() {
        return this.i;
    }

    public b j() {
        return this.j;
    }

    public Context k() {
        return this.k;
    }

    public boolean l() {
        return this.l;
    }

    public static a a(Context context) {
        return new a(context);
    }

    /* compiled from: DiskCacheConfig */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private int f1675a;

        /* renamed from: b  reason: collision with root package name */
        private String f1676b;

        /* renamed from: c  reason: collision with root package name */
        private l<File> f1677c;

        /* renamed from: d  reason: collision with root package name */
        private long f1678d;
        private long e;
        private long f;
        private h g;
        private com.facebook.b.a.a h;
        private com.facebook.b.a.c i;
        private b j;
        private boolean k;
        private final Context l;

        private a(Context context) {
            this.f1675a = 1;
            this.f1676b = "image_cache";
            this.f1678d = 41943040;
            this.e = 10485760;
            this.f = 2097152;
            this.g = new b();
            this.l = context;
        }

        public c a() {
            i.b((this.f1677c == null && this.l == null) ? false : true, "Either a non-null context or a base directory path or supplier must be provided.");
            if (this.f1677c == null && this.l != null) {
                this.f1677c = new l<File>() {
                    /* class com.facebook.b.b.c.a.AnonymousClass1 */

                    /* renamed from: a */
                    public File b() {
                        return a.this.l.getApplicationContext().getCacheDir();
                    }
                };
            }
            return new c(this);
        }
    }
}
