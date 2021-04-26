package ezvcard.parameter;

import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.d;
import ezvcard.util.GeoUri;
import ezvcard.util.g;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VCardParameters extends g<String, String> {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, Set<VCardVersion>> f5796a;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("ALTID", EnumSet.of(VCardVersion.V4_0));
        hashMap.put("CALSCALE", EnumSet.of(VCardVersion.V4_0));
        hashMap.put("CHARSET", EnumSet.of(VCardVersion.V2_1));
        hashMap.put("GEO", EnumSet.of(VCardVersion.V4_0));
        hashMap.put("INDEX", EnumSet.of(VCardVersion.V4_0));
        hashMap.put("LEVEL", EnumSet.of(VCardVersion.V4_0));
        hashMap.put("MEDIATYPE", EnumSet.of(VCardVersion.V4_0));
        hashMap.put("PID", EnumSet.of(VCardVersion.V4_0));
        hashMap.put("SORT-AS", EnumSet.of(VCardVersion.V4_0));
        hashMap.put("TZ", EnumSet.of(VCardVersion.V4_0));
        f5796a = Collections.unmodifiableMap(hashMap);
    }

    public VCardParameters() {
    }

    public VCardParameters(VCardParameters vCardParameters) {
        super(vCardParameters);
    }

    public VCardParameters(Map<String, List<String>> map) {
        super(map);
    }

    public String a() {
        return (String) c((Object) "ALTID");
    }

    public void a(String str) {
        b("ALTID", str);
    }

    public Calscale b() {
        String str = (String) c((Object) "CALSCALE");
        if (str == null) {
            return null;
        }
        return Calscale.b(str);
    }

    public void a(Calscale calscale) {
        b("CALSCALE", calscale == null ? null : calscale.c());
    }

    public String c() {
        return (String) c((Object) "CHARSET");
    }

    public a d() {
        String str = (String) c((Object) "ENCODING");
        if (str == null) {
            return null;
        }
        return a.b(str);
    }

    public GeoUri e() {
        String str = (String) c((Object) "GEO");
        if (str == null) {
            return null;
        }
        try {
            return GeoUri.a(str);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(ezvcard.b.INSTANCE.c(15, "GEO"), e);
        }
    }

    public void a(GeoUri geoUri) {
        b("GEO", geoUri == null ? null : geoUri.toString());
    }

    public Integer f() {
        String str = (String) c((Object) "INDEX");
        if (str == null) {
            return null;
        }
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(ezvcard.b.INSTANCE.c(15, "INDEX"), e);
        }
    }

    public void a(Integer num) {
        b("INDEX", num == null ? null : num.toString());
    }

    public String g() {
        return (String) c((Object) "LABEL");
    }

    public void b(String str) {
        b("LABEL", str);
    }

    public String h() {
        return (String) c((Object) "LANGUAGE");
    }

    public void c(String str) {
        b("LANGUAGE", str);
    }

    public String i() {
        return (String) c((Object) "LEVEL");
    }

    public void d(String str) {
        b("LEVEL", str);
    }

    public String j() {
        return (String) c((Object) "MEDIATYPE");
    }

    public void e(String str) {
        b("MEDIATYPE", str);
    }

    public List<c> k() {
        return new c<c>("PID") {
            /* class ezvcard.parameter.VCardParameters.AnonymousClass1 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public String _asString(c cVar) {
                return cVar.toString();
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public c _asObject(String str) {
                return c.a(str);
            }

            /* access modifiers changed from: protected */
            @Override // ezvcard.parameter.VCardParameters.c
            public IllegalStateException _exception(String str, Exception exc) {
                return new IllegalStateException(ezvcard.b.INSTANCE.c(15, "PID"), exc);
            }
        };
    }

    public Integer l() {
        String str = (String) c((Object) "PREF");
        if (str == null) {
            return null;
        }
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(ezvcard.b.INSTANCE.c(15, "PREF"), e);
        }
    }

    public void b(Integer num) {
        b("PREF", num == null ? null : num.toString());
    }

    public List<String> m() {
        return b((Object) "SORT-AS");
    }

    public void a(String... strArr) {
        d((Object) "SORT-AS");
        a((Object) "SORT-AS", (Collection) Arrays.asList(strArr));
    }

    public List<String> n() {
        return b((Object) "TYPE");
    }

    public String o() {
        return (String) c((Object) "TYPE");
    }

    public void f(String str) {
        b("TYPE", str);
    }

    public String p() {
        return (String) c((Object) "TZ");
    }

    public void g(String str) {
        b("TZ", str);
    }

    public VCardDataType q() {
        String str = (String) c((Object) "VALUE");
        if (str == null) {
            return null;
        }
        return VCardDataType.b(str);
    }

    public void a(VCardDataType vCardDataType) {
        b("VALUE", vCardDataType == null ? null : vCardDataType.a());
    }

    public List<d> a(VCardVersion vCardVersion) {
        ArrayList arrayList = new ArrayList(0);
        com.github.a.a.a syntaxStyle = vCardVersion.getSyntaxStyle();
        Iterator it = iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            if (vCardVersion == VCardVersion.V4_0 || !"LABEL".equalsIgnoreCase(str)) {
                if (!com.github.a.a.c.b.a(str, syntaxStyle, true)) {
                    if (syntaxStyle == com.github.a.a.a.OLD) {
                        arrayList.add(new d(30, str, com.github.a.a.c.b.b(syntaxStyle, true).a().a(true)));
                    } else {
                        arrayList.add(new d(26, str));
                    }
                }
                for (String str2 : (List) entry.getValue()) {
                    if ("LABEL".equalsIgnoreCase(str)) {
                        str2 = str2.replaceAll("\r\n|\r|\n", "");
                    }
                    if (!com.github.a.a.c.b.a(str2, syntaxStyle, false, true)) {
                        com.github.a.a.c.a a2 = com.github.a.a.c.b.a(syntaxStyle, false, true).a();
                        arrayList.add(new d(syntaxStyle == com.github.a.a.a.OLD ? 31 : 25, str, str2, a2.a(true)));
                    }
                }
            }
        }
        String str3 = (String) c((Object) "CALSCALE");
        if (str3 != null && Calscale.a(str3) == null) {
            arrayList.add(new d(3, "CALSCALE", str3, Calscale.a()));
        }
        String str4 = (String) c((Object) "ENCODING");
        if (str4 != null) {
            a a3 = a.a(str4);
            if (a3 == null) {
                arrayList.add(new d(3, "ENCODING", str4, a.a()));
            } else if (!a3.a(vCardVersion)) {
                arrayList.add(new d(4, "ENCODING", str4));
            }
        }
        String str5 = (String) c((Object) "VALUE");
        if (str5 != null) {
            VCardDataType a4 = VCardDataType.a(str5);
            if (a4 == null) {
                arrayList.add(new d(3, "VALUE", str5, VCardDataType.c()));
            } else if (!a4.a(vCardVersion)) {
                arrayList.add(new d(4, "VALUE", str5));
            }
        }
        try {
            e();
        } catch (IllegalStateException unused) {
            arrayList.add(new d(5, "GEO", c((Object) "GEO")));
        }
        try {
            Integer f = f();
            if (f != null && f.intValue() <= 0) {
                arrayList.add(new d(28, f));
            }
        } catch (IllegalStateException unused2) {
            arrayList.add(new d(5, "INDEX", c((Object) "INDEX")));
        }
        for (String str6 : b((Object) "PID")) {
            if (!i(str6)) {
                arrayList.add(new d(27, str6));
            }
        }
        try {
            Integer l = l();
            if (l != null && (l.intValue() < 1 || l.intValue() > 100)) {
                arrayList.add(new d(29, l));
            }
        } catch (IllegalStateException unused3) {
            arrayList.add(new d(5, "PREF", c((Object) "PREF")));
        }
        for (Map.Entry<String, Set<VCardVersion>> entry2 : f5796a.entrySet()) {
            String key = entry2.getKey();
            if (((String) c((Object) key)) != null && !entry2.getValue().contains(vCardVersion)) {
                arrayList.add(new d(6, key));
            }
        }
        String c2 = c();
        if (c2 != null) {
            try {
                Charset.forName(c2);
            } catch (IllegalCharsetNameException unused4) {
                arrayList.add(new d(22, c2));
            } catch (UnsupportedCharsetException unused5) {
                arrayList.add(new d(22, c2));
            }
        }
        return arrayList;
    }

    private static boolean i(String str) {
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '.') {
                if (i == 0 || i == str.length() - 1 || z) {
                    return false;
                }
                z = true;
            } else if (charAt < '0' || charAt > '9') {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public String a(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    @Override // ezvcard.util.g
    public int hashCode() {
        int i;
        Iterator it = iterator();
        int i2 = 1;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            int i3 = 1;
            for (String str2 : (List) entry.getValue()) {
                i3 += str2.toLowerCase().hashCode();
            }
            if (str == null) {
                i = 0;
            } else {
                i = str.toLowerCase().hashCode();
            }
            int i4 = 31 + i + 1;
            i2 += i4 + (i4 * 31) + i3;
        }
        return i2;
    }

    @Override // ezvcard.util.g
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VCardParameters vCardParameters = (VCardParameters) obj;
        if (s() != vCardParameters.s()) {
            return false;
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            List<String> list = (List) entry.getValue();
            List<String> b2 = vCardParameters.b((Object) ((String) entry.getKey()));
            if (list.size() != b2.size()) {
                return false;
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (String str : list) {
                arrayList.add(str.toLowerCase());
            }
            Collections.sort(arrayList);
            ArrayList arrayList2 = new ArrayList(b2.size());
            for (String str2 : b2) {
                arrayList2.add(str2.toLowerCase());
            }
            Collections.sort(arrayList2);
            if (!arrayList.equals(arrayList2)) {
                return false;
            }
        }
        return true;
    }

    public abstract class b<T extends VCardParameter> extends a<T> {
        public b() {
            super("TYPE");
        }
    }

    public abstract class a<T extends VCardParameter> extends c<T> {
        public a(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        public String _asString(T t) {
            return t.c();
        }
    }

    public abstract class c<T> extends AbstractList<T> {
        protected final String parameterName;
        protected final List<String> parameterValues;

        /* access modifiers changed from: protected */
        public abstract T _asObject(String str);

        /* access modifiers changed from: protected */
        public abstract String _asString(T t);

        public c(String str) {
            this.parameterName = str;
            this.parameterValues = VCardParameters.this.b((Object) str);
        }

        @Override // java.util.List, java.util.AbstractList
        public void add(int i, T t) {
            this.parameterValues.add(i, _asString(t));
        }

        @Override // java.util.List, java.util.AbstractList
        public T remove(int i) {
            return asObject(this.parameterValues.remove(i));
        }

        @Override // java.util.List, java.util.AbstractList
        public T get(int i) {
            return asObject(this.parameterValues.get(i));
        }

        @Override // java.util.List, java.util.AbstractList
        public T set(int i, T t) {
            return asObject(this.parameterValues.set(i, _asString(t)));
        }

        public int size() {
            return this.parameterValues.size();
        }

        private T asObject(String str) {
            try {
                return _asObject(str);
            } catch (Exception e) {
                throw _exception(str, e);
            }
        }

        /* access modifiers changed from: protected */
        public IllegalStateException _exception(String str, Exception exc) {
            return new IllegalStateException(ezvcard.b.INSTANCE.c(26, this.parameterName), exc);
        }
    }
}
