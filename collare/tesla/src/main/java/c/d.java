package c;

import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;

/* compiled from: BufferedSink */
public interface d extends s, WritableByteChannel {
    long a(t tVar);

    c b();

    d b(String str);

    d c(f fVar);

    d c(byte[] bArr);

    d c(byte[] bArr, int i, int i2);

    OutputStream c();

    d e();

    @Override // c.s, java.io.Flushable
    void flush();

    d g(int i);

    d h(int i);

    d i(int i);

    d m(long j);

    d n(long j);

    d x();
}
