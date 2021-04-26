package ezvcard.util.a.a.a.a.a;

import com.google.android.gms.common.api.Api;
import ezvcard.util.a.a.a.a.a.b;
import org.spongycastle.math.ec.Tnaf;

/* compiled from: Base64 */
public class a extends b {

    /* renamed from: a  reason: collision with root package name */
    static final byte[] f5832a = {13, 10};
    private static final byte[] e = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] f = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] g = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, Tnaf.POW_2_WIDTH, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    private final byte[] h;
    private final byte[] i;
    private final byte[] j;
    private final int k;
    private final int l;

    public a() {
        this(0);
    }

    public a(boolean z) {
        this(76, f5832a, z);
    }

    public a(int i2) {
        this(i2, f5832a);
    }

    public a(int i2, byte[] bArr) {
        this(i2, bArr, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(int i2, byte[] bArr, boolean z) {
        super(3, 4, i2, bArr == null ? 0 : bArr.length);
        this.i = g;
        if (bArr == null) {
            this.l = 4;
            this.j = null;
        } else if (d(bArr)) {
            String f2 = f(bArr);
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + f2 + "]");
        } else if (i2 > 0) {
            this.l = bArr.length + 4;
            this.j = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.j, 0, bArr.length);
        } else {
            this.l = 4;
            this.j = null;
        }
        this.k = this.l - 1;
        this.h = z ? f : e;
    }

    /* access modifiers changed from: package-private */
    @Override // ezvcard.util.a.a.a.a.a.b
    public void a(byte[] bArr, int i2, int i3, b.a aVar) {
        if (!aVar.f) {
            if (i3 < 0) {
                aVar.f = true;
                if (aVar.h != 0 || this.f5836d != 0) {
                    byte[] a2 = a(this.l, aVar);
                    int i4 = aVar.f5840d;
                    switch (aVar.h) {
                        case 0:
                            break;
                        case 1:
                            int i5 = aVar.f5840d;
                            aVar.f5840d = i5 + 1;
                            a2[i5] = this.h[(aVar.f5837a >> 2) & 63];
                            int i6 = aVar.f5840d;
                            aVar.f5840d = i6 + 1;
                            a2[i6] = this.h[(aVar.f5837a << 4) & 63];
                            if (this.h == e) {
                                int i7 = aVar.f5840d;
                                aVar.f5840d = i7 + 1;
                                a2[i7] = this.f5835c;
                                int i8 = aVar.f5840d;
                                aVar.f5840d = i8 + 1;
                                a2[i8] = this.f5835c;
                                break;
                            }
                            break;
                        case 2:
                            int i9 = aVar.f5840d;
                            aVar.f5840d = i9 + 1;
                            a2[i9] = this.h[(aVar.f5837a >> 10) & 63];
                            int i10 = aVar.f5840d;
                            aVar.f5840d = i10 + 1;
                            a2[i10] = this.h[(aVar.f5837a >> 4) & 63];
                            int i11 = aVar.f5840d;
                            aVar.f5840d = i11 + 1;
                            a2[i11] = this.h[(aVar.f5837a << 2) & 63];
                            if (this.h == e) {
                                int i12 = aVar.f5840d;
                                aVar.f5840d = i12 + 1;
                                a2[i12] = this.f5835c;
                                break;
                            }
                            break;
                        default:
                            throw new IllegalStateException("Impossible modulus " + aVar.h);
                    }
                    aVar.g += aVar.f5840d - i4;
                    if (this.f5836d > 0 && aVar.g > 0) {
                        System.arraycopy(this.j, 0, a2, aVar.f5840d, this.j.length);
                        aVar.f5840d += this.j.length;
                        return;
                    }
                    return;
                }
                return;
            }
            int i13 = i2;
            int i14 = 0;
            while (i14 < i3) {
                byte[] a3 = a(this.l, aVar);
                aVar.h = (aVar.h + 1) % 3;
                int i15 = i13 + 1;
                byte b2 = bArr[i13];
                int i16 = b2;
                if (b2 < 0) {
                    i16 = b2 + 256;
                }
                aVar.f5837a = (aVar.f5837a << 8) + (i16 == 1 ? 1 : 0);
                if (aVar.h == 0) {
                    int i17 = aVar.f5840d;
                    aVar.f5840d = i17 + 1;
                    a3[i17] = this.h[(aVar.f5837a >> 18) & 63];
                    int i18 = aVar.f5840d;
                    aVar.f5840d = i18 + 1;
                    a3[i18] = this.h[(aVar.f5837a >> 12) & 63];
                    int i19 = aVar.f5840d;
                    aVar.f5840d = i19 + 1;
                    a3[i19] = this.h[(aVar.f5837a >> 6) & 63];
                    int i20 = aVar.f5840d;
                    aVar.f5840d = i20 + 1;
                    a3[i20] = this.h[aVar.f5837a & 63];
                    aVar.g += 4;
                    if (this.f5836d > 0 && this.f5836d <= aVar.g) {
                        System.arraycopy(this.j, 0, a3, aVar.f5840d, this.j.length);
                        aVar.f5840d += this.j.length;
                        aVar.g = 0;
                    }
                }
                i14++;
                i13 = i15;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // ezvcard.util.a.a.a.a.a.b
    public void b(byte[] bArr, int i2, int i3, b.a aVar) {
        byte b2;
        if (!aVar.f) {
            if (i3 < 0) {
                aVar.f = true;
            }
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    break;
                }
                byte[] a2 = a(this.k, aVar);
                int i5 = i2 + 1;
                byte b3 = bArr[i2];
                if (b3 == this.f5835c) {
                    aVar.f = true;
                    break;
                }
                if (b3 >= 0) {
                    byte[] bArr2 = g;
                    if (b3 < bArr2.length && (b2 = bArr2[b3]) >= 0) {
                        aVar.h = (aVar.h + 1) % 4;
                        aVar.f5837a = (aVar.f5837a << 6) + b2;
                        if (aVar.h == 0) {
                            int i6 = aVar.f5840d;
                            aVar.f5840d = i6 + 1;
                            a2[i6] = (byte) ((aVar.f5837a >> 16) & 255);
                            int i7 = aVar.f5840d;
                            aVar.f5840d = i7 + 1;
                            a2[i7] = (byte) ((aVar.f5837a >> 8) & 255);
                            int i8 = aVar.f5840d;
                            aVar.f5840d = i8 + 1;
                            a2[i8] = (byte) (aVar.f5837a & 255);
                        }
                    }
                }
                i4++;
                i2 = i5;
            }
            if (aVar.f && aVar.h != 0) {
                byte[] a3 = a(this.k, aVar);
                switch (aVar.h) {
                    case 1:
                        return;
                    case 2:
                        aVar.f5837a >>= 4;
                        int i9 = aVar.f5840d;
                        aVar.f5840d = i9 + 1;
                        a3[i9] = (byte) (aVar.f5837a & 255);
                        return;
                    case 3:
                        aVar.f5837a >>= 2;
                        int i10 = aVar.f5840d;
                        aVar.f5840d = i10 + 1;
                        a3[i10] = (byte) ((aVar.f5837a >> 8) & 255);
                        int i11 = aVar.f5840d;
                        aVar.f5840d = i11 + 1;
                        a3[i11] = (byte) (aVar.f5837a & 255);
                        return;
                    default:
                        throw new IllegalStateException("Impossible modulus " + aVar.h);
                }
            }
        }
    }

    public static String a(byte[] bArr) {
        return f(a(bArr, false));
    }

    public static byte[] a(byte[] bArr, boolean z) {
        return a(bArr, z, false);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2) {
        return a(bArr, z, z2, (int) Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2, int i2) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = z ? new a(z2) : new a(0, f5832a, z2);
        long e2 = aVar.e(bArr);
        if (e2 <= ((long) i2)) {
            return aVar.c(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + e2 + ") than the specified maximum size of " + i2);
    }

    public static byte[] a(String str) {
        return new a().b(str);
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.util.a.a.a.a.a.b
    public boolean a(byte b2) {
        if (b2 >= 0) {
            byte[] bArr = this.i;
            return b2 < bArr.length && bArr[b2] != -1;
        }
    }
}
