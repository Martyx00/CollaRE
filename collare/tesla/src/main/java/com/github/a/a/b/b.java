package com.github.a.a.b;

import java.util.Collections;
import java.util.List;

/* compiled from: Context */
public class b {

    /* renamed from: a  reason: collision with root package name */
    final List<String> f3734a;

    /* renamed from: b  reason: collision with root package name */
    final a f3735b = new a();

    /* renamed from: c  reason: collision with root package name */
    int f3736c = 1;

    /* renamed from: d  reason: collision with root package name */
    boolean f3737d = false;

    b(List<String> list) {
        this.f3734a = Collections.unmodifiableList(list);
    }

    public List<String> a() {
        return this.f3734a;
    }

    public String b() {
        return this.f3735b.b();
    }

    public int c() {
        return this.f3736c;
    }

    public void d() {
        this.f3737d = true;
    }

    public String toString() {
        return "Context [parentComponents=" + this.f3734a + ", unfoldedLine=" + this.f3735b.b() + ", lineNumber=" + this.f3736c + ", stop=" + this.f3737d + "]";
    }
}
