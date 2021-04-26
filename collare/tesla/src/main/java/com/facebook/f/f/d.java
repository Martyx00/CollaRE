package com.facebook.f.f;

import com.facebook.common.d.i;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;

/* compiled from: RoundingParams */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private a f1957a = a.BITMAP_ONLY;

    /* renamed from: b  reason: collision with root package name */
    private boolean f1958b = false;

    /* renamed from: c  reason: collision with root package name */
    private float[] f1959c = null;

    /* renamed from: d  reason: collision with root package name */
    private int f1960d = 0;
    private float e = BitmapDescriptorFactory.HUE_RED;
    private int f = 0;
    private float g = BitmapDescriptorFactory.HUE_RED;
    private boolean h = false;

    /* compiled from: RoundingParams */
    public enum a {
        OVERLAY_COLOR,
        BITMAP_ONLY
    }

    public boolean a() {
        return this.f1958b;
    }

    public d a(float f2) {
        Arrays.fill(i(), f2);
        return this;
    }

    public d a(float f2, float f3, float f4, float f5) {
        float[] i = i();
        i[1] = f2;
        i[0] = f2;
        i[3] = f3;
        i[2] = f3;
        i[5] = f4;
        i[4] = f4;
        i[7] = f5;
        i[6] = f5;
        return this;
    }

    public float[] b() {
        return this.f1959c;
    }

    public d a(a aVar) {
        this.f1957a = aVar;
        return this;
    }

    public a c() {
        return this.f1957a;
    }

    public d a(int i) {
        this.f1960d = i;
        this.f1957a = a.OVERLAY_COLOR;
        return this;
    }

    public int d() {
        return this.f1960d;
    }

    private float[] i() {
        if (this.f1959c == null) {
            this.f1959c = new float[8];
        }
        return this.f1959c;
    }

    public static d b(float f2) {
        return new d().a(f2);
    }

    public float e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public d a(int i, float f2) {
        i.a(f2 >= BitmapDescriptorFactory.HUE_RED, "the border width cannot be < 0");
        this.e = f2;
        this.f = i;
        return this;
    }

    public float g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.f1958b == dVar.f1958b && this.f1960d == dVar.f1960d && Float.compare(dVar.e, this.e) == 0 && this.f == dVar.f && Float.compare(dVar.g, this.g) == 0 && this.f1957a == dVar.f1957a && this.h == dVar.h) {
            return Arrays.equals(this.f1959c, dVar.f1959c);
        }
        return false;
    }

    public int hashCode() {
        a aVar = this.f1957a;
        int i = 0;
        int hashCode = (((aVar != null ? aVar.hashCode() : 0) * 31) + (this.f1958b ? 1 : 0)) * 31;
        float[] fArr = this.f1959c;
        int hashCode2 = (((hashCode + (fArr != null ? Arrays.hashCode(fArr) : 0)) * 31) + this.f1960d) * 31;
        float f2 = this.e;
        int floatToIntBits = (((hashCode2 + (f2 != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(f2) : 0)) * 31) + this.f) * 31;
        float f3 = this.g;
        if (f3 != BitmapDescriptorFactory.HUE_RED) {
            i = Float.floatToIntBits(f3);
        }
        return ((floatToIntBits + i) * 31) + (this.h ? 1 : 0);
    }
}
