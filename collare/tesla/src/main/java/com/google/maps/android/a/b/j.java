package com.google.maps.android.a.b;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.a.b;
import com.google.maps.android.a.c;
import java.util.HashMap;

/* compiled from: KmlPlacemark */
public class j extends b {

    /* renamed from: a  reason: collision with root package name */
    private final String f3995a;

    /* renamed from: b  reason: collision with root package name */
    private final n f3996b;

    public j(c cVar, String str, n nVar, HashMap<String, String> hashMap) {
        super(cVar, str, hashMap);
        this.f3995a = str;
        this.f3996b = nVar;
    }

    public String e() {
        return super.b();
    }

    public n f() {
        return this.f3996b;
    }

    public PolygonOptions g() {
        return this.f3996b.n();
    }

    public MarkerOptions h() {
        return this.f3996b.l();
    }

    public PolylineOptions i() {
        return this.f3996b.m();
    }

    public String toString() {
        return "Placemark" + "{" + "\n style id=" + this.f3995a + ",\n inline style=" + this.f3996b + "\n}\n";
    }
}
