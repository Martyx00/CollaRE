package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zabu extends zal {
    private TaskCompletionSource<Void> zajp = new TaskCompletionSource<>();

    public static zabu zac(Activity activity) {
        LifecycleFragment fragment = getFragment(activity);
        zabu zabu = (zabu) fragment.getCallbackOrNull("GmsAvailabilityHelper", zabu.class);
        if (zabu == null) {
            return new zabu(fragment);
        }
        if (zabu.zajp.getTask().isComplete()) {
            zabu.zajp = new TaskCompletionSource<>();
        }
        return zabu;
    }

    private zabu(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.zal
    public final void zaa(ConnectionResult connectionResult, int i) {
        this.zajp.setException(ApiExceptionUtil.fromStatus(new Status(connectionResult.getErrorCode(), connectionResult.getErrorMessage(), connectionResult.getResolution())));
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.zal
    public final void zao() {
        int isGooglePlayServicesAvailable = this.zacd.isGooglePlayServicesAvailable(this.mLifecycleFragment.getLifecycleActivity());
        if (isGooglePlayServicesAvailable == 0) {
            this.zajp.setResult(null);
        } else if (!this.zajp.getTask().isComplete()) {
            zab(new ConnectionResult(isGooglePlayServicesAvailable, null), 0);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onDestroy() {
        super.onDestroy();
        this.zajp.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    public final Task<Void> getTask() {
        return this.zajp.getTask();
    }
}
