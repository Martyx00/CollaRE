package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* access modifiers changed from: package-private */
public final class zzcx extends zzbq {
    private static final String ID = zza.JOINER.toString();
    private static final String zzbaa = zzb.ARG0.toString();
    private static final String zzbat = zzb.ITEM_SEPARATOR.toString();
    private static final String zzbau = zzb.KEY_VALUE_SEPARATOR.toString();
    private static final String zzbav = zzb.ESCAPE.toString();

    public zzcx() {
        super(ID, zzbaa);
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Set<Ljava/lang/Character;>;)Ljava/lang/String; */
    private static String zza(String str, int i, Set set) {
        switch (zzcy.zzbaw[i - 1]) {
            case 1:
                try {
                    return zzgn.zzdv(str);
                } catch (UnsupportedEncodingException e) {
                    zzdi.zza("Joiner: unsupported encoding", e);
                    return str;
                }
            case 2:
                String replace = str.replace("\\", "\\\\");
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    String ch = ((Character) it.next()).toString();
                    String valueOf = String.valueOf(ch);
                    replace = replace.replace(ch, valueOf.length() != 0 ? "\\".concat(valueOf) : new String("\\"));
                }
                return replace;
            default:
                return str;
        }
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Set<Ljava/lang/Character;>;)V */
    private static void zza(StringBuilder sb, String str, int i, Set set) {
        sb.append(zza(str, i, set));
    }

    private static void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        zzm zzm = map.get(zzbaa);
        if (zzm == null) {
            return zzgj.zzpo();
        }
        zzm zzm2 = map.get(zzbat);
        String zzc = zzm2 != null ? zzgj.zzc(zzm2) : "";
        zzm zzm3 = map.get(zzbau);
        String zzc2 = zzm3 != null ? zzgj.zzc(zzm3) : "=";
        int i = zzcz.zzbax;
        zzm zzm4 = map.get(zzbav);
        HashSet hashSet = null;
        if (zzm4 != null) {
            String zzc3 = zzgj.zzc(zzm4);
            if (ImagesContract.URL.equals(zzc3)) {
                i = zzcz.zzbay;
            } else if ("backslash".equals(zzc3)) {
                i = zzcz.zzbaz;
                hashSet = new HashSet();
                zza(hashSet, zzc);
                zza(hashSet, zzc2);
                hashSet.remove('\\');
            } else {
                String valueOf = String.valueOf(zzc3);
                zzdi.e(valueOf.length() != 0 ? "Joiner: unsupported escape type: ".concat(valueOf) : new String("Joiner: unsupported escape type: "));
                return zzgj.zzpo();
            }
        }
        StringBuilder sb = new StringBuilder();
        switch (zzm.type) {
            case 2:
                zzm[] zzmArr = zzm.zzpy;
                int length = zzmArr.length;
                int i2 = 0;
                boolean z = true;
                while (i2 < length) {
                    zzm zzm5 = zzmArr[i2];
                    if (!z) {
                        sb.append(zzc);
                    }
                    zza(sb, zzgj.zzc(zzm5), i, hashSet);
                    i2++;
                    z = false;
                }
                break;
            case 3:
                for (int i3 = 0; i3 < zzm.zzpz.length; i3++) {
                    if (i3 > 0) {
                        sb.append(zzc);
                    }
                    String zzc4 = zzgj.zzc(zzm.zzpz[i3]);
                    String zzc5 = zzgj.zzc(zzm.zzqa[i3]);
                    zza(sb, zzc4, i, hashSet);
                    sb.append(zzc2);
                    zza(sb, zzc5, i, hashSet);
                }
                break;
            default:
                zza(sb, zzgj.zzc(zzm), i, hashSet);
                break;
        }
        return zzgj.zzj(sb.toString());
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
