package com.google.android.gms.internal.measurement;

final class zzabc {
    private static final Class<?> zzbwa = zzuw();
    private static final zzabo<?, ?> zzbwb = zzu(false);
    private static final zzabo<?, ?> zzbwc = zzu(true);
    private static final zzabo<?, ?> zzbwd = new zzabq();

    public static void zzh(Class<?> cls) {
        Class<?> cls2;
        if (!zzzv.class.isAssignableFrom(cls) && (cls2 = zzbwa) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    private static zzabo<?, ?> zzu(boolean z) {
        try {
            Class<?> zzux = zzux();
            if (zzux == null) {
                return null;
            }
            return (zzabo) zzux.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static zzabo<?, ?> zzut() {
        return zzbwb;
    }

    public static zzabo<?, ?> zzuu() {
        return zzbwc;
    }

    public static zzabo<?, ?> zzuv() {
        return zzbwd;
    }

    private static Class<?> zzuw() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzux() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }
}
