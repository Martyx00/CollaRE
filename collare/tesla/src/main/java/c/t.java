package c;

import java.io.Closeable;

/* compiled from: Source */
public interface t extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    long read(c cVar, long j);

    u timeout();
}
