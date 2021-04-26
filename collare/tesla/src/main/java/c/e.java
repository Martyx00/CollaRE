package c;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/* compiled from: BufferedSource */
public interface e extends t, ReadableByteChannel {
    long a(byte b2);

    String a(Charset charset);

    void a(long j);

    void a(c cVar, long j);

    void a(byte[] bArr);

    boolean a(long j, f fVar);

    c b();

    boolean b(long j);

    f d(long j);

    String f(long j);

    boolean f();

    InputStream g();

    byte[] h(long j);

    byte i();

    void i(long j);

    short j();

    int k();

    long l();

    short m();

    int n();

    long o();

    long p();

    String s();

    byte[] t();
}
