package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zzdc = new Object();
    private static ClassLoader zzdd = null;
    private static Integer zzde = null;
    private boolean zzdf = false;

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract boolean prepareForClientVersion(int i);

    private static ClassLoader zzp() {
        synchronized (zzdc) {
        }
        return null;
    }

    @KeepForSdk
    protected static Integer getUnparcelClientVersion() {
        synchronized (zzdc) {
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean shouldDowngrade() {
        return this.zzdf;
    }

    @KeepForSdk
    public void setShouldDowngrade(boolean z) {
        this.zzdf = z;
    }

    @KeepForSdk
    protected static boolean canUnparcelSafely(String str) {
        zzp();
        return true;
    }
}
