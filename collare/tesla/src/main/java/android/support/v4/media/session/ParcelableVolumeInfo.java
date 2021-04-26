package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new Parcelable.Creator<ParcelableVolumeInfo>() {
        /* class android.support.v4.media.session.ParcelableVolumeInfo.AnonymousClass1 */

        /* renamed from: a */
        public ParcelableVolumeInfo createFromParcel(Parcel parcel) {
            return new ParcelableVolumeInfo(parcel);
        }

        /* renamed from: a */
        public ParcelableVolumeInfo[] newArray(int i) {
            return new ParcelableVolumeInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f619a;

    /* renamed from: b  reason: collision with root package name */
    public int f620b;

    /* renamed from: c  reason: collision with root package name */
    public int f621c;

    /* renamed from: d  reason: collision with root package name */
    public int f622d;
    public int e;

    public int describeContents() {
        return 0;
    }

    public ParcelableVolumeInfo(Parcel parcel) {
        this.f619a = parcel.readInt();
        this.f621c = parcel.readInt();
        this.f622d = parcel.readInt();
        this.e = parcel.readInt();
        this.f620b = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f619a);
        parcel.writeInt(this.f621c);
        parcel.writeInt(this.f622d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f620b);
    }
}
