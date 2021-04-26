package com.github.a.a.b;

import com.github.a.a.a;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SyntaxRules */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private a f3738a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, Map<String, a>> f3739b = new HashMap();

    public c(a aVar) {
        this.f3738a = aVar;
    }

    public a a() {
        return this.f3738a;
    }

    public void a(a aVar) {
        this.f3738a = aVar;
    }

    public boolean a(String str) {
        if (str != null) {
            str = str.toUpperCase();
        }
        return this.f3739b.containsKey(str);
    }

    public a a(String str, String str2) {
        Map<String, a> map = this.f3739b.get(str == null ? null : str.toUpperCase());
        if (map == null) {
            return null;
        }
        return map.get(str2);
    }

    public void a(String str, String str2, a aVar) {
        String upperCase = str == null ? null : str.toUpperCase();
        Map<String, a> map = this.f3739b.get(upperCase);
        if (map == null) {
            map = new HashMap<>();
            this.f3739b.put(upperCase, map);
        }
        map.put(str2, aVar);
    }

    public static c b() {
        c cVar = new c(a.OLD);
        cVar.a("VCARD", "2.1", a.OLD);
        cVar.a("VCARD", "3.0", a.NEW);
        cVar.a("VCARD", "4.0", a.NEW);
        return cVar;
    }
}
