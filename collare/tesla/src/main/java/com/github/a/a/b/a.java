package com.github.a.a.b;

/* compiled from: Buffer */
class a {

    /* renamed from: a  reason: collision with root package name */
    private final StringBuilder f3733a = new StringBuilder(1024);

    a() {
    }

    public a a() {
        this.f3733a.setLength(0);
        return this;
    }

    public String b() {
        return this.f3733a.toString();
    }

    public String c() {
        String b2 = b();
        a();
        return b2;
    }

    public a a(char c2) {
        this.f3733a.append(c2);
        return this;
    }

    public a a(CharSequence charSequence) {
        this.f3733a.append(charSequence);
        return this;
    }

    public a d() {
        if (e() > 0) {
            StringBuilder sb = this.f3733a;
            sb.setLength(sb.length() - 1);
        }
        return this;
    }

    public int e() {
        return this.f3733a.length();
    }
}
