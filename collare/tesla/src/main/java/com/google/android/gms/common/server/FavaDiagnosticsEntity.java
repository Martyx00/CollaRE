package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "FavaDiagnosticsEntityCreator")
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk
    public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new zaa();
    @SafeParcelable.VersionField(id = 1)
    private final int zalf;
    @SafeParcelable.Field(id = 2)
    private final String zapj;
    @SafeParcelable.Field(id = 3)
    private final int zapk;

    @SafeParcelable.Constructor
    public FavaDiagnosticsEntity(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i2) {
        this.zalf = i;
        this.zapj = str;
        this.zapk = i2;
    }

    @KeepForSdk
    public FavaDiagnosticsEntity(String str, int i) {
        this.zalf = 1;
        this.zapj = str;
        this.zapk = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeString(parcel, 2, this.zapj, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zapk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
