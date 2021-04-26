package com.google.maps.android.a;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MultiGeometry */
public class f implements c {

    /* renamed from: a  reason: collision with root package name */
    private String f4010a = "MultiGeometry";

    /* renamed from: b  reason: collision with root package name */
    private List<c> f4011b;

    public f(List<? extends c> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (c cVar : list) {
                arrayList.add(cVar);
            }
            this.f4011b = arrayList;
            return;
        }
        throw new IllegalArgumentException("Geometries cannot be null");
    }

    @Override // com.google.maps.android.a.c
    public String c() {
        return this.f4010a;
    }

    /* renamed from: a */
    public List<c> d() {
        return this.f4011b;
    }

    public String toString() {
        String str = "Geometries=";
        if (this.f4010a.equals("MultiPoint")) {
            str = "LineStrings=";
        }
        if (this.f4010a.equals("MultiLineString")) {
            str = "points=";
        }
        if (this.f4010a.equals("MultiPolygon")) {
            str = "Polygons=";
        }
        StringBuilder sb = new StringBuilder(c());
        sb.append("{");
        sb.append("\n " + str);
        sb.append(d());
        sb.append("\n}\n");
        return sb.toString();
    }
}
