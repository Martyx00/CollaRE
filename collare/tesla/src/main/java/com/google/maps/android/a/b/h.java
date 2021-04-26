package com.google.maps.android.a.b;

import com.google.maps.android.a.c;
import com.google.maps.android.a.f;
import java.util.ArrayList;

/* compiled from: KmlMultiGeometry */
public class h extends f {
    public h(ArrayList<c> arrayList) {
        super(arrayList);
    }

    /* renamed from: b */
    public ArrayList<c> d() {
        return new ArrayList<>(super.d());
    }

    @Override // com.google.maps.android.a.f
    public String toString() {
        return c() + "{" + "\n geometries=" + d() + "\n}\n";
    }
}
