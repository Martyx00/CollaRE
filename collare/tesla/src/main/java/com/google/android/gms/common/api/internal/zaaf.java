package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* access modifiers changed from: package-private */
public final class zaaf {
    private final zai<?> zafq;
    private final TaskCompletionSource<Boolean> zafr = new TaskCompletionSource<>();

    public zaaf(zai<?> zai) {
        this.zafq = zai;
    }

    public final zai<?> zak() {
        return this.zafq;
    }

    public final TaskCompletionSource<Boolean> zaal() {
        return this.zafr;
    }
}
