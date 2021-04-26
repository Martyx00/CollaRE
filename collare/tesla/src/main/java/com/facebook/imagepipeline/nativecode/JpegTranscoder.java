package com.facebook.imagepipeline.nativecode;

import com.facebook.common.d.d;
import com.facebook.common.d.i;
import java.io.InputStream;
import java.io.OutputStream;

@d
public class JpegTranscoder {
    public static boolean b(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    @d
    private static native void nativeTranscodeJpeg(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3);

    @d
    private static native void nativeTranscodeJpegWithExifOrientation(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3);

    static {
        a.a();
    }

    public static boolean a(int i) {
        return i >= 0 && i <= 270 && i % 90 == 0;
    }

    public static void a(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) {
        boolean z = false;
        i.a(i2 >= 1);
        i.a(i2 <= 16);
        i.a(i3 >= 0);
        i.a(i3 <= 100);
        i.a(a(i));
        if (!(i2 == 8 && i == 0)) {
            z = true;
        }
        i.a(z, "no transformation requested");
        nativeTranscodeJpeg((InputStream) i.a(inputStream), (OutputStream) i.a(outputStream), i, i2, i3);
    }

    public static void b(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) {
        boolean z = false;
        i.a(i2 >= 1);
        i.a(i2 <= 16);
        i.a(i3 >= 0);
        i.a(i3 <= 100);
        i.a(b(i));
        if (!(i2 == 8 && i == 1)) {
            z = true;
        }
        i.a(z, "no transformation requested");
        nativeTranscodeJpegWithExifOrientation((InputStream) i.a(inputStream), (OutputStream) i.a(outputStream), i, i2, i3);
    }
}
