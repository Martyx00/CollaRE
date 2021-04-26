package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzi;
import com.google.android.gms.internal.measurement.zzk;
import com.google.android.gms.internal.measurement.zzl;
import com.google.android.gms.internal.measurement.zzwe;
import com.google.android.gms.internal.measurement.zzwi;
import com.google.android.gms.internal.measurement.zzwm;
import com.google.android.gms.tagmanager.zzeh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
public class Container {
    private final String zzaxm;
    private final DataLayer zzaxn;
    private zzfb zzaxo;
    private Map<String, FunctionCallMacroCallback> zzaxp = new HashMap();
    private Map<String, FunctionCallTagCallback> zzaxq = new HashMap();
    private volatile long zzaxr;
    private volatile String zzaxs = "";
    private final Context zzqx;

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    /* access modifiers changed from: package-private */
    public class zza implements zzan {
        private zza() {
        }

        @Override // com.google.android.gms.tagmanager.zzan
        public final Object zza(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzcp = Container.this.zzcp(str);
            if (zzcp == null) {
                return null;
            }
            return zzcp.getValue(str, map);
        }
    }

    /* access modifiers changed from: package-private */
    public class zzb implements zzan {
        private zzb() {
        }

        @Override // com.google.android.gms.tagmanager.zzan
        public final Object zza(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzcq = Container.this.zzcq(str);
            if (zzcq != null) {
                zzcq.execute(str, map);
            }
            return zzgj.zzpn();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzl zzl) {
        this.zzqx = context;
        this.zzaxn = dataLayer;
        this.zzaxm = str;
        this.zzaxr = j;
        zzi zzi = zzl.zzpv;
        if (zzi != null) {
            try {
                zza(zzwe.zza(zzi));
            } catch (zzwm e) {
                String valueOf = String.valueOf(zzi);
                String zzwm = e.toString();
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46 + String.valueOf(zzwm).length());
                sb.append("Not loading resource: ");
                sb.append(valueOf);
                sb.append(" because it is invalid: ");
                sb.append(zzwm);
                zzdi.e(sb.toString());
            }
            if (zzl.zzpu != null) {
                zzk[] zzkArr = zzl.zzpu;
                ArrayList arrayList = new ArrayList();
                for (zzk zzk : zzkArr) {
                    arrayList.add(zzk);
                }
                zzmo().zzf(arrayList);
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzwi zzwi) {
        this.zzqx = context;
        this.zzaxn = dataLayer;
        this.zzaxm = str;
        this.zzaxr = 0;
        zza(zzwi);
    }

    private final void zza(zzwi zzwi) {
        this.zzaxs = zzwi.getVersion();
        String str = this.zzaxs;
        zzeh.zzok().zzol().equals(zzeh.zza.CONTAINER_DEBUG);
        zza(new zzfb(this.zzqx, zzwi, this.zzaxn, new zza(), new zzb(), new zzdq()));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzaxn.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzaxm));
        }
    }

    private final synchronized void zza(zzfb zzfb) {
        this.zzaxo = zzfb;
    }

    private final synchronized zzfb zzmo() {
        return this.zzaxo;
    }

    public boolean getBoolean(String str) {
        String sb;
        zzfb zzmo = zzmo();
        if (zzmo == null) {
            sb = "getBoolean called for closed container.";
        } else {
            try {
                return zzgj.zzg(zzmo.zzdm(str).getObject()).booleanValue();
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 66);
                sb2.append("Calling getBoolean() threw an exception: ");
                sb2.append(message);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzdi.e(sb);
        return zzgj.zzpl().booleanValue();
    }

    public String getContainerId() {
        return this.zzaxm;
    }

    public double getDouble(String str) {
        String sb;
        zzfb zzmo = zzmo();
        if (zzmo == null) {
            sb = "getDouble called for closed container.";
        } else {
            try {
                return zzgj.zzf(zzmo.zzdm(str).getObject()).doubleValue();
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 65);
                sb2.append("Calling getDouble() threw an exception: ");
                sb2.append(message);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzdi.e(sb);
        return zzgj.zzpk().doubleValue();
    }

    public long getLastRefreshTime() {
        return this.zzaxr;
    }

    public long getLong(String str) {
        String sb;
        zzfb zzmo = zzmo();
        if (zzmo == null) {
            sb = "getLong called for closed container.";
        } else {
            try {
                return zzgj.zze(zzmo.zzdm(str).getObject()).longValue();
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 63);
                sb2.append("Calling getLong() threw an exception: ");
                sb2.append(message);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzdi.e(sb);
        return zzgj.zzpj().longValue();
    }

    public String getString(String str) {
        String sb;
        zzfb zzmo = zzmo();
        if (zzmo == null) {
            sb = "getString called for closed container.";
        } else {
            try {
                return zzgj.zzc(zzmo.zzdm(str).getObject());
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 65);
                sb2.append("Calling getString() threw an exception: ");
                sb2.append(message);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzdi.e(sb);
        return zzgj.zzpn();
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String str, FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback != null) {
            synchronized (this.zzaxp) {
                this.zzaxp.put(str, functionCallMacroCallback);
            }
            return;
        }
        throw new NullPointerException("Macro handler must be non-null");
    }

    public void registerFunctionCallTagCallback(String str, FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback != null) {
            synchronized (this.zzaxq) {
                this.zzaxq.put(str, functionCallTagCallback);
            }
            return;
        }
        throw new NullPointerException("Tag callback must be non-null");
    }

    /* access modifiers changed from: package-private */
    public final void release() {
        this.zzaxo = null;
    }

    public void unregisterFunctionCallMacroCallback(String str) {
        synchronized (this.zzaxp) {
            this.zzaxp.remove(str);
        }
    }

    public void unregisterFunctionCallTagCallback(String str) {
        synchronized (this.zzaxq) {
            this.zzaxq.remove(str);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final FunctionCallMacroCallback zzcp(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzaxp) {
            functionCallMacroCallback = this.zzaxp.get(str);
        }
        return functionCallMacroCallback;
    }

    @VisibleForTesting
    public final FunctionCallTagCallback zzcq(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzaxq) {
            functionCallTagCallback = this.zzaxq.get(str);
        }
        return functionCallTagCallback;
    }

    @VisibleForTesting
    public final void zzcr(String str) {
        zzmo().zzcr(str);
    }

    @VisibleForTesting
    public final String zzmn() {
        return this.zzaxs;
    }
}
