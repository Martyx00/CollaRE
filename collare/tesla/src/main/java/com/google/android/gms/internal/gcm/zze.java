package com.google.android.gms.internal.gcm;

import android.os.Parcel;
import android.os.Parcelable;

public class zze {
    private static final ClassLoader zzf = zze.class.getClassLoader();

    private zze() {
    }

    public static void zzd(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }
}
