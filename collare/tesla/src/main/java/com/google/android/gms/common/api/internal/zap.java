package com.google.android.gms.common.api.internal;

/* access modifiers changed from: package-private */
public final class zap extends ThreadLocal<Boolean> {
    zap() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* access modifiers changed from: protected */
    @Override // java.lang.ThreadLocal
    public final /* synthetic */ Boolean initialValue() {
        return false;
    }
}
