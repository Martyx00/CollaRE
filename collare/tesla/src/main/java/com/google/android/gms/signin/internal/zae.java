package com.google.android.gms.signin.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public abstract class zae extends zab implements zad {
    public zae() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.base.zab
    public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 3:
                zaa((ConnectionResult) zac.zaa(parcel, ConnectionResult.CREATOR), (zaa) zac.zaa(parcel, zaa.CREATOR));
                break;
            case 4:
                zag((Status) zac.zaa(parcel, Status.CREATOR));
                break;
            case 5:
            default:
                return false;
            case 6:
                zah((Status) zac.zaa(parcel, Status.CREATOR));
                break;
            case 7:
                zaa((Status) zac.zaa(parcel, Status.CREATOR), (GoogleSignInAccount) zac.zaa(parcel, GoogleSignInAccount.CREATOR));
                break;
            case 8:
                zab((zaj) zac.zaa(parcel, zaj.CREATOR));
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
