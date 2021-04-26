package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

/* compiled from: AirMapCircle */
public class b extends c {

    /* renamed from: a  reason: collision with root package name */
    private CircleOptions f1550a;

    /* renamed from: b  reason: collision with root package name */
    private Circle f1551b;

    /* renamed from: c  reason: collision with root package name */
    private LatLng f1552c;

    /* renamed from: d  reason: collision with root package name */
    private double f1553d;
    private int e;
    private int f;
    private float g;
    private float h;

    public b(Context context) {
        super(context);
    }

    public void setCenter(LatLng latLng) {
        this.f1552c = latLng;
        Circle circle = this.f1551b;
        if (circle != null) {
            circle.setCenter(this.f1552c);
        }
    }

    public void setRadius(double d2) {
        this.f1553d = d2;
        Circle circle = this.f1551b;
        if (circle != null) {
            circle.setRadius(this.f1553d);
        }
    }

    public void setFillColor(int i) {
        this.f = i;
        Circle circle = this.f1551b;
        if (circle != null) {
            circle.setFillColor(i);
        }
    }

    public void setStrokeColor(int i) {
        this.e = i;
        Circle circle = this.f1551b;
        if (circle != null) {
            circle.setStrokeColor(i);
        }
    }

    public void setStrokeWidth(float f2) {
        this.g = f2;
        Circle circle = this.f1551b;
        if (circle != null) {
            circle.setStrokeWidth(f2);
        }
    }

    public void setZIndex(float f2) {
        this.h = f2;
        Circle circle = this.f1551b;
        if (circle != null) {
            circle.setZIndex(f2);
        }
    }

    public CircleOptions getCircleOptions() {
        if (this.f1550a == null) {
            this.f1550a = a();
        }
        return this.f1550a;
    }

    private CircleOptions a() {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(this.f1552c);
        circleOptions.radius(this.f1553d);
        circleOptions.fillColor(this.f);
        circleOptions.strokeColor(this.e);
        circleOptions.strokeWidth(this.g);
        circleOptions.zIndex(this.h);
        return circleOptions;
    }

    @Override // com.airbnb.android.react.maps.c
    public Object getFeature() {
        return this.f1551b;
    }

    public void a(GoogleMap googleMap) {
        this.f1551b = googleMap.addCircle(getCircleOptions());
    }

    @Override // com.airbnb.android.react.maps.c
    public void b(GoogleMap googleMap) {
        this.f1551b.remove();
    }
}
