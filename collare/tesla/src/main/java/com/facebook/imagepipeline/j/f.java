package com.facebook.imagepipeline.j;

import com.google.android.gms.common.api.Api;

/* compiled from: ImmutableQualityInfo */
public class f implements g {

    /* renamed from: a  reason: collision with root package name */
    public static final g f2171a = a(Api.BaseClientBuilder.API_PRIORITY_OTHER, true, true);

    /* renamed from: b  reason: collision with root package name */
    int f2172b;

    /* renamed from: c  reason: collision with root package name */
    boolean f2173c;

    /* renamed from: d  reason: collision with root package name */
    boolean f2174d;

    private f(int i, boolean z, boolean z2) {
        this.f2172b = i;
        this.f2173c = z;
        this.f2174d = z2;
    }

    @Override // com.facebook.imagepipeline.j.g
    public int a() {
        return this.f2172b;
    }

    @Override // com.facebook.imagepipeline.j.g
    public boolean b() {
        return this.f2173c;
    }

    @Override // com.facebook.imagepipeline.j.g
    public boolean c() {
        return this.f2174d;
    }

    public int hashCode() {
        int i = 0;
        int i2 = this.f2172b ^ (this.f2173c ? 4194304 : 0);
        if (this.f2174d) {
            i = 8388608;
        }
        return i2 ^ i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.f2172b == fVar.f2172b && this.f2173c == fVar.f2173c && this.f2174d == fVar.f2174d) {
            return true;
        }
        return false;
    }

    public static g a(int i, boolean z, boolean z2) {
        return new f(i, z, z2);
    }
}
