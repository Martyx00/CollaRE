package io.realm.internal;

import io.realm.RealmFieldType;
import io.realm.q;
import java.lang.ref.WeakReference;
import java.util.Date;

/* compiled from: PendingRow */
public class l implements p {

    /* renamed from: a  reason: collision with root package name */
    private OsSharedRealm f6291a;

    /* renamed from: b  reason: collision with root package name */
    private OsResults f6292b;

    /* renamed from: c  reason: collision with root package name */
    private q<l> f6293c;

    /* renamed from: d  reason: collision with root package name */
    private WeakReference<a> f6294d;
    private boolean e;

    /* compiled from: PendingRow */
    public interface a {
        void b(p pVar);
    }

    @Override // io.realm.internal.p
    public boolean d() {
        return false;
    }

    @Override // io.realm.internal.p
    public long a() {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public String e(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public long a(String str) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public RealmFieldType f(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public Table b() {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public long c() {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public long g(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public boolean h(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public float i(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public double j(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public Date k(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public String l(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public byte[] m(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public boolean a(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public OsList d(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public OsList a(long j, RealmFieldType realmFieldType) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public void a(long j, String str) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public boolean b(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    @Override // io.realm.internal.p
    public void c(long j) {
        throw new IllegalStateException("The pending query has not been executed.");
    }

    private void f() {
        this.f6292b.a(this, this.f6293c);
        this.f6292b = null;
        this.f6293c = null;
        this.f6291a.removePendingRow(this);
    }

    private void g() {
        WeakReference<a> weakReference = this.f6294d;
        if (weakReference != null) {
            a aVar = weakReference.get();
            if (aVar == null) {
                f();
            } else if (this.f6292b.g()) {
                UncheckedRow b2 = this.f6292b.b();
                f();
                if (b2 != null) {
                    if (this.e) {
                        b2 = CheckedRow.a(b2);
                    }
                    aVar.b(b2);
                    return;
                }
                aVar.b(g.INSTANCE);
            } else {
                f();
            }
        } else {
            throw new IllegalStateException("The 'frontEnd' has not been set.");
        }
    }

    public void e() {
        if (this.f6292b != null) {
            g();
            return;
        }
        throw new IllegalStateException("The query has been executed. This 'PendingRow' is not valid anymore.");
    }
}
