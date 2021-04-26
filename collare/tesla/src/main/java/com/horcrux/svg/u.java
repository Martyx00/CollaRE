package com.horcrux.svg;

/* access modifiers changed from: package-private */
/* compiled from: PaintCompat */
public class u<F, S> {

    /* renamed from: a  reason: collision with root package name */
    final F f4666a;

    /* renamed from: b  reason: collision with root package name */
    final S f4667b;

    u(F f, S s) {
        this.f4666a = f;
        this.f4667b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        if (!a(uVar.f4666a, this.f4666a) || !a(uVar.f4667b, this.f4667b)) {
            return false;
        }
        return true;
    }

    private static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public int hashCode() {
        F f = this.f4666a;
        int i = 0;
        int hashCode = f == null ? 0 : f.hashCode();
        S s = this.f4667b;
        if (s != null) {
            i = s.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f4666a) + " " + String.valueOf(this.f4667b) + "}";
    }
}
