package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;

/* access modifiers changed from: package-private */
public final class zzab extends zzz<Bundle> {
    zzab(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.iid.zzz
    public final void zzh(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(bundle2);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16 + String.valueOf(valueOf2).length());
            sb.append("Finishing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzcl.setResult(bundle2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.iid.zzz
    public final boolean zzu() {
        return false;
    }
}
