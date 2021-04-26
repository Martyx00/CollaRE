package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;

public final class zaah implements zabd {
    private final zabe zaft;
    private boolean zafu = false;

    public zaah(zabe zabe) {
        this.zaft = zabe;
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void begin() {
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void onConnected(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        return (T) execute(t);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: T extends com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.zabd
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        try {
            this.zaft.zaee.zahf.zab(t);
            zaaw zaaw = this.zaft.zaee;
            Api.Client client = zaaw.zagz.get(t.getClientKey());
            Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
            if (client.isConnected() || !this.zaft.zahp.containsKey(t.getClientKey())) {
                boolean z = client instanceof SimpleClientAdapter;
                Object obj = client;
                if (z) {
                    obj = ((SimpleClientAdapter) client).getClient();
                }
                t.run(obj == 1 ? 1 : 0);
                return t;
            }
            t.setFailedResult(new Status(17));
            return t;
        } catch (DeadObjectException unused) {
            this.zaft.zaa(new zaai(this, this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final boolean disconnect() {
        if (this.zafu) {
            return false;
        }
        if (this.zaft.zaee.zaax()) {
            this.zafu = true;
            for (zacm zacm : this.zaft.zaee.zahe) {
                zacm.zabv();
            }
            return false;
        }
        this.zaft.zaf(null);
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void connect() {
        if (this.zafu) {
            this.zafu = false;
            this.zaft.zaa(new zaaj(this, this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void onConnectionSuspended(int i) {
        this.zaft.zaf(null);
        this.zaft.zaht.zab(i, this.zafu);
    }

    /* access modifiers changed from: package-private */
    public final void zaam() {
        if (this.zafu) {
            this.zafu = false;
            this.zaft.zaee.zahf.release();
            disconnect();
        }
    }
}
