package android.support.v4.graphics;

import android.graphics.Path;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import org.spongycastle.asn1.eac.EACTags;

/* compiled from: PathParser */
public class b {
    static float[] a(float[] fArr, int i, int i2) {
        if (i <= i2) {
            int length = fArr.length;
            if (i < 0 || i > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            float[] fArr2 = new float[i3];
            System.arraycopy(fArr, i, fArr2, 0, min);
            return fArr2;
        }
        throw new IllegalArgumentException();
    }

    public static Path a(String str) {
        Path path = new Path();
        C0012b[] b2 = b(str);
        if (b2 == null) {
            return null;
        }
        try {
            C0012b.a(b2, path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error in parsing " + str, e);
        }
    }

    public static C0012b[] b(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < str.length()) {
            int a2 = a(str, i);
            String trim = str.substring(i2, a2).trim();
            if (trim.length() > 0) {
                a(arrayList, trim.charAt(0), c(trim));
            }
            i2 = a2;
            i = a2 + 1;
        }
        if (i - i2 == 1 && i2 < str.length()) {
            a(arrayList, str.charAt(i2), new float[0]);
        }
        return (C0012b[]) arrayList.toArray(new C0012b[arrayList.size()]);
    }

    public static C0012b[] a(C0012b[] bVarArr) {
        if (bVarArr == null) {
            return null;
        }
        C0012b[] bVarArr2 = new C0012b[bVarArr.length];
        for (int i = 0; i < bVarArr.length; i++) {
            bVarArr2[i] = new C0012b(bVarArr[i]);
        }
        return bVarArr2;
    }

    public static boolean a(C0012b[] bVarArr, C0012b[] bVarArr2) {
        if (bVarArr == null || bVarArr2 == null || bVarArr.length != bVarArr2.length) {
            return false;
        }
        for (int i = 0; i < bVarArr.length; i++) {
            if (!(bVarArr[i].f547a == bVarArr2[i].f547a && bVarArr[i].f548b.length == bVarArr2[i].f548b.length)) {
                return false;
            }
        }
        return true;
    }

    public static void b(C0012b[] bVarArr, C0012b[] bVarArr2) {
        for (int i = 0; i < bVarArr2.length; i++) {
            bVarArr[i].f547a = bVarArr2[i].f547a;
            for (int i2 = 0; i2 < bVarArr2[i].f548b.length; i2++) {
                bVarArr[i].f548b[i2] = bVarArr2[i].f548b[i2];
            }
        }
    }

    private static int a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                return i;
            }
            i++;
        }
        return i;
    }

    private static void a(ArrayList<C0012b> arrayList, char c2, float[] fArr) {
        arrayList.add(new C0012b(c2, fArr));
    }

    /* access modifiers changed from: private */
    /* compiled from: PathParser */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        int f545a;

        /* renamed from: b  reason: collision with root package name */
        boolean f546b;

        a() {
        }
    }

    private static float[] c(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            a aVar = new a();
            int length = str.length();
            int i = 1;
            int i2 = 0;
            while (i < length) {
                a(str, i, aVar);
                int i3 = aVar.f545a;
                if (i < i3) {
                    fArr[i2] = Float.parseFloat(str.substring(i, i3));
                    i2++;
                }
                i = aVar.f546b ? i3 : i3 + 1;
            }
            return a(fArr, 0, i2);
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e A[LOOP:0: B:1:0x0007->B:20:0x003e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0041 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.String r8, int r9, android.support.v4.graphics.b.a r10) {
        /*
            r0 = 0
            r10.f546b = r0
            r1 = r9
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0007:
            int r5 = r8.length()
            if (r1 >= r5) goto L_0x0041
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L_0x0039
            r6 = 69
            if (r5 == r6) goto L_0x0037
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L_0x0037
            switch(r5) {
                case 44: goto L_0x0039;
                case 45: goto L_0x002c;
                case 46: goto L_0x0022;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0035
        L_0x0022:
            if (r3 != 0) goto L_0x0027
            r2 = 0
            r3 = 1
            goto L_0x003b
        L_0x0027:
            r10.f546b = r7
            r2 = 0
            r4 = 1
            goto L_0x003b
        L_0x002c:
            if (r1 == r9) goto L_0x0035
            if (r2 != 0) goto L_0x0035
            r10.f546b = r7
            r2 = 0
            r4 = 1
            goto L_0x003b
        L_0x0035:
            r2 = 0
            goto L_0x003b
        L_0x0037:
            r2 = 1
            goto L_0x003b
        L_0x0039:
            r2 = 0
            r4 = 1
        L_0x003b:
            if (r4 == 0) goto L_0x003e
            goto L_0x0041
        L_0x003e:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x0041:
            r10.f545a = r1
            return
            switch-data {44->0x0039, 45->0x002c, 46->0x0022, }
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.b.a(java.lang.String, int, android.support.v4.graphics.b$a):void");
    }

    /* renamed from: android.support.v4.graphics.b$b  reason: collision with other inner class name */
    /* compiled from: PathParser */
    public static class C0012b {

        /* renamed from: a  reason: collision with root package name */
        public char f547a;

        /* renamed from: b  reason: collision with root package name */
        public float[] f548b;

        C0012b(char c2, float[] fArr) {
            this.f547a = c2;
            this.f548b = fArr;
        }

        C0012b(C0012b bVar) {
            this.f547a = bVar.f547a;
            float[] fArr = bVar.f548b;
            this.f548b = b.a(fArr, 0, fArr.length);
        }

        public static void a(C0012b[] bVarArr, Path path) {
            float[] fArr = new float[6];
            char c2 = 'm';
            for (int i = 0; i < bVarArr.length; i++) {
                a(path, fArr, c2, bVarArr[i].f547a, bVarArr[i].f548b);
                c2 = bVarArr[i].f547a;
            }
        }

        public void a(C0012b bVar, C0012b bVar2, float f) {
            int i = 0;
            while (true) {
                float[] fArr = bVar.f548b;
                if (i < fArr.length) {
                    this.f548b[i] = (fArr[i] * (1.0f - f)) + (bVar2.f548b[i] * f);
                    i++;
                } else {
                    return;
                }
            }
        }

        private static void a(Path path, float[] fArr, char c2, char c3, float[] fArr2) {
            int i;
            int i2;
            float f;
            float f2;
            float f3;
            float f4;
            float f5;
            Path path2 = path;
            float f6 = fArr[0];
            float f7 = fArr[1];
            float f8 = fArr[2];
            float f9 = fArr[3];
            float f10 = fArr[4];
            float f11 = fArr[5];
            switch (c3) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                    i = 2;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case EACTags.SECURITY_SUPPORT_TEMPLATE /*{ENCODED_INT: 122}*/:
                    path.close();
                    path2.moveTo(f10, f11);
                    f6 = f10;
                    f8 = f6;
                    f7 = f11;
                    f9 = f7;
                    i = 2;
                    break;
                default:
                    i = 2;
                    break;
            }
            float f12 = f6;
            float f13 = f7;
            float f14 = f10;
            float f15 = f11;
            int i3 = 0;
            char c4 = c2;
            while (i3 < fArr2.length) {
                float f16 = BitmapDescriptorFactory.HUE_RED;
                switch (c3) {
                    case 'A':
                        i2 = i3;
                        int i4 = i2 + 5;
                        int i5 = i2 + 6;
                        a(path, f12, f13, fArr2[i4], fArr2[i5], fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != BitmapDescriptorFactory.HUE_RED, fArr2[i2 + 4] != BitmapDescriptorFactory.HUE_RED);
                        f12 = fArr2[i4];
                        f13 = fArr2[i5];
                        f9 = f13;
                        f8 = f12;
                        break;
                    case 'C':
                        i2 = i3;
                        int i6 = i2 + 2;
                        int i7 = i2 + 3;
                        int i8 = i2 + 4;
                        int i9 = i2 + 5;
                        path.cubicTo(fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i6], fArr2[i7], fArr2[i8], fArr2[i9]);
                        f12 = fArr2[i8];
                        float f17 = fArr2[i9];
                        float f18 = fArr2[i6];
                        float f19 = fArr2[i7];
                        f13 = f17;
                        f9 = f19;
                        f8 = f18;
                        break;
                    case 'H':
                        i2 = i3;
                        int i10 = i2 + 0;
                        path2.lineTo(fArr2[i10], f13);
                        f12 = fArr2[i10];
                        break;
                    case 'L':
                        i2 = i3;
                        int i11 = i2 + 0;
                        int i12 = i2 + 1;
                        path2.lineTo(fArr2[i11], fArr2[i12]);
                        f12 = fArr2[i11];
                        f13 = fArr2[i12];
                        break;
                    case 'M':
                        i2 = i3;
                        int i13 = i2 + 0;
                        f12 = fArr2[i13];
                        int i14 = i2 + 1;
                        f13 = fArr2[i14];
                        if (i2 <= 0) {
                            path2.moveTo(fArr2[i13], fArr2[i14]);
                            f15 = f13;
                            f14 = f12;
                            break;
                        } else {
                            path2.lineTo(fArr2[i13], fArr2[i14]);
                            break;
                        }
                    case 'Q':
                        i2 = i3;
                        int i15 = i2 + 0;
                        int i16 = i2 + 1;
                        int i17 = i2 + 2;
                        int i18 = i2 + 3;
                        path2.quadTo(fArr2[i15], fArr2[i16], fArr2[i17], fArr2[i18]);
                        float f20 = fArr2[i15];
                        float f21 = fArr2[i16];
                        f12 = fArr2[i17];
                        f13 = fArr2[i18];
                        f8 = f20;
                        f9 = f21;
                        break;
                    case 'S':
                        i2 = i3;
                        if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                            float f22 = (f12 * 2.0f) - f8;
                            f = (f13 * 2.0f) - f9;
                            f2 = f22;
                        } else {
                            f2 = f12;
                            f = f13;
                        }
                        int i19 = i2 + 0;
                        int i20 = i2 + 1;
                        int i21 = i2 + 2;
                        int i22 = i2 + 3;
                        path.cubicTo(f2, f, fArr2[i19], fArr2[i20], fArr2[i21], fArr2[i22]);
                        float f23 = fArr2[i19];
                        float f24 = fArr2[i20];
                        f12 = fArr2[i21];
                        f13 = fArr2[i22];
                        f8 = f23;
                        f9 = f24;
                        break;
                    case 'T':
                        float f25 = f13;
                        float f26 = f12;
                        i2 = i3;
                        if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                            f25 = (f25 * 2.0f) - f9;
                            f26 = (f26 * 2.0f) - f8;
                        }
                        int i23 = i2 + 0;
                        int i24 = i2 + 1;
                        path2.quadTo(f26, f25, fArr2[i23], fArr2[i24]);
                        f12 = fArr2[i23];
                        f8 = f26;
                        f9 = f25;
                        f13 = fArr2[i24];
                        break;
                    case 'V':
                        i2 = i3;
                        int i25 = i2 + 0;
                        path2 = path;
                        path2.lineTo(f12, fArr2[i25]);
                        f12 = f12;
                        f13 = fArr2[i25];
                        break;
                    case 'a':
                        int i26 = i3 + 5;
                        int i27 = i3 + 6;
                        i2 = i3;
                        a(path, f12, f13, fArr2[i26] + f12, fArr2[i27] + f13, fArr2[i3 + 0], fArr2[i3 + 1], fArr2[i3 + 2], fArr2[i3 + 3] != BitmapDescriptorFactory.HUE_RED, fArr2[i3 + 4] != BitmapDescriptorFactory.HUE_RED);
                        f12 += fArr2[i26];
                        f13 += fArr2[i27];
                        f9 = f13;
                        f8 = f12;
                        path2 = path;
                        break;
                    case 'c':
                        int i28 = i3 + 2;
                        int i29 = i3 + 3;
                        int i30 = i3 + 4;
                        int i31 = i3 + 5;
                        path.rCubicTo(fArr2[i3 + 0], fArr2[i3 + 1], fArr2[i28], fArr2[i29], fArr2[i30], fArr2[i31]);
                        float f27 = fArr2[i28] + f12;
                        float f28 = fArr2[i29] + f13;
                        f12 += fArr2[i30];
                        f13 += fArr2[i31];
                        f8 = f27;
                        f9 = f28;
                        i2 = i3;
                        break;
                    case 'h':
                        int i32 = i3 + 0;
                        path2.rLineTo(fArr2[i32], BitmapDescriptorFactory.HUE_RED);
                        f12 += fArr2[i32];
                        i2 = i3;
                        break;
                    case 'l':
                        int i33 = i3 + 0;
                        int i34 = i3 + 1;
                        path2.rLineTo(fArr2[i33], fArr2[i34]);
                        f12 += fArr2[i33];
                        f13 += fArr2[i34];
                        i2 = i3;
                        break;
                    case 'm':
                        int i35 = i3 + 0;
                        f12 += fArr2[i35];
                        int i36 = i3 + 1;
                        f13 += fArr2[i36];
                        if (i3 <= 0) {
                            path2.rMoveTo(fArr2[i35], fArr2[i36]);
                            f15 = f13;
                            f14 = f12;
                            i2 = i3;
                            break;
                        } else {
                            path2.rLineTo(fArr2[i35], fArr2[i36]);
                            i2 = i3;
                            break;
                        }
                    case 'q':
                        int i37 = i3 + 0;
                        int i38 = i3 + 1;
                        int i39 = i3 + 2;
                        int i40 = i3 + 3;
                        path2.rQuadTo(fArr2[i37], fArr2[i38], fArr2[i39], fArr2[i40]);
                        float f29 = fArr2[i37] + f12;
                        float f30 = fArr2[i38] + f13;
                        f12 += fArr2[i39];
                        f13 += fArr2[i40];
                        f8 = f29;
                        f9 = f30;
                        i2 = i3;
                        break;
                    case 's':
                        if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                            float f31 = f12 - f8;
                            f3 = f13 - f9;
                            f4 = f31;
                        } else {
                            f4 = BitmapDescriptorFactory.HUE_RED;
                            f3 = BitmapDescriptorFactory.HUE_RED;
                        }
                        int i41 = i3 + 0;
                        int i42 = i3 + 1;
                        int i43 = i3 + 2;
                        int i44 = i3 + 3;
                        path.rCubicTo(f4, f3, fArr2[i41], fArr2[i42], fArr2[i43], fArr2[i44]);
                        float f32 = fArr2[i41] + f12;
                        float f33 = fArr2[i42] + f13;
                        f12 += fArr2[i43];
                        f13 += fArr2[i44];
                        f8 = f32;
                        f9 = f33;
                        i2 = i3;
                        break;
                    case 't':
                        if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                            f16 = f12 - f8;
                            f5 = f13 - f9;
                        } else {
                            f5 = BitmapDescriptorFactory.HUE_RED;
                        }
                        int i45 = i3 + 0;
                        int i46 = i3 + 1;
                        path2.rQuadTo(f16, f5, fArr2[i45], fArr2[i46]);
                        float f34 = f16 + f12;
                        float f35 = f5 + f13;
                        f12 += fArr2[i45];
                        f13 += fArr2[i46];
                        f9 = f35;
                        i2 = i3;
                        f8 = f34;
                        break;
                    case 'v':
                        int i47 = i3 + 0;
                        path2.rLineTo(BitmapDescriptorFactory.HUE_RED, fArr2[i47]);
                        f13 += fArr2[i47];
                        i2 = i3;
                        break;
                    default:
                        i2 = i3;
                        f13 = f13;
                        break;
                }
                i3 = i2 + i;
                c4 = c3;
            }
            fArr[0] = f12;
            fArr[1] = f13;
            fArr[2] = f8;
            fArr[3] = f9;
            fArr[4] = f14;
            fArr[5] = f15;
        }

        private static void a(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d2;
            double d3;
            double radians = Math.toRadians((double) f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d4 = (double) f;
            double d5 = d4 * cos;
            double d6 = (double) f2;
            double d7 = (double) f5;
            double d8 = (d5 + (d6 * sin)) / d7;
            double d9 = (double) f6;
            double d10 = ((((double) (-f)) * sin) + (d6 * cos)) / d9;
            double d11 = (double) f4;
            double d12 = ((((double) f3) * cos) + (d11 * sin)) / d7;
            double d13 = ((((double) (-f3)) * sin) + (d11 * cos)) / d9;
            double d14 = d8 - d12;
            double d15 = d10 - d13;
            double d16 = (d8 + d12) / 2.0d;
            double d17 = (d10 + d13) / 2.0d;
            double d18 = (d14 * d14) + (d15 * d15);
            if (d18 == 0.0d) {
                Log.w("PathParser", " Points are coincident");
                return;
            }
            double d19 = (1.0d / d18) - 0.25d;
            if (d19 < 0.0d) {
                Log.w("PathParser", "Points are too far apart " + d18);
                float sqrt = (float) (Math.sqrt(d18) / 1.99999d);
                a(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d19);
            double d20 = d14 * sqrt2;
            double d21 = sqrt2 * d15;
            if (z == z2) {
                d3 = d16 - d21;
                d2 = d17 + d20;
            } else {
                d3 = d16 + d21;
                d2 = d17 - d20;
            }
            double atan2 = Math.atan2(d10 - d2, d8 - d3);
            double atan22 = Math.atan2(d13 - d2, d12 - d3) - atan2;
            int i = (atan22 > 0.0d ? 1 : (atan22 == 0.0d ? 0 : -1));
            if (z2 != (i >= 0)) {
                atan22 = i > 0 ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            double d22 = d3 * d7;
            double d23 = d2 * d9;
            a(path, (d22 * cos) - (d23 * sin), (d22 * sin) + (d23 * cos), d7, d9, d4, d6, radians, atan2, atan22);
        }

        private static void a(Path path, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
            double d11 = d4;
            int ceil = (int) Math.ceil(Math.abs((d10 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d8);
            double sin = Math.sin(d8);
            double cos2 = Math.cos(d9);
            double sin2 = Math.sin(d9);
            double d12 = -d11;
            double d13 = d12 * cos;
            double d14 = d5 * sin;
            double d15 = (d13 * sin2) - (d14 * cos2);
            double d16 = d12 * sin;
            double d17 = d5 * cos;
            double d18 = (sin2 * d16) + (cos2 * d17);
            double d19 = d10 / ((double) ceil);
            double d20 = d6;
            double d21 = d7;
            double d22 = d18;
            double d23 = d15;
            int i = 0;
            double d24 = d9;
            while (i < ceil) {
                double d25 = d24 + d19;
                double sin3 = Math.sin(d25);
                double cos3 = Math.cos(d25);
                double d26 = (d2 + ((d11 * cos) * cos3)) - (d14 * sin3);
                double d27 = d3 + (d11 * sin * cos3) + (d17 * sin3);
                double d28 = (d13 * sin3) - (d14 * cos3);
                double d29 = (sin3 * d16) + (cos3 * d17);
                double d30 = d25 - d24;
                double tan = Math.tan(d30 / 2.0d);
                double sin4 = (Math.sin(d30) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
                path.rLineTo(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                path.cubicTo((float) (d20 + (d23 * sin4)), (float) (d21 + (d22 * sin4)), (float) (d26 - (sin4 * d28)), (float) (d27 - (sin4 * d29)), (float) d26, (float) d27);
                i++;
                d19 = d19;
                ceil = ceil;
                sin = sin;
                d21 = d27;
                d16 = d16;
                d24 = d25;
                d22 = d29;
                d23 = d28;
                cos = cos;
                d11 = d4;
                d20 = d26;
            }
        }
    }
}
