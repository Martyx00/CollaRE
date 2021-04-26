package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new Parcelable.Creator<RatingCompat>() {
        /* class android.support.v4.media.RatingCompat.AnonymousClass1 */

        /* renamed from: a */
        public RatingCompat createFromParcel(Parcel parcel) {
            return new RatingCompat(parcel.readInt(), parcel.readFloat());
        }

        /* renamed from: a */
        public RatingCompat[] newArray(int i) {
            return new RatingCompat[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f596a;

    /* renamed from: b  reason: collision with root package name */
    private final float f597b;

    RatingCompat(int i, float f) {
        this.f596a = i;
        this.f597b = f;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Rating:style=");
        sb.append(this.f596a);
        sb.append(" rating=");
        float f = this.f597b;
        if (f < BitmapDescriptorFactory.HUE_RED) {
            str = "unrated";
        } else {
            str = String.valueOf(f);
        }
        sb.append(str);
        return sb.toString();
    }

    public int describeContents() {
        return this.f596a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f596a);
        parcel.writeFloat(this.f597b);
    }
}
