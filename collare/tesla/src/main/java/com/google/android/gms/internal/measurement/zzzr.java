package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzzt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzzr<FieldDescriptorType extends zzzt<FieldDescriptorType>> {
    private static final zzzr zzbub = new zzzr(true);
    private boolean zzbnw;
    private final zzabd<FieldDescriptorType, Object> zzbtz = zzabd.zzag(16);
    private boolean zzbua = false;

    private zzzr() {
    }

    private zzzr(boolean z) {
        if (!this.zzbnw) {
            this.zzbtz.zzru();
            this.zzbnw = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if ((r3 instanceof com.google.android.gms.internal.measurement.zzzz) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.measurement.zzzx) == false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.measurement.zzabu r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.measurement.zzzw.checkNotNull(r3)
            int[] r0 = com.google.android.gms.internal.measurement.zzzs.zzbuc
            com.google.android.gms.internal.measurement.zzabz r2 = r2.zzvk()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L_0x0040;
                case 2: goto L_0x003d;
                case 3: goto L_0x003a;
                case 4: goto L_0x0037;
                case 5: goto L_0x0034;
                case 6: goto L_0x0031;
                case 7: goto L_0x0028;
                case 8: goto L_0x001e;
                case 9: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0043
        L_0x0015:
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzaaq
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzzz
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x001e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzzx
            if (r2 == 0) goto L_0x0043
        L_0x0026:
            r1 = 1
            goto L_0x0043
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzzb
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x0031:
            boolean r0 = r3 instanceof java.lang.String
            goto L_0x0042
        L_0x0034:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L_0x0042
        L_0x0037:
            boolean r0 = r3 instanceof java.lang.Double
            goto L_0x0042
        L_0x003a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L_0x0042
        L_0x003d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L_0x0042
        L_0x0040:
            boolean r0 = r3 instanceof java.lang.Integer
        L_0x0042:
            r1 = r0
        L_0x0043:
            if (r1 == 0) goto L_0x0046
            return
        L_0x0046:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
            switch-data {1->0x0040, 2->0x003d, 3->0x003a, 4->0x0037, 5->0x0034, 6->0x0031, 7->0x0028, 8->0x001e, 9->0x0015, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzzr.zza(com.google.android.gms.internal.measurement.zzabu, java.lang.Object):void");
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zztz()) {
            zza(fielddescriptortype.zzty(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zzty(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzzz) {
            this.zzbua = true;
        }
        this.zzbtz.put(fielddescriptortype, obj);
    }

    public static <T extends zzzt<T>> zzzr<T> zztx() {
        return zzbub;
    }

    public final /* synthetic */ Object clone() {
        zzzr zzzr = new zzzr();
        for (int i = 0; i < this.zzbtz.zzuy(); i++) {
            Map.Entry<FieldDescriptorType, Object> zzah = this.zzbtz.zzah(i);
            zzzr.zza(zzah.getKey(), zzah.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzbtz.zzuz()) {
            zzzr.zza(entry.getKey(), entry.getValue());
        }
        zzzr.zzbua = this.zzbua;
        return zzzr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzr)) {
            return false;
        }
        return this.zzbtz.equals(((zzzr) obj).zzbtz);
    }

    public final int hashCode() {
        return this.zzbtz.hashCode();
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzbua ? new zzaac(this.zzbtz.entrySet().iterator()) : this.zzbtz.entrySet().iterator();
    }
}
