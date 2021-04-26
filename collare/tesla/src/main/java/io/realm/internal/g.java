package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.Date;

/* compiled from: InvalidRow */
public enum g implements p {
    INSTANCE;

    @Override // io.realm.internal.p
    public boolean d() {
        return false;
    }

    @Override // io.realm.internal.p
    public long a() {
        throw e();
    }

    @Override // io.realm.internal.p
    public String e(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public long a(String str) {
        throw e();
    }

    @Override // io.realm.internal.p
    public RealmFieldType f(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public Table b() {
        throw e();
    }

    @Override // io.realm.internal.p
    public long c() {
        throw e();
    }

    @Override // io.realm.internal.p
    public long g(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public boolean h(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public float i(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public double j(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public Date k(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public String l(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public byte[] m(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public boolean a(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public OsList d(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public OsList a(long j, RealmFieldType realmFieldType) {
        throw e();
    }

    @Override // io.realm.internal.p
    public void a(long j, String str) {
        throw e();
    }

    @Override // io.realm.internal.p
    public boolean b(long j) {
        throw e();
    }

    @Override // io.realm.internal.p
    public void c(long j) {
        throw e();
    }

    private RuntimeException e() {
        return new IllegalStateException("Object is no longer managed by Realm. Has it been deleted?");
    }
}
