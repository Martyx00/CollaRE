package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import java.lang.ref.WeakReference;

public final class zad extends zaa {
    private WeakReference<ImageManager.OnImageLoadedListener> zand;

    public zad(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
        super(uri, 0);
        Asserts.checkNotNull(onImageLoadedListener);
        this.zand = new WeakReference<>(onImageLoadedListener);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zamv);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zad)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zad zad = (zad) obj;
        ImageManager.OnImageLoadedListener onImageLoadedListener = this.zand.get();
        ImageManager.OnImageLoadedListener onImageLoadedListener2 = zad.zand.get();
        return onImageLoadedListener2 != null && onImageLoadedListener != null && Objects.equal(onImageLoadedListener2, onImageLoadedListener) && Objects.equal(zad.zamv, this.zamv);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.images.zaa
    public final void zaa(Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageManager.OnImageLoadedListener onImageLoadedListener;
        if (!z2 && (onImageLoadedListener = this.zand.get()) != null) {
            onImageLoadedListener.onImageLoaded(this.zamv.uri, drawable, z3);
        }
    }
}
