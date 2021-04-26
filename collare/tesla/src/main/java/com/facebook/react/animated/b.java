package com.facebook.react.animated;

import com.facebook.i.a.a;
import java.util.ArrayList;
import java.util.List;

/* access modifiers changed from: package-private */
/* compiled from: AnimatedNode */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    List<b> f2558a;

    /* renamed from: b  reason: collision with root package name */
    int f2559b = 0;

    /* renamed from: c  reason: collision with root package name */
    int f2560c = 0;

    /* renamed from: d  reason: collision with root package name */
    int f2561d = -1;

    public void a() {
    }

    public void c(b bVar) {
    }

    public void d(b bVar) {
    }

    b() {
    }

    public final void a(b bVar) {
        if (this.f2558a == null) {
            this.f2558a = new ArrayList(1);
        }
        ((List) a.a(this.f2558a)).add(bVar);
        bVar.c(this);
    }

    public final void b(b bVar) {
        if (this.f2558a != null) {
            bVar.d(this);
            this.f2558a.remove(bVar);
        }
    }
}
