package com.google.android.gms.internal.measurement;

final class zzzq {
    private static final zzzo<?> zzbtx = new zzzp();
    private static final zzzo<?> zzbty = zztu();

    private static zzzo<?> zztu() {
        try {
            return (zzzo) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzzo<?> zztv() {
        return zzbtx;
    }

    static zzzo<?> zztw() {
        zzzo<?> zzzo = zzbty;
        if (zzzo != null) {
            return zzzo;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
