package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;

/* access modifiers changed from: package-private */
/* compiled from: FragmentManager */
public final class o implements Parcelable {
    public static final Parcelable.Creator<o> CREATOR = new Parcelable.Creator<o>() {
        /* class android.support.v4.app.o.AnonymousClass1 */

        /* renamed from: a */
        public o createFromParcel(Parcel parcel) {
            return new o(parcel);
        }

        /* renamed from: a */
        public o[] newArray(int i) {
            return new o[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    p[] f249a;

    /* renamed from: b  reason: collision with root package name */
    int[] f250b;

    /* renamed from: c  reason: collision with root package name */
    d[] f251c;

    /* renamed from: d  reason: collision with root package name */
    int f252d = -1;
    int e;

    public int describeContents() {
        return 0;
    }

    public o() {
    }

    public o(Parcel parcel) {
        this.f249a = (p[]) parcel.createTypedArray(p.CREATOR);
        this.f250b = parcel.createIntArray();
        this.f251c = (d[]) parcel.createTypedArray(d.CREATOR);
        this.f252d = parcel.readInt();
        this.e = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.f249a, i);
        parcel.writeIntArray(this.f250b);
        parcel.writeTypedArray(this.f251c, i);
        parcel.writeInt(this.f252d);
        parcel.writeInt(this.e);
    }
}
