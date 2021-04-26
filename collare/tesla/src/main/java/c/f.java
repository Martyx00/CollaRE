package c;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* compiled from: ByteString */
public class f implements Serializable, Comparable<f> {

    /* renamed from: a  reason: collision with root package name */
    static final char[] f1329a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b  reason: collision with root package name */
    public static final f f1330b = a(new byte[0]);

    /* renamed from: c  reason: collision with root package name */
    final byte[] f1331c;

    /* renamed from: d  reason: collision with root package name */
    transient int f1332d;
    transient String e;

    f(byte[] bArr) {
        this.f1331c = bArr;
    }

    public static f a(byte... bArr) {
        if (bArr != null) {
            return new f((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static f a(String str) {
        if (str != null) {
            f fVar = new f(str.getBytes(v.f1370a));
            fVar.e = str;
            return fVar;
        }
        throw new IllegalArgumentException("s == null");
    }

    public static f a(String str, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        } else if (charset != null) {
            return new f(str.getBytes(charset));
        } else {
            throw new IllegalArgumentException("charset == null");
        }
    }

    public String a() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.f1331c, v.f1370a);
        this.e = str2;
        return str2;
    }

    public String b() {
        return b.a(this.f1331c);
    }

    public f c() {
        return d("MD5");
    }

    public f d() {
        return d(McElieceCCA2KeyGenParameterSpec.SHA1);
    }

    public f e() {
        return d(McElieceCCA2KeyGenParameterSpec.SHA256);
    }

    private f d(String str) {
        try {
            return a(MessageDigest.getInstance(str).digest(this.f1331c));
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    public static f b(String str) {
        if (str != null) {
            byte[] a2 = b.a(str);
            if (a2 != null) {
                return new f(a2);
            }
            return null;
        }
        throw new IllegalArgumentException("base64 == null");
    }

    public String f() {
        byte[] bArr = this.f1331c;
        char[] cArr = new char[(bArr.length * 2)];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            char[] cArr2 = f1329a;
            cArr[i] = cArr2[(b2 >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public static f c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        } else if (str.length() % 2 == 0) {
            byte[] bArr = new byte[(str.length() / 2)];
            for (int i = 0; i < bArr.length; i++) {
                int i2 = i * 2;
                bArr[i] = (byte) ((a(str.charAt(i2)) << 4) + a(str.charAt(i2 + 1)));
            }
            return a(bArr);
        } else {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        }
    }

    private static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        if (c2 >= 'a' && c2 <= 'f') {
            return (c2 - 'a') + 10;
        }
        if (c2 >= 'A' && c2 <= 'F') {
            return (c2 - 'A') + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c2);
    }

    public f g() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f1331c;
            if (i >= bArr.length) {
                return this;
            }
            byte b2 = bArr[i];
            if (b2 < 65 || b2 > 90) {
                i++;
            } else {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i] = (byte) (b2 + 32);
                for (int i2 = i + 1; i2 < bArr2.length; i2++) {
                    byte b3 = bArr2[i2];
                    if (b3 >= 65 && b3 <= 90) {
                        bArr2[i2] = (byte) (b3 + 32);
                    }
                }
                return new f(bArr2);
            }
        }
    }

    public f a(int i, int i2) {
        if (i >= 0) {
            byte[] bArr = this.f1331c;
            if (i2 <= bArr.length) {
                int i3 = i2 - i;
                if (i3 < 0) {
                    throw new IllegalArgumentException("endIndex < beginIndex");
                } else if (i == 0 && i2 == bArr.length) {
                    return this;
                } else {
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(this.f1331c, i, bArr2, 0, i3);
                    return new f(bArr2);
                }
            } else {
                throw new IllegalArgumentException("endIndex > length(" + this.f1331c.length + ")");
            }
        } else {
            throw new IllegalArgumentException("beginIndex < 0");
        }
    }

    public byte a(int i) {
        return this.f1331c[i];
    }

    public int h() {
        return this.f1331c.length;
    }

    public byte[] i() {
        return (byte[]) this.f1331c.clone();
    }

    /* access modifiers changed from: package-private */
    public byte[] j() {
        return this.f1331c;
    }

    /* access modifiers changed from: package-private */
    public void a(c cVar) {
        byte[] bArr = this.f1331c;
        cVar.c(bArr, 0, bArr.length);
    }

    public boolean a(int i, f fVar, int i2, int i3) {
        return fVar.a(i2, this.f1331c, i, i3);
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i >= 0) {
            byte[] bArr2 = this.f1331c;
            return i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && v.a(bArr2, i, bArr, i2, i3);
        }
    }

    public final boolean a(f fVar) {
        return a(0, fVar, 0, fVar.h());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            int h = fVar.h();
            byte[] bArr = this.f1331c;
            if (h != bArr.length || !fVar.a(0, bArr, 0, bArr.length)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.f1332d;
        if (i != 0) {
            return i;
        }
        int hashCode = Arrays.hashCode(this.f1331c);
        this.f1332d = hashCode;
        return hashCode;
    }

    /* renamed from: b */
    public int compareTo(f fVar) {
        int h = h();
        int h2 = fVar.h();
        int min = Math.min(h, h2);
        for (int i = 0; i < min; i++) {
            int a2 = a(i) & 255;
            int a3 = fVar.a(i) & 255;
            if (a2 != a3) {
                if (a2 < a3) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        if (h == h2) {
            return 0;
        }
        if (h < h2) {
            return -1;
        }
        return 1;
    }

    public String toString() {
        if (this.f1331c.length == 0) {
            return "[size=0]";
        }
        String a2 = a();
        int a3 = a(a2, 64);
        if (a3 != -1) {
            String replace = a2.substring(0, a3).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (a3 < a2.length()) {
                return "[size=" + this.f1331c.length + " text=" + replace + "…]";
            }
            return "[text=" + replace + "]";
        } else if (this.f1331c.length <= 64) {
            return "[hex=" + f() + "]";
        } else {
            return "[size=" + this.f1331c.length + " hex=" + a(0, 64).f() + "…]";
        }
    }

    static int a(String str, int i) {
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            if (i3 == i) {
                return i2;
            }
            int codePointAt = str.codePointAt(i2);
            if ((Character.isISOControl(codePointAt) && codePointAt != 10 && codePointAt != 13) || codePointAt == 65533) {
                return -1;
            }
            i3++;
            i2 += Character.charCount(codePointAt);
        }
        return str.length();
    }
}
