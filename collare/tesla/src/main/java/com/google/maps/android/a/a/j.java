package com.google.maps.android.a.a;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.a.i;
import java.util.Arrays;

/* compiled from: GeoJsonPointStyle */
public class j extends i implements m {

    /* renamed from: d  reason: collision with root package name */
    private static final String[] f3977d = {"Point", "MultiPoint", "GeometryCollection"};

    @Override // com.google.maps.android.a.a.m
    public String[] b() {
        return f3977d;
    }

    public float c() {
        return this.f4018a.getAlpha();
    }

    public float d() {
        return this.f4018a.getAnchorU();
    }

    public float e() {
        return this.f4018a.getAnchorV();
    }

    public boolean f() {
        return this.f4018a.isDraggable();
    }

    public boolean g() {
        return this.f4018a.isFlat();
    }

    public float h() {
        return this.f4018a.getInfoWindowAnchorU();
    }

    public float i() {
        return this.f4018a.getInfoWindowAnchorV();
    }

    @Override // com.google.maps.android.a.i
    public float a() {
        return this.f4018a.getRotation();
    }

    public String j() {
        return this.f4018a.getSnippet();
    }

    public String k() {
        return this.f4018a.getTitle();
    }

    public boolean l() {
        return this.f4018a.isVisible();
    }

    public MarkerOptions m() {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.alpha(this.f4018a.getAlpha());
        markerOptions.anchor(this.f4018a.getAnchorU(), this.f4018a.getAnchorV());
        markerOptions.draggable(this.f4018a.isDraggable());
        markerOptions.flat(this.f4018a.isFlat());
        markerOptions.icon(this.f4018a.getIcon());
        markerOptions.infoWindowAnchor(this.f4018a.getInfoWindowAnchorU(), this.f4018a.getInfoWindowAnchorV());
        markerOptions.rotation(this.f4018a.getRotation());
        markerOptions.snippet(this.f4018a.getSnippet());
        markerOptions.title(this.f4018a.getTitle());
        markerOptions.visible(this.f4018a.isVisible());
        return markerOptions;
    }

    public String toString() {
        return "PointStyle{" + "\n geometry type=" + Arrays.toString(f3977d) + ",\n alpha=" + c() + ",\n anchor U=" + d() + ",\n anchor V=" + e() + ",\n draggable=" + f() + ",\n flat=" + g() + ",\n info window anchor U=" + h() + ",\n info window anchor V=" + i() + ",\n rotation=" + a() + ",\n snippet=" + j() + ",\n title=" + k() + ",\n visible=" + l() + "\n}\n";
    }
}
