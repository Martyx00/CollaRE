package ezvcard.util;

import ezvcard.b;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TelUri {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean[] f5820a = new boolean[128];

    /* renamed from: b  reason: collision with root package name */
    private static final Pattern f5821b = Pattern.compile("(?i)%([0-9a-f]{2})");

    /* renamed from: c  reason: collision with root package name */
    private final String f5822c;

    /* renamed from: d  reason: collision with root package name */
    private final String f5823d;
    private final String e;
    private final String f;
    private final Map<String, String> g;

    static {
        for (int i = 48; i <= 57; i++) {
            f5820a[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            f5820a[i2] = true;
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            f5820a[i3] = true;
        }
        for (int i4 = 0; i4 < 16; i4++) {
            f5820a["!$&'()*+-.:[]_~/".charAt(i4)] = true;
        }
    }

    private TelUri(a aVar) {
        this.f5822c = aVar.f5824a;
        this.f5823d = aVar.f5825b;
        this.e = aVar.f5826c;
        this.f = aVar.f5827d;
        this.g = Collections.unmodifiableMap(aVar.e);
    }

    public static TelUri a(String str) {
        if (str.length() < 4 || !str.substring(0, 4).equalsIgnoreCase("tel:")) {
            throw b.INSTANCE.d(18, "tel:");
        }
        a aVar = new a();
        c cVar = new c();
        String str2 = null;
        for (int i = 4; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '=' && aVar.f5824a != null && str2 == null) {
                str2 = cVar.c();
            } else if (charAt == ';') {
                a(cVar, str2, aVar);
                str2 = null;
            } else {
                cVar.a(charAt);
            }
        }
        a(cVar, str2, aVar);
        return aVar.a();
    }

    private static void a(String str, String str2, a aVar) {
        String c2 = c(str2);
        if ("ext".equalsIgnoreCase(str)) {
            aVar.f5825b = c2;
        } else if ("isub".equalsIgnoreCase(str)) {
            aVar.f5826c = c2;
        } else if ("phone-context".equalsIgnoreCase(str)) {
            aVar.f5827d = c2;
        } else {
            aVar.e.put(str, c2);
        }
    }

    private static void a(c cVar, String str, a aVar) {
        String c2 = cVar.c();
        if (aVar.f5824a == null) {
            aVar.f5824a = c2;
        } else if (str != null) {
            a(str, c2, aVar);
        } else if (c2.length() > 0) {
            a(c2, "", aVar);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("tel:");
        sb.append(this.f5822c);
        String str = this.f5823d;
        if (str != null) {
            a("ext", str, sb);
        }
        String str2 = this.e;
        if (str2 != null) {
            a("isub", str2, sb);
        }
        String str3 = this.f;
        if (str3 != null) {
            a("phone-context", str3, sb);
        }
        for (Map.Entry<String, String> entry : this.g.entrySet()) {
            a(entry.getKey(), entry.getValue(), sb);
        }
        return sb.toString();
    }

    private static void a(String str, String str2, StringBuilder sb) {
        sb.append(';');
        sb.append(str);
        sb.append('=');
        sb.append(b(str2));
    }

    public int hashCode() {
        String str = this.f5823d;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.toLowerCase().hashCode()) + 31) * 31;
        String str2 = this.e;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.toLowerCase().hashCode())) * 31;
        String str3 = this.f5822c;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.toLowerCase().hashCode())) * 31;
        Map<String, String> map = this.g;
        int hashCode4 = (hashCode3 + (map == null ? 0 : h.a(map).hashCode())) * 31;
        String str4 = this.f;
        if (str4 != null) {
            i = str4.toLowerCase().hashCode();
        }
        return hashCode4 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TelUri telUri = (TelUri) obj;
        String str = this.f5823d;
        if (str == null) {
            if (telUri.f5823d != null) {
                return false;
            }
        } else if (!str.equalsIgnoreCase(telUri.f5823d)) {
            return false;
        }
        String str2 = this.e;
        if (str2 == null) {
            if (telUri.e != null) {
                return false;
            }
        } else if (!str2.equalsIgnoreCase(telUri.e)) {
            return false;
        }
        String str3 = this.f5822c;
        if (str3 == null) {
            if (telUri.f5822c != null) {
                return false;
            }
        } else if (!str3.equalsIgnoreCase(telUri.f5822c)) {
            return false;
        }
        Map<String, String> map = this.g;
        if (map == null) {
            if (telUri.g != null) {
                return false;
            }
        } else if (telUri.g == null || map.size() != telUri.g.size() || !h.a(this.g).equals(h.a(telUri.g))) {
            return false;
        }
        String str4 = this.f;
        if (str4 == null) {
            if (telUri.f != null) {
                return false;
            }
        } else if (!str4.equalsIgnoreCase(telUri.f)) {
            return false;
        }
        return true;
    }

    private static String b(String str) {
        StringBuilder sb = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            boolean[] zArr = f5820a;
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
        Matcher matcher = f5821b.matcher(str);
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

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f5824a;

        /* renamed from: b  reason: collision with root package name */
        private String f5825b;

        /* renamed from: c  reason: collision with root package name */
        private String f5826c;

        /* renamed from: d  reason: collision with root package name */
        private String f5827d;
        private Map<String, String> e;
        private b f;

        private a() {
            this.f = new b("a-zA-Z0-9-");
            this.e = new TreeMap();
        }

        public TelUri a() {
            return new TelUri(this);
        }
    }
}
