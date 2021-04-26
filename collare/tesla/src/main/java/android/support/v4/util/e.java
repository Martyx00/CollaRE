package android.support.v4.util;

import android.util.Log;
import java.io.Writer;

/* compiled from: LogWriter */
public class e extends Writer {

    /* renamed from: a  reason: collision with root package name */
    private final String f647a;

    /* renamed from: b  reason: collision with root package name */
    private StringBuilder f648b = new StringBuilder(128);

    public e(String str) {
        this.f647a = str;
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() {
        a();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        a();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c2 = cArr[i + i3];
            if (c2 == '\n') {
                a();
            } else {
                this.f648b.append(c2);
            }
        }
    }

    private void a() {
        if (this.f648b.length() > 0) {
            Log.d(this.f647a, this.f648b.toString());
            StringBuilder sb = this.f648b;
            sb.delete(0, sb.length());
        }
    }
}
