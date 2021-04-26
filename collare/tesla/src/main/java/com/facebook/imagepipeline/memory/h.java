package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: DefaultFlexByteArrayPoolParams */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2210a = Runtime.getRuntime().availableProcessors();

    public static SparseIntArray a(int i, int i2, int i3) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        while (i <= i2) {
            sparseIntArray.put(i, i3);
            i *= 2;
        }
        return sparseIntArray;
    }

    public static t a() {
        int i = f2210a;
        return new t(4194304, i * 4194304, a(PKIFailureInfo.unsupportedVersion, 4194304, i), PKIFailureInfo.unsupportedVersion, 4194304, f2210a);
    }
}
