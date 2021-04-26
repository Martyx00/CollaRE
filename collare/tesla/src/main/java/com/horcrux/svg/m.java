package com.horcrux.svg;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
/* compiled from: GlyphContext */
public class m {
    private double[] A = {0.0d};
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G = -1;
    private int H = -1;
    private int I = -1;
    private int J = -1;
    private int K = -1;
    private int L;
    private final float M;
    private final float N;
    private final float O;

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<i> f4633a = new ArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    private final ArrayList<String[]> f4634b = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    private final ArrayList<String[]> f4635c = new ArrayList<>();

    /* renamed from: d  reason: collision with root package name */
    private final ArrayList<String[]> f4636d = new ArrayList<>();
    private final ArrayList<String[]> e = new ArrayList<>();
    private final ArrayList<double[]> f = new ArrayList<>();
    private final ArrayList<Integer> g = new ArrayList<>();
    private final ArrayList<Integer> h = new ArrayList<>();
    private final ArrayList<Integer> i = new ArrayList<>();
    private final ArrayList<Integer> j = new ArrayList<>();
    private final ArrayList<Integer> k = new ArrayList<>();
    private final ArrayList<Integer> l = new ArrayList<>();
    private final ArrayList<Integer> m = new ArrayList<>();
    private final ArrayList<Integer> n = new ArrayList<>();
    private final ArrayList<Integer> o = new ArrayList<>();
    private final ArrayList<Integer> p = new ArrayList<>();
    private double q = 12.0d;
    private i r = i.n;
    private double s;
    private double t;
    private double u;
    private double v;
    private String[] w = new String[0];
    private String[] x = new String[0];
    private String[] y = new String[0];
    private String[] z = new String[0];

    private void j() {
        this.l.add(Integer.valueOf(this.B));
        this.m.add(Integer.valueOf(this.C));
        this.n.add(Integer.valueOf(this.D));
        this.o.add(Integer.valueOf(this.E));
        this.p.add(Integer.valueOf(this.F));
    }

    m(float f2, float f3, float f4) {
        this.M = f2;
        this.N = f3;
        this.O = f4;
        this.f4634b.add(this.w);
        this.f4635c.add(this.x);
        this.f4636d.add(this.y);
        this.e.add(this.z);
        this.f.add(this.A);
        this.g.add(Integer.valueOf(this.G));
        this.h.add(Integer.valueOf(this.H));
        this.i.add(Integer.valueOf(this.I));
        this.j.add(Integer.valueOf(this.J));
        this.k.add(Integer.valueOf(this.K));
        this.f4633a.add(this.r);
        j();
    }

    private void k() {
        this.F = 0;
        this.E = 0;
        this.D = 0;
        this.C = 0;
        this.B = 0;
        this.K = -1;
        this.J = -1;
        this.I = -1;
        this.H = -1;
        this.G = -1;
        this.v = 0.0d;
        this.u = 0.0d;
        this.t = 0.0d;
        this.s = 0.0d;
    }

    /* access modifiers changed from: package-private */
    public i a() {
        return this.r;
    }

    private i a(o oVar) {
        if (this.L > 0) {
            return this.r;
        }
        for (o parentTextRoot = oVar.getParentTextRoot(); parentTextRoot != null; parentTextRoot = parentTextRoot.getParentTextRoot()) {
            i a2 = parentTextRoot.a().a();
            if (a2 != i.n) {
                return a2;
            }
        }
        return i.n;
    }

    private void b(o oVar, ReadableMap readableMap) {
        i a2 = a(oVar);
        this.L++;
        if (readableMap == null) {
            this.f4633a.add(a2);
            return;
        }
        i iVar = new i(readableMap, a2, (double) this.M);
        this.q = iVar.f4618a;
        this.f4633a.add(iVar);
        this.r = iVar;
    }

    /* access modifiers changed from: package-private */
    public void a(o oVar, ReadableMap readableMap) {
        b(oVar, readableMap);
        j();
    }

    private String[] a(ReadableArray readableArray) {
        int size = readableArray.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = readableArray.getString(i2);
        }
        return strArr;
    }

    private double[] b(ReadableArray readableArray) {
        int size = readableArray.size();
        double[] dArr = new double[size];
        for (int i2 = 0; i2 < size; i2++) {
            dArr[i2] = Double.valueOf(readableArray.getString(i2)).doubleValue();
        }
        return dArr;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2, aj ajVar, ReadableMap readableMap, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        if (z2) {
            k();
        }
        b(ajVar, readableMap);
        if (!(readableArray == null || readableArray.size() == 0)) {
            this.B++;
            this.G = -1;
            this.g.add(Integer.valueOf(this.G));
            this.w = a(readableArray);
            this.f4634b.add(this.w);
        }
        if (!(readableArray2 == null || readableArray2.size() == 0)) {
            this.C++;
            this.H = -1;
            this.h.add(Integer.valueOf(this.H));
            this.x = a(readableArray2);
            this.f4635c.add(this.x);
        }
        if (!(readableArray3 == null || readableArray3.size() == 0)) {
            this.D++;
            this.I = -1;
            this.i.add(Integer.valueOf(this.I));
            this.y = a(readableArray3);
            this.f4636d.add(this.y);
        }
        if (!(readableArray4 == null || readableArray4.size() == 0)) {
            this.E++;
            this.J = -1;
            this.j.add(Integer.valueOf(this.J));
            this.z = a(readableArray4);
            this.e.add(this.z);
        }
        if (!(readableArray5 == null || readableArray5.size() == 0)) {
            this.F++;
            this.K = -1;
            this.k.add(Integer.valueOf(this.K));
            this.A = b(readableArray5);
            this.f.add(this.A);
        }
        j();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.f4633a.remove(this.L);
        this.l.remove(this.L);
        this.m.remove(this.L);
        this.n.remove(this.L);
        this.o.remove(this.L);
        this.p.remove(this.L);
        this.L--;
        int i2 = this.B;
        int i3 = this.C;
        int i4 = this.D;
        int i5 = this.E;
        int i6 = this.F;
        this.r = this.f4633a.get(this.L);
        this.B = this.l.get(this.L).intValue();
        this.C = this.m.get(this.L).intValue();
        this.D = this.n.get(this.L).intValue();
        this.E = this.o.get(this.L).intValue();
        this.F = this.p.get(this.L).intValue();
        if (i2 != this.B) {
            this.f4634b.remove(i2);
            this.w = this.f4634b.get(this.B);
            this.G = this.g.get(this.B).intValue();
        }
        if (i3 != this.C) {
            this.f4635c.remove(i3);
            this.x = this.f4635c.get(this.C);
            this.H = this.h.get(this.C).intValue();
        }
        if (i4 != this.D) {
            this.f4636d.remove(i4);
            this.y = this.f4636d.get(this.D);
            this.I = this.i.get(this.D).intValue();
        }
        if (i5 != this.E) {
            this.e.remove(i5);
            this.z = this.e.get(this.E);
            this.J = this.j.get(this.E).intValue();
        }
        if (i6 != this.F) {
            this.f.remove(i6);
            this.A = this.f.get(this.F);
            this.K = this.k.get(this.F).intValue();
        }
    }

    private static void a(ArrayList<Integer> arrayList, int i2) {
        while (i2 >= 0) {
            arrayList.set(i2, Integer.valueOf(arrayList.get(i2).intValue() + 1));
            i2--;
        }
    }

    /* access modifiers changed from: package-private */
    public double c() {
        return this.q;
    }

    /* access modifiers changed from: package-private */
    public double a(double d2) {
        a(this.g, this.B);
        int i2 = this.G + 1;
        String[] strArr = this.w;
        if (i2 < strArr.length) {
            this.u = 0.0d;
            this.G = i2;
            this.s = w.a(strArr[i2], (double) this.N, 0.0d, (double) this.M, this.q);
        }
        this.s += d2;
        return this.s;
    }

    /* access modifiers changed from: package-private */
    public double d() {
        a(this.h, this.C);
        int i2 = this.H + 1;
        String[] strArr = this.x;
        if (i2 < strArr.length) {
            this.v = 0.0d;
            this.H = i2;
            this.t = w.a(strArr[i2], (double) this.O, 0.0d, (double) this.M, this.q);
        }
        return this.t;
    }

    /* access modifiers changed from: package-private */
    public double e() {
        a(this.i, this.D);
        int i2 = this.I + 1;
        String[] strArr = this.y;
        if (i2 < strArr.length) {
            this.I = i2;
            this.u += w.a(strArr[i2], (double) this.N, 0.0d, (double) this.M, this.q);
        }
        return this.u;
    }

    /* access modifiers changed from: package-private */
    public double f() {
        a(this.j, this.E);
        int i2 = this.J + 1;
        String[] strArr = this.z;
        if (i2 < strArr.length) {
            this.J = i2;
            this.v += w.a(strArr[i2], (double) this.O, 0.0d, (double) this.M, this.q);
        }
        return this.v;
    }

    /* access modifiers changed from: package-private */
    public double g() {
        a(this.k, this.F);
        this.K = Math.min(this.K + 1, this.A.length - 1);
        return this.A[this.K];
    }

    /* access modifiers changed from: package-private */
    public float h() {
        return this.N;
    }

    /* access modifiers changed from: package-private */
    public float i() {
        return this.O;
    }
}
