package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final /* synthetic */ class zaa implements Comparator {
    static final Comparator zaq = new zaa();

    private zaa() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return GoogleSignInAccount.zaa((Scope) obj, (Scope) obj2);
    }
}
