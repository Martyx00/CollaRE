package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@VisibleForTesting
public class TagManager {
    private static TagManager zzben;
    private final DataLayer zzaxn;
    private final zzal zzbco;
    private final zza zzbek;
    private final zzfm zzbel;
    private final ConcurrentMap<String, zzv> zzbem;
    private final Context zzqx;

    @VisibleForTesting
    public interface zza {
        zzy zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzal);
    }

    @VisibleForTesting
    private TagManager(Context context, zza zza2, DataLayer dataLayer, zzfm zzfm) {
        if (context != null) {
            this.zzqx = context.getApplicationContext();
            this.zzbel = zzfm;
            this.zzbek = zza2;
            this.zzbem = new ConcurrentHashMap();
            this.zzaxn = dataLayer;
            this.zzaxn.zza(new zzga(this));
            this.zzaxn.zza(new zzg(this.zzqx));
            this.zzbco = new zzal();
            this.zzqx.registerComponentCallbacks(new zzgc(this));
            zza.zzh(this.zzqx);
            return;
        }
        throw new NullPointerException("context cannot be null");
    }

    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzben == null) {
                if (context != null) {
                    zzben = new TagManager(context, new zzgb(), new DataLayer(new zzat(context)), zzfn.zzpc());
                } else {
                    zzdi.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
            }
            tagManager = zzben;
        }
        return tagManager;
    }

    /* access modifiers changed from: private */
    public final void zzdo(String str) {
        for (zzv zzv : this.zzbem.values()) {
            zzv.zzcr(str);
        }
    }

    public void dispatch() {
        this.zzbel.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.zzaxn;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, int i) {
        zzy zza2 = this.zzbek.zza(this.zzqx, this, null, str, i, this.zzbco);
        zza2.zzms();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, int i, Handler handler) {
        zzy zza2 = this.zzbek.zza(this.zzqx, this, handler.getLooper(), str, i, this.zzbco);
        zza2.zzms();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, int i) {
        zzy zza2 = this.zzbek.zza(this.zzqx, this, null, str, i, this.zzbco);
        zza2.zzmu();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, int i, Handler handler) {
        zzy zza2 = this.zzbek.zza(this.zzqx, this, handler.getLooper(), str, i, this.zzbco);
        zza2.zzmu();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, int i) {
        zzy zza2 = this.zzbek.zza(this.zzqx, this, null, str, i, this.zzbco);
        zza2.zzmt();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, int i, Handler handler) {
        zzy zza2 = this.zzbek.zza(this.zzqx, this, handler.getLooper(), str, i, this.zzbco);
        zza2.zzmt();
        return zza2;
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzdi.setLogLevel(z ? 2 : 5);
    }

    @VisibleForTesting
    public final int zza(zzv zzv) {
        this.zzbem.put(zzv.getContainerId(), zzv);
        return this.zzbem.size();
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzb(Uri uri) {
        boolean z;
        zzeh zzok = zzeh.zzok();
        if (zzok.zzb(uri)) {
            String containerId = zzok.getContainerId();
            switch (zzgd.zzbep[zzok.zzol().ordinal()]) {
                case 1:
                    zzv zzv = this.zzbem.get(containerId);
                    if (zzv != null) {
                        zzv.zzcs(null);
                        zzv.refresh();
                        break;
                    }
                    break;
                case 2:
                case 3:
                    for (String str : this.zzbem.keySet()) {
                        zzv zzv2 = this.zzbem.get(str);
                        if (str.equals(containerId)) {
                            zzv2.zzcs(zzok.zzom());
                        } else if (zzv2.zzmp() != null) {
                            zzv2.zzcs(null);
                        }
                        zzv2.refresh();
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    @VisibleForTesting
    public final boolean zzb(zzv zzv) {
        return this.zzbem.remove(zzv.getContainerId()) != null;
    }
}
