package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.d.i;
import com.facebook.common.h.c;
import com.facebook.h.a;

/* compiled from: BitmapCounter */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private int f2196a;

    /* renamed from: b  reason: collision with root package name */
    private long f2197b;

    /* renamed from: c  reason: collision with root package name */
    private final int f2198c;

    /* renamed from: d  reason: collision with root package name */
    private final int f2199d;
    private final c<Bitmap> e;

    public b(int i, int i2) {
        boolean z = true;
        i.a(i > 0);
        i.a(i2 <= 0 ? false : z);
        this.f2198c = i;
        this.f2199d = i2;
        this.e = new c<Bitmap>() {
            /* class com.facebook.imagepipeline.memory.b.AnonymousClass1 */

            public void a(Bitmap bitmap) {
                try {
                    b.this.b(bitmap);
                } finally {
                    bitmap.recycle();
                }
            }
        };
    }

    public synchronized boolean a(Bitmap bitmap) {
        int a2 = a.a(bitmap);
        if (this.f2196a < this.f2198c) {
            long j = (long) a2;
            if (this.f2197b + j <= ((long) this.f2199d)) {
                this.f2196a++;
                this.f2197b += j;
                return true;
            }
        }
        return false;
    }

    public synchronized void b(Bitmap bitmap) {
        int a2 = a.a(bitmap);
        i.a(this.f2196a > 0, "No bitmaps registered.");
        long j = (long) a2;
        i.a(j <= this.f2197b, "Bitmap size bigger than the total registered size: %d, %d", Integer.valueOf(a2), Long.valueOf(this.f2197b));
        this.f2197b -= j;
        this.f2196a--;
    }

    public synchronized int a() {
        return this.f2196a;
    }

    public synchronized long b() {
        return this.f2197b;
    }

    public synchronized int c() {
        return this.f2198c;
    }

    public synchronized int d() {
        return this.f2199d;
    }

    public c<Bitmap> e() {
        return this.e;
    }
}
