package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.app.e;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.a;
import android.support.v4.media.session.b;
import android.support.v4.media.session.c;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

public final class MediaControllerCompat {

    public static abstract class a implements IBinder.DeathRecipient {

        /* renamed from: a  reason: collision with root package name */
        HandlerC0014a f602a;

        /* renamed from: b  reason: collision with root package name */
        boolean f603b;

        /* renamed from: c  reason: collision with root package name */
        private final Object f604c;

        public void a() {
        }

        public void a(int i) {
        }

        public void a(Bundle bundle) {
        }

        public void a(MediaMetadataCompat mediaMetadataCompat) {
        }

        public void a(b bVar) {
        }

        public void a(PlaybackStateCompat playbackStateCompat) {
        }

        public void a(CharSequence charSequence) {
        }

        public void a(String str, Bundle bundle) {
        }

        public void a(List<MediaSessionCompat.QueueItem> list) {
        }

        public void a(boolean z) {
        }

        public void b(int i) {
        }

        @Deprecated
        public void b(boolean z) {
        }

        public a() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f604c = c.a(new b(this));
            } else {
                this.f604c = new c(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(int i, Object obj, Bundle bundle) {
            HandlerC0014a aVar = this.f602a;
            if (aVar != null) {
                Message obtainMessage = aVar.obtainMessage(i, obj);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }
        }

        private static class b implements c.a {

            /* renamed from: a  reason: collision with root package name */
            private final WeakReference<a> f607a;

            b(a aVar) {
                this.f607a = new WeakReference<>(aVar);
            }

            @Override // android.support.v4.media.session.c.a
            public void a() {
                a aVar = this.f607a.get();
                if (aVar != null) {
                    aVar.a();
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void a(String str, Bundle bundle) {
                a aVar = this.f607a.get();
                if (aVar == null) {
                    return;
                }
                if (!aVar.f603b || Build.VERSION.SDK_INT >= 23) {
                    aVar.a(str, bundle);
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void a(Object obj) {
                a aVar = this.f607a.get();
                if (aVar != null && !aVar.f603b) {
                    aVar.a(PlaybackStateCompat.a(obj));
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void b(Object obj) {
                a aVar = this.f607a.get();
                if (aVar != null) {
                    aVar.a(MediaMetadataCompat.a(obj));
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void a(List<?> list) {
                a aVar = this.f607a.get();
                if (aVar != null) {
                    aVar.a(MediaSessionCompat.QueueItem.a(list));
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void a(CharSequence charSequence) {
                a aVar = this.f607a.get();
                if (aVar != null) {
                    aVar.a(charSequence);
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void a(Bundle bundle) {
                a aVar = this.f607a.get();
                if (aVar != null) {
                    aVar.a(bundle);
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void a(int i, int i2, int i3, int i4, int i5) {
                a aVar = this.f607a.get();
                if (aVar != null) {
                    aVar.a(new b(i, i2, i3, i4, i5));
                }
            }
        }

        private static class c extends a.AbstractBinderC0015a {

            /* renamed from: a  reason: collision with root package name */
            private final WeakReference<a> f608a;

            c(a aVar) {
                this.f608a = new WeakReference<>(aVar);
            }

            @Override // android.support.v4.media.session.a
            public void a(String str, Bundle bundle) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(1, str, bundle);
                }
            }

            @Override // android.support.v4.media.session.a
            public void a() {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(8, null, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void a(PlaybackStateCompat playbackStateCompat) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(2, playbackStateCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void a(MediaMetadataCompat mediaMetadataCompat) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(3, mediaMetadataCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void a(List<MediaSessionCompat.QueueItem> list) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(5, list, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void a(CharSequence charSequence) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(6, charSequence, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void b(boolean z) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(11, Boolean.valueOf(z), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void a(int i) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(9, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void a(boolean z) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(10, Boolean.valueOf(z), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void b(int i) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(12, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void a(Bundle bundle) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(7, bundle, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void a(ParcelableVolumeInfo parcelableVolumeInfo) {
                a aVar = this.f608a.get();
                if (aVar != null) {
                    aVar.a(4, parcelableVolumeInfo != null ? new b(parcelableVolumeInfo.f619a, parcelableVolumeInfo.f620b, parcelableVolumeInfo.f621c, parcelableVolumeInfo.f622d, parcelableVolumeInfo.e) : null, null);
                }
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: android.support.v4.media.session.MediaControllerCompat$a$a  reason: collision with other inner class name */
        public class HandlerC0014a extends Handler {

            /* renamed from: a  reason: collision with root package name */
            boolean f605a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a f606b;

            public void handleMessage(Message message) {
                if (this.f605a) {
                    switch (message.what) {
                        case 1:
                            this.f606b.a((String) message.obj, message.getData());
                            return;
                        case 2:
                            this.f606b.a((PlaybackStateCompat) message.obj);
                            return;
                        case 3:
                            this.f606b.a((MediaMetadataCompat) message.obj);
                            return;
                        case 4:
                            this.f606b.a((b) message.obj);
                            return;
                        case 5:
                            this.f606b.a((List) message.obj);
                            return;
                        case 6:
                            this.f606b.a((CharSequence) message.obj);
                            return;
                        case 7:
                            this.f606b.a((Bundle) message.obj);
                            return;
                        case 8:
                            this.f606b.a();
                            return;
                        case 9:
                            this.f606b.a(((Integer) message.obj).intValue());
                            return;
                        case 10:
                            this.f606b.b(((Boolean) message.obj).booleanValue());
                            return;
                        case 11:
                            this.f606b.a(((Boolean) message.obj).booleanValue());
                            return;
                        case 12:
                            this.f606b.b(((Integer) message.obj).intValue());
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final int f609a;

        /* renamed from: b  reason: collision with root package name */
        private final int f610b;

        /* renamed from: c  reason: collision with root package name */
        private final int f611c;

        /* renamed from: d  reason: collision with root package name */
        private final int f612d;
        private final int e;

        b(int i, int i2, int i3, int i4, int i5) {
            this.f609a = i;
            this.f610b = i2;
            this.f611c = i3;
            this.f612d = i4;
            this.e = i5;
        }
    }

    static class MediaControllerImplApi21 {

        /* renamed from: a  reason: collision with root package name */
        private final List<a> f598a;

        /* renamed from: b  reason: collision with root package name */
        private b f599b;

        /* renamed from: c  reason: collision with root package name */
        private HashMap<a, a> f600c;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a() {
            if (this.f599b != null) {
                synchronized (this.f598a) {
                    for (a aVar : this.f598a) {
                        a aVar2 = new a(aVar);
                        this.f600c.put(aVar, aVar2);
                        aVar.f603b = true;
                        try {
                            this.f599b.a(aVar2);
                        } catch (RemoteException e) {
                            Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                        }
                    }
                    this.f598a.clear();
                }
            }
        }

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* renamed from: a  reason: collision with root package name */
            private WeakReference<MediaControllerImplApi21> f601a;

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.f601a.get();
                if (mediaControllerImplApi21 != null && bundle != null) {
                    mediaControllerImplApi21.f599b = b.a.a(e.a(bundle, "android.support.v4.media.session.EXTRA_BINDER"));
                    mediaControllerImplApi21.a();
                }
            }
        }

        /* access modifiers changed from: private */
        public static class a extends a.c {
            a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.a, android.support.v4.media.session.MediaControllerCompat.a.c
            public void a() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a, android.support.v4.media.session.MediaControllerCompat.a.c
            public void a(MediaMetadataCompat mediaMetadataCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a, android.support.v4.media.session.MediaControllerCompat.a.c
            public void a(List<MediaSessionCompat.QueueItem> list) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a, android.support.v4.media.session.MediaControllerCompat.a.c
            public void a(CharSequence charSequence) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a, android.support.v4.media.session.MediaControllerCompat.a.c
            public void a(Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a, android.support.v4.media.session.MediaControllerCompat.a.c
            public void a(ParcelableVolumeInfo parcelableVolumeInfo) {
                throw new AssertionError();
            }
        }
    }
}
