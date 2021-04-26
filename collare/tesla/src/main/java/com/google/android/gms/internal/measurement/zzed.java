package com.google.android.gms.internal.measurement;

import android.support.v4.util.a;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* access modifiers changed from: package-private */
public final class zzed extends zzjs {
    zzed(zzjt zzjt) {
        super(zzjt);
    }

    private final Boolean zza(double d2, zzkj zzkj) {
        try {
            return zza(new BigDecimal(d2), zzkj, Math.ulp(d2));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(long j, zzkj zzkj) {
        try {
            return zza(new BigDecimal(j), zzkj, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(zzkh zzkh, String str, zzks[] zzksArr, long j) {
        Boolean bool;
        String str2;
        Object obj;
        if (zzkh.zzato != null) {
            Boolean zza = zza(j, zzkh.zzato);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        zzki[] zzkiArr = zzkh.zzatm;
        for (zzki zzki : zzkiArr) {
            if (TextUtils.isEmpty(zzki.zzatt)) {
                zzgi().zziy().zzg("null or empty param name in filter. event", zzgf().zzbm(str));
                return null;
            }
            hashSet.add(zzki.zzatt);
        }
        a aVar = new a();
        for (zzks zzks : zzksArr) {
            if (hashSet.contains(zzks.name)) {
                if (zzks.zzave != null) {
                    str2 = zzks.name;
                    obj = zzks.zzave;
                } else if (zzks.zzasw != null) {
                    str2 = zzks.name;
                    obj = zzks.zzasw;
                } else if (zzks.zzale != null) {
                    str2 = zzks.name;
                    obj = zzks.zzale;
                } else {
                    zzgi().zziy().zze("Unknown value for param. event, param", zzgf().zzbm(str), zzgf().zzbn(zzks.name));
                    return null;
                }
                aVar.put(str2, obj);
            }
        }
        zzki[] zzkiArr2 = zzkh.zzatm;
        for (zzki zzki2 : zzkiArr2) {
            boolean equals = Boolean.TRUE.equals(zzki2.zzats);
            String str3 = zzki2.zzatt;
            if (TextUtils.isEmpty(str3)) {
                zzgi().zziy().zzg("Event has empty param name. event", zzgf().zzbm(str));
                return null;
            }
            Object obj2 = aVar.get(str3);
            if (obj2 instanceof Long) {
                if (zzki2.zzatr == null) {
                    zzgi().zziy().zze("No number filter for long param. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                    return null;
                }
                Boolean zza2 = zza(((Long) obj2).longValue(), zzki2.zzatr);
                if (zza2 == null) {
                    return null;
                }
                if ((true ^ zza2.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj2 instanceof Double) {
                if (zzki2.zzatr == null) {
                    zzgi().zziy().zze("No number filter for double param. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                    return null;
                }
                Boolean zza3 = zza(((Double) obj2).doubleValue(), zzki2.zzatr);
                if (zza3 == null) {
                    return null;
                }
                if ((true ^ zza3.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj2 instanceof String) {
                if (zzki2.zzatq != null) {
                    bool = zza((String) obj2, zzki2.zzatq);
                } else if (zzki2.zzatr != null) {
                    String str4 = (String) obj2;
                    if (zzjz.zzcf(str4)) {
                        bool = zza(str4, zzki2.zzatr);
                    } else {
                        zzgi().zziy().zze("Invalid param value for number filter. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                        return null;
                    }
                } else {
                    zzgi().zziy().zze("No filter for String param. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                    return null;
                }
                if (bool == null) {
                    return null;
                }
                if ((true ^ bool.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj2 == null) {
                zzgi().zzjc().zze("Missing param for filter. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                return false;
            } else {
                zzgi().zziy().zze("Unknown param type. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                return null;
            }
        }
        return true;
    }

    private final Boolean zza(zzkk zzkk, zzkx zzkx) {
        zzfk zziy;
        String str;
        Boolean zza;
        zzki zzki = zzkk.zzaud;
        if (zzki == null) {
            zziy = zzgi().zziy();
            str = "Missing property filter. property";
        } else {
            boolean equals = Boolean.TRUE.equals(zzki.zzats);
            if (zzkx.zzave != null) {
                if (zzki.zzatr == null) {
                    zziy = zzgi().zziy();
                    str = "No number filter for long property. property";
                } else {
                    zza = zza(zzkx.zzave.longValue(), zzki.zzatr);
                }
            } else if (zzkx.zzasw != null) {
                if (zzki.zzatr == null) {
                    zziy = zzgi().zziy();
                    str = "No number filter for double property. property";
                } else {
                    zza = zza(zzkx.zzasw.doubleValue(), zzki.zzatr);
                }
            } else if (zzkx.zzale == null) {
                zziy = zzgi().zziy();
                str = "User property has no value, property";
            } else if (zzki.zzatq == null) {
                if (zzki.zzatr == null) {
                    zzgi().zziy().zzg("No string or number filter defined. property", zzgf().zzbo(zzkx.name));
                } else if (zzjz.zzcf(zzkx.zzale)) {
                    zza = zza(zzkx.zzale, zzki.zzatr);
                } else {
                    zzgi().zziy().zze("Invalid user property value for Numeric number filter. property, value", zzgf().zzbo(zzkx.name), zzkx.zzale);
                }
                return null;
            } else {
                zza = zza(zzkx.zzale, zzki.zzatq);
            }
            return zza(zza, equals);
        }
        zziy.zzg(str, zzgf().zzbo(zzkx.name));
        return null;
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        boolean startsWith;
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    zzgi().zziy().zzg("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                startsWith = str.startsWith(str2);
                break;
            case 3:
                startsWith = str.endsWith(str2);
                break;
            case 4:
                startsWith = str.contains(str2);
                break;
            case 5:
                startsWith = str.equals(str2);
                break;
            case 6:
                startsWith = list.contains(str);
                break;
            default:
                return null;
        }
        return Boolean.valueOf(startsWith);
    }

    private final Boolean zza(String str, zzkj zzkj) {
        if (!zzjz.zzcf(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzkj, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzkl zzkl) {
        ArrayList arrayList;
        Preconditions.checkNotNull(zzkl);
        if (str == null || zzkl.zzaue == null || zzkl.zzaue.intValue() == 0) {
            return null;
        }
        if (zzkl.zzaue.intValue() == 6) {
            if (zzkl.zzauh == null || zzkl.zzauh.length == 0) {
                return null;
            }
        } else if (zzkl.zzauf == null) {
            return null;
        }
        int intValue = zzkl.zzaue.intValue();
        boolean z = zzkl.zzaug != null && zzkl.zzaug.booleanValue();
        String upperCase = (z || intValue == 1 || intValue == 6) ? zzkl.zzauf : zzkl.zzauf.toUpperCase(Locale.ENGLISH);
        if (zzkl.zzauh == null) {
            arrayList = null;
        } else {
            String[] strArr = zzkl.zzauh;
            if (z) {
                arrayList = Arrays.asList(strArr);
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (String str2 : strArr) {
                    arrayList2.add(str2.toUpperCase(Locale.ENGLISH));
                }
                arrayList = arrayList2;
            }
        }
        return zza(str, intValue, z, upperCase, arrayList, intValue == 1 ? upperCase : null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0071, code lost:
        if (r3 != null) goto L_0x0073;
     */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r7, com.google.android.gms.internal.measurement.zzkj r8, double r9) {
        /*
        // Method dump skipped, instructions count: 250
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzed.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzkj, double):java.lang.Boolean");
    }

    private final void zza(Integer num, Integer num2, zzki zzki, Boolean bool, Boolean bool2) {
        if (zzki == null) {
            zzgi().zziy().zze("The leaf filter of event or user property filter is null. audience ID, filter ID", num, num2);
            return;
        }
        boolean z = true;
        zzki.zzatu = Boolean.valueOf((bool != null && bool.booleanValue()) || (bool2 != null && bool2.booleanValue()));
        if (bool2 == null || !bool2.booleanValue()) {
            z = false;
        }
        zzki.zzatv = Boolean.valueOf(z);
    }

    private static void zza(Map<Integer, Long> map, int i, long j) {
        Long l = map.get(Integer.valueOf(i));
        long j2 = j / 1000;
        if (l == null || j2 > l.longValue()) {
            map.put(Integer.valueOf(i), Long.valueOf(j2));
        }
    }

    private static zzkq[] zzd(Map<Integer, Long> map) {
        if (map == null) {
            return null;
        }
        int i = 0;
        zzkq[] zzkqArr = new zzkq[map.size()];
        for (Integer num : map.keySet()) {
            zzkq zzkq = new zzkq();
            zzkq.zzaux = num;
            zzkq.zzauy = map.get(num);
            zzkqArr[i] = zzkq;
            i++;
        }
        return zzkqArr;
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzkg[] zzkgArr) {
        Preconditions.checkNotNull(zzkgArr);
        for (zzkg zzkg : zzkgArr) {
            zzkh[] zzkhArr = zzkg.zzatg;
            for (zzkh zzkh : zzkhArr) {
                String zzal = AppMeasurement.Event.zzal(zzkh.zzatl);
                if (zzal != null) {
                    zzkh.zzatl = zzal;
                }
                zzki[] zzkiArr = zzkh.zzatm;
                for (zzki zzki : zzkiArr) {
                    String zzal2 = AppMeasurement.Param.zzal(zzki.zzatt);
                    if (zzal2 != null) {
                        zzki.zzatt = zzal2;
                    }
                    zza(zzkg.zzate, zzkh.zzatk, zzki, zzkg.zzath, zzkg.zzati);
                }
            }
            zzkk[] zzkkArr = zzkg.zzatf;
            for (zzkk zzkk : zzkkArr) {
                String zzal3 = AppMeasurement.UserProperty.zzal(zzkk.zzauc);
                if (zzal3 != null) {
                    zzkk.zzauc = zzal3;
                }
                zza(zzkg.zzate, zzkk.zzatk, zzkk.zzaud, zzkg.zzath, zzkg.zzati);
            }
        }
        zzjh().zzb(str, zzkgArr);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x02d5  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x036e  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x03c0  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x03e6  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0407  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x04d5  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x05ae  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x05b1  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x05b9  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x029c  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x02b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzkp[] zza(java.lang.String r49, com.google.android.gms.internal.measurement.zzkr[] r50, com.google.android.gms.internal.measurement.zzkx[] r51) {
        /*
        // Method dump skipped, instructions count: 2761
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzed.zza(java.lang.String, com.google.android.gms.internal.measurement.zzkr[], com.google.android.gms.internal.measurement.zzkx[]):com.google.android.gms.internal.measurement.zzkp[]");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzjs
    public final boolean zzgn() {
        return false;
    }
}
