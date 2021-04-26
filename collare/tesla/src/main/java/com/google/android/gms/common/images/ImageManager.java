package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.g;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    private static final Object zamh = new Object();
    private static HashSet<Uri> zami = new HashSet<>();
    private static ImageManager zamj;
    private final Context mContext;
    private final Handler mHandler = new zap(Looper.getMainLooper());
    private final ExecutorService zamk = Executors.newFixedThreadPool(4);
    private final zaa zaml = null;
    private final zak zamm = new zak();
    private final Map<zaa, ImageReceiver> zamn = new HashMap();
    private final Map<Uri, ImageReceiver> zamo = new HashMap();
    private final Map<Uri, Long> zamp = new HashMap();

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    public static ImageManager create(Context context) {
        if (zamj == null) {
            zamj = new ImageManager(context, false);
        }
        return zamj;
    }

    /* access modifiers changed from: private */
    public static final class zaa extends g<zab, Bitmap> {
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        /* access modifiers changed from: protected */
        @Override // android.support.v4.util.g
        public final /* synthetic */ int sizeOf(zab zab, Bitmap bitmap) {
            Bitmap bitmap2 = bitmap;
            return bitmap2.getHeight() * bitmap2.getRowBytes();
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [boolean, java.lang.Object, java.lang.Object, java.lang.Object] */
        /* access modifiers changed from: protected */
        @Override // android.support.v4.util.g
        public final /* synthetic */ void entryRemoved(boolean z, zab zab, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zab, bitmap, bitmap2);
        }
    }

    /* access modifiers changed from: private */
    public final class zac implements Runnable {
        private final zaa zamt;

        public zac(zaa zaa) {
            this.zamt = zaa;
        }

        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zamn.get(this.zamt);
            if (imageReceiver != null) {
                ImageManager.this.zamn.remove(this.zamt);
                imageReceiver.zac(this.zamt);
            }
            zab zab = this.zamt.zamv;
            if (zab.uri == null) {
                this.zamt.zaa(ImageManager.this.mContext, ImageManager.this.zamm, true);
                return;
            }
            Bitmap zaa = ImageManager.this.zaa((ImageManager) zab);
            if (zaa != null) {
                this.zamt.zaa(ImageManager.this.mContext, zaa, true);
                return;
            }
            Long l = (Long) ImageManager.this.zamp.get(zab.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zamt.zaa(ImageManager.this.mContext, ImageManager.this.zamm, true);
                    return;
                }
                ImageManager.this.zamp.remove(zab.uri);
            }
            this.zamt.zaa(ImageManager.this.mContext, ImageManager.this.zamm);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.zamo.get(zab.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(zab.uri);
                ImageManager.this.zamo.put(zab.uri, imageReceiver2);
            }
            imageReceiver2.zab(this.zamt);
            if (!(this.zamt instanceof zad)) {
                ImageManager.this.zamn.put(this.zamt, imageReceiver2);
            }
            synchronized (ImageManager.zamh) {
                if (!ImageManager.zami.contains(zab.uri)) {
                    ImageManager.zami.add(zab.uri);
                    imageReceiver2.zace();
                }
            }
        }
    }

    private final class zab implements Runnable {
        private final Uri mUri;
        private final ParcelFileDescriptor zams;

        public zab(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.zams = parcelFileDescriptor;
        }

        public final void run() {
            boolean z;
            Bitmap bitmap;
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            ParcelFileDescriptor parcelFileDescriptor = this.zams;
            boolean z2 = false;
            Bitmap bitmap2 = null;
            if (parcelFileDescriptor != null) {
                try {
                    bitmap2 = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String valueOf = String.valueOf(this.mUri);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34);
                    sb.append("OOM while loading bitmap for uri: ");
                    sb.append(valueOf);
                    Log.e("ImageManager", sb.toString(), e);
                    z2 = true;
                }
                try {
                    this.zams.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
                z = z2;
                bitmap = bitmap2;
            } else {
                bitmap = null;
                z = false;
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(new zad(this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException unused) {
                String valueOf2 = String.valueOf(this.mUri);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 32);
                sb2.append("Latch interrupted while posting ");
                sb2.append(valueOf2);
                Log.w("ImageManager", sb2.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    @KeepName
    public final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        private final ArrayList<zaa> zamq = new ArrayList<>();

        ImageReceiver(Uri uri) {
            super(new zap(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public final void zab(zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zamq.add(zaa);
        }

        public final void zac(zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zamq.remove(zaa);
        }

        public final void zace() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.putExtra(Constants.EXTRA_URI, this.mUri);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }

        public final void onReceiveResult(int i, Bundle bundle) {
            ImageManager.this.zamk.execute(new zab(this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    private final class zad implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zadr;
        private boolean zamu;

        public zad(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zamu = z;
            this.zadr = countDownLatch;
        }

        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.zaml != null) {
                if (this.zamu) {
                    ImageManager.this.zaml.evictAll();
                    System.gc();
                    this.zamu = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                } else if (z) {
                    ImageManager.this.zaml.put(new zab(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zamo.remove(this.mUri);
            if (imageReceiver != null) {
                ArrayList arrayList = imageReceiver.zamq;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    zaa zaa = (zaa) arrayList.get(i);
                    if (z) {
                        zaa.zaa(ImageManager.this.mContext, this.mBitmap, false);
                    } else {
                        ImageManager.this.zamp.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                        zaa.zaa(ImageManager.this.mContext, ImageManager.this.zamm, false);
                    }
                    if (!(zaa instanceof zad)) {
                        ImageManager.this.zamn.remove(zaa);
                    }
                }
            }
            this.zadr.countDown();
            synchronized (ImageManager.zamh) {
                ImageManager.zami.remove(this.mUri);
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
    }

    public final void loadImage(ImageView imageView, Uri uri) {
        zaa(new zac(imageView, uri));
    }

    public final void loadImage(ImageView imageView, int i) {
        zaa(new zac(imageView, i));
    }

    public final void loadImage(ImageView imageView, Uri uri, int i) {
        zac zac2 = new zac(imageView, uri);
        zac2.zamx = i;
        zaa(zac2);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zaa(new zad(onImageLoadedListener, uri));
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        zad zad2 = new zad(onImageLoadedListener, uri);
        zad2.zamx = i;
        zaa(zad2);
    }

    private final void zaa(zaa zaa2) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zac(zaa2).run();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final Bitmap zaa(zab zab2) {
        zaa zaa2 = this.zaml;
        if (zaa2 == null) {
            return null;
        }
        return (Bitmap) zaa2.get(zab2);
    }
}
