package com.facebook.react.common;

/* compiled from: LongArray */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private long[] f2605a;

    /* renamed from: b  reason: collision with root package name */
    private int f2606b = 0;

    public static d a(int i) {
        return new d(i);
    }

    private d(int i) {
        this.f2605a = new long[i];
    }

    public void a(long j) {
        b();
        long[] jArr = this.f2605a;
        int i = this.f2606b;
        this.f2606b = i + 1;
        jArr[i] = j;
    }

    public long b(int i) {
        if (i < this.f2606b) {
            return this.f2605a[i];
        }
        throw new IndexOutOfBoundsException("" + i + " >= " + this.f2606b);
    }

    public void a(int i, long j) {
        if (i < this.f2606b) {
            this.f2605a[i] = j;
            return;
        }
        throw new IndexOutOfBoundsException("" + i + " >= " + this.f2606b);
    }

    public int a() {
        return this.f2606b;
    }

    public void c(int i) {
        int i2 = this.f2606b;
        if (i <= i2) {
            this.f2606b = i2 - i;
            return;
        }
        throw new IndexOutOfBoundsException("Trying to drop " + i + " items from array of length " + this.f2606b);
    }

    private void b() {
        int i = this.f2606b;
        if (i == this.f2605a.length) {
            long[] jArr = new long[Math.max(i + 1, (int) (((double) i) * 1.8d))];
            System.arraycopy(this.f2605a, 0, jArr, 0, this.f2606b);
            this.f2605a = jArr;
        }
    }
}
