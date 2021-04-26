package com.google.android.gms.common.api.internal;

import android.support.v4.util.a;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zaaa implements OnCompleteListener<Map<zai<?>, String>> {
    private final /* synthetic */ zax zafi;
    private SignInConnectionListener zafj;

    zaaa(zax zax, SignInConnectionListener signInConnectionListener) {
        this.zafi = zax;
        this.zafj = signInConnectionListener;
    }

    /* access modifiers changed from: package-private */
    public final void cancel() {
        this.zafj.onComplete();
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<Map<zai<?>, String>> task) {
        this.zafi.zaeo.lock();
        try {
            if (!(this.zafi.zafd)) {
                this.zafj.onComplete();
                return;
            }
            if (task.isSuccessful()) {
                this.zafi.zaff = new a(this.zafi.zaev.size());
                for (zaw zaw : this.zafi.zaev.values()) {
                    this.zafi.zaff.put(zaw.zak(), ConnectionResult.RESULT_SUCCESS);
                }
            } else if (task.getException() instanceof AvailabilityException) {
                AvailabilityException availabilityException = (AvailabilityException) task.getException();
                if (this.zafi.zafb) {
                    this.zafi.zaff = new a(this.zafi.zaev.size());
                    for (zaw zaw2 : this.zafi.zaev.values()) {
                        zai zak = zaw2.zak();
                        ConnectionResult connectionResult = availabilityException.getConnectionResult(zaw2);
                        if (this.zafi.zaa(zaw2, connectionResult)) {
                            this.zafi.zaff.put(zak, new ConnectionResult(16));
                        } else {
                            this.zafi.zaff.put(zak, connectionResult);
                        }
                    }
                } else {
                    this.zafi.zaff = availabilityException.zaj();
                }
            } else {
                Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                this.zafi.zaff = Collections.emptyMap();
            }
            if (this.zafi.isConnected()) {
                this.zafi.zafe.putAll(this.zafi.zaff);
                if (this.zafi.zaaf() == null) {
                    this.zafi.zaad();
                    this.zafi.zaae();
                    this.zafi.zaez.signalAll();
                }
            }
            this.zafj.onComplete();
            this.zafi.zaeo.unlock();
        } finally {
            this.zafi.zaeo.unlock();
        }
    }
}
