package com.horcrux.svg;

import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import ezvcard.property.Gender;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.spongycastle.asn1.eac.EACTags;

/* access modifiers changed from: package-private */
/* compiled from: PropHelper */
public class w {

    /* renamed from: a  reason: collision with root package name */
    private static final Pattern f4669a = Pattern.compile("^(-?\\d+(?:\\.\\d+)?)%$");

    static int a(ReadableArray readableArray, float[] fArr, float f) {
        int size = readableArray.size();
        if (size != 6) {
            return size;
        }
        fArr[0] = (float) readableArray.getDouble(0);
        fArr[1] = (float) readableArray.getDouble(2);
        fArr[2] = ((float) readableArray.getDouble(4)) * f;
        fArr[3] = (float) readableArray.getDouble(1);
        fArr[4] = (float) readableArray.getDouble(3);
        fArr[5] = ((float) readableArray.getDouble(5)) * f;
        return 6;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static double a(java.lang.String r7, double r8, double r10, double r12, double r14) {
        /*
        // Method dump skipped, instructions count: 246
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.w.a(java.lang.String, double, double, double, double):double");
    }

    static boolean a(String str) {
        return f4669a.matcher(str).matches();
    }

    /* compiled from: PropHelper */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final Pattern f4670a = Pattern.compile("[a-df-z]|[\\-+]?(?:[\\d.]e[\\-+]?|[^\\s\\-+,a-z])+", 2);

        /* renamed from: b  reason: collision with root package name */
        private static final Pattern f4671b = Pattern.compile("(\\.\\d+)(?=-?\\.)");

        /* renamed from: c  reason: collision with root package name */
        private Matcher f4672c;

        /* renamed from: d  reason: collision with root package name */
        private Path f4673d;
        private final String e;
        private float f = BitmapDescriptorFactory.HUE_RED;
        private float g = BitmapDescriptorFactory.HUE_RED;
        private float h;
        private float i;
        private float j = BitmapDescriptorFactory.HUE_RED;
        private float k = BitmapDescriptorFactory.HUE_RED;
        private float l = 1.0f;
        private boolean m = true;
        private boolean n = false;
        private String o;
        private String p;
        private WritableArray q;
        private WritableMap r;

        a(String str, float f2) {
            this.l = f2;
            this.e = str;
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        private void a(String str) {
            char c2;
            switch (str.hashCode()) {
                case 65:
                    if (str.equals("A")) {
                        c2 = 17;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 67:
                    if (str.equals("C")) {
                        c2 = '\t';
                        break;
                    }
                    c2 = 65535;
                    break;
                case 72:
                    if (str.equals("H")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 76:
                    if (str.equals("L")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 77:
                    if (str.equals(Gender.MALE)) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 81:
                    if (str.equals("Q")) {
                        c2 = '\r';
                        break;
                    }
                    c2 = 65535;
                    break;
                case 83:
                    if (str.equals("S")) {
                        c2 = 11;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 84:
                    if (str.equals("T")) {
                        c2 = 15;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 86:
                    if (str.equals("V")) {
                        c2 = 7;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 90:
                    if (str.equals("Z")) {
                        c2 = 18;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 97:
                    if (str.equals("a")) {
                        c2 = 16;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 99:
                    if (str.equals("c")) {
                        c2 = '\b';
                        break;
                    }
                    c2 = 65535;
                    break;
                case 104:
                    if (str.equals("h")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 108:
                    if (str.equals("l")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 109:
                    if (str.equals("m")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 113:
                    if (str.equals("q")) {
                        c2 = '\f';
                        break;
                    }
                    c2 = 65535;
                    break;
                case 115:
                    if (str.equals("s")) {
                        c2 = '\n';
                        break;
                    }
                    c2 = 65535;
                    break;
                case 116:
                    if (str.equals("t")) {
                        c2 = 14;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 118:
                    if (str.equals("v")) {
                        c2 = 6;
                        break;
                    }
                    c2 = 65535;
                    break;
                case EACTags.SECURITY_SUPPORT_TEMPLATE /*{ENCODED_INT: 122}*/:
                    if (str.equals("z")) {
                        c2 = 19;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            switch (c2) {
                case 0:
                    b(c(), c());
                    break;
                case 1:
                    c(c(), c());
                    break;
                case 2:
                    d(c(), c());
                    break;
                case 3:
                    e(c(), c());
                    break;
                case 4:
                    d(c(), BitmapDescriptorFactory.HUE_RED);
                    break;
                case 5:
                    e(c(), this.g);
                    break;
                case 6:
                    d(BitmapDescriptorFactory.HUE_RED, c());
                    break;
                case 7:
                    e(this.f, c());
                    break;
                case '\b':
                    a(c(), c(), c(), c(), c(), c());
                    break;
                case '\t':
                    b(c(), c(), c(), c(), c(), c());
                    break;
                case '\n':
                    a(c(), c(), c(), c());
                    break;
                case 11:
                    b(c(), c(), c(), c());
                    break;
                case '\f':
                    c(c(), c(), c(), c());
                    break;
                case '\r':
                    d(c(), c(), c(), c());
                    break;
                case 14:
                    f(c(), c());
                    break;
                case 15:
                    g(c(), c());
                    break;
                case 16:
                    a(c(), c(), c(), b(), b(), c(), c());
                    break;
                case 17:
                    b(c(), c(), c(), b(), b(), c(), c());
                    break;
                case 18:
                case 19:
                    d();
                    break;
                default:
                    this.p = str;
                    a(this.o);
                    return;
            }
            this.o = str;
            if (str.equals("m")) {
                this.o = "l";
            } else if (str.equals(Gender.MALE)) {
                this.o = "L";
            }
        }

        public Path a() {
            this.f4673d = new Path();
            this.q = Arguments.createArray();
            this.f4672c = f4670a.matcher(f4671b.matcher(this.e).replaceAll("$1,"));
            while (this.f4672c.find() && this.m) {
                a(this.f4672c.group());
            }
            return this.f4673d;
        }

        private WritableMap a(float f2, float f3) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", (double) (f2 * this.l));
            createMap.putDouble("y", (double) (f3 * this.l));
            return createMap;
        }

        private WritableMap a(WritableMap writableMap) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", writableMap.getDouble("x"));
            createMap.putDouble("y", writableMap.getDouble("y"));
            return createMap;
        }

        private boolean b() {
            if (this.f4672c.find()) {
                return this.f4672c.group().equals("1");
            }
            this.m = false;
            this.f4673d = new Path();
            return false;
        }

        private float c() {
            String str = this.p;
            if (str != null) {
                this.p = null;
                return Float.parseFloat(str);
            } else if (this.f4672c.find()) {
                return Float.parseFloat(this.f4672c.group());
            } else {
                this.m = false;
                this.f4673d = new Path();
                return BitmapDescriptorFactory.HUE_RED;
            }
        }

        private void b(float f2, float f3) {
            c(f2 + this.f, f3 + this.g);
        }

        private void c(float f2, float f3) {
            this.f = f2;
            this.j = f2;
            this.g = f3;
            this.k = f3;
            Path path = this.f4673d;
            float f4 = this.l;
            path.moveTo(f2 * f4, f4 * f3);
            this.r = a(f2, f3);
            WritableArray createArray = Arguments.createArray();
            createArray.pushMap(a(f2, f3));
            this.q.pushArray(createArray);
        }

        private void d(float f2, float f3) {
            e(f2 + this.f, f3 + this.g);
        }

        private void e(float f2, float f3) {
            e();
            this.f = f2;
            this.j = f2;
            this.g = f3;
            this.k = f3;
            Path path = this.f4673d;
            float f4 = this.l;
            path.lineTo(f2 * f4, f4 * f3);
            WritableArray createArray = Arguments.createArray();
            createArray.pushMap(a(f2, f3));
            createArray.pushMap(a(f2, f3));
            createArray.pushMap(a(f2, f3));
            this.q.pushArray(createArray);
        }

        private void a(float f2, float f3, float f4, float f5, float f6, float f7) {
            float f8 = this.f;
            float f9 = f2 + f8;
            float f10 = this.g;
            b(f9, f3 + f10, f4 + f8, f5 + f10, f6 + f8, f7 + f10);
        }

        private void b(float f2, float f3, float f4, float f5, float f6, float f7) {
            this.j = f4;
            this.k = f5;
            c(f2, f3, f4, f5, f6, f7);
        }

        private void c(float f2, float f3, float f4, float f5, float f6, float f7) {
            e();
            this.f = f6;
            this.g = f7;
            Path path = this.f4673d;
            float f8 = this.l;
            path.cubicTo(f2 * f8, f3 * f8, f4 * f8, f5 * f8, f6 * f8, f7 * f8);
            WritableArray createArray = Arguments.createArray();
            createArray.pushMap(a(f2, f3));
            createArray.pushMap(a(f4, f5));
            createArray.pushMap(a(f6, f7));
            this.q.pushArray(createArray);
        }

        private void a(float f2, float f3, float f4, float f5) {
            float f6 = this.f;
            float f7 = this.g;
            b(f2 + f6, f3 + f7, f4 + f6, f5 + f7);
        }

        private void b(float f2, float f3, float f4, float f5) {
            this.j = f2;
            this.k = f3;
            c((this.f * 2.0f) - this.j, (this.g * 2.0f) - this.k, f2, f3, f4, f5);
        }

        private void c(float f2, float f3, float f4, float f5) {
            float f6 = this.f;
            float f7 = this.g;
            d(f2 + f6, f3 + f7, f4 + f6, f5 + f7);
        }

        private void d(float f2, float f3, float f4, float f5) {
            this.j = f2;
            this.k = f3;
            float f6 = f2 * 2.0f;
            float f7 = f3 * 2.0f;
            float f8 = (this.f + f6) / 3.0f;
            float f9 = (this.g + f7) / 3.0f;
            c(f8, f9, (f4 + f6) / 3.0f, (f5 + f7) / 3.0f, f4, f5);
        }

        private void f(float f2, float f3) {
            g(f2 + this.f, f3 + this.g);
        }

        private void g(float f2, float f3) {
            d((this.f * 2.0f) - this.j, (this.g * 2.0f) - this.k, f2, f3);
        }

        private void a(float f2, float f3, float f4, boolean z, boolean z2, float f5, float f6) {
            b(f2, f3, f4, z, z2, f5 + this.f, f6 + this.g);
        }

        private void b(float f2, float f3, float f4, boolean z, boolean z2, float f5, float f6) {
            float f7;
            float f8;
            float f9;
            float f10 = this.f;
            float f11 = this.g;
            float abs = Math.abs(f3 == BitmapDescriptorFactory.HUE_RED ? f2 == BitmapDescriptorFactory.HUE_RED ? f6 - f11 : f2 : f3);
            float abs2 = Math.abs(f2 == BitmapDescriptorFactory.HUE_RED ? f5 - f10 : f2);
            if (abs2 == BitmapDescriptorFactory.HUE_RED || abs == BitmapDescriptorFactory.HUE_RED || (f5 == f10 && f6 == f11)) {
                e(f5, f6);
                return;
            }
            float radians = (float) Math.toRadians((double) f4);
            double d2 = (double) radians;
            float cos = (float) Math.cos(d2);
            float sin = (float) Math.sin(d2);
            float f12 = f5 - f10;
            float f13 = f6 - f11;
            float f14 = ((cos * f12) / 2.0f) + ((sin * f13) / 2.0f);
            float f15 = -sin;
            float f16 = ((f15 * f12) / 2.0f) + ((cos * f13) / 2.0f);
            float f17 = abs2 * abs2;
            float f18 = f17 * abs * abs;
            float f19 = abs * abs * f14 * f14;
            float f20 = f17 * f16 * f16;
            float f21 = (f18 - f20) - f19;
            if (f21 < BitmapDescriptorFactory.HUE_RED) {
                f7 = f15;
                float sqrt = (float) Math.sqrt((double) (1.0f - (f21 / f18)));
                abs2 *= sqrt;
                abs *= sqrt;
                f8 = f13 / 2.0f;
                f9 = f12 / 2.0f;
            } else {
                f7 = f15;
                float sqrt2 = (float) Math.sqrt((double) (f21 / (f20 + f19)));
                if (z == z2) {
                    sqrt2 = -sqrt2;
                }
                float f22 = (((-sqrt2) * f16) * abs2) / abs;
                float f23 = ((sqrt2 * f14) * abs) / abs2;
                f9 = ((cos * f22) - (sin * f23)) + (f12 / 2.0f);
                f8 = (f13 / 2.0f) + (f22 * sin) + (f23 * cos);
            }
            float f24 = cos / abs2;
            float f25 = sin / abs2;
            float f26 = f7 / abs;
            float f27 = cos / abs;
            float f28 = -f9;
            float f29 = -f8;
            float atan2 = (float) Math.atan2((double) ((f26 * f28) + (f27 * f29)), (double) ((f28 * f24) + (f29 * f25)));
            float f30 = f12 - f9;
            float f31 = f13 - f8;
            float atan22 = (float) Math.atan2((double) ((f26 * f30) + (f27 * f31)), (double) ((f24 * f30) + (f25 * f31)));
            float f32 = f9 + f10;
            float f33 = f8 + f11;
            float f34 = f12 + f10;
            float f35 = f13 + f11;
            e();
            this.j = f34;
            this.f = f34;
            this.k = f35;
            this.g = f35;
            if (abs2 == abs && radians == BitmapDescriptorFactory.HUE_RED) {
                float degrees = (float) Math.toDegrees((double) atan2);
                float abs3 = Math.abs((degrees - ((float) Math.toDegrees((double) atan22))) % 360.0f);
                if (z) {
                    if (abs3 < 180.0f) {
                        abs3 = 360.0f - abs3;
                    }
                } else if (abs3 > 180.0f) {
                    abs3 = 360.0f - abs3;
                }
                if (!z2) {
                    abs3 = -abs3;
                }
                float f36 = this.l;
                this.f4673d.arcTo(new RectF((f32 - abs2) * f36, (f33 - abs2) * f36, (f32 + abs2) * f36, (f33 + abs2) * f36), degrees, abs3);
                return;
            }
            a(f32, f33, abs2, abs, atan2, atan22, z2, radians);
        }

        private void d() {
            if (this.n) {
                this.f = this.h;
                this.g = this.i;
                this.n = false;
                this.f4673d.close();
                WritableArray createArray = Arguments.createArray();
                createArray.pushMap(a(this.r));
                createArray.pushMap(a(this.r));
                createArray.pushMap(a(this.r));
                this.q.pushArray(createArray);
            }
        }

        private void a(float f2, float f3, float f4, float f5, float f6, float f7, boolean z, float f8) {
            float f9 = f6;
            double d2 = (double) f8;
            float cos = (float) Math.cos(d2);
            float sin = (float) Math.sin(d2);
            float f10 = cos * f4;
            float f11 = (-sin) * f5;
            float f12 = sin * f4;
            float f13 = cos * f5;
            float f14 = f7 - f9;
            if (f14 < BitmapDescriptorFactory.HUE_RED && z) {
                f14 = (float) (((double) f14) + 6.283185307179586d);
            } else if (f14 > BitmapDescriptorFactory.HUE_RED && !z) {
                f14 = (float) (((double) f14) - 6.283185307179586d);
            }
            int ceil = (int) Math.ceil(Math.abs(a(((double) f14) / 1.5707963267948966d, 4)));
            float f15 = f14 / ((float) ceil);
            float tan = (float) (Math.tan((double) (f15 / 4.0f)) * 1.3333333333333333d);
            double d3 = (double) f9;
            float cos2 = (float) Math.cos(d3);
            float sin2 = (float) Math.sin(d3);
            int i2 = 0;
            while (i2 < ceil) {
                float f16 = cos2 - (tan * sin2);
                float f17 = sin2 + (cos2 * tan);
                float f18 = f9 + f15;
                double d4 = (double) f18;
                float cos3 = (float) Math.cos(d4);
                float sin3 = (float) Math.sin(d4);
                float f19 = (tan * sin3) + cos3;
                float f20 = sin3 - (tan * cos3);
                Path path = this.f4673d;
                float f21 = this.l;
                path.cubicTo((f2 + (f10 * f16) + (f11 * f17)) * f21, (f3 + (f16 * f12) + (f17 * f13)) * f21, (f2 + (f10 * f19) + (f11 * f20)) * f21, (f3 + (f19 * f12) + (f20 * f13)) * f21, (f2 + (f10 * cos3) + (f11 * sin3)) * f21, (f3 + (f12 * cos3) + (f13 * sin3)) * f21);
                i2++;
                f9 = f18;
                f12 = f12;
                sin2 = sin3;
                ceil = ceil;
                cos2 = cos3;
                f15 = f15;
            }
        }

        private void e() {
            if (!this.n) {
                this.h = this.f;
                this.i = this.g;
                this.n = true;
            }
        }

        private double a(double d2, int i2) {
            double pow = Math.pow(10.0d, (double) i2);
            return ((double) Math.round(d2 * pow)) / pow;
        }
    }
}
