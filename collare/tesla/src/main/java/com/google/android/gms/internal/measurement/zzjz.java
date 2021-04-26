package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class zzjz extends zzjs {
    zzjz(zzjt zzjt) {
        super(zzjt);
    }

    static zzks zza(zzkr zzkr, String str) {
        zzks[] zzksArr = zzkr.zzava;
        for (zzks zzks : zzksArr) {
            if (zzks.name.equals(str)) {
                return zzks;
            }
        }
        return null;
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private final void zza(StringBuilder sb, int i, zzki zzki) {
        if (zzki != null) {
            zza(sb, i);
            sb.append("filter {\n");
            zza(sb, i, "complement", zzki.zzats);
            zza(sb, i, "param_name", zzgf().zzbn(zzki.zzatt));
            int i2 = i + 1;
            zzkl zzkl = zzki.zzatq;
            if (zzkl != null) {
                zza(sb, i2);
                sb.append("string_filter");
                sb.append(" {\n");
                if (zzkl.zzaue != null) {
                    String str = "UNKNOWN_MATCH_TYPE";
                    switch (zzkl.zzaue.intValue()) {
                        case 1:
                            str = "REGEXP";
                            break;
                        case 2:
                            str = "BEGINS_WITH";
                            break;
                        case 3:
                            str = "ENDS_WITH";
                            break;
                        case 4:
                            str = "PARTIAL";
                            break;
                        case 5:
                            str = "EXACT";
                            break;
                        case 6:
                            str = "IN_LIST";
                            break;
                    }
                    zza(sb, i2, "match_type", str);
                }
                zza(sb, i2, "expression", zzkl.zzauf);
                zza(sb, i2, "case_sensitive", zzkl.zzaug);
                if (zzkl.zzauh.length > 0) {
                    zza(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    String[] strArr = zzkl.zzauh;
                    for (String str2 : strArr) {
                        zza(sb, i2 + 2);
                        sb.append(str2);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zza(sb, i2);
                sb.append("}\n");
            }
            zza(sb, i2, "number_filter", zzki.zzatr);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzkj zzkj) {
        if (zzkj != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzkj.zzatw != null) {
                String str2 = "UNKNOWN_COMPARISON_TYPE";
                switch (zzkj.zzatw.intValue()) {
                    case 1:
                        str2 = "LESS_THAN";
                        break;
                    case 2:
                        str2 = "GREATER_THAN";
                        break;
                    case 3:
                        str2 = "EQUAL";
                        break;
                    case 4:
                        str2 = "BETWEEN";
                        break;
                }
                zza(sb, i, "comparison_type", str2);
            }
            zza(sb, i, "match_as_float", zzkj.zzatx);
            zza(sb, i, "comparison_value", zzkj.zzaty);
            zza(sb, i, "min_comparison_value", zzkj.zzatz);
            zza(sb, i, "max_comparison_value", zzkj.zzaua);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzkv zzkv) {
        if (zzkv != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            int i2 = 0;
            if (zzkv.zzawm != null) {
                zza(sb, 4);
                sb.append("results: ");
                long[] jArr = zzkv.zzawm;
                int length = jArr.length;
                int i3 = 0;
                int i4 = 0;
                while (i3 < length) {
                    Long valueOf = Long.valueOf(jArr[i3]);
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf);
                    i3++;
                    i4 = i5;
                }
                sb.append('\n');
            }
            if (zzkv.zzawl != null) {
                zza(sb, 4);
                sb.append("status: ");
                long[] jArr2 = zzkv.zzawl;
                int length2 = jArr2.length;
                int i6 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf2);
                    i2++;
                    i6 = i7;
                }
                sb.append('\n');
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append('\n');
        }
    }

    static boolean zza(long[] jArr, int i) {
        if (i >= (jArr.length << 6)) {
            return false;
        }
        return ((1 << (i % 64)) & jArr[i / 64]) != 0;
    }

    static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
            }
        }
        return jArr;
    }

    static zzks[] zza(zzks[] zzksArr, String str, Object obj) {
        for (zzks zzks : zzksArr) {
            if (str.equals(zzks.name)) {
                zzks.zzave = null;
                zzks.zzale = null;
                zzks.zzasw = null;
                if (obj instanceof Long) {
                    zzks.zzave = (Long) obj;
                } else if (obj instanceof String) {
                    zzks.zzale = (String) obj;
                } else if (obj instanceof Double) {
                    zzks.zzasw = (Double) obj;
                }
                return zzksArr;
            }
        }
        zzks[] zzksArr2 = new zzks[(zzksArr.length + 1)];
        System.arraycopy(zzksArr, 0, zzksArr2, 0, zzksArr.length);
        zzks zzks2 = new zzks();
        zzks2.name = str;
        if (obj instanceof Long) {
            zzks2.zzave = (Long) obj;
        } else if (obj instanceof String) {
            zzks2.zzale = (String) obj;
        } else if (obj instanceof Double) {
            zzks2.zzasw = (Double) obj;
        }
        zzksArr2[zzksArr.length] = zzks2;
        return zzksArr2;
    }

    static Object zzb(zzkr zzkr, String str) {
        zzks zza = zza(zzkr, str);
        if (zza == null) {
            return null;
        }
        if (zza.zzale != null) {
            return zza.zzale;
        }
        if (zza.zzave != null) {
            return zza.zzave;
        }
        if (zza.zzasw != null) {
            return zza.zzasw;
        }
        return null;
    }

    static boolean zzcf(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzgi().zziv().log("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends android.os.Parcelable> T zza(byte[] r5, android.os.Parcelable.Creator<T> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ ParseException -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ ParseException -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ ParseException -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ ParseException -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ ParseException -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r5 = move-exception
            goto L_0x002d
        L_0x001c:
            com.google.android.gms.internal.measurement.zzfi r5 = r4.zzgi()     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.log(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjz.zza(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzkh zzkh) {
        if (zzkh == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        zza(sb, 0, "filter_id", zzkh.zzatk);
        zza(sb, 0, "event_name", zzgf().zzbm(zzkh.zzatl));
        zza(sb, 1, "event_count_filter", zzkh.zzato);
        sb.append("  filters {\n");
        for (zzki zzki : zzkh.zzatm) {
            zza(sb, 2, zzki);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzkk zzkk) {
        if (zzkk == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        zza(sb, 0, "filter_id", zzkk.zzatk);
        zza(sb, 0, "property_name", zzgf().zzbo(zzkk.zzauc));
        zza(sb, 1, zzkk.zzaud);
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzks zzks, Object obj) {
        Preconditions.checkNotNull(obj);
        zzks.zzale = null;
        zzks.zzave = null;
        zzks.zzasw = null;
        if (obj instanceof String) {
            zzks.zzale = (String) obj;
        } else if (obj instanceof Long) {
            zzks.zzave = (Long) obj;
        } else if (obj instanceof Double) {
            zzks.zzasw = (Double) obj;
        } else {
            zzgi().zziv().zzg("Ignoring invalid (type) event param value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzkx zzkx, Object obj) {
        Preconditions.checkNotNull(obj);
        zzkx.zzale = null;
        zzkx.zzave = null;
        zzkx.zzasw = null;
        if (obj instanceof String) {
            zzkx.zzale = (String) obj;
        } else if (obj instanceof Long) {
            zzkx.zzave = (Long) obj;
        } else if (obj instanceof Double) {
            zzkx.zzasw = (Double) obj;
        } else {
            zzgi().zziv().zzg("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzbt().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zza(zzkt zzkt) {
        try {
            byte[] bArr = new byte[zzkt.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkt.zza(zzb);
            zzb.zzvt();
            return bArr;
        } catch (IOException e) {
            zzgi().zziv().zzg("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final byte[] zza(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzgi().zziv().zzg("Failed to ungzip content", e);
            throw e;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: package-private */
    public final String zzb(zzkt zzkt) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzkt.zzavf != null) {
            zzku[] zzkuArr = zzkt.zzavf;
            for (zzku zzku : zzkuArr) {
                if (!(zzku == null || zzku == null)) {
                    zza(sb, 1);
                    sb.append("bundle {\n");
                    zza(sb, 1, "protocol_version", zzku.zzavh);
                    zza(sb, 1, "platform", zzku.zzavp);
                    zza(sb, 1, "gmp_version", zzku.zzavt);
                    zza(sb, 1, "uploading_gmp_version", zzku.zzavu);
                    zza(sb, 1, "config_version", zzku.zzawf);
                    zza(sb, 1, "gmp_app_id", zzku.zzafa);
                    zza(sb, 1, "app_id", zzku.zzth);
                    zza(sb, 1, "app_version", zzku.zztg);
                    zza(sb, 1, "app_version_major", zzku.zzawb);
                    zza(sb, 1, "firebase_instance_id", zzku.zzafc);
                    zza(sb, 1, "dev_cert_hash", zzku.zzavx);
                    zza(sb, 1, "app_store", zzku.zzafh);
                    zza(sb, 1, "upload_timestamp_millis", zzku.zzavk);
                    zza(sb, 1, "start_timestamp_millis", zzku.zzavl);
                    zza(sb, 1, "end_timestamp_millis", zzku.zzavm);
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", zzku.zzavn);
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", zzku.zzavo);
                    zza(sb, 1, "app_instance_id", zzku.zzaez);
                    zza(sb, 1, "resettable_device_id", zzku.zzavv);
                    zza(sb, 1, "device_id", zzku.zzawe);
                    zza(sb, 1, "ds_id", zzku.zzawh);
                    zza(sb, 1, "limited_ad_tracking", zzku.zzavw);
                    zza(sb, 1, "os_version", zzku.zzavq);
                    zza(sb, 1, "device_model", zzku.zzavr);
                    zza(sb, 1, "user_default_language", zzku.zzahd);
                    zza(sb, 1, "time_zone_offset_minutes", zzku.zzavs);
                    zza(sb, 1, "bundle_sequential_index", zzku.zzavy);
                    zza(sb, 1, "service_upload", zzku.zzavz);
                    zza(sb, 1, "health_monitor", zzku.zzafy);
                    if (!(zzku.zzawg == null || zzku.zzawg.longValue() == 0)) {
                        zza(sb, 1, "android_id", zzku.zzawg);
                    }
                    if (zzku.zzawj != null) {
                        zza(sb, 1, "retry_counter", zzku.zzawj);
                    }
                    zzkx[] zzkxArr = zzku.zzavj;
                    if (zzkxArr != null) {
                        for (zzkx zzkx : zzkxArr) {
                            if (zzkx != null) {
                                zza(sb, 2);
                                sb.append("user_property {\n");
                                zza(sb, 2, "set_timestamp_millis", zzkx.zzaws);
                                zza(sb, 2, "name", zzgf().zzbo(zzkx.name));
                                zza(sb, 2, "string_value", zzkx.zzale);
                                zza(sb, 2, "int_value", zzkx.zzave);
                                zza(sb, 2, "double_value", zzkx.zzasw);
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zzkp[] zzkpArr = zzku.zzawa;
                    if (zzkpArr != null) {
                        for (zzkp zzkp : zzkpArr) {
                            if (zzkp != null) {
                                zza(sb, 2);
                                sb.append("audience_membership {\n");
                                zza(sb, 2, "audience_id", zzkp.zzate);
                                zza(sb, 2, "new_audience", zzkp.zzauv);
                                zza(sb, 2, "current_data", zzkp.zzaut);
                                zza(sb, 2, "previous_data", zzkp.zzauu);
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zzkr[] zzkrArr = zzku.zzavi;
                    if (zzkrArr != null) {
                        for (zzkr zzkr : zzkrArr) {
                            if (zzkr != null) {
                                zza(sb, 2);
                                sb.append("event {\n");
                                zza(sb, 2, "name", zzgf().zzbm(zzkr.name));
                                zza(sb, 2, "timestamp_millis", zzkr.zzavb);
                                zza(sb, 2, "previous_timestamp_millis", zzkr.zzavc);
                                zza(sb, 2, "count", zzkr.count);
                                zzks[] zzksArr = zzkr.zzava;
                                if (zzksArr != null) {
                                    for (zzks zzks : zzksArr) {
                                        if (zzks != null) {
                                            zza(sb, 3);
                                            sb.append("param {\n");
                                            zza(sb, 3, "name", zzgf().zzbn(zzks.name));
                                            zza(sb, 3, "string_value", zzks.zzale);
                                            zza(sb, 3, "int_value", zzks.zzave);
                                            zza(sb, 3, "double_value", zzks.zzasw);
                                            zza(sb, 3);
                                            sb.append("}\n");
                                        }
                                    }
                                }
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zza(sb, 1);
                    sb.append("}\n");
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzb(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzgi().zziv().zzg("Failed to gzip content", e);
            throw e;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd(zzex zzex, zzeb zzeb) {
        Preconditions.checkNotNull(zzex);
        Preconditions.checkNotNull(zzeb);
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            return true;
        }
        zzgl();
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzjs
    public final boolean zzgn() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzjr
    public final /* bridge */ /* synthetic */ zzjz zzjf() {
        return super.zzjf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjr
    public final /* bridge */ /* synthetic */ zzed zzjg() {
        return super.zzjg();
    }

    @Override // com.google.android.gms.internal.measurement.zzjr
    public final /* bridge */ /* synthetic */ zzek zzjh() {
        return super.zzjh();
    }
}
