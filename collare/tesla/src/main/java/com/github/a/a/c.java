package com.github.a.a;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: VObjectParameters */
public class c implements Iterable<Map.Entry<String, List<String>>> {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, List<String>> f3754a = new LinkedHashMap();

    public List<String> a(String str) {
        return c(d(str));
    }

    private List<String> c(String str) {
        return this.f3754a.get(str);
    }

    public void a(String str, String str2) {
        b(d(str), str2);
    }

    private void b(String str, String str2) {
        List<String> c2 = c(str);
        if (c2 == null) {
            c2 = new ArrayList<>();
            this.f3754a.put(str, c2);
        }
        c2.add(str2);
    }

    public String b(String str) {
        List<String> a2 = a(str);
        if (a2 == null || a2.isEmpty()) {
            return null;
        }
        return a2.get(0);
    }

    public boolean a() {
        for (String str : new String[]{"ENCODING", null}) {
            List<String> c2 = c(str);
            if (c2 != null) {
                for (String str2 : c2) {
                    if ("QUOTED-PRINTABLE".equalsIgnoreCase(str2)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public Charset b() {
        String b2 = b("CHARSET");
        if (b2 == null) {
            return null;
        }
        return Charset.forName(b2);
    }

    public Map<String, List<String>> c() {
        return this.f3754a;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<String, List<String>>> iterator() {
        return this.f3754a.entrySet().iterator();
    }

    private String d(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    public int hashCode() {
        return this.f3754a.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.f3754a.equals(((c) obj).f3754a);
        }
        return false;
    }

    public String toString() {
        return this.f3754a.toString();
    }
}
