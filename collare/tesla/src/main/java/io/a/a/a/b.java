package io.a.a.a;

import android.util.Log;

/* compiled from: DefaultLogger */
public class b implements l {

    /* renamed from: a  reason: collision with root package name */
    private int f6078a;

    public b(int i) {
        this.f6078a = i;
    }

    public b() {
        this.f6078a = 4;
    }

    @Override // io.a.a.a.l
    public boolean a(String str, int i) {
        return this.f6078a <= i;
    }

    @Override // io.a.a.a.l
    public void a(String str, String str2, Throwable th) {
        if (a(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    public void b(String str, String str2, Throwable th) {
        if (a(str, 2)) {
            Log.v(str, str2, th);
        }
    }

    public void c(String str, String str2, Throwable th) {
        if (a(str, 4)) {
            Log.i(str, str2, th);
        }
    }

    @Override // io.a.a.a.l
    public void d(String str, String str2, Throwable th) {
        if (a(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    @Override // io.a.a.a.l
    public void e(String str, String str2, Throwable th) {
        if (a(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    @Override // io.a.a.a.l
    public void a(String str, String str2) {
        a(str, str2, (Throwable) null);
    }

    @Override // io.a.a.a.l
    public void b(String str, String str2) {
        b(str, str2, null);
    }

    @Override // io.a.a.a.l
    public void c(String str, String str2) {
        c(str, str2, null);
    }

    @Override // io.a.a.a.l
    public void d(String str, String str2) {
        d(str, str2, null);
    }

    @Override // io.a.a.a.l
    public void e(String str, String str2) {
        e(str, str2, null);
    }

    @Override // io.a.a.a.l
    public void a(int i, String str, String str2) {
        a(i, str, str2, false);
    }

    @Override // io.a.a.a.l
    public void a(int i, String str, String str2, boolean z) {
        if (z || a(str, i)) {
            Log.println(i, str, str2);
        }
    }
}
