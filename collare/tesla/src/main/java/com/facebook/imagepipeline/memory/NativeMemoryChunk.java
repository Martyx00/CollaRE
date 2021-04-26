package com.facebook.imagepipeline.memory;

import android.util.Log;
import com.facebook.common.d.d;
import com.facebook.common.d.i;
import com.facebook.imagepipeline.nativecode.a;
import java.io.Closeable;

@d
public class NativeMemoryChunk implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final long f2187a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2188b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f2189c;

    @d
    private static native long nativeAllocate(int i);

    @d
    private static native void nativeCopyFromByteArray(long j, byte[] bArr, int i, int i2);

    @d
    private static native void nativeCopyToByteArray(long j, byte[] bArr, int i, int i2);

    @d
    private static native void nativeFree(long j);

    @d
    private static native void nativeMemcpy(long j, long j2, int i);

    @d
    private static native byte nativeReadByte(long j);

    static {
        a.a();
    }

    public NativeMemoryChunk(int i) {
        i.a(i > 0);
        this.f2188b = i;
        this.f2187a = nativeAllocate(this.f2188b);
        this.f2189c = false;
    }

    public NativeMemoryChunk() {
        this.f2188b = 0;
        this.f2187a = 0;
        this.f2189c = true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.f2189c) {
            this.f2189c = true;
            nativeFree(this.f2187a);
        }
    }

    public synchronized boolean a() {
        return this.f2189c;
    }

    public int b() {
        return this.f2188b;
    }

    public synchronized int a(int i, byte[] bArr, int i2, int i3) {
        int a2;
        i.a(bArr);
        i.b(!a());
        a2 = a(i, i3);
        a(i, bArr.length, i2, a2);
        nativeCopyFromByteArray(this.f2187a + ((long) i), bArr, i2, a2);
        return a2;
    }

    public synchronized int b(int i, byte[] bArr, int i2, int i3) {
        int a2;
        i.a(bArr);
        i.b(!a());
        a2 = a(i, i3);
        a(i, bArr.length, i2, a2);
        nativeCopyToByteArray(this.f2187a + ((long) i), bArr, i2, a2);
        return a2;
    }

    public synchronized byte a(int i) {
        boolean z = true;
        i.b(!a());
        i.a(i >= 0);
        if (i >= this.f2188b) {
            z = false;
        }
        i.a(z);
        return nativeReadByte(this.f2187a + ((long) i));
    }

    public void a(int i, NativeMemoryChunk nativeMemoryChunk, int i2, int i3) {
        i.a(nativeMemoryChunk);
        if (nativeMemoryChunk.f2187a == this.f2187a) {
            Log.w("NativeMemoryChunk", "Copying from NativeMemoryChunk " + Integer.toHexString(System.identityHashCode(this)) + " to NativeMemoryChunk " + Integer.toHexString(System.identityHashCode(nativeMemoryChunk)) + " which share the same address " + Long.toHexString(this.f2187a));
            i.a(false);
        }
        if (nativeMemoryChunk.f2187a < this.f2187a) {
            synchronized (nativeMemoryChunk) {
                synchronized (this) {
                    b(i, nativeMemoryChunk, i2, i3);
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (nativeMemoryChunk) {
                b(i, nativeMemoryChunk, i2, i3);
            }
        }
    }

    private void b(int i, NativeMemoryChunk nativeMemoryChunk, int i2, int i3) {
        i.b(!a());
        i.b(!nativeMemoryChunk.a());
        a(i, nativeMemoryChunk.f2188b, i2, i3);
        nativeMemcpy(nativeMemoryChunk.f2187a + ((long) i2), this.f2187a + ((long) i), i3);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.Object
    public void finalize() {
        if (!a()) {
            Log.w("NativeMemoryChunk", "finalize: Chunk " + Integer.toHexString(System.identityHashCode(this)) + " still active. Underlying address = " + Long.toHexString(this.f2187a));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }

    private int a(int i, int i2) {
        return Math.min(Math.max(0, this.f2188b - i), i2);
    }

    private void a(int i, int i2, int i3, int i4) {
        boolean z = true;
        i.a(i4 >= 0);
        i.a(i >= 0);
        i.a(i3 >= 0);
        i.a(i + i4 <= this.f2188b);
        if (i3 + i4 > i2) {
            z = false;
        }
        i.a(z);
    }
}
