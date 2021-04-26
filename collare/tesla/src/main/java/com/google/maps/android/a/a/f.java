package com.google.maps.android.a.a;

import com.google.maps.android.a.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: GeoJsonMultiLineString */
public class f extends com.google.maps.android.a.f {
    public List<d> b() {
        List<c> a2 = d();
        ArrayList arrayList = new ArrayList();
        Iterator<c> it = a2.iterator();
        while (it.hasNext()) {
            arrayList.add((d) it.next());
        }
        return arrayList;
    }
}
