package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;

final class zaa implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zarj;

    zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zarj = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(T t) {
        DeferredLifecycleHelper.zaa(this.zarj, (LifecycleDelegate) t);
        Iterator it = DeferredLifecycleHelper.zaa(this.zarj).iterator();
        while (it.hasNext()) {
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(DeferredLifecycleHelper.zab(this.zarj));
        }
        DeferredLifecycleHelper.zaa(this.zarj).clear();
        DeferredLifecycleHelper.zaa(this.zarj, (Bundle) null);
    }
}
