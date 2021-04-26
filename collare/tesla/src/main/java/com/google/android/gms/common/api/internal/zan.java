package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

/* access modifiers changed from: package-private */
public final class zan implements Runnable {
    private final zam zadj;
    final /* synthetic */ zal zadk;

    zan(zal zal, zam zam) {
        this.zadk = zal;
        this.zadj = zam;
    }

    public final void run() {
        if (this.zadk.mStarted) {
            ConnectionResult connectionResult = this.zadj.getConnectionResult();
            if (connectionResult.hasResolution()) {
                this.zadk.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(this.zadk.getActivity(), connectionResult.getResolution(), this.zadj.zar(), false), 1);
            } else if (this.zadk.zacd.isUserResolvableError(connectionResult.getErrorCode())) {
                this.zadk.zacd.zaa(this.zadk.getActivity(), this.zadk.mLifecycleFragment, connectionResult.getErrorCode(), 2, this.zadk);
            } else if (connectionResult.getErrorCode() == 18) {
                this.zadk.zacd.zaa(this.zadk.getActivity().getApplicationContext(), new zao(this, GoogleApiAvailability.zaa(this.zadk.getActivity(), this.zadk)));
            } else {
                this.zadk.zaa(connectionResult, this.zadj.zar());
            }
        }
    }
}
