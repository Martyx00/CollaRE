package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.c;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
/* compiled from: BackStackRecord */
public final class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() {
        /* class android.support.v4.app.d.AnonymousClass1 */

        /* renamed from: a */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        /* renamed from: a */
        public d[] newArray(int i) {
            return new d[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final int[] f181a;

    /* renamed from: b  reason: collision with root package name */
    final int f182b;

    /* renamed from: c  reason: collision with root package name */
    final int f183c;

    /* renamed from: d  reason: collision with root package name */
    final String f184d;
    final int e;
    final int f;
    final CharSequence g;
    final int h;
    final CharSequence i;
    final ArrayList<String> j;
    final ArrayList<String> k;
    final boolean l;

    public int describeContents() {
        return 0;
    }

    public d(c cVar) {
        int size = cVar.f174b.size();
        this.f181a = new int[(size * 6)];
        if (cVar.i) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                c.a aVar = cVar.f174b.get(i2);
                int i4 = i3 + 1;
                this.f181a[i3] = aVar.f177a;
                int i5 = i4 + 1;
                this.f181a[i4] = aVar.f178b != null ? aVar.f178b.mIndex : -1;
                int i6 = i5 + 1;
                this.f181a[i5] = aVar.f179c;
                int i7 = i6 + 1;
                this.f181a[i6] = aVar.f180d;
                int i8 = i7 + 1;
                this.f181a[i7] = aVar.e;
                this.f181a[i8] = aVar.f;
                i2++;
                i3 = i8 + 1;
            }
            this.f182b = cVar.g;
            this.f183c = cVar.h;
            this.f184d = cVar.k;
            this.e = cVar.m;
            this.f = cVar.n;
            this.g = cVar.o;
            this.h = cVar.p;
            this.i = cVar.q;
            this.j = cVar.r;
            this.k = cVar.s;
            this.l = cVar.t;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public d(Parcel parcel) {
        this.f181a = parcel.createIntArray();
        this.f182b = parcel.readInt();
        this.f183c = parcel.readInt();
        this.f184d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.h = parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.createStringArrayList();
        this.k = parcel.createStringArrayList();
        this.l = parcel.readInt() != 0;
    }

    public c a(m mVar) {
        c cVar = new c(mVar);
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.f181a.length) {
            c.a aVar = new c.a();
            int i4 = i2 + 1;
            aVar.f177a = this.f181a[i2];
            if (m.f209a) {
                Log.v("FragmentManager", "Instantiate " + cVar + " op #" + i3 + " base fragment #" + this.f181a[i4]);
            }
            int i5 = i4 + 1;
            int i6 = this.f181a[i4];
            if (i6 >= 0) {
                aVar.f178b = mVar.f.get(i6);
            } else {
                aVar.f178b = null;
            }
            int[] iArr = this.f181a;
            int i7 = i5 + 1;
            aVar.f179c = iArr[i5];
            int i8 = i7 + 1;
            aVar.f180d = iArr[i7];
            int i9 = i8 + 1;
            aVar.e = iArr[i8];
            aVar.f = iArr[i9];
            cVar.f175c = aVar.f179c;
            cVar.f176d = aVar.f180d;
            cVar.e = aVar.e;
            cVar.f = aVar.f;
            cVar.a(aVar);
            i3++;
            i2 = i9 + 1;
        }
        cVar.g = this.f182b;
        cVar.h = this.f183c;
        cVar.k = this.f184d;
        cVar.m = this.e;
        cVar.i = true;
        cVar.n = this.f;
        cVar.o = this.g;
        cVar.p = this.h;
        cVar.q = this.i;
        cVar.r = this.j;
        cVar.s = this.k;
        cVar.t = this.l;
        cVar.a(1);
        return cVar;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f181a);
        parcel.writeInt(this.f182b);
        parcel.writeInt(this.f183c);
        parcel.writeString(this.f184d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        TextUtils.writeToParcel(this.g, parcel, 0);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeStringList(this.j);
        parcel.writeStringList(this.k);
        parcel.writeInt(this.l ? 1 : 0);
    }
}
