package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f3818a;

    /* renamed from: b  reason: collision with root package name */
    private final int f3819b;

    /* renamed from: c  reason: collision with root package name */
    private final int f3820c;

    private e(Class<?> cls, int i, int i2) {
        this.f3818a = (Class) Preconditions.checkNotNull(cls, "Null dependency anInterface.");
        this.f3819b = i;
        this.f3820c = i2;
    }

    @KeepForSdk
    public static e a(Class<?> cls) {
        return new e(cls, 1, 0);
    }

    public final Class<?> a() {
        return this.f3818a;
    }

    public final boolean b() {
        return this.f3819b == 1;
    }

    public final boolean c() {
        return this.f3820c == 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (this.f3818a == eVar.f3818a && this.f3819b == eVar.f3819b && this.f3820c == eVar.f3820c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f3818a.hashCode() ^ 1000003) * 1000003) ^ this.f3819b) * 1000003) ^ this.f3820c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.f3818a);
        sb.append(", required=");
        boolean z = false;
        sb.append(this.f3819b == 1);
        sb.append(", direct=");
        if (this.f3820c == 0) {
            z = true;
        }
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
