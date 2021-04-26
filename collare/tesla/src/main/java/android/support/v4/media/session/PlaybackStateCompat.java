package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.e;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new Parcelable.Creator<PlaybackStateCompat>() {
        /* class android.support.v4.media.session.PlaybackStateCompat.AnonymousClass1 */

        /* renamed from: a */
        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        /* renamed from: a */
        public PlaybackStateCompat[] newArray(int i) {
            return new PlaybackStateCompat[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final int f623a;

    /* renamed from: b  reason: collision with root package name */
    final long f624b;

    /* renamed from: c  reason: collision with root package name */
    final long f625c;

    /* renamed from: d  reason: collision with root package name */
    final float f626d;
    final long e;
    final int f;
    final CharSequence g;
    final long h;
    List<CustomAction> i;
    final long j;
    final Bundle k;
    private Object l;

    public int describeContents() {
        return 0;
    }

    PlaybackStateCompat(int i2, long j2, long j3, float f2, long j4, int i3, CharSequence charSequence, long j5, List<CustomAction> list, long j6, Bundle bundle) {
        this.f623a = i2;
        this.f624b = j2;
        this.f625c = j3;
        this.f626d = f2;
        this.e = j4;
        this.f = i3;
        this.g = charSequence;
        this.h = j5;
        this.i = new ArrayList(list);
        this.j = j6;
        this.k = bundle;
    }

    PlaybackStateCompat(Parcel parcel) {
        this.f623a = parcel.readInt();
        this.f624b = parcel.readLong();
        this.f626d = parcel.readFloat();
        this.h = parcel.readLong();
        this.f625c = parcel.readLong();
        this.e = parcel.readLong();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.i = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.j = parcel.readLong();
        this.k = parcel.readBundle();
        this.f = parcel.readInt();
    }

    public String toString() {
        return "PlaybackState {" + "state=" + this.f623a + ", position=" + this.f624b + ", buffered position=" + this.f625c + ", speed=" + this.f626d + ", updated=" + this.h + ", actions=" + this.e + ", error code=" + this.f + ", error message=" + this.g + ", custom actions=" + this.i + ", active item id=" + this.j + "}";
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f623a);
        parcel.writeLong(this.f624b);
        parcel.writeFloat(this.f626d);
        parcel.writeLong(this.h);
        parcel.writeLong(this.f625c);
        parcel.writeLong(this.e);
        TextUtils.writeToParcel(this.g, parcel, i2);
        parcel.writeTypedList(this.i);
        parcel.writeLong(this.j);
        parcel.writeBundle(this.k);
        parcel.writeInt(this.f);
    }

    public static PlaybackStateCompat a(Object obj) {
        ArrayList arrayList;
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        List<Object> h2 = e.h(obj);
        if (h2 != null) {
            ArrayList arrayList2 = new ArrayList(h2.size());
            for (Object obj2 : h2) {
                arrayList2.add(CustomAction.a(obj2));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(e.a(obj), e.b(obj), e.c(obj), e.d(obj), e.e(obj), 0, e.f(obj), e.g(obj), arrayList, e.i(obj), Build.VERSION.SDK_INT >= 22 ? f.a(obj) : null);
        playbackStateCompat.l = obj;
        return playbackStateCompat;
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new Parcelable.Creator<CustomAction>() {
            /* class android.support.v4.media.session.PlaybackStateCompat.CustomAction.AnonymousClass1 */

            /* renamed from: a */
            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            /* renamed from: a */
            public CustomAction[] newArray(int i) {
                return new CustomAction[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private final String f627a;

        /* renamed from: b  reason: collision with root package name */
        private final CharSequence f628b;

        /* renamed from: c  reason: collision with root package name */
        private final int f629c;

        /* renamed from: d  reason: collision with root package name */
        private final Bundle f630d;
        private Object e;

        public int describeContents() {
            return 0;
        }

        CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.f627a = str;
            this.f628b = charSequence;
            this.f629c = i;
            this.f630d = bundle;
        }

        CustomAction(Parcel parcel) {
            this.f627a = parcel.readString();
            this.f628b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f629c = parcel.readInt();
            this.f630d = parcel.readBundle();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f627a);
            TextUtils.writeToParcel(this.f628b, parcel, i);
            parcel.writeInt(this.f629c);
            parcel.writeBundle(this.f630d);
        }

        public static CustomAction a(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            CustomAction customAction = new CustomAction(e.a.a(obj), e.a.b(obj), e.a.c(obj), e.a.d(obj));
            customAction.e = obj;
            return customAction;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.f628b) + ", mIcon=" + this.f629c + ", mExtras=" + this.f630d;
        }
    }
}
