package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;

/* compiled from: MediaDescriptionCompatApi21 */
class a {
    public static String a(Object obj) {
        return ((MediaDescription) obj).getMediaId();
    }

    public static CharSequence b(Object obj) {
        return ((MediaDescription) obj).getTitle();
    }

    public static CharSequence c(Object obj) {
        return ((MediaDescription) obj).getSubtitle();
    }

    public static CharSequence d(Object obj) {
        return ((MediaDescription) obj).getDescription();
    }

    public static Bitmap e(Object obj) {
        return ((MediaDescription) obj).getIconBitmap();
    }

    public static Uri f(Object obj) {
        return ((MediaDescription) obj).getIconUri();
    }

    public static Bundle g(Object obj) {
        return ((MediaDescription) obj).getExtras();
    }

    public static void a(Object obj, Parcel parcel, int i) {
        ((MediaDescription) obj).writeToParcel(parcel, i);
    }

    public static Object a(Parcel parcel) {
        return MediaDescription.CREATOR.createFromParcel(parcel);
    }

    /* renamed from: android.support.v4.media.a$a  reason: collision with other inner class name */
    /* compiled from: MediaDescriptionCompatApi21 */
    static class C0013a {
        public static Object a() {
            return new MediaDescription.Builder();
        }

        public static void a(Object obj, String str) {
            ((MediaDescription.Builder) obj).setMediaId(str);
        }

        public static void a(Object obj, CharSequence charSequence) {
            ((MediaDescription.Builder) obj).setTitle(charSequence);
        }

        public static void b(Object obj, CharSequence charSequence) {
            ((MediaDescription.Builder) obj).setSubtitle(charSequence);
        }

        public static void c(Object obj, CharSequence charSequence) {
            ((MediaDescription.Builder) obj).setDescription(charSequence);
        }

        public static void a(Object obj, Bitmap bitmap) {
            ((MediaDescription.Builder) obj).setIconBitmap(bitmap);
        }

        public static void a(Object obj, Uri uri) {
            ((MediaDescription.Builder) obj).setIconUri(uri);
        }

        public static void a(Object obj, Bundle bundle) {
            ((MediaDescription.Builder) obj).setExtras(bundle);
        }

        public static Object a(Object obj) {
            return ((MediaDescription.Builder) obj).build();
        }
    }
}
