package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import android.util.LogPrinter;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;

@VisibleForTesting
public final class zze implements zzo {
    private static final Uri zzqs;
    private final LogPrinter zzrm = new LogPrinter(4, "GA/LogCatTransport");

    static {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority(ImagesContract.LOCAL);
        zzqs = builder.build();
    }

    @Override // com.google.android.gms.analytics.zzo
    public final void zzb(zzg zzg) {
        ArrayList arrayList = new ArrayList(zzg.zzp());
        Collections.sort(arrayList, new zzf(this));
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            String obj2 = ((zzi) obj).toString();
            if (!TextUtils.isEmpty(obj2)) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(obj2);
            }
        }
        this.zzrm.println(sb.toString());
    }

    @Override // com.google.android.gms.analytics.zzo
    public final Uri zzk() {
        return zzqs;
    }
}
