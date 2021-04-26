package com.github.a.a.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: VObjectPropertyValues */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3740a = System.getProperty("line.separator");

    public static String a(String str) {
        return a(str, 0, str.length());
    }

    private static String a(String str, int i, int i2) {
        StringBuilder sb = null;
        boolean z = false;
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (z) {
                if (sb == null) {
                    sb = new StringBuilder(i2 - i);
                    sb.append(str.substring(i, i3 - 1));
                }
                if (charAt == 'N' || charAt == 'n') {
                    sb.append(f3740a);
                } else {
                    sb.append(charAt);
                }
                z = false;
            } else if (charAt == '\\') {
                z = true;
            } else if (sb != null) {
                sb.append(charAt);
            }
        }
        if (sb != null) {
            return sb.toString();
        }
        if (i == 0 && i2 == str.length()) {
            return str;
        }
        return str.substring(i, i2);
    }

    public static List<String> b(String str) {
        return a(str, ',', -1);
    }

    public static List<String> c(String str) {
        return a(str, -1);
    }

    public static List<String> a(String str, int i) {
        return a(str, ';', i);
    }

    public static List<List<String>> d(String str) {
        if (str.length() == 0) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(arrayList2);
        ArrayList arrayList3 = arrayList2;
        int i = 0;
        boolean z = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (z) {
                z = false;
            } else {
                char charAt = str.charAt(i2);
                if (charAt == ',') {
                    arrayList3.add(a(str, i, i2));
                    i = i2 + 1;
                } else if (charAt == ';') {
                    String a2 = a(str, i, i2);
                    if (!arrayList3.isEmpty() || a2.length() != 0) {
                        arrayList3.add(a2);
                    }
                    ArrayList arrayList4 = new ArrayList();
                    arrayList.add(arrayList4);
                    arrayList3 = arrayList4;
                    i = i2 + 1;
                } else if (charAt == '\\') {
                    z = true;
                }
            }
        }
        String a3 = a(str, i, str.length());
        if (!arrayList3.isEmpty() || a3.length() != 0) {
            arrayList3.add(a3);
        }
        return arrayList;
    }

    private static List<String> a(String str, char c2, int i) {
        if (str.length() == 0) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (z) {
                z = false;
            } else if (charAt == c2) {
                arrayList.add(a(str, i2, i3));
                i2 = i3 + 1;
                if (i > 0 && arrayList.size() == i - 1) {
                    break;
                }
            } else if (charAt == '\\') {
                z = true;
            }
        }
        arrayList.add(a(str, i2, str.length()));
        return arrayList;
    }

    /* compiled from: VObjectPropertyValues */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Iterator<String> f3741a;

        public a(String str) {
            this(str, -1);
        }

        public a(String str, int i) {
            this.f3741a = e.a(str, i).iterator();
        }

        public String a() {
            if (!b()) {
                return null;
            }
            String next = this.f3741a.next();
            if (next.length() == 0) {
                return null;
            }
            return next;
        }

        public boolean b() {
            return this.f3741a.hasNext();
        }
    }

    /* compiled from: VObjectPropertyValues */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final Iterator<List<String>> f3742a;

        public b(String str) {
            this(e.d(str));
        }

        public b(List<List<String>> list) {
            this.f3742a = list.iterator();
        }

        public String a() {
            if (!c()) {
                return null;
            }
            List<String> next = this.f3742a.next();
            if (next.isEmpty()) {
                return null;
            }
            return next.get(0);
        }

        public List<String> b() {
            if (!c()) {
                return new ArrayList(0);
            }
            return this.f3742a.next();
        }

        public boolean c() {
            return this.f3742a.hasNext();
        }
    }
}
