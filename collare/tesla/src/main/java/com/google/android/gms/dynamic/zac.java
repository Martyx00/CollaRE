package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;

final class zac implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ DeferredLifecycleHelper zarj;
    private final /* synthetic */ Bundle zarl;

    zac(DeferredLifecycleHelper deferredLifecycleHelper, Bundle bundle) {
        this.zarj = deferredLifecycleHelper;
        this.zarl = bundle;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 1;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        DeferredLifecycleHelper.zab(this.zarj).onCreate(this.zarl);
    }
}
