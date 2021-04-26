package android.support.v4.media.session;

import android.media.session.MediaSession;

/* compiled from: MediaSessionCompatApi21 */
class d {

    /* access modifiers changed from: package-private */
    /* compiled from: MediaSessionCompatApi21 */
    public static class a {
        public static Object a(Object obj) {
            return ((MediaSession.QueueItem) obj).getDescription();
        }

        public static long b(Object obj) {
            return ((MediaSession.QueueItem) obj).getQueueId();
        }
    }
}
