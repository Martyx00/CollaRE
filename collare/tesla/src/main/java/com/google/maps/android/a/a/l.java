package com.google.maps.android.a.a;

import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.a.i;
import java.util.Arrays;

/* compiled from: GeoJsonPolygonStyle */
public class l extends i implements m {

    /* renamed from: d  reason: collision with root package name */
    private static final String[] f3979d = {"Polygon", "MultiPolygon", "GeometryCollection"};

    @Override // com.google.maps.android.a.a.m
    public String[] b() {
        return f3979d;
    }

    public int c() {
        return this.f4020c.getFillColor();
    }

    public boolean d() {
        return this.f4020c.isGeodesic();
    }

    public int e() {
        return this.f4020c.getStrokeColor();
    }

    public float f() {
        return this.f4020c.getStrokeWidth();
    }

    public float g() {
        return this.f4020c.getZIndex();
    }

    public boolean h() {
        return this.f4020c.isVisible();
    }

    public PolygonOptions i() {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.fillColor(this.f4020c.getFillColor());
        polygonOptions.geodesic(this.f4020c.isGeodesic());
        polygonOptions.strokeColor(this.f4020c.getStrokeColor());
        polygonOptions.strokeWidth(this.f4020c.getStrokeWidth());
        polygonOptions.visible(this.f4020c.isVisible());
        polygonOptions.zIndex(this.f4020c.getZIndex());
        return polygonOptions;
    }

    public String toString() {
        return "PolygonStyle{" + "\n geometry type=" + Arrays.toString(f3979d) + ",\n fill color=" + c() + ",\n geodesic=" + d() + ",\n stroke color=" + e() + ",\n stroke width=" + f() + ",\n visible=" + h() + ",\n z index=" + g() + "\n}\n";
    }
}
