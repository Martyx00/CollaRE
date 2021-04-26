package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzab;
import com.google.android.gms.internal.measurement.zzac;
import com.google.android.gms.internal.measurement.zzad;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.internal.measurement.zzaf;
import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.internal.measurement.zzaq;
import com.google.android.gms.internal.measurement.zzas;
import com.google.android.gms.internal.measurement.zzat;
import com.google.android.gms.internal.measurement.zzaw;
import com.google.android.gms.internal.measurement.zzch;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.android.gms.internal.measurement.zzu;
import com.google.android.gms.internal.measurement.zzv;
import com.google.android.gms.internal.measurement.zzw;
import com.google.android.gms.internal.measurement.zzx;
import com.google.android.gms.internal.measurement.zzy;
import com.google.android.gms.internal.measurement.zzz;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzb extends zzaq implements zzo {
    private static DecimalFormat zzqq;
    private final zzat zzqm;
    private final String zzqr;
    private final Uri zzqs;

    public zzb(zzat zzat, String str) {
        this(zzat, str, true, false);
    }

    private zzb(zzat zzat, String str, boolean z, boolean z2) {
        super(zzat);
        Preconditions.checkNotEmpty(str);
        this.zzqm = zzat;
        this.zzqr = str;
        this.zzqs = zzb(this.zzqr);
    }

    private static String zza(double d2) {
        if (zzqq == null) {
            zzqq = new DecimalFormat("0.######");
        }
        return zzqq.format(d2);
    }

    private static void zza(Map<String, String> map, String str, double d2) {
        if (d2 != 0.0d) {
            map.put(str, zza(d2));
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i > 0 && i2 > 0) {
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append("x");
            sb.append(i2);
            map.put(str, sb.toString());
        }
    }

    private static void zza(Map<String, String> map, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    private static void zza(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    static Uri zzb(String str) {
        Preconditions.checkNotEmpty(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    @VisibleForTesting
    private static Map<String, String> zzc(zzg zzg) {
        HashMap hashMap = new HashMap();
        zzy zzy = (zzy) zzg.zza(zzy.class);
        if (zzy != null) {
            for (Map.Entry<String, Object> entry : zzy.zzas().entrySet()) {
                Object value = entry.getValue();
                String str = null;
                if (value != null) {
                    if (value instanceof String) {
                        String str2 = (String) value;
                        if (!TextUtils.isEmpty(str2)) {
                            str = str2;
                        }
                    } else if (value instanceof Double) {
                        Double d2 = (Double) value;
                        if (d2.doubleValue() != 0.0d) {
                            str = zza(d2.doubleValue());
                        }
                    } else if (!(value instanceof Boolean)) {
                        str = String.valueOf(value);
                    } else if (value != Boolean.FALSE) {
                        str = "1";
                    }
                }
                if (str != null) {
                    hashMap.put(entry.getKey(), str);
                }
            }
        }
        zzad zzad = (zzad) zzg.zza(zzad.class);
        if (zzad != null) {
            zza(hashMap, "t", zzad.zzay());
            zza(hashMap, "cid", zzad.zzaz());
            zza(hashMap, "uid", zzad.zzba());
            zza(hashMap, "sc", zzad.zzbd());
            zza(hashMap, "sf", zzad.zzbf());
            zza(hashMap, "ni", zzad.zzbe());
            zza(hashMap, "adid", zzad.zzbb());
            zza(hashMap, "ate", zzad.zzbc());
        }
        zzae zzae = (zzae) zzg.zza(zzae.class);
        if (zzae != null) {
            zza(hashMap, "cd", zzae.zzbg());
            zza(hashMap, "a", (double) zzae.zzbh());
            zza(hashMap, "dr", zzae.zzbi());
        }
        zzab zzab = (zzab) zzg.zza(zzab.class);
        if (zzab != null) {
            zza(hashMap, "ec", zzab.zzax());
            zza(hashMap, "ea", zzab.getAction());
            zza(hashMap, "el", zzab.getLabel());
            zza(hashMap, "ev", (double) zzab.getValue());
        }
        zzv zzv = (zzv) zzg.zza(zzv.class);
        if (zzv != null) {
            zza(hashMap, "cn", zzv.getName());
            zza(hashMap, "cs", zzv.getSource());
            zza(hashMap, "cm", zzv.zzaj());
            zza(hashMap, "ck", zzv.zzak());
            zza(hashMap, "cc", zzv.zzal());
            zza(hashMap, "ci", zzv.getId());
            zza(hashMap, "anid", zzv.zzam());
            zza(hashMap, "gclid", zzv.zzan());
            zza(hashMap, "dclid", zzv.zzao());
            zza(hashMap, FirebaseAnalytics.b.ACLID, zzv.zzap());
        }
        zzac zzac = (zzac) zzg.zza(zzac.class);
        if (zzac != null) {
            zza(hashMap, "exd", zzac.zzua);
            zza(hashMap, "exf", zzac.zzub);
        }
        zzaf zzaf = (zzaf) zzg.zza(zzaf.class);
        if (zzaf != null) {
            zza(hashMap, "sn", zzaf.zzur);
            zza(hashMap, "sa", zzaf.zzus);
            zza(hashMap, "st", zzaf.zzut);
        }
        zzag zzag = (zzag) zzg.zza(zzag.class);
        if (zzag != null) {
            zza(hashMap, "utv", zzag.zzuu);
            zza(hashMap, "utt", (double) zzag.zzuv);
            zza(hashMap, "utc", zzag.mCategory);
            zza(hashMap, "utl", zzag.zzuw);
        }
        zzw zzw = (zzw) zzg.zza(zzw.class);
        if (zzw != null) {
            for (Map.Entry<Integer, String> entry2 : zzw.zzaq().entrySet()) {
                String zzc = zzd.zzc(entry2.getKey().intValue());
                if (!TextUtils.isEmpty(zzc)) {
                    hashMap.put(zzc, entry2.getValue());
                }
            }
        }
        zzx zzx = (zzx) zzg.zza(zzx.class);
        if (zzx != null) {
            for (Map.Entry<Integer, Double> entry3 : zzx.zzar().entrySet()) {
                String zze = zzd.zze(entry3.getKey().intValue());
                if (!TextUtils.isEmpty(zze)) {
                    hashMap.put(zze, zza(entry3.getValue().doubleValue()));
                }
            }
        }
        zzaa zzaa = (zzaa) zzg.zza(zzaa.class);
        if (zzaa != null) {
            ProductAction zzat = zzaa.zzat();
            if (zzat != null) {
                for (Map.Entry<String, String> entry4 : zzat.build().entrySet()) {
                    hashMap.put(entry4.getKey().startsWith("&") ? entry4.getKey().substring(1) : entry4.getKey(), entry4.getValue());
                }
            }
            int i = 1;
            for (Promotion promotion : zzaa.zzaw()) {
                hashMap.putAll(promotion.zzn(zzd.zzi(i)));
                i++;
            }
            int i2 = 1;
            for (Product product : zzaa.zzau()) {
                hashMap.putAll(product.zzn(zzd.zzg(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry<String, List<Product>> entry5 : zzaa.zzav().entrySet()) {
                String zzl = zzd.zzl(i3);
                int i4 = 1;
                for (Product product2 : entry5.getValue()) {
                    String valueOf = String.valueOf(zzl);
                    String valueOf2 = String.valueOf(zzd.zzj(i4));
                    hashMap.putAll(product2.zzn(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty(entry5.getKey())) {
                    String valueOf3 = String.valueOf(zzl);
                    String valueOf4 = String.valueOf("nm");
                    hashMap.put(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), entry5.getKey());
                }
                i3++;
            }
        }
        zzz zzz = (zzz) zzg.zza(zzz.class);
        if (zzz != null) {
            zza(hashMap, "ul", zzz.getLanguage());
            zza(hashMap, "sd", (double) zzz.zztu);
            zza(hashMap, "sr", zzz.zztv, zzz.zztw);
            zza(hashMap, "vp", zzz.zztx, zzz.zzty);
        }
        zzu zzu = (zzu) zzg.zza(zzu.class);
        if (zzu != null) {
            zza(hashMap, "an", zzu.zzaf());
            zza(hashMap, "aid", zzu.zzah());
            zza(hashMap, "aiid", zzu.zzai());
            zza(hashMap, "av", zzu.zzag());
        }
        return hashMap;
    }

    @Override // com.google.android.gms.analytics.zzo
    public final void zzb(zzg zzg) {
        Preconditions.checkNotNull(zzg);
        Preconditions.checkArgument(zzg.zzt(), "Can't deliver not submitted measurement");
        Preconditions.checkNotMainThread("deliver should be called on worker thread");
        zzg zzo = zzg.zzo();
        zzad zzad = (zzad) zzo.zzb(zzad.class);
        if (TextUtils.isEmpty(zzad.zzay())) {
            zzbu().zza(zzc(zzo), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(zzad.zzaz())) {
            zzbu().zza(zzc(zzo), "Ignoring measurement without client id");
        } else if (!this.zzqm.zzck().getAppOptOut()) {
            double zzbf = zzad.zzbf();
            if (zzdd.zza(zzbf, zzad.zzaz())) {
                zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(zzbf));
                return;
            }
            Map<String, String> zzc = zzc(zzo);
            zzc.put("v", "1");
            zzc.put("_v", zzas.zzvo);
            zzc.put("tid", this.zzqr);
            if (this.zzqm.zzck().isDryRunEnabled()) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> entry : zzc.entrySet()) {
                    if (sb.length() != 0) {
                        sb.append(", ");
                    }
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                }
                zzc("Dry run is enabled. GoogleAnalytics would have sent", sb.toString());
                return;
            }
            HashMap hashMap = new HashMap();
            zzdd.zzb(hashMap, "uid", zzad.zzba());
            zzu zzu = (zzu) zzg.zza(zzu.class);
            if (zzu != null) {
                zzdd.zzb(hashMap, "an", zzu.zzaf());
                zzdd.zzb(hashMap, "aid", zzu.zzah());
                zzdd.zzb(hashMap, "av", zzu.zzag());
                zzdd.zzb(hashMap, "aiid", zzu.zzai());
            }
            zzc.put("_s", String.valueOf(zzby().zza(new zzaw(0, zzad.zzaz(), this.zzqr, !TextUtils.isEmpty(zzad.zzbb()), 0, hashMap))));
            zzby().zza(new zzch(zzbu(), zzc, zzg.zzr(), true));
        }
    }

    @Override // com.google.android.gms.analytics.zzo
    public final Uri zzk() {
        return this.zzqs;
    }
}
