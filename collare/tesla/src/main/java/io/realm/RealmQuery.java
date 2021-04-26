package io.realm;

import io.realm.internal.OsList;
import io.realm.internal.OsResults;
import io.realm.internal.Table;
import io.realm.internal.TableQuery;
import io.realm.internal.a.c;
import io.realm.internal.core.DescriptorOrdering;
import io.realm.internal.r;
import io.realm.internal.sync.a;

public class RealmQuery<E> {

    /* renamed from: a  reason: collision with root package name */
    private final Table f6105a;

    /* renamed from: b  reason: collision with root package name */
    private final a f6106b;

    /* renamed from: c  reason: collision with root package name */
    private final TableQuery f6107c;

    /* renamed from: d  reason: collision with root package name */
    private final x f6108d;
    private Class<E> e;
    private String f;
    private final boolean g;
    private final OsList h;
    private DescriptorOrdering i = new DescriptorOrdering();

    private static native String nativeSerializeQuery(long j, long j2);

    private static native long nativeSubscribe(long j, String str, long j2, long j3, long j4, boolean z);

    static <E extends u> RealmQuery<E> a(o oVar, Class<E> cls) {
        return new RealmQuery<>(oVar, cls);
    }

    static <E> RealmQuery<E> a(y<E> yVar) {
        if (yVar.f6333b == null) {
            return new RealmQuery<>(yVar, yVar.f6334c);
        }
        return new RealmQuery<>(yVar, yVar.f6333b);
    }

    private static boolean a(Class<?> cls) {
        return u.class.isAssignableFrom(cls);
    }

    private RealmQuery(o oVar, Class<E> cls) {
        this.f6106b = oVar;
        this.e = cls;
        this.g = !a((Class<?>) cls);
        if (this.g) {
            this.f6108d = null;
            this.f6105a = null;
            this.h = null;
            this.f6107c = null;
            return;
        }
        this.f6108d = oVar.h().b(cls);
        this.f6105a = this.f6108d.b();
        this.h = null;
        this.f6107c = this.f6105a.g();
    }

    private RealmQuery(y<E> yVar, Class<E> cls) {
        this.f6106b = yVar.f6332a;
        this.e = cls;
        this.g = !a((Class<?>) cls);
        if (this.g) {
            this.f6108d = null;
            this.f6105a = null;
            this.h = null;
            this.f6107c = null;
            return;
        }
        this.f6108d = this.f6106b.h().b(cls);
        this.f6105a = yVar.a();
        this.h = null;
        this.f6107c = yVar.b().d();
    }

    private RealmQuery(y<d> yVar, String str) {
        this.f6106b = yVar.f6332a;
        this.f = str;
        this.g = false;
        this.f6108d = this.f6106b.h().b(str);
        this.f6105a = this.f6108d.b();
        this.f6107c = yVar.b().d();
        this.h = null;
    }

    public RealmQuery<E> a(String str, String str2) {
        return a(str, str2, b.SENSITIVE);
    }

    public RealmQuery<E> a(String str, String str2, b bVar) {
        this.f6106b.d();
        return c(str, str2, bVar);
    }

    private RealmQuery<E> c(String str, String str2, b bVar) {
        c a2 = this.f6108d.a(str, RealmFieldType.STRING);
        this.f6107c.a(a2.a(), a2.b(), str2, bVar);
        return this;
    }

    public RealmQuery<E> a(String str, String[] strArr) {
        return a(str, strArr, b.SENSITIVE);
    }

    public RealmQuery<E> a(String str, String[] strArr, b bVar) {
        this.f6106b.d();
        if (strArr == null || strArr.length == 0) {
            c();
            return this;
        }
        d().c(str, strArr[0], bVar);
        for (int i2 = 1; i2 < strArr.length; i2++) {
            f().c(str, strArr[i2], bVar);
        }
        return e();
    }

    public RealmQuery<E> b(String str, String str2) {
        return b(str, str2, b.SENSITIVE);
    }

    public RealmQuery<E> b(String str, String str2, b bVar) {
        this.f6106b.d();
        c a2 = this.f6108d.a(str, RealmFieldType.STRING);
        this.f6107c.b(a2.a(), a2.b(), str2, bVar);
        return this;
    }

    private RealmQuery<E> d() {
        this.f6107c.c();
        return this;
    }

    private RealmQuery<E> e() {
        this.f6107c.d();
        return this;
    }

    private RealmQuery<E> f() {
        this.f6107c.e();
        return this;
    }

    public RealmQuery<E> a() {
        this.f6106b.d();
        this.f6107c.f();
        return this;
    }

    public y<E> b() {
        this.f6106b.d();
        return a(this.f6107c, this.i, true, a.f6321a);
    }

    public RealmQuery<E> c() {
        this.f6106b.d();
        this.f6107c.g();
        return this;
    }

    private boolean g() {
        return this.f != null;
    }

    private y<E> a(TableQuery tableQuery, DescriptorOrdering descriptorOrdering, boolean z, a aVar) {
        OsResults osResults;
        y<E> yVar;
        if (aVar.a()) {
            osResults = r.a(this.f6106b.e, tableQuery, descriptorOrdering, aVar);
        } else {
            osResults = OsResults.a(this.f6106b.e, tableQuery, descriptorOrdering);
        }
        if (g()) {
            yVar = new y<>(this.f6106b, osResults, this.f);
        } else {
            yVar = new y<>(this.f6106b, osResults, this.e);
        }
        if (z) {
            yVar.f();
        }
        return yVar;
    }
}
