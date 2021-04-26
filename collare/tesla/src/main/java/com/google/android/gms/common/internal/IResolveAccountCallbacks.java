package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public interface IResolveAccountCallbacks extends IInterface {

    public static abstract class Stub extends zab implements IResolveAccountCallbacks {
        public Stub() {
            super("com.google.android.gms.common.internal.IResolveAccountCallbacks");
        }

        public static class Proxy extends zaa implements IResolveAccountCallbacks {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IResolveAccountCallbacks");
            }

            @Override // com.google.android.gms.common.internal.IResolveAccountCallbacks
            public void onAccountResolutionComplete(ResolveAccountResponse resolveAccountResponse) {
                Parcel zaa = zaa();
                zac.zaa(zaa, resolveAccountResponse);
                zab(2, zaa);
            }
        }

        public static IResolveAccountCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
            if (queryLocalInterface instanceof IResolveAccountCallbacks) {
                return (IResolveAccountCallbacks) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.base.zab
        public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 2) {
                return false;
            }
            onAccountResolutionComplete((ResolveAccountResponse) zac.zaa(parcel, ResolveAccountResponse.CREATOR));
            parcel2.writeNoException();
            return true;
        }
    }

    void onAccountResolutionComplete(ResolveAccountResponse resolveAccountResponse);
}
