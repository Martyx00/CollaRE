package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.d.e;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class MediaBrowserCompat {

    /* renamed from: a  reason: collision with root package name */
    static final boolean f578a = Log.isLoggable("MediaBrowserCompat", 3);

    public static abstract class a {
        public void a(String str, Bundle bundle, Bundle bundle2) {
        }

        public void b(String str, Bundle bundle, Bundle bundle2) {
        }

        public void c(String str, Bundle bundle, Bundle bundle2) {
        }
    }

    public static abstract class b {
        public void a(MediaItem mediaItem) {
        }

        public void a(String str) {
        }
    }

    public static abstract class c {
        public void a(String str, Bundle bundle) {
        }

        public void a(String str, Bundle bundle, List<MediaItem> list) {
        }
    }

    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator<MediaItem>() {
            /* class android.support.v4.media.MediaBrowserCompat.MediaItem.AnonymousClass1 */

            /* renamed from: a */
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            /* renamed from: a */
            public MediaItem[] newArray(int i) {
                return new MediaItem[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private final int f581a;

        /* renamed from: b  reason: collision with root package name */
        private final MediaDescriptionCompat f582b;

        public int describeContents() {
            return 0;
        }

        MediaItem(Parcel parcel) {
            this.f581a = parcel.readInt();
            this.f582b = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f581a);
            this.f582b.writeToParcel(parcel, i);
        }

        public String toString() {
            return "MediaItem{" + "mFlags=" + this.f581a + ", mDescription=" + this.f582b + '}';
        }
    }

    private static class ItemReceiver extends e {

        /* renamed from: d  reason: collision with root package name */
        private final String f580d;
        private final b e;

        /* access modifiers changed from: protected */
        @Override // android.support.v4.d.e
        public void a(int i, Bundle bundle) {
            if (bundle != null) {
                bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (i != 0 || bundle == null || !bundle.containsKey("media_item")) {
                this.e.a(this.f580d);
                return;
            }
            Parcelable parcelable = bundle.getParcelable("media_item");
            if (parcelable == null || (parcelable instanceof MediaItem)) {
                this.e.a((MediaItem) parcelable);
            } else {
                this.e.a(this.f580d);
            }
        }
    }

    private static class SearchResultReceiver extends e {

        /* renamed from: d  reason: collision with root package name */
        private final String f583d;
        private final Bundle e;
        private final c f;

        /* access modifiers changed from: protected */
        @Override // android.support.v4.d.e
        public void a(int i, Bundle bundle) {
            if (bundle != null) {
                bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (i != 0 || bundle == null || !bundle.containsKey("search_results")) {
                this.f.a(this.f583d, this.e);
                return;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray("search_results");
            ArrayList arrayList = null;
            if (parcelableArray != null) {
                arrayList = new ArrayList();
                for (Parcelable parcelable : parcelableArray) {
                    arrayList.add((MediaItem) parcelable);
                }
            }
            this.f.a(this.f583d, this.e, arrayList);
        }
    }

    private static class CustomActionResultReceiver extends e {

        /* renamed from: d  reason: collision with root package name */
        private final String f579d;
        private final Bundle e;
        private final a f;

        /* access modifiers changed from: protected */
        @Override // android.support.v4.d.e
        public void a(int i, Bundle bundle) {
            a aVar = this.f;
            if (aVar != null) {
                switch (i) {
                    case -1:
                        aVar.c(this.f579d, this.e, bundle);
                        return;
                    case 0:
                        aVar.b(this.f579d, this.e, bundle);
                        return;
                    case 1:
                        aVar.a(this.f579d, this.e, bundle);
                        return;
                    default:
                        Log.w("MediaBrowserCompat", "Unknown result code: " + i + " (extras=" + this.e + ", resultData=" + bundle + ")");
                        return;
                }
            }
        }
    }
}
