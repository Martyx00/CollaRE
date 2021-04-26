package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzza {
    private static final Class<?> zzbtc = zzfm("libcore.io.Memory");
    private static final boolean zzbtd = (zzfm("org.robolectric.Robolectric") != null);

    private static <T> Class<T> zzfm(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zztk() {
        return zzbtc != null && !zzbtd;
    }

    static Class<?> zztl() {
        return zzbtc;
    }
}
