package com.facebook.f.i;

import com.facebook.common.d.i;
import com.facebook.f.h.b;
import java.util.ArrayList;

/* compiled from: MultiDraweeHolder */
public class e<DH extends b> {

    /* renamed from: a  reason: collision with root package name */
    boolean f1979a = false;

    /* renamed from: b  reason: collision with root package name */
    ArrayList<b<DH>> f1980b = new ArrayList<>();

    public void a() {
        if (!this.f1979a) {
            this.f1979a = true;
            for (int i = 0; i < this.f1980b.size(); i++) {
                this.f1980b.get(i).b();
            }
        }
    }

    public void b() {
        if (this.f1979a) {
            this.f1979a = false;
            for (int i = 0; i < this.f1980b.size(); i++) {
                this.f1980b.get(i).c();
            }
        }
    }

    public void c() {
        if (this.f1979a) {
            for (int i = 0; i < this.f1980b.size(); i++) {
                this.f1980b.get(i).c();
            }
        }
        this.f1980b.clear();
    }

    public void a(b<DH> bVar) {
        a(this.f1980b.size(), bVar);
    }

    public void a(int i, b<DH> bVar) {
        i.a(bVar);
        i.a(i, this.f1980b.size() + 1);
        this.f1980b.add(i, bVar);
        if (this.f1979a) {
            bVar.b();
        }
    }
}
