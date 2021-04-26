package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AirMapPolyline */
public class h extends c {

    /* renamed from: a  reason: collision with root package name */
    private PolylineOptions f1575a;

    /* renamed from: b  reason: collision with root package name */
    private Polyline f1576b;

    /* renamed from: c  reason: collision with root package name */
    private List<LatLng> f1577c;

    /* renamed from: d  reason: collision with root package name */
    private int f1578d;
    private float e;
    private boolean f;
    private boolean g;
    private float h;
    private Cap i = new RoundCap();
    private ReadableArray j;
    private List<PatternItem> k;

    public h(Context context) {
        super(context);
    }

    public void setCoordinates(ReadableArray readableArray) {
        this.f1577c = new ArrayList(readableArray.size());
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            ReadableMap map = readableArray.getMap(i2);
            this.f1577c.add(i2, new LatLng(map.getDouble("latitude"), map.getDouble("longitude")));
        }
        Polyline polyline = this.f1576b;
        if (polyline != null) {
            polyline.setPoints(this.f1577c);
        }
    }

    public void setColor(int i2) {
        this.f1578d = i2;
        Polyline polyline = this.f1576b;
        if (polyline != null) {
            polyline.setColor(i2);
        }
    }

    public void setWidth(float f2) {
        this.e = f2;
        Polyline polyline = this.f1576b;
        if (polyline != null) {
            polyline.setWidth(f2);
        }
    }

    public void setZIndex(float f2) {
        this.h = f2;
        Polyline polyline = this.f1576b;
        if (polyline != null) {
            polyline.setZIndex(f2);
        }
    }

    public void setTappable(boolean z) {
        this.f = z;
        Polyline polyline = this.f1576b;
        if (polyline != null) {
            polyline.setClickable(this.f);
        }
    }

    public void setGeodesic(boolean z) {
        this.g = z;
        Polyline polyline = this.f1576b;
        if (polyline != null) {
            polyline.setGeodesic(z);
        }
    }

    public void setLineCap(Cap cap) {
        this.i = cap;
        Polyline polyline = this.f1576b;
        if (polyline != null) {
            polyline.setStartCap(cap);
            this.f1576b.setEndCap(cap);
        }
        a();
    }

    public void setLineDashPattern(ReadableArray readableArray) {
        this.j = readableArray;
        a();
    }

    private void a() {
        PatternItem patternItem;
        ReadableArray readableArray = this.j;
        if (readableArray != null) {
            this.k = new ArrayList(readableArray.size());
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                float f2 = (float) this.j.getDouble(i2);
                if (i2 % 2 != 0) {
                    this.k.add(new Gap(f2));
                } else {
                    if (this.i instanceof RoundCap) {
                        patternItem = new Dot();
                    } else {
                        patternItem = new Dash(f2);
                    }
                    this.k.add(patternItem);
                }
            }
            Polyline polyline = this.f1576b;
            if (polyline != null) {
                polyline.setPattern(this.k);
            }
        }
    }

    public PolylineOptions getPolylineOptions() {
        if (this.f1575a == null) {
            this.f1575a = b();
        }
        return this.f1575a;
    }

    private PolylineOptions b() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(this.f1577c);
        polylineOptions.color(this.f1578d);
        polylineOptions.width(this.e);
        polylineOptions.geodesic(this.g);
        polylineOptions.zIndex(this.h);
        polylineOptions.startCap(this.i);
        polylineOptions.endCap(this.i);
        polylineOptions.pattern(this.k);
        return polylineOptions;
    }

    @Override // com.airbnb.android.react.maps.c
    public Object getFeature() {
        return this.f1576b;
    }

    public void a(GoogleMap googleMap) {
        this.f1576b = googleMap.addPolyline(getPolylineOptions());
        this.f1576b.setClickable(this.f);
    }

    @Override // com.airbnb.android.react.maps.c
    public void b(GoogleMap googleMap) {
        this.f1576b.remove();
    }
}
