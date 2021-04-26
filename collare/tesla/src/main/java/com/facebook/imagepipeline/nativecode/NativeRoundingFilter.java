package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.d.d;

@d
public class NativeRoundingFilter {
    @d
    private static native void nativeToCircleFilter(Bitmap bitmap);

    static {
        a.a();
    }
}
