package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

/* compiled from: TypefaceCompatApi21Impl */
class d extends h {
    d() {
    }

    private File a(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
            return null;
        } catch (ErrnoException unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0045, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0046, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004a, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004b, code lost:
        r8 = r5;
        r5 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005d, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005e, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0062, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0063, code lost:
        r7 = r5;
        r5 = r7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005d A[ExcHandler: all (th java.lang.Throwable)] */
    @Override // android.support.v4.graphics.h
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface a(android.content.Context r5, android.os.CancellationSignal r6, android.support.v4.e.b.C0008b[] r7, int r8) {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.d.a(android.content.Context, android.os.CancellationSignal, android.support.v4.e.b$b[], int):android.graphics.Typeface");
    }
}
