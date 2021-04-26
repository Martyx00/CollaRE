package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.os.Build;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VisibleForTesting
public final class zzg {
    private final Clock clock;
    private final zzj zzrn;
    private boolean zzro;
    private long zzrp;
    private long zzrq;
    private long zzrr;
    private long zzrs;
    private long zzrt;
    private boolean zzru;
    private final Map<Class<? extends zzi>, zzi> zzrv;
    private final List<zzo> zzrw;

    private zzg(zzg zzg) {
        this.zzrn = zzg.zzrn;
        this.clock = zzg.clock;
        this.zzrp = zzg.zzrp;
        this.zzrq = zzg.zzrq;
        this.zzrr = zzg.zzrr;
        this.zzrs = zzg.zzrs;
        this.zzrt = zzg.zzrt;
        this.zzrw = new ArrayList(zzg.zzrw);
        this.zzrv = new HashMap(zzg.zzrv.size());
        for (Map.Entry<Class<? extends zzi>, zzi> entry : zzg.zzrv.entrySet()) {
            zzi zzc = zzc(entry.getKey());
            entry.getValue().zzb(zzc);
            this.zzrv.put(entry.getKey(), zzc);
        }
    }

    @VisibleForTesting
    zzg(zzj zzj, Clock clock2) {
        Preconditions.checkNotNull(zzj);
        Preconditions.checkNotNull(clock2);
        this.zzrn = zzj;
        this.clock = clock2;
        this.zzrs = 1800000;
        this.zzrt = 3024000000L;
        this.zzrv = new HashMap();
        this.zzrw = new ArrayList();
    }

    @TargetApi(19)
    private static <T extends zzi> T zzc(Class<T> cls) {
        try {
            return cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            if (e instanceof InstantiationException) {
                throw new IllegalArgumentException("dataType doesn't have default constructor", e);
            } else if (e instanceof IllegalAccessException) {
                throw new IllegalArgumentException("dataType default constructor is not accessible", e);
            } else if (Build.VERSION.SDK_INT < 19 || !(e instanceof ReflectiveOperationException)) {
                throw new RuntimeException(e);
            } else {
                throw new IllegalArgumentException("Linkage exception", e);
            }
        }
    }

    @VisibleForTesting
    public final <T extends zzi> T zza(Class<T> cls) {
        return (T) this.zzrv.get(cls);
    }

    @VisibleForTesting
    public final void zza(long j) {
        this.zzrq = j;
    }

    @VisibleForTesting
    public final void zza(zzi zzi) {
        Preconditions.checkNotNull(zzi);
        Class<?> cls = zzi.getClass();
        if (cls.getSuperclass() == zzi.class) {
            zzi.zzb(zzb(cls));
            return;
        }
        throw new IllegalArgumentException();
    }

    @VisibleForTesting
    public final <T extends zzi> T zzb(Class<T> cls) {
        T t = (T) this.zzrv.get(cls);
        if (t != null) {
            return t;
        }
        T t2 = (T) zzc(cls);
        this.zzrv.put(cls, t2);
        return t2;
    }

    @VisibleForTesting
    public final zzg zzo() {
        return new zzg(this);
    }

    @VisibleForTesting
    public final Collection<zzi> zzp() {
        return this.zzrv.values();
    }

    public final List<zzo> zzq() {
        return this.zzrw;
    }

    @VisibleForTesting
    public final long zzr() {
        return this.zzrp;
    }

    @VisibleForTesting
    public final void zzs() {
        this.zzrn.zzy().zze(this);
    }

    @VisibleForTesting
    public final boolean zzt() {
        return this.zzro;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzu() {
        this.zzrr = this.clock.elapsedRealtime();
        long j = this.zzrq;
        if (j == 0) {
            j = this.clock.currentTimeMillis();
        }
        this.zzrp = j;
        this.zzro = true;
    }

    /* access modifiers changed from: package-private */
    public final zzj zzv() {
        return this.zzrn;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final boolean zzw() {
        return this.zzru;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzx() {
        this.zzru = true;
    }
}
