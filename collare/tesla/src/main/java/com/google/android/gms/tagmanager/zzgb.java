package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.tagmanager.TagManager;

/* access modifiers changed from: package-private */
public final class zzgb implements TagManager.zza {
    zzgb() {
    }

    @Override // com.google.android.gms.tagmanager.TagManager.zza
    public final zzy zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzal) {
        return new zzy(context, tagManager, looper, str, i, zzal);
    }
}
