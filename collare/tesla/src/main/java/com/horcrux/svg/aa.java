package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.os.Build;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.w;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.analytics.FirebaseAnalytics;

/* access modifiers changed from: package-private */
/* compiled from: TSpanShadowNode */
public class aa extends aj {

    /* renamed from: b  reason: collision with root package name */
    String f4559b;
    private Path e;
    private ag f;

    aa() {
    }

    @a(a = FirebaseAnalytics.b.CONTENT)
    public void setContent(String str) {
        this.f4559b = str;
        markUpdated();
    }

    @Override // com.horcrux.svg.aj, com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public void draw(Canvas canvas, Paint paint, float f2) {
        if (this.f4559b != null) {
            b(canvas, paint, f2);
            return;
        }
        clip(canvas, paint);
        a(canvas, paint, f2);
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.aj
    public void e() {
        this.e = null;
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.aj, com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = this.e;
        if (path != null) {
            return path;
        }
        if (this.f4559b == null) {
            return a(canvas, paint);
        }
        f();
        c();
        this.e = a(this.f4559b, paint, canvas);
        d();
        return this.e;
    }

    private Path a(String str, Paint paint, Canvas canvas) {
        double d2;
        PathMeasure pathMeasure;
        boolean z;
        n nVar;
        m mVar;
        PathMeasure pathMeasure2;
        float[] fArr;
        double d3;
        double d4;
        double d5;
        boolean z2;
        int i;
        double d6;
        double d7;
        double d8;
        double d9;
        boolean z3;
        String str2;
        String str3;
        int i2;
        double d10;
        double d11;
        m mVar2;
        Matrix matrix;
        double d12;
        double d13;
        float[] fArr2;
        double d14;
        int i3;
        Path path;
        int i4;
        Matrix matrix2;
        Matrix matrix3;
        float[] fArr3;
        PathMeasure pathMeasure3;
        n nVar2;
        double d15;
        double d16;
        double d17;
        double d18;
        Path path2;
        int i5;
        String str4;
        int i6;
        Paint paint2 = paint;
        int length = str.length();
        Path path3 = new Path();
        if (length == 0) {
            return path3;
        }
        boolean z4 = this.f != null;
        if (z4) {
            PathMeasure pathMeasure4 = new PathMeasure(this.f.i(), false);
            double length2 = (double) pathMeasure4.getLength();
            boolean isClosed = pathMeasure4.isClosed();
            if (length2 == 0.0d) {
                return path3;
            }
            pathMeasure = pathMeasure4;
            d2 = length2;
            z = isClosed;
        } else {
            pathMeasure = null;
            d2 = 0.0d;
            z = false;
        }
        m b2 = b();
        i a2 = b2.a();
        a(paint2, a2);
        n nVar3 = new n(paint2);
        boolean[] zArr = new boolean[length];
        char[] charArray = str.toCharArray();
        Path path4 = path3;
        double d19 = a2.j;
        double d20 = a2.k;
        double d21 = a2.l;
        boolean z5 = !a2.m;
        boolean z6 = d21 == 0.0d && a2.g == k.normal;
        if (Build.VERSION.SDK_INT >= 21) {
            String str5 = "'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk'," + "'kern', ";
            if (z6) {
                paint2.setFontFeatureSettings(str5 + "'hlig', 'cala', " + a2.f);
            } else {
                paint2.setFontFeatureSettings(str5 + "'liga' 0, 'clig' 0, 'dlig' 0, 'hlig' 0, 'cala' 0, " + a2.f);
            }
        }
        ReadableMap readableMap = a2.f4621d;
        float[] fArr4 = new float[length];
        paint2.getTextWidths(str, fArr4);
        ab abVar = a2.h;
        double measureText = (double) paint2.measureText(str);
        double a3 = a(abVar, measureText);
        double c2 = b2.c();
        char c3 = 65535;
        if (z4) {
            boolean z7 = this.f.g() == af.sharp;
            int i7 = this.f.f() == ah.right ? -1 : 1;
            d3 = measureText;
            fArr = fArr4;
            pathMeasure2 = pathMeasure;
            mVar = b2;
            nVar = nVar3;
            double a4 = a(this.f.h(), d2, c2);
            a3 += a4;
            if (z) {
                double d22 = a4 + (abVar == ab.middle ? -(d2 / 2.0d) : 0.0d);
                d5 = d22 + d2;
                d4 = d22;
                z2 = z7;
                i = i7;
            } else {
                d5 = d2;
                d4 = 0.0d;
                z2 = z7;
                i = i7;
            }
        } else {
            d3 = measureText;
            fArr = fArr4;
            mVar = b2;
            nVar = nVar3;
            pathMeasure2 = pathMeasure;
            d5 = d2;
            d4 = 0.0d;
            i = 1;
            z2 = false;
        }
        if (this.f4588c != null) {
            d7 = d2;
            d6 = d4;
            double a5 = w.a(this.f4588c, (double) canvas.getWidth(), 0.0d, (double) this.mScale, c2);
            if (a5 < 0.0d) {
                throw new IllegalArgumentException("Negative textLength value");
            } else if (AnonymousClass1.f4560a[this.f4589d.ordinal()] != 2) {
                d9 = d21 + ((a5 - d3) / ((double) (length - 1)));
                d8 = 1.0d;
            } else {
                d8 = a5 / d3;
                d9 = d21;
            }
        } else {
            d6 = d4;
            d7 = d2;
            d9 = d21;
            d8 = 1.0d;
        }
        double d23 = (double) i;
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        double d24 = d8 * d23;
        double d25 = (double) fontMetrics.descent;
        int i8 = i;
        double d26 = ((double) fontMetrics.leading) + d25;
        double d27 = (double) ((-fontMetrics.ascent) + fontMetrics.leading);
        double d28 = d23;
        double d29 = (double) (-fontMetrics.top);
        double d30 = d29 + d26;
        String k = k();
        a j = j();
        if (j != null) {
            switch (j) {
                case textBottom:
                case afterEdge:
                case textAfterEdge:
                    d26 = -d25;
                    break;
                case alphabetic:
                    d26 = 0.0d;
                    break;
                case ideographic:
                    d26 = -d25;
                    break;
                case middle:
                    Rect rect = new Rect();
                    paint2.getTextBounds("x", 0, 1, rect);
                    d26 = (double) (rect.height() / 2);
                    break;
                case central:
                    d26 = (d27 - d25) / 2.0d;
                    break;
                case mathematical:
                    d26 = 0.5d * d27;
                    break;
                case hanging:
                    d26 = 0.8d * d27;
                    break;
                case textTop:
                case beforeEdge:
                case textBeforeEdge:
                    d26 = d27;
                    break;
                case bottom:
                    break;
                case center:
                    d26 = d30 / 2.0d;
                    break;
                case top:
                    d26 = d29;
                    break;
                default:
                    d26 = 0.0d;
                    break;
            }
        } else {
            d26 = 0.0d;
        }
        if (!(k == null || k.isEmpty() || (i6 = AnonymousClass1.f4561b[j.ordinal()]) == 14 || i6 == 16)) {
            int hashCode = k.hashCode();
            if (hashCode != -1720785339) {
                if (hashCode != 114240) {
                    if (hashCode == 109801339 && k.equals("super")) {
                        c3 = 1;
                    }
                } else if (k.equals("sub")) {
                    c3 = 0;
                }
            } else if (k.equals("baseline")) {
                c3 = 2;
            }
            switch (c3) {
                case 0:
                    if (readableMap != null && readableMap.hasKey("tables") && readableMap.hasKey("unitsPerEm")) {
                        int i9 = readableMap.getInt("unitsPerEm");
                        ReadableMap map = readableMap.getMap("tables");
                        if (map.hasKey("os2")) {
                            ReadableMap map2 = map.getMap("os2");
                            if (map2.hasKey("ySubscriptYOffset")) {
                                d26 += ((((double) this.mScale) * c2) * map2.getDouble("ySubscriptYOffset")) / ((double) i9);
                                break;
                            }
                        }
                    }
                    break;
                case 1:
                    if (readableMap != null && readableMap.hasKey("tables") && readableMap.hasKey("unitsPerEm")) {
                        int i10 = readableMap.getInt("unitsPerEm");
                        ReadableMap map3 = readableMap.getMap("tables");
                        if (map3.hasKey("os2")) {
                            ReadableMap map4 = map3.getMap("os2");
                            if (map4.hasKey("ySuperscriptYOffset")) {
                                d26 -= ((((double) this.mScale) * c2) * map4.getDouble("ySuperscriptYOffset")) / ((double) i10);
                                break;
                            }
                        }
                    }
                    break;
                case 2:
                    break;
                default:
                    d26 -= w.a(k, ((double) this.mScale) * c2, 0.0d, (double) this.mScale, c2);
                    break;
            }
        }
        Matrix matrix4 = new Matrix();
        Matrix matrix5 = new Matrix();
        Matrix matrix6 = new Matrix();
        float[] fArr5 = new float[9];
        float[] fArr6 = new float[9];
        int i11 = 0;
        while (i11 < length) {
            char c4 = charArray[i11];
            String valueOf = String.valueOf(c4);
            boolean z8 = zArr[i11];
            float f2 = BitmapDescriptorFactory.HUE_RED;
            if (z8) {
                str2 = "";
                z3 = false;
            } else {
                String str6 = valueOf;
                int i12 = i11;
                z3 = false;
                while (true) {
                    i12++;
                    if (i12 >= length) {
                        str4 = str6;
                    } else if (fArr[i12] > f2) {
                        str4 = str6;
                    } else {
                        String str7 = str6 + String.valueOf(charArray[i12]);
                        if (t.a(paint2, str7)) {
                            zArr[i12] = true;
                            str6 = str7;
                            z3 = true;
                        } else {
                            str6 = str6;
                        }
                        f2 = BitmapDescriptorFactory.HUE_RED;
                    }
                }
                str2 = str4;
            }
            double measureText2 = ((double) paint2.measureText(str2)) * d8;
            if (z5) {
                str3 = str2;
                i2 = i11;
                d19 = (((double) fArr[i11]) * d8) - measureText2;
            } else {
                str3 = str2;
                i2 = i11;
            }
            double d31 = (c4 == ' ' ? d20 : 0.0d) + d9 + measureText2;
            if (z8) {
                d10 = d9;
                d11 = 0.0d;
                mVar2 = mVar;
            } else {
                mVar2 = mVar;
                d11 = d19 + d31;
                d10 = d9;
            }
            double a6 = mVar2.a(d11);
            double d32 = mVar2.d();
            double e2 = mVar2.e();
            double f3 = mVar2.f();
            double g = mVar2.g();
            if (z8) {
                d12 = d26;
                matrix = matrix4;
                mVar = mVar2;
                fArr2 = fArr6;
                fArr3 = fArr5;
                matrix3 = matrix6;
                matrix2 = matrix5;
                path = path4;
                i3 = i2;
                d14 = d24;
                pathMeasure3 = pathMeasure2;
                nVar2 = nVar;
                d15 = d7;
                i4 = i8;
                d13 = d28;
            } else {
                double d33 = measureText2 * d28;
                double d34 = (a3 + ((a6 + e2) * d28)) - (d31 * d28);
                if (z4) {
                    double d35 = d34 + d33;
                    double d36 = d33 / 2.0d;
                    double d37 = d34 + d36;
                    if (d37 > d5) {
                        d12 = d26;
                        matrix = matrix4;
                        mVar = mVar2;
                        fArr2 = fArr6;
                        fArr3 = fArr5;
                        matrix3 = matrix6;
                        matrix2 = matrix5;
                        path = path4;
                        i3 = i2;
                        d14 = d24;
                        pathMeasure3 = pathMeasure2;
                        nVar2 = nVar;
                        d15 = d7;
                        i4 = i8;
                        d13 = d28;
                    } else if (d37 < d6) {
                        d12 = d26;
                        matrix = matrix4;
                        mVar = mVar2;
                        fArr2 = fArr6;
                        fArr3 = fArr5;
                        matrix3 = matrix6;
                        matrix2 = matrix5;
                        path = path4;
                        i3 = i2;
                        d14 = d24;
                        pathMeasure3 = pathMeasure2;
                        nVar2 = nVar;
                        d15 = d7;
                        i4 = i8;
                        d13 = d28;
                    } else {
                        mVar = mVar2;
                        if (z2) {
                            pathMeasure2.getMatrix((float) d37, matrix5, 3);
                            d12 = d26;
                            matrix = matrix4;
                            pathMeasure3 = pathMeasure2;
                            d16 = d7;
                        } else {
                            pathMeasure3 = pathMeasure2;
                            if (d34 < 0.0d) {
                                d12 = d26;
                                pathMeasure3.getMatrix(BitmapDescriptorFactory.HUE_RED, matrix4, 3);
                                matrix4.preTranslate((float) d34, BitmapDescriptorFactory.HUE_RED);
                                i5 = 1;
                            } else {
                                d12 = d26;
                                i5 = 1;
                                pathMeasure3.getMatrix((float) d34, matrix4, 1);
                            }
                            pathMeasure3.getMatrix((float) d37, matrix5, i5);
                            if (d35 > d7) {
                                d16 = d7;
                                pathMeasure3.getMatrix((float) d16, matrix6, 3);
                                matrix6.preTranslate((float) (d35 - d16), BitmapDescriptorFactory.HUE_RED);
                            } else {
                                d16 = d7;
                                pathMeasure3.getMatrix((float) d35, matrix6, i5);
                            }
                            matrix4.getValues(fArr5);
                            matrix6.getValues(fArr6);
                            matrix = matrix4;
                            matrix5.preRotate((float) (Math.atan2(((double) fArr6[5]) - ((double) fArr5[5]), ((double) fArr6[2]) - ((double) fArr5[2])) * 57.29577951308232d * d28));
                        }
                        matrix5.preTranslate((float) (-d36), (float) (f3 + d12));
                        d18 = d24;
                        i4 = i8;
                        matrix5.preScale((float) d18, (float) i4);
                        matrix5.postTranslate(BitmapDescriptorFactory.HUE_RED, (float) d32);
                        d17 = g;
                    }
                } else {
                    d12 = d26;
                    matrix = matrix4;
                    mVar = mVar2;
                    d18 = d24;
                    pathMeasure3 = pathMeasure2;
                    d16 = d7;
                    i4 = i8;
                    matrix5.setTranslate((float) d34, (float) (d32 + f3 + d12));
                    d17 = g;
                }
                matrix5.preRotate((float) d17);
                if (z3) {
                    Path path5 = new Path();
                    int length3 = str3.length();
                    i3 = i2;
                    d14 = d18;
                    d15 = d16;
                    fArr2 = fArr6;
                    fArr3 = fArr5;
                    d13 = d28;
                    matrix3 = matrix6;
                    matrix2 = matrix5;
                    path = path4;
                    paint.getTextPath(str3, 0, length3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, path5);
                    path2 = path5;
                    nVar2 = nVar;
                } else {
                    fArr3 = fArr5;
                    matrix3 = matrix6;
                    path = path4;
                    i3 = i2;
                    nVar2 = nVar;
                    d13 = d28;
                    d14 = d18;
                    d15 = d16;
                    fArr2 = fArr6;
                    matrix2 = matrix5;
                    path2 = nVar2.a(c4, str3);
                }
                path2.transform(matrix2);
                path.addPath(path2);
            }
            i11 = i3 + 1;
            d7 = d15;
            nVar = nVar2;
            pathMeasure2 = pathMeasure3;
            fArr5 = fArr3;
            matrix6 = matrix3;
            matrix5 = matrix2;
            i8 = i4;
            path4 = path;
            d24 = d14;
            fArr6 = fArr2;
            d9 = d10;
            length = length;
            d28 = d13;
            d26 = d12;
            paint2 = paint;
            matrix4 = matrix;
        }
        return path4;
    }

    private double a(String str, double d2, double d3) {
        return w.a(str, d2, 0.0d, (double) this.mScale, d3);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.aa$1  reason: invalid class name */
    /* compiled from: TSpanShadowNode */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f4560a = new int[ad.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(43:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|(3:49|50|52)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(45:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|52) */
        /* JADX WARNING: Can't wrap try/catch for region: R(46:0|(2:1|2)|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|52) */
        /* JADX WARNING: Can't wrap try/catch for region: R(47:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|52) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0072 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0089 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0095 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00ad */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00b9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00c5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00d1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00dd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00fc */
        static {
            /*
            // Method dump skipped, instructions count: 263
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.aa.AnonymousClass1.<clinit>():void");
        }
    }

    private double a(ab abVar, double d2) {
        switch (abVar) {
            case middle:
                return (-d2) / 2.0d;
            case end:
                return -d2;
            default:
                return 0.0d;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(1:2)(1:3)|4|(1:6)(1:7)|8|(1:10)(2:11|(1:13)(1:14))|(1:(1:(1:20)(1:21)))(1:17)|22|23|24|25|26|(2:27|28)|29|31) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0065 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0080 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.graphics.Paint r10, com.horcrux.svg.i r11) {
        /*
        // Method dump skipped, instructions count: 151
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.aa.a(android.graphics.Paint, com.horcrux.svg.i):void");
    }

    private void f() {
        for (w parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent.getClass() == ag.class) {
                this.f = (ag) parent;
                return;
            } else if (!(parent instanceof aj)) {
                return;
            }
        }
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public int hitTest(float[] fArr) {
        if (this.f4559b == null) {
            return super.hitTest(fArr);
        }
        if (this.mPath == null || !this.mInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
        if (this.mRegion == null && this.mPath != null) {
            this.mRegion = getRegion(this.mPath);
        }
        if (this.mRegion == null || !this.mRegion.contains(round, round2)) {
            return -1;
        }
        Path clipPath = getClipPath();
        if (clipPath != null) {
            if (this.mClipRegionPath != clipPath) {
                this.mClipRegionPath = clipPath;
                this.mClipRegion = getRegion(clipPath);
            }
            if (!this.mClipRegion.contains(round, round2)) {
                return -1;
            }
        }
        return getReactTag();
    }
}
