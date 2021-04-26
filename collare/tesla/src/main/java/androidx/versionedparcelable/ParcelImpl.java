package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new Parcelable.Creator<ParcelImpl>() {
        /* class androidx.versionedparcelable.ParcelImpl.AnonymousClass1 */

        /* renamed from: a */
        public ParcelImpl createFromParcel(Parcel parcel) {
            return new ParcelImpl(parcel);
        }

        /* renamed from: a */
        public ParcelImpl[] newArray(int i) {
            return new ParcelImpl[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final c f1309a;

    public int describeContents() {
        return 0;
    }

    protected ParcelImpl(Parcel parcel) {
        this.f1309a = new b(parcel).h();
    }

    public void writeToParcel(Parcel parcel, int i) {
        new b(parcel).a(this.f1309a);
    }
}
