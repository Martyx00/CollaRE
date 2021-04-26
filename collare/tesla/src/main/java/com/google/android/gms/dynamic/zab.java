package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;

final class zab implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ DeferredLifecycleHelper zarj;
    private final /* synthetic */ Bundle zark;
    private final /* synthetic */ Bundle zarl;

    zab(DeferredLifecycleHelper deferredLifecycleHelper, Activity activity, Bundle bundle, Bundle bundle2) {
        this.zarj = deferredLifecycleHelper;
        this.val$activity = activity;
        this.zark = bundle;
        this.zarl = bundle2;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 0;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        DeferredLifecycleHelper.zab(this.zarj).onInflate(this.val$activity, this.zark, this.zarl);
    }
}
