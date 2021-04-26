package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* access modifiers changed from: package-private */
public final class zzacg implements Cloneable {
    private Object value;
    private zzace<?, ?> zzbzl;
    private List<zzacl> zzbzm = new ArrayList();

    zzacg() {
    }

    private final byte[] toByteArray() {
        byte[] bArr = new byte[zza()];
        zza(zzacb.zzj(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzvv */
    public final zzacg clone() {
        Object clone;
        zzacg zzacg = new zzacg();
        try {
            zzacg.zzbzl = this.zzbzl;
            if (this.zzbzm == null) {
                zzacg.zzbzm = null;
            } else {
                zzacg.zzbzm.addAll(this.zzbzm);
            }
            if (this.value != null) {
                if (this.value instanceof zzacj) {
                    clone = (zzacj) ((zzacj) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    clone = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzacg.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        clone = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        clone = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        clone = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        clone = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        clone = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzacj[]) {
                        zzacj[] zzacjArr = (zzacj[]) this.value;
                        zzacj[] zzacjArr2 = new zzacj[zzacjArr.length];
                        zzacg.value = zzacjArr2;
                        while (i < zzacjArr.length) {
                            zzacjArr2[i] = (zzacj) zzacjArr[i].clone();
                            i++;
                        }
                    }
                }
                zzacg.value = clone;
            }
            return zzacg;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        List<zzacl> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacg)) {
            return false;
        }
        zzacg zzacg = (zzacg) obj;
        if (this.value == null || zzacg.value == null) {
            List<zzacl> list2 = this.zzbzm;
            if (list2 != null && (list = zzacg.zzbzm) != null) {
                return list2.equals(list);
            }
            try {
                return Arrays.equals(toByteArray(), zzacg.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            zzace<?, ?> zzace = this.zzbzl;
            if (zzace != zzacg.zzbzl) {
                return false;
            }
            if (!zzace.zzbze.isArray()) {
                return this.value.equals(zzacg.value);
            }
            Object obj2 = this.value;
            return obj2 instanceof byte[] ? Arrays.equals((byte[]) obj2, (byte[]) zzacg.value) : obj2 instanceof int[] ? Arrays.equals((int[]) obj2, (int[]) zzacg.value) : obj2 instanceof long[] ? Arrays.equals((long[]) obj2, (long[]) zzacg.value) : obj2 instanceof float[] ? Arrays.equals((float[]) obj2, (float[]) zzacg.value) : obj2 instanceof double[] ? Arrays.equals((double[]) obj2, (double[]) zzacg.value) : obj2 instanceof boolean[] ? Arrays.equals((boolean[]) obj2, (boolean[]) zzacg.value) : Arrays.deepEquals((Object[]) obj2, (Object[]) zzacg.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        Object obj = this.value;
        if (obj != null) {
            zzace<?, ?> zzace = this.zzbzl;
            if (!zzace.zzbzf) {
                return zzace.zzv(obj);
            }
            int length = Array.getLength(obj);
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (Array.get(obj, i2) != null) {
                    i += zzace.zzv(Array.get(obj, i2));
                }
            }
            return i;
        }
        int i3 = 0;
        for (zzacl zzacl : this.zzbzm) {
            i3 += zzacb.zzas(zzacl.tag) + 0 + zzacl.zzbtj.length;
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzacb zzacb) {
        Object obj = this.value;
        if (obj != null) {
            zzace<?, ?> zzace = this.zzbzl;
            if (zzace.zzbzf) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzace.zza(obj2, zzacb);
                    }
                }
                return;
            }
            zzace.zza(obj, zzacb);
            return;
        }
        for (zzacl zzacl : this.zzbzm) {
            zzacb.zzar(zzacl.tag);
            zzacb.zzk(zzacl.zzbtj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzacl zzacl) {
        Object obj;
        List<zzacl> list = this.zzbzm;
        if (list != null) {
            list.add(zzacl);
            return;
        }
        Object obj2 = this.value;
        if (obj2 instanceof zzacj) {
            byte[] bArr = zzacl.zzbtj;
            zzaca zza = zzaca.zza(bArr, 0, bArr.length);
            int zzvn = zza.zzvn();
            if (zzvn == bArr.length - zzacb.zzao(zzvn)) {
                obj = ((zzacj) this.value).zzb(zza);
            } else {
                throw zzaci.zzvw();
            }
        } else if (obj2 instanceof zzacj[]) {
            zzacj[] zzacjArr = (zzacj[]) this.zzbzl.zzi(Collections.singletonList(zzacl));
            zzacj[] zzacjArr2 = (zzacj[]) this.value;
            zzacj[] zzacjArr3 = (zzacj[]) Arrays.copyOf(zzacjArr2, zzacjArr2.length + zzacjArr.length);
            System.arraycopy(zzacjArr, 0, zzacjArr3, zzacjArr2.length, zzacjArr.length);
            obj = zzacjArr3;
        } else {
            obj = this.zzbzl.zzi(Collections.singletonList(zzacl));
        }
        this.zzbzl = this.zzbzl;
        this.value = obj;
        this.zzbzm = null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.android.gms.internal.measurement.zzace<?, T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    public final <T> T zzb(zzace<?, T> zzace) {
        if (this.value == null) {
            this.zzbzl = zzace;
            this.value = zzace.zzi(this.zzbzm);
            this.zzbzm = null;
        } else if (!this.zzbzl.equals(zzace)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return (T) this.value;
    }
}
