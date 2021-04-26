package com.facebook.imagepipeline.memory;

import com.facebook.common.d.i;
import com.facebook.common.e.a;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: Bucket */
class e<V> {

    /* renamed from: a  reason: collision with root package name */
    public final int f2205a;

    /* renamed from: b  reason: collision with root package name */
    public final int f2206b;

    /* renamed from: c  reason: collision with root package name */
    final Queue f2207c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f2208d;
    private int e;

    public e(int i, int i2, int i3, boolean z) {
        boolean z2 = true;
        i.b(i > 0);
        i.b(i2 >= 0);
        i.b(i3 < 0 ? false : z2);
        this.f2205a = i;
        this.f2206b = i2;
        this.f2207c = new LinkedList();
        this.e = i3;
        this.f2208d = z;
    }

    public boolean a() {
        return this.e + b() > this.f2206b;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.f2207c.size();
    }

    public V c() {
        V d2 = d();
        if (d2 != null) {
            this.e++;
        }
        return d2;
    }

    public V d() {
        return (V) this.f2207c.poll();
    }

    public void e() {
        this.e++;
    }

    public void a(V v) {
        i.a(v);
        boolean z = false;
        if (this.f2208d) {
            if (this.e > 0) {
                z = true;
            }
            i.b(z);
            this.e--;
            b(v);
            return;
        }
        int i = this.e;
        if (i > 0) {
            this.e = i - 1;
            b(v);
            return;
        }
        a.c("BUCKET", "Tried to release value %s from an empty bucket!", v);
    }

    /* access modifiers changed from: package-private */
    public void b(V v) {
        this.f2207c.add(v);
    }

    public void f() {
        i.b(this.e > 0);
        this.e--;
    }
}
