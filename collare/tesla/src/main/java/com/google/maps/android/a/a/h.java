package com.google.maps.android.a.a;

import com.google.maps.android.a.c;
import com.google.maps.android.a.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: GeoJsonMultiPolygon */
public class h extends f {
    public List<k> b() {
        List<c> a2 = d();
        ArrayList arrayList = new ArrayList();
        Iterator<c> it = a2.iterator();
        while (it.hasNext()) {
            arrayList.add((k) it.next());
        }
        return arrayList;
    }
}
