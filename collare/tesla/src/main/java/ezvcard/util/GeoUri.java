package ezvcard.util;

import ezvcard.b;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GeoUri {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean[] f5804a = new boolean[128];

    /* renamed from: b  reason: collision with root package name */
    private static final Pattern f5805b = Pattern.compile("(?i)%([0-9a-f]{2})");

    /* renamed from: c  reason: collision with root package name */
    private final Double f5806c;

    /* renamed from: d  reason: collision with root package name */
    private final Double f5807d;
    private final Double e;
    private final String f;
    private final Double g;
    private final Map<String, String> h;

    static {
        for (int i = 48; i <= 57; i++) {
            f5804a[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            f5804a[i2] = true;
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            f5804a[i3] = true;
        }
        for (int i4 = 0; i4 < 15; i4++) {
            f5804a["!$&'()*+-.:[]_~".charAt(i4)] = true;
        }
    }

    private GeoUri(a aVar) {
        this.f5806c = aVar.f5808a == null ? Double.valueOf(0.0d) : aVar.f5808a;
        this.f5807d = aVar.f5809b == null ? Double.valueOf(0.0d) : aVar.f5809b;
        this.e = aVar.f5810c;
        this.f = aVar.f5811d;
        this.g = aVar.e;
        this.h = Collections.unmodifiableMap(aVar.f);
    }

    public static GeoUri a(String str) {
        if (str.length() < 4 || !str.substring(0, 4).equalsIgnoreCase("geo:")) {
            throw b.INSTANCE.d(18, "geo:");
        }
        a aVar = new a(null, null);
        c cVar = new c();
        String str2 = null;
        boolean z = false;
        for (int i = 4; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == ',' && !z) {
                a(cVar, aVar);
            } else if (charAt == ';') {
                if (z) {
                    a(cVar, str2, aVar);
                    str2 = null;
                } else {
                    a(cVar, aVar);
                    if (aVar.f5809b != null) {
                        z = true;
                    } else {
                        throw b.INSTANCE.d(21, new Object[0]);
                    }
                }
            } else if (charAt == '=' && z && str2 == null) {
                str2 = cVar.c();
            } else {
                cVar.a(charAt);
            }
        }
        if (z) {
            a(cVar, str2, aVar);
        } else {
            a(cVar, aVar);
            if (aVar.f5809b == null) {
                throw b.INSTANCE.d(21, new Object[0]);
            }
        }
        return aVar.a();
    }

    private static void a(c cVar, a aVar) {
        String c2 = cVar.c();
        if (aVar.f5808a == null) {
            try {
                aVar.f5808a = Double.valueOf(Double.parseDouble(c2));
            } catch (NumberFormatException e2) {
                throw new IllegalArgumentException(b.INSTANCE.c(22, "A"), e2);
            }
        } else if (aVar.f5809b == null) {
            try {
                aVar.f5809b = Double.valueOf(Double.parseDouble(c2));
            } catch (NumberFormatException e3) {
                throw new IllegalArgumentException(b.INSTANCE.c(22, "B"), e3);
            }
        } else if (aVar.f5810c == null) {
            try {
                aVar.f5810c = Double.valueOf(Double.parseDouble(c2));
            } catch (NumberFormatException e4) {
                throw new IllegalArgumentException(b.INSTANCE.c(22, "C"), e4);
            }
        }
    }

    private static void a(String str, String str2, a aVar) {
        String c2 = c(str2);
        if ("crs".equalsIgnoreCase(str)) {
            aVar.f5811d = c2;
            return;
        }
        if ("u".equalsIgnoreCase(str)) {
            try {
                aVar.e = Double.valueOf(c2);
                return;
            } catch (NumberFormatException unused) {
            }
        }
        aVar.f.put(str, c2);
    }

    private static void a(c cVar, String str, a aVar) {
        String c2 = cVar.c();
        if (str != null) {
            a(str, c2, aVar);
        } else if (c2.length() > 0) {
            a(c2, "", aVar);
        }
    }

    public Double a() {
        return this.f5806c;
    }

    public Double b() {
        return this.f5807d;
    }

    public String toString() {
        return a(6);
    }

    public String a(int i) {
        j jVar = new j(i);
        StringBuilder sb = new StringBuilder("geo:");
        sb.append(jVar.format(this.f5806c));
        sb.append(',');
        sb.append(jVar.format(this.f5807d));
        if (this.e != null) {
            sb.append(',');
            sb.append(this.e);
        }
        String str = this.f;
        if (str != null && !str.equalsIgnoreCase("wgs84")) {
            a("crs", this.f, sb);
        }
        Double d2 = this.g;
        if (d2 != null) {
            a("u", jVar.format(d2), sb);
        }
        for (Map.Entry<String, String> entry : this.h.entrySet()) {
            a(entry.getKey(), entry.getValue(), sb);
        }
        return sb.toString();
    }

    private void a(String str, String str2, StringBuilder sb) {
        sb.append(';');
        sb.append(str);
        sb.append('=');
        sb.append(b(str2));
    }

    private static String b(String str) {
        StringBuilder sb = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            boolean[] zArr = f5804a;
            if (charAt >= zArr.length || !zArr[charAt]) {
                if (sb == null) {
                    sb = new StringBuilder(str.length() * 2);
                    sb.append(str.substring(0, i));
                }
                String num = Integer.toString(charAt, 16);
                sb.append('%');
                sb.append(num);
            } else if (sb != null) {
                sb.append(charAt);
            }
        }
        return sb == null ? str : sb.toString();
    }

    private static String c(String str) {
        Matcher matcher = f5805b.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer(str.length());
            }
            matcher.appendReplacement(stringBuffer, Character.toString((char) Integer.parseInt(matcher.group(1), 16)));
        }
        if (stringBuffer == null) {
            return str;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public int hashCode() {
        Double d2 = this.f5806c;
        int i = 0;
        int hashCode = ((d2 == null ? 0 : d2.hashCode()) + 31) * 31;
        Double d3 = this.f5807d;
        int hashCode2 = (hashCode + (d3 == null ? 0 : d3.hashCode())) * 31;
        Double d4 = this.e;
        int hashCode3 = (hashCode2 + (d4 == null ? 0 : d4.hashCode())) * 31;
        String str = this.f;
        int hashCode4 = (hashCode3 + (str == null ? 0 : str.toLowerCase().hashCode())) * 31;
        Map<String, String> map = this.h;
        int hashCode5 = (hashCode4 + (map == null ? 0 : h.a(map).hashCode())) * 31;
        Double d5 = this.g;
        if (d5 != null) {
            i = d5.hashCode();
        }
        return hashCode5 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoUri geoUri = (GeoUri) obj;
        Double d2 = this.f5806c;
        if (d2 == null) {
            if (geoUri.f5806c != null) {
                return false;
            }
        } else if (!d2.equals(geoUri.f5806c)) {
            return false;
        }
        Double d3 = this.f5807d;
        if (d3 == null) {
            if (geoUri.f5807d != null) {
                return false;
            }
        } else if (!d3.equals(geoUri.f5807d)) {
            return false;
        }
        Double d4 = this.e;
        if (d4 == null) {
            if (geoUri.e != null) {
                return false;
            }
        } else if (!d4.equals(geoUri.e)) {
            return false;
        }
        String str = this.f;
        if (str == null) {
            if (geoUri.f != null) {
                return false;
            }
        } else if (!str.equalsIgnoreCase(geoUri.f)) {
            return false;
        }
        Double d5 = this.g;
        if (d5 == null) {
            if (geoUri.g != null) {
                return false;
            }
        } else if (!d5.equals(geoUri.g)) {
            return false;
        }
        Map<String, String> map = this.h;
        if (map != null) {
            return geoUri.h != null && map.size() == geoUri.h.size() && h.a(this.h).equals(h.a(geoUri.h));
        }
        if (geoUri.h != null) {
            return false;
        }
    }

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private Double f5808a;

        /* renamed from: b  reason: collision with root package name */
        private Double f5809b;

        /* renamed from: c  reason: collision with root package name */
        private Double f5810c;

        /* renamed from: d  reason: collision with root package name */
        private String f5811d;
        private Double e;
        private Map<String, String> f;
        private b g;

        public a(Double d2, Double d3) {
            this.g = new b("a-zA-Z0-9-");
            this.f = new LinkedHashMap(0);
            a(d2);
            b(d3);
        }

        public a(GeoUri geoUri) {
            this.g = new b("a-zA-Z0-9-");
            a(geoUri.f5806c);
            b(geoUri.f5807d);
            this.f5810c = geoUri.e;
            this.f5811d = geoUri.f;
            this.e = geoUri.g;
            this.f = new LinkedHashMap(geoUri.h);
        }

        public a a(Double d2) {
            this.f5808a = d2;
            return this;
        }

        public a b(Double d2) {
            this.f5809b = d2;
            return this;
        }

        public GeoUri a() {
            return new GeoUri(this);
        }
    }
}
