package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.d.i;
import com.facebook.common.g.c;
import com.facebook.imagepipeline.memory.a;

/* compiled from: NativeMemoryChunkPool */
public class l extends a<NativeMemoryChunk> {
    private final int[] g;

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.a
    public int d(int i) {
        return i;
    }

    public l(c cVar, t tVar, u uVar) {
        super(cVar, tVar, uVar);
        SparseIntArray sparseIntArray = tVar.f2237c;
        this.g = new int[sparseIntArray.size()];
        int i = 0;
        while (true) {
            int[] iArr = this.g;
            if (i < iArr.length) {
                iArr[i] = sparseIntArray.keyAt(i);
                i++;
            } else {
                a();
                return;
            }
        }
    }

    public int d() {
        return this.g[0];
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public NativeMemoryChunk b(int i) {
        return new NativeMemoryChunk(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(NativeMemoryChunk nativeMemoryChunk) {
        i.a(nativeMemoryChunk);
        nativeMemoryChunk.close();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.a
    public int c(int i) {
        if (i > 0) {
            int[] iArr = this.g;
            for (int i2 : iArr) {
                if (i2 >= i) {
                    return i2;
                }
            }
            return i;
        }
        throw new a.b(Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int c(NativeMemoryChunk nativeMemoryChunk) {
        i.a(nativeMemoryChunk);
        return nativeMemoryChunk.b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean d(NativeMemoryChunk nativeMemoryChunk) {
        i.a(nativeMemoryChunk);
        return !nativeMemoryChunk.a();
    }
}
