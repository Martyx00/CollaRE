package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;

@SafeParcelable.Class(creator = "EventParamsCreator")
@SafeParcelable.Reserved({1})
public final class zzeu extends AbstractSafeParcelable implements Iterable<String> {
    public static final Parcelable.Creator<zzeu> CREATOR = new zzew();
    @SafeParcelable.Field(getter = "z", id = 2)
    private final Bundle zzaho;

    @SafeParcelable.Constructor
    zzeu(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.zzaho = bundle;
    }

    /* access modifiers changed from: package-private */
    public final Object get(String str) {
        return this.zzaho.get(str);
    }

    /* access modifiers changed from: package-private */
    public final Long getLong(String str) {
        return Long.valueOf(this.zzaho.getLong(str));
    }

    /* access modifiers changed from: package-private */
    public final String getString(String str) {
        return this.zzaho.getString(str);
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return new zzev(this);
    }

    public final int size() {
        return this.zzaho.size();
    }

    public final String toString() {
        return this.zzaho.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zzin(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: package-private */
    public final Double zzbk(String str) {
        return Double.valueOf(this.zzaho.getDouble(str));
    }

    public final Bundle zzin() {
        return new Bundle(this.zzaho);
    }
}
