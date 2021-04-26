package com.facebook.imagepipeline.e;

import com.facebook.common.k.b;

/* compiled from: RotationOptions */
public class f {

    /* renamed from: c  reason: collision with root package name */
    private static final f f2094c = new f(-1, false);

    /* renamed from: d  reason: collision with root package name */
    private static final f f2095d = new f(-2, false);
    private static final f e = new f(-1, true);

    /* renamed from: a  reason: collision with root package name */
    private final int f2096a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f2097b;

    public static f a() {
        return f2094c;
    }

    public static f b() {
        return f2095d;
    }

    public static f c() {
        return e;
    }

    private f(int i, boolean z) {
        this.f2096a = i;
        this.f2097b = z;
    }

    public boolean d() {
        return this.f2096a == -1;
    }

    public boolean e() {
        return this.f2096a != -2;
    }

    public int f() {
        if (!d()) {
            return this.f2096a;
        }
        throw new IllegalStateException("Rotation is set to use EXIF");
    }

    public boolean g() {
        return this.f2097b;
    }

    public int hashCode() {
        return b.a(Integer.valueOf(this.f2096a), Boolean.valueOf(this.f2097b));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.f2096a == fVar.f2096a && this.f2097b == fVar.f2097b) {
            return true;
        }
        return false;
    }

    public String toString() {
        return String.format(null, "%d defer:%b", Integer.valueOf(this.f2096a), Boolean.valueOf(this.f2097b));
    }
}
