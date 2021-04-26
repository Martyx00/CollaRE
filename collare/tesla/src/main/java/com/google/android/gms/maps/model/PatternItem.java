package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "PatternItemCreator")
@SafeParcelable.Reserved({1})
public class PatternItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzi();
    private static final String TAG = "PatternItem";
    @SafeParcelable.Field(getter = "getType", id = 2)
    private final int type;
    @SafeParcelable.Field(getter = "getLength", id = 3)
    private final Float zzdv;

    @SafeParcelable.Constructor
    public PatternItem(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) Float f) {
        boolean z = true;
        if (i != 1 && (f == null || f.floatValue() < BitmapDescriptorFactory.HUE_RED)) {
            z = false;
        }
        String valueOf = String.valueOf(f);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 45);
        sb.append("Invalid PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        Preconditions.checkArgument(z, sb.toString());
        this.type = i;
        this.zzdv = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.writeFloatObject(parcel, 3, this.zzdv, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.zzdv);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.type == patternItem.type && Objects.equal(this.zzdv, patternItem.zzdv);
    }

    public String toString() {
        int i = this.type;
        String valueOf = String.valueOf(this.zzdv);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39);
        sb.append("[PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    static List<PatternItem> zza(List<PatternItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (PatternItem patternItem : list) {
            if (patternItem == null) {
                patternItem = null;
            } else {
                int i = patternItem.type;
                switch (i) {
                    case 0:
                        patternItem = new Dash(patternItem.zzdv.floatValue());
                        continue;
                    case 1:
                        patternItem = new Dot();
                        continue;
                    case 2:
                        patternItem = new Gap(patternItem.zzdv.floatValue());
                        continue;
                    default:
                        String str = TAG;
                        StringBuilder sb = new StringBuilder(37);
                        sb.append("Unknown PatternItem type: ");
                        sb.append(i);
                        Log.w(str, sb.toString());
                        continue;
                }
            }
            arrayList.add(patternItem);
        }
        return arrayList;
    }
}
