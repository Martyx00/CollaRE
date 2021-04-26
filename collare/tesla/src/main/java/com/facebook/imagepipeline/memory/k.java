package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.d.i;
import com.facebook.common.g.a;
import com.facebook.common.g.c;
import com.facebook.imagepipeline.memory.a;

/* compiled from: GenericByteArrayPool */
public class k extends a<byte[]> implements a {
    private final int[] g;

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.a
    public int d(int i) {
        return i;
    }

    public k(c cVar, t tVar, u uVar) {
        super(cVar, tVar, uVar);
        SparseIntArray sparseIntArray = tVar.f2237c;
        this.g = new int[sparseIntArray.size()];
        for (int i = 0; i < sparseIntArray.size(); i++) {
            this.g[i] = sparseIntArray.keyAt(i);
        }
        a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public byte[] b(int i) {
        return new byte[i];
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(byte[] bArr) {
        i.a(bArr);
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
    public int c(byte[] bArr) {
        i.a(bArr);
        return bArr.length;
    }
}
