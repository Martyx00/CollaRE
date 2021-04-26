package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;

@KeepForSdk
@KeepName
public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new zza();
    private IBinder zzcz;

    public BinderWrapper() {
        this.zzcz = null;
    }

    public final int describeContents() {
        return 0;
    }

    @KeepForSdk
    public BinderWrapper(IBinder iBinder) {
        this.zzcz = null;
        this.zzcz = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.zzcz = null;
        this.zzcz = parcel.readStrongBinder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.zzcz);
    }

    /* synthetic */ BinderWrapper(Parcel parcel, zza zza) {
        this(parcel);
    }
}
