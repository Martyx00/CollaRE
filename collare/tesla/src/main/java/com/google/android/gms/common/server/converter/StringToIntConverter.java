package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

@SafeParcelable.Class(creator = "StringToIntConverterCreator")
@KeepForSdk
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {
    public static final Parcelable.Creator<StringToIntConverter> CREATOR = new zac();
    @SafeParcelable.VersionField(id = 1)
    private final int zalf;
    private final HashMap<String, Integer> zapm;
    private final SparseArray<String> zapn;
    @SafeParcelable.Field(getter = "getSerializedMap", id = 2)
    private final ArrayList<zaa> zapo;

    @SafeParcelable.Constructor
    StringToIntConverter(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) ArrayList<zaa> arrayList) {
        this.zalf = i;
        this.zapm = new HashMap<>();
        this.zapn = new SparseArray<>();
        this.zapo = null;
        ArrayList<zaa> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            zaa zaa2 = arrayList2.get(i2);
            i2++;
            zaa zaa3 = zaa2;
            add(zaa3.zapp, zaa3.zapq);
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final int zacj() {
        return 7;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final int zack() {
        return 0;
    }

    @SafeParcelable.Class(creator = "StringToIntConverterEntryCreator")
    public static final class zaa extends AbstractSafeParcelable {
        public static final Parcelable.Creator<zaa> CREATOR = new zad();
        @SafeParcelable.VersionField(id = 1)
        private final int versionCode;
        @SafeParcelable.Field(id = 2)
        final String zapp;
        @SafeParcelable.Field(id = 3)
        final int zapq;

        @SafeParcelable.Constructor
        zaa(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i2) {
            this.versionCode = i;
            this.zapp = str;
            this.zapq = i2;
        }

        zaa(String str, int i) {
            this.versionCode = 1;
            this.zapp = str;
            this.zapq = i;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
            SafeParcelWriter.writeString(parcel, 2, this.zapp, false);
            SafeParcelWriter.writeInt(parcel, 3, this.zapq);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @KeepForSdk
    public StringToIntConverter() {
        this.zalf = 1;
        this.zapm = new HashMap<>();
        this.zapn = new SparseArray<>();
        this.zapo = null;
    }

    @KeepForSdk
    public final StringToIntConverter add(String str, int i) {
        this.zapm.put(str, Integer.valueOf(i));
        this.zapn.put(i, str);
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zapm.keySet()) {
            arrayList.add(new zaa(str, this.zapm.get(str).intValue()));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final /* synthetic */ String convertBack(Integer num) {
        String str = this.zapn.get(num.intValue());
        return (str != null || !this.zapm.containsKey("gms_unknown")) ? str : "gms_unknown";
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final /* synthetic */ Integer convert(String str) {
        Integer num = this.zapm.get(str);
        return num == null ? this.zapm.get("gms_unknown") : num;
    }
}
