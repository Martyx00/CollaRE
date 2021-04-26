package com.teslamotors.plugins.client.b;

import io.realm.ad;
import io.realm.internal.n;
import io.realm.v;

/* compiled from: RealmItem */
public class c extends v implements ad {

    /* renamed from: a  reason: collision with root package name */
    private String f5507a;

    /* renamed from: b  reason: collision with root package name */
    private String f5508b;

    @Override // io.realm.ad
    public String c() {
        return this.f5507a;
    }

    @Override // io.realm.ad
    public void c(String str) {
        this.f5507a = str;
    }

    @Override // io.realm.ad
    public String d() {
        return this.f5508b;
    }

    @Override // io.realm.ad
    public void d(String str) {
        this.f5508b = str;
    }

    public c() {
        if (this instanceof n) {
            ((n) this).b_();
        }
    }

    public String a() {
        return c();
    }

    public c a(String str) {
        c(str);
        return this;
    }

    public String b() {
        return d();
    }

    public c b(String str) {
        d(str);
        return this;
    }
}
