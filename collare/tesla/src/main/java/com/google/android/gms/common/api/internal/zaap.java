package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

final class zaap extends zabf {
    private final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks zago;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zaap(zaan zaan, zabd zabd, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zabd);
        this.zago = connectionProgressReportCallbacks;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zaan() {
        this.zago.onReportServiceBinding(new ConnectionResult(16, null));
    }
}
