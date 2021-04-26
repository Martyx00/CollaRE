package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* access modifiers changed from: package-private */
public class zzm extends zzgh {
    private static final String ID = com.google.android.gms.internal.measurement.zza.ARBITRARY_PIXEL.toString();
    private static final String URL = zzb.URL.toString();
    private static final String zzaxg = zzb.ADDITIONAL_PARAMS.toString();
    private static final String zzaxh = zzb.UNREPEATABLE.toString();
    private static final String zzaxi;
    private static final Set<String> zzaxj = new HashSet();
    private final zza zzaxk;
    private final Context zzqx;

    public interface zza {
        zzbx zzmk();
    }

    static {
        String str = ID;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 17);
        sb.append("gtm_");
        sb.append(str);
        sb.append("_unrepeatable");
        zzaxi = sb.toString();
    }

    public zzm(Context context) {
        this(context, new zzn(context));
    }

    @VisibleForTesting
    private zzm(Context context, zza zza2) {
        super(ID, URL);
        this.zzaxk = zza2;
        this.zzqx = context;
    }

    private final synchronized boolean zzco(String str) {
        if (zzaxj.contains(str)) {
            return true;
        }
        if (!this.zzqx.getSharedPreferences(zzaxi, 0).contains(str)) {
            return false;
        }
        zzaxj.add(str);
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzgh
    public final void zzg(Map<String, com.google.android.gms.internal.measurement.zzm> map) {
        String zzc = map.get(zzaxh) != null ? zzgj.zzc(map.get(zzaxh)) : null;
        if (zzc == null || !zzco(zzc)) {
            Uri.Builder buildUpon = Uri.parse(zzgj.zzc(map.get(URL))).buildUpon();
            com.google.android.gms.internal.measurement.zzm zzm = map.get(zzaxg);
            if (zzm != null) {
                Object zzh = zzgj.zzh(zzm);
                if (!(zzh instanceof List)) {
                    String valueOf = String.valueOf(buildUpon.build().toString());
                    zzdi.e(valueOf.length() != 0 ? "ArbitraryPixel: additional params not a list: not sending partial hit: ".concat(valueOf) : new String("ArbitraryPixel: additional params not a list: not sending partial hit: "));
                    return;
                }
                for (Object obj : (List) zzh) {
                    if (!(obj instanceof Map)) {
                        String valueOf2 = String.valueOf(buildUpon.build().toString());
                        zzdi.e(valueOf2.length() != 0 ? "ArbitraryPixel: additional params contains non-map: not sending partial hit: ".concat(valueOf2) : new String("ArbitraryPixel: additional params contains non-map: not sending partial hit: "));
                        return;
                    }
                    for (Map.Entry entry : ((Map) obj).entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            String uri = buildUpon.build().toString();
            this.zzaxk.zzmk().zzdb(uri);
            String valueOf3 = String.valueOf(uri);
            zzdi.v(valueOf3.length() != 0 ? "ArbitraryPixel: url = ".concat(valueOf3) : new String("ArbitraryPixel: url = "));
            if (zzc != null) {
                synchronized (zzm.class) {
                    zzaxj.add(zzc);
                    zzft.zza(this.zzqx, zzaxi, zzc, "true");
                }
            }
        }
    }
}
