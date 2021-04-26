package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AirMapPolygon */
public class g extends c {

    /* renamed from: a  reason: collision with root package name */
    private PolygonOptions f1571a;

    /* renamed from: b  reason: collision with root package name */
    private Polygon f1572b;

    /* renamed from: c  reason: collision with root package name */
    private List<LatLng> f1573c;

    /* renamed from: d  reason: collision with root package name */
    private List<List<LatLng>> f1574d;
    private int e;
    private int f;
    private float g;
    private boolean h;
    private boolean i;
    private float j;

    public g(Context context) {
        super(context);
    }

    public void setCoordinates(ReadableArray readableArray) {
        this.f1573c = new ArrayList(readableArray.size());
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            ReadableMap map = readableArray.getMap(i2);
            this.f1573c.add(i2, new LatLng(map.getDouble("latitude"), map.getDouble("longitude")));
        }
        Polygon polygon = this.f1572b;
        if (polygon != null) {
            polygon.setPoints(this.f1573c);
        }
    }

    public void setHoles(ReadableArray readableArray) {
        if (readableArray != null) {
            this.f1574d = new ArrayList(readableArray.size());
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                ReadableArray array = readableArray.getArray(i2);
                if (array.size() >= 3) {
                    ArrayList arrayList = new ArrayList();
                    for (int i3 = 0; i3 < array.size(); i3++) {
                        ReadableMap map = array.getMap(i3);
                        arrayList.add(new LatLng(map.getDouble("latitude"), map.getDouble("longitude")));
                    }
                    if (arrayList.size() == 3) {
                        arrayList.add(arrayList.get(0));
                    }
                    this.f1574d.add(arrayList);
                }
            }
            Polygon polygon = this.f1572b;
            if (polygon != null) {
                polygon.setHoles(this.f1574d);
            }
        }
    }

    public void setFillColor(int i2) {
        this.f = i2;
        Polygon polygon = this.f1572b;
        if (polygon != null) {
            polygon.setFillColor(i2);
        }
    }

    public void setStrokeColor(int i2) {
        this.e = i2;
        Polygon polygon = this.f1572b;
        if (polygon != null) {
            polygon.setStrokeColor(i2);
        }
    }

    public void setStrokeWidth(float f2) {
        this.g = f2;
        Polygon polygon = this.f1572b;
        if (polygon != null) {
            polygon.setStrokeWidth(f2);
        }
    }

    public void setTappable(boolean z) {
        this.i = z;
        Polygon polygon = this.f1572b;
        if (polygon != null) {
            polygon.setClickable(this.i);
        }
    }

    public void setGeodesic(boolean z) {
        this.h = z;
        Polygon polygon = this.f1572b;
        if (polygon != null) {
            polygon.setGeodesic(z);
        }
    }

    public void setZIndex(float f2) {
        this.j = f2;
        Polygon polygon = this.f1572b;
        if (polygon != null) {
            polygon.setZIndex(f2);
        }
    }

    public PolygonOptions getPolygonOptions() {
        if (this.f1571a == null) {
            this.f1571a = a();
        }
        return this.f1571a;
    }

    private PolygonOptions a() {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(this.f1573c);
        polygonOptions.fillColor(this.f);
        polygonOptions.strokeColor(this.e);
        polygonOptions.strokeWidth(this.g);
        polygonOptions.geodesic(this.h);
        polygonOptions.zIndex(this.j);
        if (this.f1574d != null) {
            for (int i2 = 0; i2 < this.f1574d.size(); i2++) {
                polygonOptions.addHole(this.f1574d.get(i2));
            }
        }
        return polygonOptions;
    }

    @Override // com.airbnb.android.react.maps.c
    public Object getFeature() {
        return this.f1572b;
    }

    public void a(GoogleMap googleMap) {
        this.f1572b = googleMap.addPolygon(getPolygonOptions());
        this.f1572b.setClickable(this.i);
    }

    @Override // com.airbnb.android.react.maps.c
    public void b(GoogleMap googleMap) {
        this.f1572b.remove();
    }
}
