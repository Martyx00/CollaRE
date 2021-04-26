package android.support.v4.media;

import android.media.MediaDescription;
import android.net.Uri;
import android.support.v4.media.a;

/* compiled from: MediaDescriptionCompatApi23 */
class b extends a {
    public static Uri h(Object obj) {
        return ((MediaDescription) obj).getMediaUri();
    }

    /* compiled from: MediaDescriptionCompatApi23 */
    static class a extends a.C0013a {
        public static void b(Object obj, Uri uri) {
            ((MediaDescription.Builder) obj).setMediaUri(uri);
        }
    }
}
