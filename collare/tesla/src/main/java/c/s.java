package c;

import java.io.Closeable;
import java.io.Flushable;

/* compiled from: Sink */
public interface s extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    @Override // java.io.Flushable
    void flush();

    u timeout();

    void write(c cVar, long j);
}
