package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
public final class zzgj {
    private static final Object zzbev = null;
    private static Long zzbew = new Long(0);
    private static Double zzbex = new Double(0.0d);
    private static zzgi zzbey = zzgi.zzao(0);
    private static String zzbez = new String("");
    private static Boolean zzbfa = new Boolean(false);
    private static List<Object> zzbfb = new ArrayList(0);
    private static Map<Object, Object> zzbfc = new HashMap();
    private static zzm zzbfd = zzj(zzbez);

    private static double getDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        zzdi.e("getDouble received non-Number");
        return 0.0d;
    }

    public static String zzc(zzm zzm) {
        return zzi(zzh(zzm));
    }

    public static zzgi zzd(zzm zzm) {
        Object zzh = zzh(zzm);
        return zzh instanceof zzgi ? (zzgi) zzh : zzl(zzh) ? zzgi.zzao(zzm(zzh)) : zzk(zzh) ? zzgi.zza(Double.valueOf(getDouble(zzh))) : zzdt(zzi(zzh));
    }

    public static zzm zzds(String str) {
        zzm zzm = new zzm();
        zzm.type = 5;
        zzm.zzqc = str;
        return zzm;
    }

    private static zzgi zzdt(String str) {
        try {
            return zzgi.zzdr(str);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33);
            sb.append("Failed to convert '");
            sb.append(str);
            sb.append("' to a number.");
            zzdi.e(sb.toString());
            return zzbey;
        }
    }

    public static Long zze(zzm zzm) {
        long longValue;
        Object zzh = zzh(zzm);
        if (zzl(zzh)) {
            longValue = zzm(zzh);
        } else {
            zzgi zzdt = zzdt(zzi(zzh));
            if (zzdt == zzbey) {
                return zzbew;
            }
            longValue = zzdt.longValue();
        }
        return Long.valueOf(longValue);
    }

    public static Double zzf(zzm zzm) {
        double doubleValue;
        Object zzh = zzh(zzm);
        if (zzk(zzh)) {
            doubleValue = getDouble(zzh);
        } else {
            zzgi zzdt = zzdt(zzi(zzh));
            if (zzdt == zzbey) {
                return zzbex;
            }
            doubleValue = zzdt.doubleValue();
        }
        return Double.valueOf(doubleValue);
    }

    public static Boolean zzg(zzm zzm) {
        Object zzh = zzh(zzm);
        if (zzh instanceof Boolean) {
            return (Boolean) zzh;
        }
        String zzi = zzi(zzh);
        return "true".equalsIgnoreCase(zzi) ? Boolean.TRUE : "false".equalsIgnoreCase(zzi) ? Boolean.FALSE : zzbfa;
    }

    public static Object zzh(zzm zzm) {
        String str;
        if (zzm == null) {
            return null;
        }
        int i = 0;
        switch (zzm.type) {
            case 1:
                return zzm.string;
            case 2:
                ArrayList arrayList = new ArrayList(zzm.zzpy.length);
                zzm[] zzmArr = zzm.zzpy;
                int length = zzmArr.length;
                while (i < length) {
                    Object zzh = zzh(zzmArr[i]);
                    if (zzh == null) {
                        return null;
                    }
                    arrayList.add(zzh);
                    i++;
                }
                return arrayList;
            case 3:
                if (zzm.zzpz.length != zzm.zzqa.length) {
                    String valueOf = String.valueOf(zzm.toString());
                    zzdi.e(valueOf.length() != 0 ? "Converting an invalid value to object: ".concat(valueOf) : new String("Converting an invalid value to object: "));
                    return null;
                }
                HashMap hashMap = new HashMap(zzm.zzqa.length);
                while (i < zzm.zzpz.length) {
                    Object zzh2 = zzh(zzm.zzpz[i]);
                    Object zzh3 = zzh(zzm.zzqa[i]);
                    if (zzh2 == null || zzh3 == null) {
                        return null;
                    }
                    hashMap.put(zzh2, zzh3);
                    i++;
                }
                return hashMap;
            case 4:
                str = "Trying to convert a macro reference to object";
                break;
            case 5:
                str = "Trying to convert a function id to object";
                break;
            case 6:
                return Long.valueOf(zzm.zzqd);
            case 7:
                StringBuilder sb = new StringBuilder();
                zzm[] zzmArr2 = zzm.zzqf;
                int length2 = zzmArr2.length;
                while (i < length2) {
                    String zzi = zzi(zzh(zzmArr2[i]));
                    if (zzi == zzbez) {
                        return null;
                    }
                    sb.append(zzi);
                    i++;
                }
                return sb.toString();
            case 8:
                return Boolean.valueOf(zzm.zzqe);
            default:
                int i2 = zzm.type;
                StringBuilder sb2 = new StringBuilder(46);
                sb2.append("Failed to convert a value of type: ");
                sb2.append(i2);
                str = sb2.toString();
                break;
        }
        zzdi.e(str);
        return null;
    }

    private static String zzi(Object obj) {
        return obj == null ? zzbez : obj.toString();
    }

    public static zzm zzj(Object obj) {
        String obj2;
        zzm zzm = new zzm();
        if (obj instanceof zzm) {
            return (zzm) obj;
        }
        boolean z = false;
        if (obj instanceof String) {
            zzm.type = 1;
            obj2 = (String) obj;
        } else {
            if (obj instanceof List) {
                zzm.type = 2;
                List<Object> list = (List) obj;
                ArrayList arrayList = new ArrayList(list.size());
                boolean z2 = false;
                for (Object obj3 : list) {
                    zzm zzj = zzj(obj3);
                    zzm zzm2 = zzbfd;
                    if (zzj == zzm2) {
                        return zzm2;
                    }
                    z2 = z2 || zzj.zzqh;
                    arrayList.add(zzj);
                }
                zzm.zzpy = (zzm[]) arrayList.toArray(new zzm[0]);
                z = z2;
            } else if (obj instanceof Map) {
                zzm.type = 3;
                Set<Map.Entry> entrySet = ((Map) obj).entrySet();
                ArrayList arrayList2 = new ArrayList(entrySet.size());
                ArrayList arrayList3 = new ArrayList(entrySet.size());
                boolean z3 = false;
                for (Map.Entry entry : entrySet) {
                    zzm zzj2 = zzj(entry.getKey());
                    zzm zzj3 = zzj(entry.getValue());
                    zzm zzm3 = zzbfd;
                    if (zzj2 == zzm3 || zzj3 == zzm3) {
                        return zzbfd;
                    }
                    z3 = z3 || zzj2.zzqh || zzj3.zzqh;
                    arrayList2.add(zzj2);
                    arrayList3.add(zzj3);
                }
                zzm.zzpz = (zzm[]) arrayList2.toArray(new zzm[0]);
                zzm.zzqa = (zzm[]) arrayList3.toArray(new zzm[0]);
                z = z3;
            } else if (zzk(obj)) {
                zzm.type = 1;
                obj2 = obj.toString();
            } else if (zzl(obj)) {
                zzm.type = 6;
                zzm.zzqd = zzm(obj);
            } else if (obj instanceof Boolean) {
                zzm.type = 8;
                zzm.zzqe = ((Boolean) obj).booleanValue();
            } else {
                String valueOf = String.valueOf(obj == null ? "null" : obj.getClass().toString());
                zzdi.e(valueOf.length() != 0 ? "Converting to Value from unknown object type: ".concat(valueOf) : new String("Converting to Value from unknown object type: "));
                return zzbfd;
            }
            zzm.zzqh = z;
            return zzm;
        }
        zzm.string = obj2;
        zzm.zzqh = z;
        return zzm;
    }

    private static boolean zzk(Object obj) {
        if ((obj instanceof Double) || (obj instanceof Float)) {
            return true;
        }
        return (obj instanceof zzgi) && ((zzgi) obj).zzpg();
    }

    private static boolean zzl(Object obj) {
        if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
            return true;
        }
        return (obj instanceof zzgi) && ((zzgi) obj).zzph();
    }

    private static long zzm(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzdi.e("getInt64 received non-Number");
        return 0;
    }

    public static Object zzpi() {
        return null;
    }

    public static Long zzpj() {
        return zzbew;
    }

    public static Double zzpk() {
        return zzbex;
    }

    public static Boolean zzpl() {
        return zzbfa;
    }

    public static zzgi zzpm() {
        return zzbey;
    }

    public static String zzpn() {
        return zzbez;
    }

    public static zzm zzpo() {
        return zzbfd;
    }
}
