package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

@Deprecated
public class FirebaseInstanceIdService extends ad {
    @Deprecated
    public void a() {
    }

    /* access modifiers changed from: protected */
    @Override // com.google.firebase.iid.ad
    public final Intent a(Intent intent) {
        return y.a().f3956a.poll();
    }

    @Override // com.google.firebase.iid.ad
    public final void b(Intent intent) {
        if ("com.google.firebase.iid.TOKEN_REFRESH".equals(intent.getAction())) {
            a();
            return;
        }
        String stringExtra = intent.getStringExtra("CMD");
        if (stringExtra != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(intent.getExtras());
                StringBuilder sb = new StringBuilder(String.valueOf(stringExtra).length() + 21 + String.valueOf(valueOf).length());
                sb.append("Received command: ");
                sb.append(stringExtra);
                sb.append(" - ");
                sb.append(valueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
                FirebaseInstanceId.a().i();
            } else if ("SYNC".equals(stringExtra)) {
                FirebaseInstanceId.a().m();
            }
        }
    }
}
