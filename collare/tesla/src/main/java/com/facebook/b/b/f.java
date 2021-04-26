package com.facebook.b.b;

import com.facebook.b.a.a;
import com.facebook.b.b.d;
import com.facebook.common.c.c;
import com.facebook.common.d.i;
import com.facebook.common.d.l;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

/* compiled from: DynamicDefaultDiskStorage */
public class f implements d {

    /* renamed from: b  reason: collision with root package name */
    private static final Class<?> f1691b = f.class;

    /* renamed from: a  reason: collision with root package name */
    volatile a f1692a = new a(null, null);

    /* renamed from: c  reason: collision with root package name */
    private final int f1693c;

    /* renamed from: d  reason: collision with root package name */
    private final l<File> f1694d;
    private final String e;
    private final com.facebook.b.a.a f;

    /* access modifiers changed from: package-private */
    /* compiled from: DynamicDefaultDiskStorage */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final d f1695a;

        /* renamed from: b  reason: collision with root package name */
        public final File f1696b;

        a(File file, d dVar) {
            this.f1695a = dVar;
            this.f1696b = file;
        }
    }

    public f(int i, l<File> lVar, String str, com.facebook.b.a.a aVar) {
        this.f1693c = i;
        this.f = aVar;
        this.f1694d = lVar;
        this.e = str;
    }

    @Override // com.facebook.b.b.d
    public boolean a() {
        try {
            return d().a();
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // com.facebook.b.b.d
    public com.facebook.a.a b(String str, Object obj) {
        return d().b(str, obj);
    }

    @Override // com.facebook.b.b.d
    public boolean c(String str, Object obj) {
        return d().c(str, obj);
    }

    @Override // com.facebook.b.b.d
    public void b() {
        try {
            d().b();
        } catch (IOException e2) {
            com.facebook.common.e.a.b(f1691b, "purgeUnexpectedResources", (Throwable) e2);
        }
    }

    @Override // com.facebook.b.b.d
    public d.b a(String str, Object obj) {
        return d().a(str, obj);
    }

    @Override // com.facebook.b.b.d
    public Collection<d.a> e() {
        return d().e();
    }

    @Override // com.facebook.b.b.d
    public long a(d.a aVar) {
        return d().a(aVar);
    }

    @Override // com.facebook.b.b.d
    public long b(String str) {
        return d().b(str);
    }

    @Override // com.facebook.b.b.d
    public void c() {
        d().c();
    }

    /* access modifiers changed from: package-private */
    public synchronized d d() {
        if (g()) {
            f();
            h();
        }
        return (d) i.a(this.f1692a.f1695a);
    }

    private boolean g() {
        a aVar = this.f1692a;
        return aVar.f1695a == null || aVar.f1696b == null || !aVar.f1696b.exists();
    }

    /* access modifiers changed from: package-private */
    public void f() {
        if (this.f1692a.f1695a != null && this.f1692a.f1696b != null) {
            com.facebook.common.c.a.b(this.f1692a.f1696b);
        }
    }

    private void h() {
        File file = new File(this.f1694d.b(), this.e);
        a(file);
        this.f1692a = new a(file, new a(file, this.f1693c, this.f));
    }

    /* access modifiers changed from: package-private */
    public void a(File file) {
        try {
            c.a(file);
            com.facebook.common.e.a.b(f1691b, "Created cache directory %s", file.getAbsolutePath());
        } catch (c.a e2) {
            this.f.a(a.EnumC0036a.WRITE_CREATE_DIR, f1691b, "createRootDirectoryIfNecessary", e2);
            throw e2;
        }
    }
}
