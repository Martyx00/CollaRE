package com.google.maps.android.a.a;

import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.a.i;
import java.util.Arrays;

/* compiled from: GeoJsonLineStringStyle */
public class e extends i implements m {

    /* renamed from: d  reason: collision with root package name */
    private static final String[] f3976d = {"LineString", "MultiLineString", "GeometryCollection"};

    @Override // com.google.maps.android.a.a.m
    public String[] b() {
        return f3976d;
    }

    public int c() {
        return this.f4019b.getColor();
    }

    public boolean d() {
        return this.f4019b.isClickable();
    }

    public boolean e() {
        return this.f4019b.isGeodesic();
    }

    public float f() {
        return this.f4019b.getWidth();
    }

    public float g() {
        return this.f4019b.getZIndex();
    }

    public boolean h() {
        return this.f4019b.isVisible();
    }

    public PolylineOptions i() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.f4019b.getColor());
        polylineOptions.clickable(this.f4019b.isClickable());
        polylineOptions.geodesic(this.f4019b.isGeodesic());
        polylineOptions.visible(this.f4019b.isVisible());
        polylineOptions.width(this.f4019b.getWidth());
        polylineOptions.zIndex(this.f4019b.getZIndex());
        return polylineOptions;
    }

    public String toString() {
        return "LineStringStyle{" + "\n geometry type=" + Arrays.toString(f3976d) + ",\n color=" + c() + ",\n clickable=" + d() + ",\n geodesic=" + e() + ",\n visible=" + h() + ",\n width=" + f() + ",\n z index=" + g() + "\n}\n";
    }
}
