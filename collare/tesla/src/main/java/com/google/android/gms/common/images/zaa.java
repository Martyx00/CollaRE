package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zak;

public abstract class zaa {
    final zab zamv;
    private int zamw = 0;
    protected int zamx = 0;
    private boolean zamy = false;
    private boolean zamz = true;
    private boolean zana = false;
    private boolean zanb = true;

    public zaa(Uri uri, int i) {
        this.zamv = new zab(uri);
        this.zamx = i;
    }

    /* access modifiers changed from: protected */
    public abstract void zaa(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* access modifiers changed from: package-private */
    public final void zaa(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* access modifiers changed from: package-private */
    public final void zaa(Context context, zak zak) {
        if (this.zanb) {
            zaa(null, false, true, false);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zaa(Context context, zak zak, boolean z) {
        int i = this.zamx;
        zaa(i != 0 ? context.getResources().getDrawable(i) : null, z, false, false);
    }

    /* access modifiers changed from: protected */
    public final boolean zaa(boolean z, boolean z2) {
        return this.zamz && !z2 && !z;
    }
}
