package android.support.v4.g;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: AbsSavedState */
public abstract class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.ClassLoaderCreator<a>() {
        /* class android.support.v4.g.a.AnonymousClass2 */

        /* renamed from: a */
        public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return a.f457a;
            }
            throw new IllegalStateException("superState must be null");
        }

        /* renamed from: a */
        public a createFromParcel(Parcel parcel) {
            return createFromParcel(parcel, null);
        }

        /* renamed from: a */
        public a[] newArray(int i) {
            return new a[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final a f457a = new a() {
        /* class android.support.v4.g.a.AnonymousClass1 */
    };

    /* renamed from: b  reason: collision with root package name */
    private final Parcelable f458b;

    public int describeContents() {
        return 0;
    }

    private a() {
        this.f458b = null;
    }

    protected a(Parcelable parcelable) {
        if (parcelable != null) {
            this.f458b = parcelable == f457a ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    protected a(Parcel parcel, ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.f458b = readParcelable == null ? f457a : readParcelable;
    }

    public final Parcelable a() {
        return this.f458b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f458b, i);
    }
}
