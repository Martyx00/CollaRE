package com.google.firebase.iid;

import android.os.Bundle;

final class m extends n<Void> {
    m(int i, int i2, Bundle bundle) {
        super(i, 2, bundle);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.firebase.iid.n
    public final boolean a() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.firebase.iid.n
    public final void a(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            a((Object) null);
        } else {
            a(new o(4, "Invalid response to one way request"));
        }
    }
}
