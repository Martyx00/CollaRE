package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: DefaultNativeMemoryChunkPoolParams */
public class i {
    public static t a() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1024, 5);
        sparseIntArray.put(2048, 5);
        sparseIntArray.put(4096, 5);
        sparseIntArray.put(PKIFailureInfo.certRevoked, 5);
        sparseIntArray.put(16384, 5);
        sparseIntArray.put(32768, 5);
        sparseIntArray.put(PKIFailureInfo.notAuthorized, 5);
        sparseIntArray.put(PKIFailureInfo.unsupportedVersion, 5);
        sparseIntArray.put(PKIFailureInfo.transactionIdInUse, 2);
        sparseIntArray.put(PKIFailureInfo.signerNotTrusted, 2);
        sparseIntArray.put(PKIFailureInfo.badCertTemplate, 2);
        return new t(b(), c(), sparseIntArray);
    }

    private static int b() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            return 3145728;
        }
        return min < 33554432 ? 6291456 : 12582912;
    }

    private static int c() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            return min / 2;
        }
        return (min / 4) * 3;
    }
}
