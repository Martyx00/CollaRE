package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;
import org.spongycastle.i18n.TextBundle;

/* access modifiers changed from: package-private */
public final class zzbk extends zzbq {
    private static final String ID = zza.ENCODE.toString();
    private static final String zzbaa = zzb.ARG0.toString();
    private static final String zzbab = zzb.NO_PADDING.toString();
    private static final String zzbac = zzb.INPUT_FORMAT.toString();
    private static final String zzbad = zzb.OUTPUT_FORMAT.toString();

    public zzbk() {
        super(ID, zzbaa);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        String str;
        byte[] bArr;
        String str2;
        zzm zzm = map.get(zzbaa);
        if (zzm == null || zzm == zzgj.zzpo()) {
            return zzgj.zzpo();
        }
        String zzc = zzgj.zzc(zzm);
        zzm zzm2 = map.get(zzbac);
        String zzc2 = zzm2 == null ? TextBundle.TEXT_ENTRY : zzgj.zzc(zzm2);
        zzm zzm3 = map.get(zzbad);
        String zzc3 = zzm3 == null ? "base16" : zzgj.zzc(zzm3);
        int i = 2;
        zzm zzm4 = map.get(zzbab);
        if (zzm4 != null && zzgj.zzg(zzm4).booleanValue()) {
            i = 3;
        }
        try {
            if (TextBundle.TEXT_ENTRY.equals(zzc2)) {
                bArr = zzc.getBytes();
            } else if ("base16".equals(zzc2)) {
                bArr = zzo.decode(zzc);
            } else if ("base64".equals(zzc2)) {
                bArr = Base64.decode(zzc, i);
            } else if ("base64url".equals(zzc2)) {
                bArr = Base64.decode(zzc, i | 8);
            } else {
                String valueOf = String.valueOf(zzc2);
                zzdi.e(valueOf.length() != 0 ? "Encode: unknown input format: ".concat(valueOf) : new String("Encode: unknown input format: "));
                return zzgj.zzpo();
            }
            if ("base16".equals(zzc3)) {
                str2 = zzo.encode(bArr);
            } else if ("base64".equals(zzc3)) {
                str2 = Base64.encodeToString(bArr, i);
            } else if ("base64url".equals(zzc3)) {
                str2 = Base64.encodeToString(bArr, i | 8);
            } else {
                String valueOf2 = String.valueOf(zzc3);
                str = valueOf2.length() != 0 ? "Encode: unknown output format: ".concat(valueOf2) : new String("Encode: unknown output format: ");
                zzdi.e(str);
                return zzgj.zzpo();
            }
            return zzgj.zzj(str2);
        } catch (IllegalArgumentException unused) {
            str = "Encode: invalid input:";
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
