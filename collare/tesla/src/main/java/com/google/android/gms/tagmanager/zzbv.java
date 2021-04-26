package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import org.spongycastle.i18n.TextBundle;

/* access modifiers changed from: package-private */
public final class zzbv extends zzbq {
    private static final String ID = zza.HASH.toString();
    private static final String zzbaa = zzb.ARG0.toString();
    private static final String zzbac = zzb.INPUT_FORMAT.toString();
    private static final String zzbaf = zzb.ALGORITHM.toString();

    public zzbv() {
        super(ID, zzbaa);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        String str;
        byte[] bArr;
        zzm zzm = map.get(zzbaa);
        if (zzm == null || zzm == zzgj.zzpo()) {
            return zzgj.zzpo();
        }
        String zzc = zzgj.zzc(zzm);
        zzm zzm2 = map.get(zzbaf);
        String zzc2 = zzm2 == null ? "MD5" : zzgj.zzc(zzm2);
        zzm zzm3 = map.get(zzbac);
        String zzc3 = zzm3 == null ? TextBundle.TEXT_ENTRY : zzgj.zzc(zzm3);
        if (TextBundle.TEXT_ENTRY.equals(zzc3)) {
            bArr = zzc.getBytes();
        } else if ("base16".equals(zzc3)) {
            bArr = zzo.decode(zzc);
        } else {
            String valueOf = String.valueOf(zzc3);
            str = valueOf.length() != 0 ? "Hash: unknown input format: ".concat(valueOf) : new String("Hash: unknown input format: ");
            zzdi.e(str);
            return zzgj.zzpo();
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(zzc2);
            instance.update(bArr);
            return zzgj.zzj(zzo.encode(instance.digest()));
        } catch (NoSuchAlgorithmException unused) {
            String valueOf2 = String.valueOf(zzc2);
            str = valueOf2.length() != 0 ? "Hash: unknown algorithm: ".concat(valueOf2) : new String("Hash: unknown algorithm: ");
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
