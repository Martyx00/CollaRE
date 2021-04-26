package com.facebook.imagepipeline.e;

import com.facebook.common.d.i;
import com.facebook.common.k.b;
import com.google.android.gms.common.api.Api;
import java.util.regex.Pattern;

/* compiled from: BytesRange */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static Pattern f2075c;

    /* renamed from: a  reason: collision with root package name */
    public final int f2076a;

    /* renamed from: b  reason: collision with root package name */
    public final int f2077b;

    public a(int i, int i2) {
        this.f2076a = i;
        this.f2077b = i2;
    }

    public String a() {
        return String.format(null, "bytes=%s-%s", c(this.f2076a), c(this.f2077b));
    }

    public boolean a(a aVar) {
        return aVar != null && this.f2076a <= aVar.f2076a && this.f2077b >= aVar.f2077b;
    }

    public String toString() {
        return String.format(null, "%s-%s", c(this.f2076a), c(this.f2077b));
    }

    private static String c(int i) {
        return i == Integer.MAX_VALUE ? "" : Integer.toString(i);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.f2076a == aVar.f2076a && this.f2077b == aVar.f2077b) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return b.a(this.f2076a, this.f2077b);
    }

    public static a a(int i) {
        i.a(i >= 0);
        return new a(i, Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public static a b(int i) {
        i.a(i > 0);
        return new a(0, i);
    }

    public static a a(String str) {
        if (str == null) {
            return null;
        }
        if (f2075c == null) {
            f2075c = Pattern.compile("[-/ ]");
        }
        try {
            String[] split = f2075c.split(str);
            i.a(split.length == 4);
            i.a(split[0].equals("bytes"));
            int parseInt = Integer.parseInt(split[1]);
            int parseInt2 = Integer.parseInt(split[2]);
            int parseInt3 = Integer.parseInt(split[3]);
            i.a(parseInt2 > parseInt);
            i.a(parseInt3 > parseInt2);
            if (parseInt2 < parseInt3 - 1) {
                return new a(parseInt, parseInt2);
            }
            return new a(parseInt, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(null, "Invalid Content-Range header value: \"%s\"", str), e);
        }
    }
}
