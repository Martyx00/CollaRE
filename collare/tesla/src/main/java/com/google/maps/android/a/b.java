package com.google.maps.android.a;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/* compiled from: Feature */
public class b extends Observable {

    /* renamed from: a  reason: collision with root package name */
    private final String f3980a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, String> f3981b;

    /* renamed from: c  reason: collision with root package name */
    private c f3982c;

    public b(c cVar, String str, Map<String, String> map) {
        this.f3982c = cVar;
        this.f3980a = str;
        if (map == null) {
            this.f3981b = new HashMap();
        } else {
            this.f3981b = map;
        }
    }

    public Iterable a() {
        return this.f3981b.entrySet();
    }

    public String a(String str) {
        return this.f3981b.get(str);
    }

    public String b() {
        return this.f3980a;
    }

    public boolean b(String str) {
        return this.f3981b.containsKey(str);
    }

    public c c() {
        return this.f3982c;
    }

    public boolean d() {
        return this.f3982c != null;
    }
}
