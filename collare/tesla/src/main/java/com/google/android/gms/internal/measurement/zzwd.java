package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

public final class zzwd {
    private final Clock clock;
    private String zzayt;
    @VisibleForTesting
    private Map<String, Object> zzbls;
    private final Map<String, Object> zzblt;
    private final zzwn zzboc;
    private final Context zzqx;

    public zzwd(Context context) {
        this(context, new HashMap(), new zzwn(context), DefaultClock.getInstance());
    }

    @VisibleForTesting
    private zzwd(Context context, Map<String, Object> map, zzwn zzwn, Clock clock2) {
        this.zzayt = null;
        this.zzbls = new HashMap();
        this.zzqx = context;
        this.clock = clock2;
        this.zzboc = zzwn;
        this.zzblt = map;
    }

    public final void zzeu(String str) {
        this.zzayt = str;
    }
}
