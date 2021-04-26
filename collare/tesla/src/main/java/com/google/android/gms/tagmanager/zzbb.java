package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzbb implements zzbx {
    private static final Object zzaxa = new Object();
    private static zzbb zzazr;
    private zzej zzaye;
    private zzby zzazs;

    private zzbb(Context context) {
        this(zzbz.zzo(context), new zzfl());
    }

    @VisibleForTesting
    private zzbb(zzby zzby, zzej zzej) {
        this.zzazs = zzby;
        this.zzaye = zzej;
    }

    public static zzbx zzi(Context context) {
        zzbb zzbb;
        synchronized (zzaxa) {
            if (zzazr == null) {
                zzazr = new zzbb(context);
            }
            zzbb = zzazr;
        }
        return zzbb;
    }

    @Override // com.google.android.gms.tagmanager.zzbx
    public final boolean zzdb(String str) {
        if (!this.zzaye.zzes()) {
            zzdi.zzab("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        this.zzazs.zzdg(str);
        return true;
    }
}
