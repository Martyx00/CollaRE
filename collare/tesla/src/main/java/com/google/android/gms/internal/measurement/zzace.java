package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzacd;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzace<M extends zzacd<M>, T> {
    public final int tag;
    private final int type;
    protected final Class<T> zzbze;
    protected final boolean zzbzf;
    private final zzzv<?, ?> zzbzg;

    private zzace(int i, Class<T> cls, int i2, boolean z) {
        this(11, cls, null, 810, false);
    }

    private zzace(int i, Class<T> cls, zzzv<?, ?> zzzv, int i2, boolean z) {
        this.type = i;
        this.zzbze = cls;
        this.tag = i2;
        this.zzbzf = false;
        this.zzbzg = null;
    }

    public static <M extends zzacd<M>, T extends zzacj> zzace<M, T> zza(int i, Class<T> cls, long j) {
        return new zzace<>(11, cls, 810, false);
    }

    private final Object zzf(zzaca zzaca) {
        Class componentType = this.zzbzf ? this.zzbze.getComponentType() : this.zzbze;
        try {
            switch (this.type) {
                case 10:
                    zzacj zzacj = (zzacj) componentType.newInstance();
                    zzaca.zza(zzacj, this.tag >>> 3);
                    return zzacj;
                case 11:
                    zzacj zzacj2 = (zzacj) componentType.newInstance();
                    zzaca.zza(zzacj2);
                    return zzacj2;
                default:
                    int i = this.type;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (InstantiationException e) {
            String valueOf = String.valueOf(componentType);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 33);
            sb2.append("Error creating instance of class ");
            sb2.append(valueOf);
            throw new IllegalArgumentException(sb2.toString(), e);
        } catch (IllegalAccessException e2) {
            String valueOf2 = String.valueOf(componentType);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 33);
            sb3.append("Error creating instance of class ");
            sb3.append(valueOf2);
            throw new IllegalArgumentException(sb3.toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzace)) {
            return false;
        }
        zzace zzace = (zzace) obj;
        return this.type == zzace.type && this.zzbze == zzace.zzbze && this.tag == zzace.tag && this.zzbzf == zzace.zzbzf;
    }

    public final int hashCode() {
        return ((((((this.type + 1147) * 31) + this.zzbze.hashCode()) * 31) + this.tag) * 31) + (this.zzbzf ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public final void zza(Object obj, zzacb zzacb) {
        try {
            zzacb.zzar(this.tag);
            switch (this.type) {
                case 10:
                    ((zzacj) obj).zza(zzacb);
                    zzacb.zzg(this.tag >>> 3, 4);
                    return;
                case 11:
                    zzacb.zzb((zzacj) obj);
                    return;
                default:
                    int i = this.type;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final T zzi(List<zzacl> list) {
        if (list == null) {
            return null;
        }
        if (this.zzbzf) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                zzacl zzacl = list.get(i);
                if (zzacl.zzbtj.length != 0) {
                    arrayList.add(zzf(zzaca.zzi(zzacl.zzbtj)));
                }
            }
            int size = arrayList.size();
            if (size == 0) {
                return null;
            }
            Class<T> cls = this.zzbze;
            T cast = cls.cast(Array.newInstance(cls.getComponentType(), size));
            for (int i2 = 0; i2 < size; i2++) {
                Array.set(cast, i2, arrayList.get(i2));
            }
            return cast;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return this.zzbze.cast(zzf(zzaca.zzi(list.get(list.size() - 1).zzbtj)));
        }
    }

    /* access modifiers changed from: protected */
    public final int zzv(Object obj) {
        int i = this.tag >>> 3;
        int i2 = this.type;
        switch (i2) {
            case 10:
                return (zzacb.zzaq(i) << 1) + ((zzacj) obj).zzwb();
            case 11:
                return zzacb.zzb(i, (zzacj) obj);
            default:
                StringBuilder sb = new StringBuilder(24);
                sb.append("Unknown type ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
        }
    }
}
