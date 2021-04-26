package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
public final class zzgk extends zzgh {
    private static final String ID = zza.UNIVERSAL_ANALYTICS.toString();
    private static final String zzbfe = zzb.ACCOUNT.toString();
    private static final String zzbff = zzb.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzbfg = zzb.ENABLE_ECOMMERCE.toString();
    private static final String zzbfh = zzb.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzbfi = zzb.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzbfj = zzb.ANALYTICS_FIELDS.toString();
    private static final String zzbfk = zzb.TRACK_TRANSACTION.toString();
    private static final String zzbfl = zzb.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzbfm = zzb.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzbfn = Arrays.asList(ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, "checkout_option", "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND);
    private static final Pattern zzbfo = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzbfp = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzbfq;
    private static Map<String, String> zzbfr;
    private final DataLayer zzaxn;
    private final Set<String> zzbfs;
    private final zzgf zzbft;

    public zzgk(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzgf(context));
    }

    @VisibleForTesting
    private zzgk(Context context, DataLayer dataLayer, zzgf zzgf) {
        super(ID, new String[0]);
        this.zzaxn = dataLayer;
        this.zzbft = zzgf;
        this.zzbfs = new HashSet();
        this.zzbfs.add("");
        this.zzbfs.add("0");
        this.zzbfs.add("false");
    }

    private static boolean zzc(Map<String, zzm> map, String str) {
        zzm zzm = map.get(str);
        if (zzm == null) {
            return false;
        }
        return zzgj.zzg(zzm).booleanValue();
    }

    private static void zzd(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private final String zzdu(String str) {
        Object obj = this.zzaxn.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private static Product zzi(Map<String, Object> map) {
        String str;
        String valueOf;
        String str2;
        Product product = new Product();
        Object obj = map.get("id");
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        Object obj2 = map.get("name");
        if (obj2 != null) {
            product.setName(String.valueOf(obj2));
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(String.valueOf(obj3));
        }
        Object obj4 = map.get("category");
        if (obj4 != null) {
            product.setCategory(String.valueOf(obj4));
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(String.valueOf(obj5));
        }
        Object obj6 = map.get(FirebaseAnalytics.b.COUPON);
        if (obj6 != null) {
            product.setCouponCode(String.valueOf(obj6));
        }
        Object obj7 = map.get("position");
        if (obj7 != null) {
            product.setPosition(zzo(obj7).intValue());
        }
        Object obj8 = map.get(FirebaseAnalytics.b.PRICE);
        if (obj8 != null) {
            product.setPrice(zzn(obj8).doubleValue());
        }
        Object obj9 = map.get(FirebaseAnalytics.b.QUANTITY);
        if (obj9 != null) {
            product.setQuantity(zzo(obj9).intValue());
        }
        for (String str3 : map.keySet()) {
            Matcher matcher = zzbfo.matcher(str3);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str3)));
                } catch (NumberFormatException unused) {
                    str = "illegal number in custom dimension value: ";
                    valueOf = String.valueOf(str3);
                    if (valueOf.length() == 0) {
                        str2 = new String(str);
                        zzdi.zzab(str2);
                    }
                    str2 = str.concat(valueOf);
                    zzdi.zzab(str2);
                }
            } else {
                Matcher matcher2 = zzbfp.matcher(str3);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), zzo(map.get(str3)).intValue());
                    } catch (NumberFormatException unused2) {
                        str = "illegal number in custom metric value: ";
                        valueOf = String.valueOf(str3);
                        if (valueOf.length() == 0) {
                            str2 = new String(str);
                            zzdi.zzab(str2);
                        }
                        str2 = str.concat(valueOf);
                        zzdi.zzab(str2);
                    }
                }
            }
        }
        return product;
    }

    private static Map<String, String> zzi(zzm zzm) {
        Object zzh = zzgj.zzh(zzm);
        if (!(zzh instanceof Map)) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((Map) zzh).entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private final Map<String, String> zzj(zzm zzm) {
        if (zzm == null) {
            return new HashMap();
        }
        Map<String, String> zzi = zzi(zzm);
        if (zzi == null) {
            return new HashMap();
        }
        String str = zzi.get("&aip");
        if (str != null && this.zzbfs.contains(str.toLowerCase())) {
            zzi.remove("&aip");
        }
        return zzi;
    }

    private static Double zzn(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf) : new String("Cannot convert the object to Double: "));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf2) : new String("Cannot convert the object to Double: "));
        }
    }

    private static Integer zzo(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf) : new String("Cannot convert the object to Integer: "));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf2) : new String("Cannot convert the object to Integer: "));
        }
    }

    @Override // com.google.android.gms.tagmanager.zzgh, com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ zzm zze(Map map) {
        return super.zze(map);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0041, code lost:
        if ((r11 instanceof java.util.Map) != false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0052, code lost:
        if ((r11 instanceof java.util.Map) != false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0057, code lost:
        r11 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x017a  */
    @Override // com.google.android.gms.tagmanager.zzgh
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzg(java.util.Map<java.lang.String, com.google.android.gms.internal.measurement.zzm> r11) {
        /*
        // Method dump skipped, instructions count: 1137
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzgk.zzg(java.util.Map):void");
    }

    @Override // com.google.android.gms.tagmanager.zzgh, com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ boolean zzmj() {
        return super.zzmj();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ String zzns() {
        return super.zzns();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ Set zznt() {
        return super.zznt();
    }
}
