package com.facebook.b.b;

import com.facebook.b.a.b;
import com.facebook.b.a.c;
import com.facebook.b.a.d;
import java.io.IOException;

/* compiled from: SettableCacheEvent */
public class j implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f1697a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static j f1698b;

    /* renamed from: c  reason: collision with root package name */
    private static int f1699c;

    /* renamed from: d  reason: collision with root package name */
    private d f1700d;
    private String e;
    private long f;
    private long g;
    private long h;
    private IOException i;
    private c.a j;
    private j k;

    public static j a() {
        synchronized (f1697a) {
            if (f1698b == null) {
                return new j();
            }
            j jVar = f1698b;
            f1698b = jVar.k;
            jVar.k = null;
            f1699c--;
            return jVar;
        }
    }

    private j() {
    }

    public j a(d dVar) {
        this.f1700d = dVar;
        return this;
    }

    public j a(String str) {
        this.e = str;
        return this;
    }

    public j a(long j2) {
        this.f = j2;
        return this;
    }

    public j b(long j2) {
        this.h = j2;
        return this;
    }

    public j c(long j2) {
        this.g = j2;
        return this;
    }

    public j a(IOException iOException) {
        this.i = iOException;
        return this;
    }

    public j a(c.a aVar) {
        this.j = aVar;
        return this;
    }

    public void b() {
        synchronized (f1697a) {
            if (f1699c < 5) {
                c();
                f1699c++;
                if (f1698b != null) {
                    this.k = f1698b;
                }
                f1698b = this;
            }
        }
    }

    private void c() {
        this.f1700d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = null;
    }
}
