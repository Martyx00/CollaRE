package com.facebook.react.a;

import android.view.View;

/* compiled from: Animation */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private final int f2497a;

    /* renamed from: b  reason: collision with root package name */
    private final c f2498b;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f2499c;

    /* renamed from: d  reason: collision with root package name */
    private volatile boolean f2500d;
    private b e;
    private View f;

    public abstract void a();

    public void a(b bVar) {
        this.e = bVar;
    }

    public final void a(View view) {
        this.f = view;
        this.f2498b.a(view);
        a();
    }

    public final void b() {
        if (!this.f2500d && !this.f2499c) {
            this.f2499c = true;
            b bVar = this.e;
            if (bVar != null) {
                bVar.a();
            }
        }
    }

    public int c() {
        return this.f2497a;
    }
}
