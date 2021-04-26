package com.horcrux.svg;

import com.facebook.react.uimanager.w;
import java.util.ArrayList;

public class TextLayoutAlgorithm {

    class d {

        /* renamed from: a  reason: collision with root package name */
        aj f4554a;
    }

    /* access modifiers changed from: package-private */
    public class c {

        /* renamed from: a  reason: collision with root package name */
        int f4550a;

        /* renamed from: b  reason: collision with root package name */
        double f4551b = 0.0d;

        /* renamed from: c  reason: collision with root package name */
        double f4552c = 0.0d;

        /* renamed from: d  reason: collision with root package name */
        double f4553d;
        char e;
        double f = 0.0d;
        boolean g = false;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        boolean l = true;
        boolean m = false;
        boolean n = false;
        boolean o = false;

        c(int i2, char c2) {
            this.f4550a = i2;
            this.e = c2;
        }
    }

    private void getSubTreeTypographicCharacterPositions(ArrayList<ag> arrayList, ArrayList<aj> arrayList2, StringBuilder sb, w wVar, ag agVar) {
        int i = 0;
        if (wVar instanceof aa) {
            aa aaVar = (aa) wVar;
            String str = aaVar.f4559b;
            if (str == null) {
                while (i < wVar.getChildCount()) {
                    getSubTreeTypographicCharacterPositions(arrayList, arrayList2, sb, wVar.getChildAt(i), agVar);
                    i++;
                }
                return;
            }
            while (i < str.length()) {
                arrayList2.add(aaVar);
                arrayList.add(agVar);
                i++;
            }
            sb.append(str);
            return;
        }
        if (wVar instanceof ag) {
            agVar = (ag) wVar;
        }
        while (i < wVar.getChildCount()) {
            getSubTreeTypographicCharacterPositions(arrayList, arrayList2, sb, wVar.getChildAt(i), agVar);
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01a0, code lost:
        if (r18 == Double.POSITIVE_INFINITY) goto L_0x01a5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.horcrux.svg.TextLayoutAlgorithm.c[] layoutText(com.horcrux.svg.TextLayoutAlgorithm.d r27) {
        /*
        // Method dump skipped, instructions count: 970
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TextLayoutAlgorithm.layoutText(com.horcrux.svg.TextLayoutAlgorithm$d):com.horcrux.svg.TextLayoutAlgorithm$c[]");
    }

    class a {

        /* renamed from: b  reason: collision with root package name */
        private int f4544b = 0;

        /* renamed from: c  reason: collision with root package name */
        private boolean f4545c = true;

        /* renamed from: d  reason: collision with root package name */
        private boolean f4546d = false;
        private c[] e;
        private String[] f;
        private String[] g;
        private String[] h;
        private String[] i;

        a(c[] cVarArr, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4) {
            this.e = cVarArr;
            this.f = strArr;
            this.g = strArr2;
            this.h = strArr3;
            this.i = strArr4;
        }
    }

    class b {

        /* renamed from: a  reason: collision with root package name */
        int f4547a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ c[] f4548b;

        /* JADX WARN: Incorrect args count in method signature: ()V */
        b(c[] cVarArr) {
            this.f4548b = cVarArr;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(aj ajVar) {
            int i;
            int i2;
            int i3;
            int i4;
            Class<?> cls = ajVar.getClass();
            boolean z = ajVar.f4588c != null;
            if (cls == aa.class && z) {
                aa aaVar = (aa) ajVar;
                String str = aaVar.f4559b;
                int i5 = this.f4547a;
                if (str == null) {
                    i = 0;
                } else {
                    i = str.length();
                }
                int i6 = i + i5;
                double d2 = Double.NEGATIVE_INFINITY;
                int i7 = i5;
                double d3 = Double.POSITIVE_INFINITY;
                while (i7 <= i6) {
                    if (!this.f4548b[i5].l) {
                        i3 = i6;
                        i4 = i5;
                    } else {
                        char c2 = this.f4548b[i5].e;
                        if (c2 != '\n' && c2 != '\r') {
                            double d4 = this.f4548b[i7].f4551b;
                            double d5 = this.f4548b[i7].f4553d + d4;
                            i3 = i6;
                            i4 = i5;
                            double min = Math.min(d3, Math.min(d4, d5));
                            d2 = Math.max(d2, Math.max(d4, d5));
                            d3 = min;
                        } else {
                            return;
                        }
                    }
                    i7++;
                    i5 = i4;
                    i6 = i3;
                }
                if (d3 != Double.POSITIVE_INFINITY) {
                    double parseDouble = Double.parseDouble(ajVar.f4588c) - (d2 - d3);
                    int i8 = 0;
                    int i9 = 0;
                    for (int i10 = 0; i10 < ajVar.getChildCount(); i10++) {
                        if (((ag) ajVar.getChildAt(i10)).f4588c == null) {
                            String str2 = aaVar.f4559b;
                            if (str2 == null) {
                                i2 = 0;
                            } else {
                                i2 = str2.length();
                            }
                            i8 += i2;
                        } else {
                            this.f4548b[i8].o = true;
                            i9++;
                        }
                    }
                    double d6 = parseDouble / ((double) (i8 + (i9 - 1)));
                    double d7 = 0.0d;
                    for (int i11 = i5; i11 <= i6; i11++) {
                        this.f4548b[i11].f4551b += d7;
                        if (!this.f4548b[i11].h && (!this.f4548b[i11].i || this.f4548b[i11].o)) {
                            d7 += d6;
                        }
                    }
                }
            }
        }
    }
}
