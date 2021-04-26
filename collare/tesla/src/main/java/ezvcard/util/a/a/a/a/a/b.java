package ezvcard.util.a.a.a.a.a;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: BaseNCodec */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private final int f5833a;
    @Deprecated

    /* renamed from: b  reason: collision with root package name */
    protected final byte f5834b;

    /* renamed from: c  reason: collision with root package name */
    protected final byte f5835c;

    /* renamed from: d  reason: collision with root package name */
    protected final int f5836d;
    private final int e;
    private final int f;

    /* access modifiers changed from: protected */
    public int a() {
        return PKIFailureInfo.certRevoked;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(byte[] bArr, int i, int i2, a aVar);

    /* access modifiers changed from: protected */
    public abstract boolean a(byte b2);

    /* access modifiers changed from: package-private */
    public abstract void b(byte[] bArr, int i, int i2, a aVar);

    /* access modifiers changed from: package-private */
    /* compiled from: BaseNCodec */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        int f5837a;

        /* renamed from: b  reason: collision with root package name */
        long f5838b;

        /* renamed from: c  reason: collision with root package name */
        byte[] f5839c;

        /* renamed from: d  reason: collision with root package name */
        int f5840d;
        int e;
        boolean f;
        int g;
        int h;

        a() {
        }

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", getClass().getSimpleName(), Arrays.toString(this.f5839c), Integer.valueOf(this.g), Boolean.valueOf(this.f), Integer.valueOf(this.f5837a), Long.valueOf(this.f5838b), Integer.valueOf(this.h), Integer.valueOf(this.f5840d), Integer.valueOf(this.e));
        }
    }

    protected b(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, (byte) 61);
    }

    protected b(int i, int i2, int i3, int i4, byte b2) {
        this.f5834b = 61;
        this.f5833a = i;
        this.e = i2;
        this.f5836d = i3 > 0 && i4 > 0 ? (i3 / i2) * i2 : 0;
        this.f = i4;
        this.f5835c = b2;
    }

    /* access modifiers changed from: package-private */
    public int a(a aVar) {
        if (aVar.f5839c != null) {
            return aVar.f5840d - aVar.e;
        }
        return 0;
    }

    private byte[] b(a aVar) {
        if (aVar.f5839c == null) {
            aVar.f5839c = new byte[a()];
            aVar.f5840d = 0;
            aVar.e = 0;
        } else {
            byte[] bArr = new byte[(aVar.f5839c.length * 2)];
            System.arraycopy(aVar.f5839c, 0, bArr, 0, aVar.f5839c.length);
            aVar.f5839c = bArr;
        }
        return aVar.f5839c;
    }

    /* access modifiers changed from: protected */
    public byte[] a(int i, a aVar) {
        if (aVar.f5839c == null || aVar.f5839c.length < aVar.f5840d + i) {
            return b(aVar);
        }
        return aVar.f5839c;
    }

    /* access modifiers changed from: package-private */
    public int c(byte[] bArr, int i, int i2, a aVar) {
        if (aVar.f5839c == null) {
            return aVar.f ? -1 : 0;
        }
        int min = Math.min(a(aVar), i2);
        System.arraycopy(aVar.f5839c, aVar.e, bArr, i, min);
        aVar.e += min;
        if (aVar.e >= aVar.f5840d) {
            aVar.f5839c = null;
        }
        return min;
    }

    public byte[] b(String str) {
        return b(c(str));
    }

    public byte[] b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        b(bArr, 0, bArr.length, aVar);
        b(bArr, 0, -1, aVar);
        byte[] bArr2 = new byte[aVar.f5840d];
        c(bArr2, 0, bArr2.length, aVar);
        return bArr2;
    }

    public byte[] c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        a(bArr, 0, bArr.length, aVar);
        a(bArr, 0, -1, aVar);
        byte[] bArr2 = new byte[(aVar.f5840d - aVar.e)];
        c(bArr2, 0, bArr2.length, aVar);
        return bArr2;
    }

    /* access modifiers changed from: protected */
    public boolean d(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b2 : bArr) {
            if (this.f5835c == b2 || a(b2)) {
                return true;
            }
        }
        return false;
    }

    public long e(byte[] bArr) {
        int length = bArr.length;
        int i = this.f5833a;
        long j = ((long) (((length + i) - 1) / i)) * ((long) this.e);
        int i2 = this.f5836d;
        return i2 > 0 ? j + ((((((long) i2) + j) - 1) / ((long) i2)) * ((long) this.f)) : j;
    }

    protected static byte[] c(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    protected static String f(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }
}
