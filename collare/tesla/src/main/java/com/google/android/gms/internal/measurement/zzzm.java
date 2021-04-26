package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzzm {
    private static final Class<?> zzbts = zztq();

    private static final zzzn zzfo(String str) {
        return (zzzn) zzbts.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }

    private static Class<?> zztq() {
        try {
            return Class.forName("com.google.protobuf.o");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzzn zztr() {
        if (zzbts != null) {
            try {
                return zzfo("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return zzzn.zzbtv;
    }
}
