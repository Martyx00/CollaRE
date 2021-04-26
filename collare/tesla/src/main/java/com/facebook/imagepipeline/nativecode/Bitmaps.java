package com.facebook.imagepipeline.nativecode;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.common.d.d;
import com.facebook.common.d.i;
import com.facebook.h.a;
import java.nio.ByteBuffer;

@d
public class Bitmaps {
    @d
    private static native void nativeCopyBitmap(Bitmap bitmap, int i, Bitmap bitmap2, int i2, int i3);

    @d
    private static native ByteBuffer nativeGetByteBuffer(Bitmap bitmap, long j, long j2);

    @d
    private static native void nativePinBitmap(Bitmap bitmap);

    @d
    private static native void nativeReleaseByteBuffer(Bitmap bitmap);

    static {
        a.a();
    }

    public static void a(Bitmap bitmap) {
        i.a(bitmap);
        nativePinBitmap(bitmap);
    }

    public static void a(Bitmap bitmap, Bitmap bitmap2) {
        boolean z = true;
        i.a(bitmap2.getConfig() == bitmap.getConfig());
        i.a(bitmap.isMutable());
        i.a(bitmap.getWidth() == bitmap2.getWidth());
        if (bitmap.getHeight() != bitmap2.getHeight()) {
            z = false;
        }
        i.a(z);
        nativeCopyBitmap(bitmap, bitmap.getRowBytes(), bitmap2, bitmap2.getRowBytes(), bitmap.getHeight());
    }

    @TargetApi(19)
    public static void a(Bitmap bitmap, int i, int i2, Bitmap.Config config) {
        i.a(bitmap.getAllocationByteCount() >= (i * i2) * a.a(config));
        bitmap.reconfigure(i, i2, config);
    }
}
