package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: DefaultByteArrayPoolParams */
public class g {
    public static t a() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(16384, 5);
        return new t(81920, PKIFailureInfo.badCertTemplate, sparseIntArray);
    }
}
