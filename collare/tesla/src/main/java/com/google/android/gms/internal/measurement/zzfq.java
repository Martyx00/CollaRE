package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzfq implements Runnable {
    private final String packageName;
    private final URL url;
    private final byte[] zzalk;
    private final zzfo zzall;
    private final Map<String, String> zzalm;
    private final /* synthetic */ zzfm zzaln;

    public zzfq(zzfm zzfm, String str, URL url2, byte[] bArr, Map<String, String> map, zzfo zzfo) {
        this.zzaln = zzfm;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url2);
        Preconditions.checkNotNull(zzfo);
        this.url = url2;
        this.zzalk = bArr;
        this.zzall = zzfo;
        this.packageName = str;
        this.zzalm = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c4 A[SYNTHETIC, Splitter:B:44:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0101 A[SYNTHETIC, Splitter:B:57:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 308
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfq.run():void");
    }
}
