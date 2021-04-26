package com.google.firebase.messaging;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.a;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Map;

@SafeParcelable.Class(creator = "RemoteMessageCreator")
@SafeParcelable.Reserved({1})
public final class d extends AbstractSafeParcelable {
    public static final Parcelable.Creator<d> CREATOR = new g();
    @SafeParcelable.Field(id = 2)

    /* renamed from: a  reason: collision with root package name */
    Bundle f3965a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, String> f3966b;

    @SafeParcelable.Constructor
    public d(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.f3965a = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.f3965a, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final Map<String, String> a() {
        if (this.f3966b == null) {
            Bundle bundle = this.f3965a;
            a aVar = new a();
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!str.startsWith("google.") && !str.startsWith("gcm.") && !str.equals("from") && !str.equals("message_type") && !str.equals("collapse_key")) {
                        aVar.put(str, str2);
                    }
                }
            }
            this.f3966b = aVar;
        }
        return this.f3966b;
    }
}
