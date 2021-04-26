package com.google.firebase.iid;

import android.os.Bundle;

/* access modifiers changed from: package-private */
public final class p extends n<Bundle> {
    p(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.firebase.iid.n
    public final boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.firebase.iid.n
    public final void a(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        a((Object) bundle2);
    }
}
