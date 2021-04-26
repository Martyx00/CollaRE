package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.a;
import android.support.v4.media.b;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new Parcelable.Creator<MediaDescriptionCompat>() {
        /* class android.support.v4.media.MediaDescriptionCompat.AnonymousClass1 */

        /* renamed from: a */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            if (Build.VERSION.SDK_INT < 21) {
                return new MediaDescriptionCompat(parcel);
            }
            return MediaDescriptionCompat.a(a.a(parcel));
        }

        /* renamed from: a */
        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final String f584a;

    /* renamed from: b  reason: collision with root package name */
    private final CharSequence f585b;

    /* renamed from: c  reason: collision with root package name */
    private final CharSequence f586c;

    /* renamed from: d  reason: collision with root package name */
    private final CharSequence f587d;
    private final Bitmap e;
    private final Uri f;
    private final Bundle g;
    private final Uri h;
    private Object i;

    public int describeContents() {
        return 0;
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f584a = str;
        this.f585b = charSequence;
        this.f586c = charSequence2;
        this.f587d = charSequence3;
        this.e = bitmap;
        this.f = uri;
        this.g = bundle;
        this.h = uri2;
    }

    MediaDescriptionCompat(Parcel parcel) {
        this.f584a = parcel.readString();
        this.f585b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f586c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f587d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.e = (Bitmap) parcel.readParcelable(null);
        this.f = (Uri) parcel.readParcelable(null);
        this.g = parcel.readBundle();
        this.h = (Uri) parcel.readParcelable(null);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        if (Build.VERSION.SDK_INT < 21) {
            parcel.writeString(this.f584a);
            TextUtils.writeToParcel(this.f585b, parcel, i2);
            TextUtils.writeToParcel(this.f586c, parcel, i2);
            TextUtils.writeToParcel(this.f587d, parcel, i2);
            parcel.writeParcelable(this.e, i2);
            parcel.writeParcelable(this.f, i2);
            parcel.writeBundle(this.g);
            parcel.writeParcelable(this.h, i2);
            return;
        }
        a.a(a(), parcel, i2);
    }

    public String toString() {
        return ((Object) this.f585b) + ", " + ((Object) this.f586c) + ", " + ((Object) this.f587d);
    }

    public Object a() {
        if (this.i != null || Build.VERSION.SDK_INT < 21) {
            return this.i;
        }
        Object a2 = a.C0013a.a();
        a.C0013a.a(a2, this.f584a);
        a.C0013a.a(a2, this.f585b);
        a.C0013a.b(a2, this.f586c);
        a.C0013a.c(a2, this.f587d);
        a.C0013a.a(a2, this.e);
        a.C0013a.a(a2, this.f);
        Bundle bundle = this.g;
        if (Build.VERSION.SDK_INT < 23 && this.h != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }
            bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.h);
        }
        a.C0013a.a(a2, bundle);
        if (Build.VERSION.SDK_INT >= 23) {
            b.a.b(a2, this.h);
        }
        this.i = a.C0013a.a(a2);
        return this.i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.v4.media.MediaDescriptionCompat a(java.lang.Object r6) {
        /*
        // Method dump skipped, instructions count: 131
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.a(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private String f588a;

        /* renamed from: b  reason: collision with root package name */
        private CharSequence f589b;

        /* renamed from: c  reason: collision with root package name */
        private CharSequence f590c;

        /* renamed from: d  reason: collision with root package name */
        private CharSequence f591d;
        private Bitmap e;
        private Uri f;
        private Bundle g;
        private Uri h;

        public a a(String str) {
            this.f588a = str;
            return this;
        }

        public a a(CharSequence charSequence) {
            this.f589b = charSequence;
            return this;
        }

        public a b(CharSequence charSequence) {
            this.f590c = charSequence;
            return this;
        }

        public a c(CharSequence charSequence) {
            this.f591d = charSequence;
            return this;
        }

        public a a(Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        public a a(Uri uri) {
            this.f = uri;
            return this;
        }

        public a a(Bundle bundle) {
            this.g = bundle;
            return this;
        }

        public a b(Uri uri) {
            this.h = uri;
            return this;
        }

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f588a, this.f589b, this.f590c, this.f591d, this.e, this.f, this.g, this.h);
        }
    }
}
