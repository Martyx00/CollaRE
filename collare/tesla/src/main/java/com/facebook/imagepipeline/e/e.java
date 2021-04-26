package com.facebook.imagepipeline.e;

import com.facebook.common.d.i;
import com.facebook.common.k.b;

/* compiled from: ResizeOptions */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public final int f2090a;

    /* renamed from: b  reason: collision with root package name */
    public final int f2091b;

    /* renamed from: c  reason: collision with root package name */
    public final float f2092c;

    /* renamed from: d  reason: collision with root package name */
    public final float f2093d;

    public e(int i, int i2) {
        this(i, i2, 2048.0f);
    }

    public e(int i, int i2, float f) {
        this(i, i2, f, 0.6666667f);
    }

    public e(int i, int i2, float f, float f2) {
        boolean z = true;
        i.a(i > 0);
        i.a(i2 <= 0 ? false : z);
        this.f2090a = i;
        this.f2091b = i2;
        this.f2092c = f;
        this.f2093d = f2;
    }

    public int hashCode() {
        return b.a(this.f2090a, this.f2091b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (this.f2090a == eVar.f2090a && this.f2091b == eVar.f2091b) {
            return true;
        }
        return false;
    }

    public String toString() {
        return String.format(null, "%dx%d", Integer.valueOf(this.f2090a), Integer.valueOf(this.f2091b));
    }
}
