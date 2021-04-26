package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.tagmanager.zzdi;
import com.google.android.gms.tagmanager.zzgj;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class zzwe {
    private static zzm zza(int i, zzi zzi, zzm[] zzmArr, Set<Integer> set) {
        if (set.contains(Integer.valueOf(i))) {
            String valueOf = String.valueOf(set);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 90);
            sb.append("Value cycle detected.  Current value reference: ");
            sb.append(i);
            sb.append(".  Previous value references: ");
            sb.append(valueOf);
            sb.append(".");
            zzei(sb.toString());
        }
        zzm zzm = (zzm) zza(zzi.zzoj, i, "values");
        if (zzmArr[i] != null) {
            return zzmArr[i];
        }
        zzm zzm2 = null;
        set.add(Integer.valueOf(i));
        int i2 = 0;
        switch (zzm.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zzm2 = zzm;
                break;
            case 2:
                zzc.zza zzl = zzl(zzm);
                zzm zzk = zzk(zzm);
                zzk.zzpy = new zzm[zzl.zzpk.length];
                int[] iArr = zzl.zzpk;
                int length = iArr.length;
                int i3 = 0;
                while (i2 < length) {
                    int i4 = i3 + 1;
                    zzk.zzpy[i3] = zza(iArr[i2], zzi, zzmArr, set);
                    i2++;
                    i3 = i4;
                }
                zzm2 = zzk;
                break;
            case 3:
                zzm2 = zzk(zzm);
                zzc.zza zzl2 = zzl(zzm);
                if (zzl2.zzpl.length != zzl2.zzpm.length) {
                    int length2 = zzl2.zzpl.length;
                    int length3 = zzl2.zzpm.length;
                    StringBuilder sb2 = new StringBuilder(58);
                    sb2.append("Uneven map keys (");
                    sb2.append(length2);
                    sb2.append(") and map values (");
                    sb2.append(length3);
                    sb2.append(")");
                    zzei(sb2.toString());
                }
                zzm2.zzpz = new zzm[zzl2.zzpl.length];
                zzm2.zzqa = new zzm[zzl2.zzpl.length];
                int[] iArr2 = zzl2.zzpl;
                int length4 = iArr2.length;
                int i5 = 0;
                int i6 = 0;
                while (i5 < length4) {
                    zzm2.zzpz[i6] = zza(iArr2[i5], zzi, zzmArr, set);
                    i5++;
                    i6++;
                }
                int[] iArr3 = zzl2.zzpm;
                int length5 = iArr3.length;
                int i7 = 0;
                while (i2 < length5) {
                    zzm2.zzqa[i7] = zza(iArr3[i2], zzi, zzmArr, set);
                    i2++;
                    i7++;
                }
                break;
            case 4:
                zzm2 = zzk(zzm);
                zzm2.zzqb = zzgj.zzc(zza(zzl(zzm).zzpp, zzi, zzmArr, set));
                break;
            case 7:
                zzm2 = zzk(zzm);
                zzc.zza zzl3 = zzl(zzm);
                zzm2.zzqf = new zzm[zzl3.zzpo.length];
                int[] iArr4 = zzl3.zzpo;
                int length6 = iArr4.length;
                int i8 = 0;
                while (i2 < length6) {
                    zzm2.zzqf[i8] = zza(iArr4[i2], zzi, zzmArr, set);
                    i2++;
                    i8++;
                }
                break;
        }
        if (zzm2 == null) {
            String valueOf2 = String.valueOf(zzm);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 15);
            sb3.append("Invalid value: ");
            sb3.append(valueOf2);
            zzei(sb3.toString());
        }
        zzmArr[i] = zzm2;
        set.remove(Integer.valueOf(i));
        return zzm2;
    }

    private static zzwg zza(zze zze, zzi zzi, zzm[] zzmArr, int i) {
        zzwh zzrx = zzwg.zzrx();
        for (int i2 : zze.zznt) {
            zzh zzh = (zzh) zza(zzi.zzok, Integer.valueOf(i2).intValue(), "properties");
            String str = (String) zza(zzi.zzoi, zzh.key, "keys");
            zzm zzm = (zzm) zza(zzmArr, zzh.value, "values");
            if (zzb.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzrx.zzm(zzm);
            } else {
                zzrx.zzb(str, zzm);
            }
        }
        return zzrx.zzry();
    }

    public static zzwi zza(zzi zzi) {
        zzm[] zzmArr = new zzm[zzi.zzoj.length];
        for (int i = 0; i < zzi.zzoj.length; i++) {
            zza(i, zzi, zzmArr, new HashSet(0));
        }
        zzwj zzrz = zzwi.zzrz();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < zzi.zzom.length; i2++) {
            arrayList.add(zza(zzi.zzom[i2], zzi, zzmArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < zzi.zzon.length; i3++) {
            arrayList2.add(zza(zzi.zzon[i3], zzi, zzmArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < zzi.zzol.length; i4++) {
            zzwg zza = zza(zzi.zzol[i4], zzi, zzmArr, i4);
            zzrz.zzc(zza);
            arrayList3.add(zza);
        }
        zzj[] zzjArr = zzi.zzoo;
        for (zzj zzj : zzjArr) {
            zzwl zzwl = new zzwl();
            for (int i5 : zzj.zzoy) {
                zzwl.zzd((zzwg) arrayList2.get(Integer.valueOf(i5).intValue()));
            }
            for (int i6 : zzj.zzoz) {
                zzwl.zze((zzwg) arrayList2.get(Integer.valueOf(i6).intValue()));
            }
            for (int i7 : zzj.zzpa) {
                zzwl.zzf((zzwg) arrayList.get(Integer.valueOf(i7).intValue()));
            }
            for (int i8 : zzj.zzpc) {
                zzwl.zzew(zzi.zzoj[Integer.valueOf(i8).intValue()].string);
            }
            for (int i9 : zzj.zzpb) {
                zzwl.zzg((zzwg) arrayList.get(Integer.valueOf(i9).intValue()));
            }
            for (int i10 : zzj.zzpd) {
                zzwl.zzex(zzi.zzoj[Integer.valueOf(i10).intValue()].string);
            }
            for (int i11 : zzj.zzpe) {
                zzwl.zzh((zzwg) arrayList3.get(Integer.valueOf(i11).intValue()));
            }
            for (int i12 : zzj.zzpg) {
                zzwl.zzey(zzi.zzoj[Integer.valueOf(i12).intValue()].string);
            }
            for (int i13 : zzj.zzpf) {
                zzwl.zzi((zzwg) arrayList3.get(Integer.valueOf(i13).intValue()));
            }
            for (int i14 : zzj.zzph) {
                zzwl.zzez(zzi.zzoj[Integer.valueOf(i14).intValue()].string);
            }
            zzrz.zzb(zzwl.zzse());
        }
        zzrz.zzev(zzi.version);
        zzrz.zzad(zzi.zzow);
        return zzrz.zzsb();
    }

    private static <T> T zza(T[] tArr, int i, String str) {
        if (i < 0 || i >= tArr.length) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 45);
            sb.append("Index out of bounds detected: ");
            sb.append(i);
            sb.append(" in ");
            sb.append(str);
            zzei(sb.toString());
        }
        return tArr[i];
    }

    public static void zza(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static void zzei(String str) {
        zzdi.e(str);
        throw new zzwm(str);
    }

    public static zzm zzk(zzm zzm) {
        zzm zzm2 = new zzm();
        zzm2.type = zzm.type;
        zzm2.zzqg = (int[]) zzm.zzqg.clone();
        if (zzm.zzqh) {
            zzm2.zzqh = zzm.zzqh;
        }
        return zzm2;
    }

    private static zzc.zza zzl(zzm zzm) {
        if (((zzc.zza) zzm.zza(zzc.zza.zzpi)) == null) {
            String valueOf = String.valueOf(zzm);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 54);
            sb.append("Expected a ServingValue and didn't get one. Value is: ");
            sb.append(valueOf);
            zzei(sb.toString());
        }
        return (zzc.zza) zzm.zza(zzc.zza.zzpi);
    }
}
