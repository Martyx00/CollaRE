package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzaay {
    private static final zzaay zzbvx = new zzaay();
    private final zzabb zzbvy;
    private final ConcurrentMap<Class<?>, zzaba<?>> zzbvz = new ConcurrentHashMap();

    private zzaay() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzabb zzabb = null;
        for (int i = 0; i <= 0; i++) {
            zzabb = zzfq(strArr[0]);
            if (zzabb != null) {
                break;
            }
        }
        this.zzbvy = zzabb == null ? new zzaai() : zzabb;
    }

    private static zzabb zzfq(String str) {
        try {
            return (zzabb) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static zzaay zzus() {
        return zzbvx;
    }

    public final <T> zzaba<T> zzt(T t) {
        Class<?> cls = t.getClass();
        zzzw.zza(cls, "messageType");
        zzaba<T> zzaba = (zzaba<T>) this.zzbvz.get(cls);
        if (zzaba != null) {
            return zzaba;
        }
        zzaba<T> zzg = this.zzbvy.zzg(cls);
        zzzw.zza(cls, "messageType");
        zzzw.zza(zzg, "schema");
        zzaba<T> zzaba2 = (zzaba<T>) this.zzbvz.putIfAbsent(cls, zzg);
        return zzaba2 != null ? zzaba2 : zzg;
    }
}
