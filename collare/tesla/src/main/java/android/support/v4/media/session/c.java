package android.support.v4.media.session;

import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.List;

/* compiled from: MediaControllerCompatApi21 */
class c {

    /* compiled from: MediaControllerCompatApi21 */
    public interface a {
        void a();

        void a(int i, int i2, int i3, int i4, int i5);

        void a(Bundle bundle);

        void a(CharSequence charSequence);

        void a(Object obj);

        void a(String str, Bundle bundle);

        void a(List<?> list);

        void b(Object obj);
    }

    public static Object a(a aVar) {
        return new b(aVar);
    }

    /* renamed from: android.support.v4.media.session.c$c  reason: collision with other inner class name */
    /* compiled from: MediaControllerCompatApi21 */
    public static class C0018c {
        public static AudioAttributes a(Object obj) {
            return ((MediaController.PlaybackInfo) obj).getAudioAttributes();
        }

        public static int b(Object obj) {
            return a(a(obj));
        }

        private static int a(AudioAttributes audioAttributes) {
            if ((audioAttributes.getFlags() & 1) == 1) {
                return 7;
            }
            if ((audioAttributes.getFlags() & 4) == 4) {
                return 6;
            }
            switch (audioAttributes.getUsage()) {
                case 1:
                case 11:
                case 12:
                case 14:
                    return 3;
                case 2:
                    return 0;
                case 3:
                    return 8;
                case 4:
                    return 4;
                case 5:
                case 7:
                case 8:
                case 9:
                case 10:
                    return 5;
                case 6:
                    return 2;
                case 13:
                    return 1;
                default:
                    return 3;
            }
        }
    }

    /* compiled from: MediaControllerCompatApi21 */
    static class b<T extends a> extends MediaController.Callback {

        /* renamed from: a  reason: collision with root package name */
        protected final T f633a;

        public b(T t) {
            this.f633a = t;
        }

        public void onSessionDestroyed() {
            this.f633a.a();
        }

        public void onSessionEvent(String str, Bundle bundle) {
            this.f633a.a(str, bundle);
        }

        public void onPlaybackStateChanged(PlaybackState playbackState) {
            this.f633a.a(playbackState);
        }

        public void onMetadataChanged(MediaMetadata mediaMetadata) {
            this.f633a.b(mediaMetadata);
        }

        @Override // android.media.session.MediaController.Callback
        public void onQueueChanged(List<MediaSession.QueueItem> list) {
            this.f633a.a(list);
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
            this.f633a.a(charSequence);
        }

        public void onExtrasChanged(Bundle bundle) {
            this.f633a.a(bundle);
        }

        public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
            this.f633a.a(playbackInfo.getPlaybackType(), C0018c.b(playbackInfo), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume());
        }
    }
}
