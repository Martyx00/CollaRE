package ezvcard.util;

import ezvcard.b;
import ezvcard.util.a.a.a.a.a.a;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* compiled from: DataUri */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f5844a;

    /* renamed from: b  reason: collision with root package name */
    private final String f5845b;

    /* renamed from: c  reason: collision with root package name */
    private final String f5846c;

    private d(String str, byte[] bArr, String str2) {
        String str3;
        if (str == null) {
            str3 = "";
        } else {
            str3 = str.toLowerCase();
        }
        this.f5846c = str3;
        this.f5844a = bArr;
        this.f5845b = str2;
    }

    public static d a(String str) {
        String str2;
        int i = 5;
        if (str.length() < 5 || !str.substring(0, 5).equalsIgnoreCase("data:")) {
            throw b.INSTANCE.d(18, "data:");
        }
        byte[] bArr = null;
        String str3 = null;
        String str4 = null;
        int i2 = 5;
        boolean z = false;
        while (true) {
            if (i >= str.length()) {
                str2 = null;
                break;
            }
            char charAt = str.charAt(i);
            if (charAt == ';') {
                String substring = str.substring(i2, i);
                if (str3 == null) {
                    str3 = substring.toLowerCase();
                } else if (substring.toLowerCase().startsWith("charset=")) {
                    str4 = substring.substring(substring.indexOf(61) + 1);
                } else if ("base64".equalsIgnoreCase(substring)) {
                    z = true;
                }
                i2 = i + 1;
            } else if (charAt == ',') {
                String substring2 = str.substring(i2, i);
                if (str3 == null) {
                    str3 = substring2.toLowerCase();
                } else if (substring2.toLowerCase().startsWith("charset=")) {
                    str4 = substring2.substring(substring2.indexOf(61) + 1);
                } else if ("base64".equalsIgnoreCase(substring2)) {
                    z = true;
                }
                str2 = str.substring(i + 1);
            }
            i++;
        }
        if (str2 != null) {
            if (z) {
                byte[] a2 = a.a(str2.replaceAll("\\s", ""));
                if (str4 != null) {
                    try {
                        str2 = new String(a2, str4);
                    } catch (UnsupportedEncodingException e) {
                        throw new IllegalArgumentException(b.INSTANCE.c(43, str4), e);
                    }
                } else {
                    bArr = a2;
                    str2 = null;
                }
            }
            return new d(str3, bArr, str2);
        }
        throw b.INSTANCE.d(20, new Object[0]);
    }

    public byte[] a() {
        return this.f5844a;
    }

    public String b() {
        return this.f5846c;
    }

    public String toString() {
        return b(null);
    }

    public String b(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("data:");
        sb.append(this.f5846c);
        if (this.f5844a != null) {
            sb.append(";base64,");
            sb.append(a.a(this.f5844a));
        } else {
            String str2 = this.f5845b;
            if (str2 == null) {
                sb.append(',');
            } else if (str == null) {
                sb.append(',');
                sb.append(this.f5845b);
            } else {
                try {
                    byte[] bytes = str2.getBytes(str);
                    sb.append(";charset=");
                    sb.append(str);
                    sb.append(";base64,");
                    sb.append(a.a(bytes));
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalArgumentException(b.INSTANCE.c(44, str), e);
                }
            }
        }
        return sb.toString();
    }

    public int hashCode() {
        int hashCode = (((this.f5846c.hashCode() + 31) * 31) + Arrays.hashCode(this.f5844a)) * 31;
        String str = this.f5845b;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (!this.f5846c.equals(dVar.f5846c) || !Arrays.equals(this.f5844a, dVar.f5844a)) {
            return false;
        }
        String str = this.f5845b;
        if (str == null) {
            if (dVar.f5845b != null) {
                return false;
            }
        } else if (!str.equals(dVar.f5845b)) {
            return false;
        }
        return true;
    }
}
