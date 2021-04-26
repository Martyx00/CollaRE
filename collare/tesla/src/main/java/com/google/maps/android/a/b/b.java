package com.google.maps.android.a.b;

import com.google.android.gms.maps.model.GroundOverlay;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: KmlContainer */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<String, String> f3983a;

    /* renamed from: b  reason: collision with root package name */
    private final HashMap<j, Object> f3984b;

    /* renamed from: c  reason: collision with root package name */
    private final ArrayList<b> f3985c;

    /* renamed from: d  reason: collision with root package name */
    private final HashMap<e, GroundOverlay> f3986d;
    private final HashMap<String, String> e;
    private HashMap<String, n> f;
    private String g;

    b(HashMap<String, String> hashMap, HashMap<String, n> hashMap2, HashMap<j, Object> hashMap3, HashMap<String, String> hashMap4, ArrayList<b> arrayList, HashMap<e, GroundOverlay> hashMap5, String str) {
        this.f3983a = hashMap;
        this.f3984b = hashMap3;
        this.f = hashMap2;
        this.e = hashMap4;
        this.f3985c = arrayList;
        this.f3986d = hashMap5;
        this.g = str;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, n> a() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar, Object obj) {
        this.f3984b.put(jVar, obj);
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, String> b() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public HashMap<e, GroundOverlay> c() {
        return this.f3986d;
    }

    public n a(String str) {
        return this.f.get(str);
    }

    /* access modifiers changed from: package-private */
    public HashMap<j, Object> d() {
        return this.f3984b;
    }

    public String b(String str) {
        return this.f3983a.get(str);
    }

    public boolean c(String str) {
        return this.f3983a.containsKey(str);
    }

    public boolean e() {
        return this.f3985c.size() > 0;
    }

    public Iterable<b> f() {
        return this.f3985c;
    }

    public Iterable<j> g() {
        return this.f3984b.keySet();
    }

    public String toString() {
        return "Container" + "{" + "\n properties=" + this.f3983a + ",\n placemarks=" + this.f3984b + ",\n containers=" + this.f3985c + ",\n ground overlays=" + this.f3986d + ",\n style maps=" + this.e + ",\n styles=" + this.f + "\n}\n";
    }
}
