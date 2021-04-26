package com.google.maps.android.a;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.Observable;

/* compiled from: Style */
public abstract class i extends Observable {

    /* renamed from: a  reason: collision with root package name */
    protected MarkerOptions f4018a = new MarkerOptions();

    /* renamed from: b  reason: collision with root package name */
    protected PolylineOptions f4019b = new PolylineOptions();

    /* renamed from: c  reason: collision with root package name */
    protected PolygonOptions f4020c = new PolygonOptions();

    public float a() {
        return this.f4018a.getRotation();
    }

    public void a(float f) {
        this.f4018a.rotation(f);
    }

    public void a(float f, float f2, String str, String str2) {
        if (!str.equals("fraction")) {
            f = 0.5f;
        }
        if (!str2.equals("fraction")) {
            f2 = 1.0f;
        }
        this.f4018a.anchor(f, f2);
    }

    public void b(float f) {
        this.f4019b.width(f);
    }

    public void c(float f) {
        this.f4020c.strokeWidth(f);
    }

    public void a(int i) {
        this.f4020c.fillColor(i);
    }
}
