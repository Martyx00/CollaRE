package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.base.zae;
import com.google.android.gms.internal.base.zaj;
import java.lang.ref.WeakReference;

public final class zac extends zaa {
    private WeakReference<ImageView> zanc;

    public zac(ImageView imageView, Uri uri) {
        super(uri, 0);
        Asserts.checkNotNull(imageView);
        this.zanc = new WeakReference<>(imageView);
    }

    public final int hashCode() {
        return 0;
    }

    public zac(ImageView imageView, int i) {
        super(null, i);
        Asserts.checkNotNull(imageView);
        this.zanc = new WeakReference<>(imageView);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zac)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ImageView imageView = this.zanc.get();
        ImageView imageView2 = ((zac) obj).zanc.get();
        return (imageView2 == null || imageView == null || !Objects.equal(imageView2, imageView)) ? false : true;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.images.zaa
    public final void zaa(Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageView imageView = this.zanc.get();
        if (imageView != null) {
            int i = 0;
            boolean z4 = !z2 && !z3;
            if (z4 && (imageView instanceof zaj)) {
                int zach = zaj.zach();
                if (this.zamx != 0 && zach == this.zamx) {
                    return;
                }
            }
            boolean zaa = zaa(z, z2);
            Uri uri = null;
            if (zaa) {
                Drawable drawable2 = imageView.getDrawable();
                if (drawable2 == null) {
                    drawable2 = null;
                } else if (drawable2 instanceof zae) {
                    drawable2 = ((zae) drawable2).zacf();
                }
                drawable = new zae(drawable2, drawable);
            }
            imageView.setImageDrawable(drawable);
            if (imageView instanceof zaj) {
                if (z3) {
                    uri = this.zamv.uri;
                }
                zaj.zaa(uri);
                if (z4) {
                    i = this.zamx;
                }
                zaj.zai(i);
            }
            if (zaa) {
                ((zae) drawable).startTransition(250);
            }
        }
    }
}
