package com.crashlytics.android.core;

import com.crashlytics.android.core.BinaryImagesConverter;
import io.a.a.a.a.b.i;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;

/* access modifiers changed from: package-private */
public class Sha1FileIdStrategy implements BinaryImagesConverter.FileIdStrategy {
    Sha1FileIdStrategy() {
    }

    @Override // com.crashlytics.android.core.BinaryImagesConverter.FileIdStrategy
    public String createId(File file) {
        return getFileSHA(file.getPath());
    }

    private static String getFileSHA(String str) {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(str));
            try {
                String b2 = i.b(bufferedInputStream2);
                i.a((Closeable) bufferedInputStream2);
                return b2;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                i.a((Closeable) bufferedInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            i.a((Closeable) bufferedInputStream);
            throw th;
        }
    }
}
