package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzf;
import com.google.android.gms.internal.measurement.zzk;
import com.google.android.gms.internal.measurement.zzm;
import com.google.android.gms.internal.measurement.zzwe;
import com.google.android.gms.internal.measurement.zzwg;
import com.google.android.gms.internal.measurement.zzwi;
import com.google.android.gms.internal.measurement.zzwk;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzfb {
    private static final zzdz<zzm> zzbcw = new zzdz<>(zzgj.zzpo(), true);
    private final DataLayer zzaxn;
    private final zzwi zzbcx;
    private final zzbo zzbcy;
    private final Map<String, zzbq> zzbcz;
    private final Map<String, zzbq> zzbda;
    private final Map<String, zzbq> zzbdb;
    private final zzp<zzwg, zzdz<zzm>> zzbdc;
    private final zzp<String, zzfh> zzbdd;
    private final Set<zzwk> zzbde;
    private final Map<String, zzfi> zzbdf;
    private volatile String zzbdg;
    private int zzbdh;

    public zzfb(Context context, zzwi zzwi, DataLayer dataLayer, zzan zzan, zzan zzan2, zzbo zzbo) {
        if (zzwi != null) {
            this.zzbcx = zzwi;
            this.zzbde = new HashSet(zzwi.zzre());
            this.zzaxn = dataLayer;
            this.zzbcy = zzbo;
            zzfc zzfc = new zzfc(this);
            new zzq();
            this.zzbdc = zzq.zza(PKIFailureInfo.badCertTemplate, zzfc);
            zzfd zzfd = new zzfd(this);
            new zzq();
            this.zzbdd = zzq.zza(PKIFailureInfo.badCertTemplate, zzfd);
            this.zzbcz = new HashMap();
            zzb(new zzm(context));
            zzb(new zzam(zzan2));
            zzb(new zzaz(dataLayer));
            zzb(new zzgk(context, dataLayer));
            this.zzbda = new HashMap();
            zzc(new zzak());
            zzc(new zzbl());
            zzc(new zzbm());
            zzc(new zzbs());
            zzc(new zzbt());
            zzc(new zzde());
            zzc(new zzdf());
            zzc(new zzel());
            zzc(new zzfy());
            this.zzbdb = new HashMap();
            zza(new zze(context));
            zza(new zzf(context));
            zza(new zzh(context));
            zza(new zzi(context));
            zza(new zzj(context));
            zza(new zzk(context));
            zza(new zzl(context));
            zza(new zzt());
            zza(new zzaj(this.zzbcx.getVersion()));
            zza(new zzam(zzan));
            zza(new zzas(dataLayer));
            zza(new zzbc(context));
            zza(new zzbd());
            zza(new zzbk());
            zza(new zzbp(this));
            zza(new zzbu());
            zza(new zzbv());
            zza(new zzcv(context));
            zza(new zzcx());
            zza(new zzdd());
            zza(new zzdk());
            zza(new zzdm(context));
            zza(new zzea());
            zza(new zzee());
            zza(new zzei());
            zza(new zzek());
            zza(new zzem(context));
            zza(new zzfj());
            zza(new zzfk());
            zza(new zzge());
            zza(new zzgl());
            this.zzbdf = new HashMap();
            for (zzwk zzwk : this.zzbde) {
                for (int i = 0; i < zzwk.zzsc().size(); i++) {
                    zzwg zzwg = zzwk.zzsc().get(i);
                    zzfi zzb = zzb(this.zzbdf, zza(zzwg));
                    zzb.zza(zzwk);
                    zzb.zza(zzwk, zzwg);
                    zzb.zza(zzwk, "Unknown");
                }
                for (int i2 = 0; i2 < zzwk.zzsd().size(); i2++) {
                    zzwg zzwg2 = zzwk.zzsd().get(i2);
                    zzfi zzb2 = zzb(this.zzbdf, zza(zzwg2));
                    zzb2.zza(zzwk);
                    zzb2.zzb(zzwk, zzwg2);
                    zzb2.zzb(zzwk, "Unknown");
                }
            }
            for (Map.Entry<String, List<zzwg>> entry : this.zzbcx.zzsa().entrySet()) {
                for (zzwg zzwg3 : entry.getValue()) {
                    if (!zzgj.zzg(zzwg3.zzrg().get(zzb.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                        zzb(this.zzbdf, entry.getKey()).zzb(zzwg3);
                    }
                }
            }
            return;
        }
        throw new NullPointerException("resource cannot be null");
    }

    private final zzdz<zzm> zza(zzm zzm, Set<String> set, zzgm zzgm) {
        if (!zzm.zzqh) {
            return new zzdz<>(zzm, true);
        }
        int i = zzm.type;
        if (i != 7) {
            switch (i) {
                case 2:
                    zzm zzk = zzwe.zzk(zzm);
                    zzk.zzpy = new zzm[zzm.zzpy.length];
                    for (int i2 = 0; i2 < zzm.zzpy.length; i2++) {
                        zzdz<zzm> zza = zza(zzm.zzpy[i2], set, zzgm.zzt(i2));
                        zzdz<zzm> zzdz = zzbcw;
                        if (zza == zzdz) {
                            return zzdz;
                        }
                        zzk.zzpy[i2] = zza.getObject();
                    }
                    return new zzdz<>(zzk, false);
                case 3:
                    zzm zzk2 = zzwe.zzk(zzm);
                    if (zzm.zzpz.length != zzm.zzqa.length) {
                        String valueOf = String.valueOf(zzm.toString());
                        zzdi.e(valueOf.length() != 0 ? "Invalid serving value: ".concat(valueOf) : new String("Invalid serving value: "));
                        return zzbcw;
                    }
                    zzk2.zzpz = new zzm[zzm.zzpz.length];
                    zzk2.zzqa = new zzm[zzm.zzpz.length];
                    for (int i3 = 0; i3 < zzm.zzpz.length; i3++) {
                        zzdz<zzm> zza2 = zza(zzm.zzpz[i3], set, zzgm.zzu(i3));
                        zzdz<zzm> zza3 = zza(zzm.zzqa[i3], set, zzgm.zzv(i3));
                        zzdz<zzm> zzdz2 = zzbcw;
                        if (zza2 == zzdz2 || zza3 == zzdz2) {
                            return zzbcw;
                        }
                        zzk2.zzpz[i3] = zza2.getObject();
                        zzk2.zzqa[i3] = zza3.getObject();
                    }
                    return new zzdz<>(zzk2, false);
                case 4:
                    if (set.contains(zzm.zzqb)) {
                        String str = zzm.zzqb;
                        String obj = set.toString();
                        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 79 + String.valueOf(obj).length());
                        sb.append("Macro cycle detected.  Current macro reference: ");
                        sb.append(str);
                        sb.append(".  Previous macro references: ");
                        sb.append(obj);
                        sb.append(".");
                        zzdi.e(sb.toString());
                        return zzbcw;
                    }
                    set.add(zzm.zzqb);
                    zzdz<zzm> zza4 = zzgn.zza(zza(zzm.zzqb, set, zzgm.zzof()), zzm.zzqg);
                    set.remove(zzm.zzqb);
                    return zza4;
                default:
                    int i4 = zzm.type;
                    StringBuilder sb2 = new StringBuilder(25);
                    sb2.append("Unknown type: ");
                    sb2.append(i4);
                    zzdi.e(sb2.toString());
                    return zzbcw;
            }
        } else {
            zzm zzk3 = zzwe.zzk(zzm);
            zzk3.zzqf = new zzm[zzm.zzqf.length];
            for (int i5 = 0; i5 < zzm.zzqf.length; i5++) {
                zzdz<zzm> zza5 = zza(zzm.zzqf[i5], set, zzgm.zzw(i5));
                zzdz<zzm> zzdz3 = zzbcw;
                if (zza5 == zzdz3) {
                    return zzdz3;
                }
                zzk3.zzqf[i5] = zza5.getObject();
            }
            return new zzdz<>(zzk3, false);
        }
    }

    @VisibleForTesting
    private final zzdz<Boolean> zza(zzwg zzwg, Set<String> set, zzen zzen) {
        zzdz<zzm> zza = zza(this.zzbda, zzwg, set, zzen);
        Boolean zzg = zzgj.zzg(zza.getObject());
        zzen.zza(zzgj.zzj(zzg));
        return new zzdz<>(zzg, zza.zzog());
    }

    private final zzdz<zzm> zza(String str, Set<String> set, zzdl zzdl) {
        zzwg zzwg;
        this.zzbdh++;
        zzfh zzfh = this.zzbdd.get(str);
        if (zzfh != null) {
            this.zzbcy.zznr();
            zza(zzfh.zzou(), set);
            this.zzbdh--;
            return zzfh.zzot();
        }
        zzfi zzfi = this.zzbdf.get(str);
        if (zzfi == null) {
            String zzos = zzos();
            StringBuilder sb = new StringBuilder(String.valueOf(zzos).length() + 15 + String.valueOf(str).length());
            sb.append(zzos);
            sb.append("Invalid macro: ");
            sb.append(str);
            zzdi.e(sb.toString());
            this.zzbdh--;
            return zzbcw;
        }
        zzdz<Set<zzwg>> zza = zza(zzfi.zzov(), set, new zzfe(this, zzfi.zzow(), zzfi.zzox(), zzfi.zzoz(), zzfi.zzoy()), zzdl.zznf());
        if (zza.getObject().isEmpty()) {
            zzwg = zzfi.zzpa();
        } else {
            if (zza.getObject().size() > 1) {
                String zzos2 = zzos();
                StringBuilder sb2 = new StringBuilder(String.valueOf(zzos2).length() + 37 + String.valueOf(str).length());
                sb2.append(zzos2);
                sb2.append("Multiple macros active for macroName ");
                sb2.append(str);
                zzdi.zzab(sb2.toString());
            }
            zzwg = zza.getObject().iterator().next();
        }
        if (zzwg == null) {
            this.zzbdh--;
            return zzbcw;
        }
        zzdz<zzm> zza2 = zza(this.zzbdb, zzwg, set, zzdl.zznx());
        boolean z = zza.zzog() && zza2.zzog();
        zzdz<zzm> zzdz = zzbcw;
        if (zza2 != zzdz) {
            zzdz = new zzdz<>(zza2.getObject(), z);
        }
        zzm zzou = zzwg.zzou();
        if (zzdz.zzog()) {
            this.zzbdd.zza(str, new zzfh(zzdz, zzou));
        }
        zza(zzou, set);
        this.zzbdh--;
        return zzdz;
    }

    private final zzdz<zzm> zza(Map<String, zzbq> map, zzwg zzwg, Set<String> set, zzen zzen) {
        String sb;
        zzm zzm = zzwg.zzrg().get(zzb.FUNCTION.toString());
        if (zzm == null) {
            sb = "No function id in properties";
        } else {
            String str = zzm.zzqc;
            zzbq zzbq = map.get(str);
            if (zzbq == null) {
                sb = String.valueOf(str).concat(" has no backing implementation.");
            } else {
                zzdz<zzm> zzdz = this.zzbdc.get(zzwg);
                if (zzdz != null) {
                    this.zzbcy.zznr();
                    return zzdz;
                }
                HashMap hashMap = new HashMap();
                boolean z = true;
                boolean z2 = true;
                for (Map.Entry<String, zzm> entry : zzwg.zzrg().entrySet()) {
                    zzdz<zzm> zza = zza(entry.getValue(), set, zzen.zzdj(entry.getKey()).zzb(entry.getValue()));
                    zzdz<zzm> zzdz2 = zzbcw;
                    if (zza == zzdz2) {
                        return zzdz2;
                    }
                    if (zza.zzog()) {
                        zzwg.zza(entry.getKey(), zza.getObject());
                    } else {
                        z2 = false;
                    }
                    hashMap.put(entry.getKey(), zza.getObject());
                }
                if (!zzbq.zza(hashMap.keySet())) {
                    String valueOf = String.valueOf(zzbq.zznt());
                    String valueOf2 = String.valueOf(hashMap.keySet());
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
                    sb2.append("Incorrect keys for function ");
                    sb2.append(str);
                    sb2.append(" required ");
                    sb2.append(valueOf);
                    sb2.append(" had ");
                    sb2.append(valueOf2);
                    sb = sb2.toString();
                } else {
                    if (!z2 || !zzbq.zzmj()) {
                        z = false;
                    }
                    zzdz<zzm> zzdz3 = new zzdz<>(zzbq.zze(hashMap), z);
                    if (z) {
                        this.zzbdc.zza(zzwg, zzdz3);
                    }
                    zzen.zza(zzdz3.getObject());
                    return zzdz3;
                }
            }
        }
        zzdi.e(sb);
        return zzbcw;
    }

    private final zzdz<Set<zzwg>> zza(Set<zzwk> set, Set<String> set2, zzfg zzfg, zzfa zzfa) {
        zzdz zzdz;
        Set<zzwg> hashSet = new HashSet<>();
        Set<zzwg> hashSet2 = new HashSet<>();
        Iterator<zzwk> it = set.iterator();
        while (true) {
            boolean z = true;
            while (true) {
                if (it.hasNext()) {
                    zzwk next = it.next();
                    zzeq zzoe = zzfa.zzoe();
                    Iterator<zzwg> it2 = next.zzrj().iterator();
                    while (true) {
                        boolean z2 = true;
                        while (true) {
                            if (!it2.hasNext()) {
                                Iterator<zzwg> it3 = next.zzri().iterator();
                                while (true) {
                                    if (!it3.hasNext()) {
                                        zzgj.zzj(true);
                                        zzdz = new zzdz(true, z2);
                                        break;
                                    }
                                    zzdz<Boolean> zza = zza(it3.next(), set2, zzoe.zznz());
                                    if (!zza.getObject().booleanValue()) {
                                        zzgj.zzj(false);
                                        zzdz = new zzdz(false, zza.zzog());
                                        break;
                                    }
                                    z2 = z2 && zza.zzog();
                                }
                            } else {
                                zzdz<Boolean> zza2 = zza(it2.next(), set2, zzoe.zzny());
                                if (zza2.getObject().booleanValue()) {
                                    zzgj.zzj(false);
                                    zzdz = new zzdz(false, zza2.zzog());
                                    break;
                                } else if (!z2 || !zza2.zzog()) {
                                    z2 = false;
                                }
                            }
                        }
                    }
                    if (((Boolean) zzdz.getObject()).booleanValue()) {
                        zzfg.zza(next, hashSet, hashSet2, zzoe);
                    }
                    if (!z || !zzdz.zzog()) {
                        z = false;
                    }
                } else {
                    hashSet.removeAll(hashSet2);
                    zzfa.zzb(hashSet);
                    return new zzdz<>(hashSet, z);
                }
            }
        }
    }

    private static String zza(zzwg zzwg) {
        return zzgj.zzc(zzwg.zzrg().get(zzb.INSTANCE_NAME.toString()));
    }

    private final void zza(zzm zzm, Set<String> set) {
        zzdz<zzm> zza;
        if (zzm != null && (zza = zza(zzm, set, new zzdx())) != zzbcw) {
            Object zzh = zzgj.zzh(zza.getObject());
            if (zzh instanceof Map) {
                this.zzaxn.push((Map) zzh);
            } else if (zzh instanceof List) {
                for (Object obj : (List) zzh) {
                    if (obj instanceof Map) {
                        this.zzaxn.push((Map) obj);
                    } else {
                        zzdi.zzab("pushAfterEvaluate: value not a Map");
                    }
                }
            } else {
                zzdi.zzab("pushAfterEvaluate: value not a Map or List");
            }
        }
    }

    @VisibleForTesting
    private final void zza(zzbq zzbq) {
        zza(this.zzbdb, zzbq);
    }

    private static void zza(Map<String, zzbq> map, zzbq zzbq) {
        if (map.containsKey(zzbq.zzns())) {
            String valueOf = String.valueOf(zzbq.zzns());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Duplicate function type name: ".concat(valueOf) : new String("Duplicate function type name: "));
        } else {
            map.put(zzbq.zzns(), zzbq);
        }
    }

    private static zzfi zzb(Map<String, zzfi> map, String str) {
        zzfi zzfi = map.get(str);
        if (zzfi != null) {
            return zzfi;
        }
        zzfi zzfi2 = new zzfi();
        map.put(str, zzfi2);
        return zzfi2;
    }

    @VisibleForTesting
    private final void zzb(zzbq zzbq) {
        zza(this.zzbcz, zzbq);
    }

    @VisibleForTesting
    private final void zzc(zzbq zzbq) {
        zza(this.zzbda, zzbq);
    }

    @VisibleForTesting
    private final synchronized void zzdn(String str) {
        this.zzbdg = str;
    }

    private final String zzos() {
        if (this.zzbdh <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzbdh));
        for (int i = 2; i < this.zzbdh; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    public final synchronized void zzcr(String str) {
        zzdn(str);
        zzar zznq = this.zzbcy.zzdd(str).zznq();
        for (zzwg zzwg : zza(this.zzbde, new HashSet(), new zzff(this), zznq.zznf()).getObject()) {
            zza(this.zzbcz, zzwg, new HashSet(), zznq.zzne());
        }
        zzdn(null);
    }

    public final zzdz<zzm> zzdm(String str) {
        this.zzbdh = 0;
        return zza(str, new HashSet(), this.zzbcy.zzdc(str).zznp());
    }

    public final synchronized void zzf(List<zzk> list) {
        String str;
        for (zzk zzk : list) {
            if (zzk.name != null) {
                if (zzk.name.startsWith("gaExperiment:")) {
                    DataLayer dataLayer = this.zzaxn;
                    if (zzk.zzpt == null) {
                        zzdi.zzab("supplemental missing experimentSupplemental");
                    } else {
                        for (zzm zzm : zzk.zzpt.zzoe) {
                            dataLayer.zzcu(zzgj.zzc(zzm));
                        }
                        zzm[] zzmArr = zzk.zzpt.zzod;
                        int length = zzmArr.length;
                        int i = 0;
                        while (true) {
                            Map<String, Object> map = null;
                            if (i >= length) {
                                break;
                            }
                            Object zzh = zzgj.zzh(zzmArr[i]);
                            if (!(zzh instanceof Map)) {
                                String valueOf = String.valueOf(zzh);
                                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
                                sb.append("value: ");
                                sb.append(valueOf);
                                sb.append(" is not a map value, ignored.");
                                zzdi.zzab(sb.toString());
                            } else {
                                map = (Map) zzh;
                            }
                            if (map != null) {
                                dataLayer.push(map);
                            }
                            i++;
                        }
                        zzf[] zzfArr = zzk.zzpt.zzof;
                        for (zzf zzf : zzfArr) {
                            if (zzf.zzny == null) {
                                str = "GaExperimentRandom: No key";
                            } else {
                                Object obj = dataLayer.get(zzf.zzny);
                                Long valueOf2 = !(obj instanceof Number) ? null : Long.valueOf(((Number) obj).longValue());
                                long j = zzf.zznz;
                                long j2 = zzf.zzoa;
                                if (!zzf.zzob || valueOf2 == null || valueOf2.longValue() < j || valueOf2.longValue() > j2) {
                                    if (j <= j2) {
                                        obj = Long.valueOf(Math.round((Math.random() * ((double) (j2 - j))) + ((double) j)));
                                    } else {
                                        str = "GaExperimentRandom: random range invalid";
                                    }
                                }
                                dataLayer.zzcu(zzf.zzny);
                                Map<String, Object> zzk2 = DataLayer.zzk(zzf.zzny, obj);
                                if (zzf.zzoc > 0) {
                                    if (!zzk2.containsKey("gtm")) {
                                        zzk2.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(zzf.zzoc)));
                                    } else {
                                        Object obj2 = zzk2.get("gtm");
                                        if (obj2 instanceof Map) {
                                            ((Map) obj2).put("lifetime", Long.valueOf(zzf.zzoc));
                                        } else {
                                            zzdi.zzab("GaExperimentRandom: gtm not a map");
                                        }
                                    }
                                }
                                dataLayer.push(zzk2);
                            }
                            zzdi.zzab(str);
                        }
                    }
                }
            }
            String valueOf3 = String.valueOf(zzk);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf3).length() + 22);
            sb2.append("Ignored supplemental: ");
            sb2.append(valueOf3);
            zzdi.v(sb2.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized String zzor() {
        return this.zzbdg;
    }
}
