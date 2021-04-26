package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Looper;

public final class zzee {
    private final boolean zzagd = false;

    zzee(Context context) {
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
