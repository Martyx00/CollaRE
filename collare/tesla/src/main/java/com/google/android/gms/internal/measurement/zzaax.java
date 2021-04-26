package com.google.android.gms.internal.measurement;

final class zzaax {
    private static final zzaav zzbvv = zzur();
    private static final zzaav zzbvw = new zzaaw();

    static zzaav zzup() {
        return zzbvv;
    }

    static zzaav zzuq() {
        return zzbvw;
    }

    private static zzaav zzur() {
        try {
            return (zzaav) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
