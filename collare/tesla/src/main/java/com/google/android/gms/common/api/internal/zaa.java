package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0066zaa> zacl;

    public zaa(Activity activity) {
        this(C0066zaa.zaa(activity));
    }

    private zaa(C0066zaa zaa) {
        this.zacl = new WeakReference<>(zaa);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0066zaa zaa = this.zacl.get();
        if (zaa != null) {
            zaa.zaa((C0066zaa) runnable);
            return this;
        }
        throw new IllegalStateException("The target activity has already been GC'd");
    }

    /* renamed from: com.google.android.gms.common.api.internal.zaa$zaa  reason: collision with other inner class name */
    static class C0066zaa extends LifecycleCallback {
        private List<Runnable> zacm = new ArrayList();

        /* access modifiers changed from: private */
        public static C0066zaa zaa(Activity activity) {
            C0066zaa zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                zaa = (C0066zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0066zaa.class);
                if (zaa == null) {
                    zaa = new C0066zaa(fragment);
                }
            }
            return zaa;
        }

        private C0066zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final synchronized void zaa(Runnable runnable) {
            this.zacm.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacm;
                this.zacm = new ArrayList();
            }
            for (Runnable runnable : list) {
                runnable.run();
            }
        }
    }
}
