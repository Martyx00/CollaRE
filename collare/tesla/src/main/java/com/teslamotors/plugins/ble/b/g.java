package com.teslamotors.plugins.ble.b;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PeripheralMap */
public class g implements Parcelable {
    public static final Parcelable.Creator<g> CREATOR = new Parcelable.Creator<g>() {
        /* class com.teslamotors.plugins.ble.b.g.AnonymousClass1 */

        /* renamed from: a */
        public g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }

        /* renamed from: a */
        public g[] newArray(int i) {
            return new g[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public Map<String, f> f5414a = new HashMap();

    public int describeContents() {
        return 0;
    }

    protected g(Parcel parcel) {
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f5414a.put(parcel.readString(), (f) parcel.readParcelable(f.class.getClassLoader()));
        }
    }

    public g() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f5414a.size());
        for (Map.Entry<String, f> entry : this.f5414a.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeParcelable(entry.getValue(), i);
        }
    }
}
