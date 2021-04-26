package com.google.android.gms.internal.gcm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
public final class zzi extends WeakReference<Throwable> {
    private final int zzdh;

    public zzi(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th != null) {
            this.zzdh = System.identityHashCode(th);
            return;
        }
        throw new NullPointerException("The referent cannot be null");
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzi zzi = (zzi) obj;
            return this.zzdh == zzi.zzdh && get() == zzi.get();
        }
    }

    public final int hashCode() {
        return this.zzdh;
    }
}
