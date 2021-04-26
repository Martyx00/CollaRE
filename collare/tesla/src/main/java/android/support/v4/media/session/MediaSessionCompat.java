package android.support.v4.media.session;

import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat {

    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new Parcelable.Creator<Token>() {
            /* class android.support.v4.media.session.MediaSessionCompat.Token.AnonymousClass1 */

            /* renamed from: a */
            public Token createFromParcel(Parcel parcel) {
                Object obj;
                if (Build.VERSION.SDK_INT >= 21) {
                    obj = parcel.readParcelable(null);
                } else {
                    obj = parcel.readStrongBinder();
                }
                return new Token(obj);
            }

            /* renamed from: a */
            public Token[] newArray(int i) {
                return new Token[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private final Object f617a;

        /* renamed from: b  reason: collision with root package name */
        private final b f618b;

        public int describeContents() {
            return 0;
        }

        Token(Object obj) {
            this(obj, null);
        }

        Token(Object obj, b bVar) {
            this.f617a = obj;
            this.f618b = bVar;
        }

        public void writeToParcel(Parcel parcel, int i) {
            if (Build.VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.f617a, i);
            } else {
                parcel.writeStrongBinder((IBinder) this.f617a);
            }
        }

        public int hashCode() {
            Object obj = this.f617a;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Object obj2 = this.f617a;
            if (obj2 != null) {
                Object obj3 = token.f617a;
                if (obj3 == null) {
                    return false;
                }
                return obj2.equals(obj3);
            } else if (token.f617a == null) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new Parcelable.Creator<QueueItem>() {
            /* class android.support.v4.media.session.MediaSessionCompat.QueueItem.AnonymousClass1 */

            /* renamed from: a */
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            /* renamed from: a */
            public QueueItem[] newArray(int i) {
                return new QueueItem[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private final MediaDescriptionCompat f613a;

        /* renamed from: b  reason: collision with root package name */
        private final long f614b;

        /* renamed from: c  reason: collision with root package name */
        private Object f615c;

        public int describeContents() {
            return 0;
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            } else if (j != -1) {
                this.f613a = mediaDescriptionCompat;
                this.f614b = j;
                this.f615c = obj;
            } else {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
        }

        QueueItem(Parcel parcel) {
            this.f613a = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.f614b = parcel.readLong();
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.f613a.writeToParcel(parcel, i);
            parcel.writeLong(this.f614b);
        }

        public static QueueItem a(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new QueueItem(obj, MediaDescriptionCompat.a(d.a.a(obj)), d.a.b(obj));
        }

        public static List<QueueItem> a(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(a(it.next()));
            }
            return arrayList;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.f613a + ", Id=" + this.f614b + " }";
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new Parcelable.Creator<ResultReceiverWrapper>() {
            /* class android.support.v4.media.session.MediaSessionCompat.ResultReceiverWrapper.AnonymousClass1 */

            /* renamed from: a */
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            /* renamed from: a */
            public ResultReceiverWrapper[] newArray(int i) {
                return new ResultReceiverWrapper[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private ResultReceiver f616a;

        public int describeContents() {
            return 0;
        }

        ResultReceiverWrapper(Parcel parcel) {
            this.f616a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.f616a.writeToParcel(parcel, i);
        }
    }
}
