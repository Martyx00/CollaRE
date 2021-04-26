package com.google.protobuf;

import java.util.Arrays;

/* compiled from: UnknownFieldSetLite */
public final class aw {

    /* renamed from: a  reason: collision with root package name */
    private static final aw f4143a = new aw(0, new int[0], new Object[0], false);

    /* renamed from: b  reason: collision with root package name */
    private int f4144b;

    /* renamed from: c  reason: collision with root package name */
    private int[] f4145c;

    /* renamed from: d  reason: collision with root package name */
    private Object[] f4146d;
    private int e;
    private boolean f;

    public static aw a() {
        return f4143a;
    }

    static aw a(aw awVar, aw awVar2) {
        int i = awVar.f4144b + awVar2.f4144b;
        int[] copyOf = Arrays.copyOf(awVar.f4145c, i);
        System.arraycopy(awVar2.f4145c, 0, copyOf, awVar.f4144b, awVar2.f4144b);
        Object[] copyOf2 = Arrays.copyOf(awVar.f4146d, i);
        System.arraycopy(awVar2.f4146d, 0, copyOf2, awVar.f4144b, awVar2.f4144b);
        return new aw(i, copyOf, copyOf2, true);
    }

    private aw() {
        this(0, new int[8], new Object[8], true);
    }

    private aw(int i, int[] iArr, Object[] objArr, boolean z) {
        this.e = -1;
        this.f4144b = i;
        this.f4145c = iArr;
        this.f4146d = objArr;
        this.f = z;
    }

    public void b() {
        this.f = false;
    }

    private static boolean a(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(Object[] objArr, Object[] objArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!objArr[i2].equals(objArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof aw)) {
            return false;
        }
        aw awVar = (aw) obj;
        int i = this.f4144b;
        return i == awVar.f4144b && a(this.f4145c, awVar.f4145c, i) && a(this.f4146d, awVar.f4146d, this.f4144b);
    }

    private static int a(int[] iArr, int i) {
        int i2 = 17;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 * 31) + iArr[i3];
        }
        return i2;
    }

    private static int a(Object[] objArr, int i) {
        int i2 = 17;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 * 31) + objArr[i3].hashCode();
        }
        return i2;
    }

    public int hashCode() {
        int i = this.f4144b;
        return ((((527 + i) * 31) + a(this.f4145c, i)) * 31) + a(this.f4146d, this.f4144b);
    }

    /* access modifiers changed from: package-private */
    public final void a(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f4144b; i2++) {
            af.a(sb, i, String.valueOf(ba.b(this.f4145c[i2])), this.f4146d[i2]);
        }
    }
}
