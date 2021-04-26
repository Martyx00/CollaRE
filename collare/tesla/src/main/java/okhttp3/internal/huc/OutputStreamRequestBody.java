package okhttp3.internal.huc;

import c.d;
import c.u;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/* access modifiers changed from: package-private */
public abstract class OutputStreamRequestBody extends RequestBody {
    boolean closed;
    private long expectedContentLength;
    private OutputStream outputStream;
    private u timeout;

    @Override // okhttp3.RequestBody
    public final MediaType contentType() {
        return null;
    }

    public Request prepareToSendRequest(Request request) {
        return request;
    }

    OutputStreamRequestBody() {
    }

    /* access modifiers changed from: protected */
    public void initOutputStream(final d dVar, final long j) {
        this.timeout = dVar.timeout();
        this.expectedContentLength = j;
        this.outputStream = new OutputStream() {
            /* class okhttp3.internal.huc.OutputStreamRequestBody.AnonymousClass1 */
            private long bytesReceived;

            @Override // java.io.OutputStream
            public void write(int i) {
                write(new byte[]{(byte) i}, 0, 1);
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                if (!OutputStreamRequestBody.this.closed) {
                    long j = j;
                    if (j == -1 || this.bytesReceived + ((long) i2) <= j) {
                        this.bytesReceived += (long) i2;
                        try {
                            dVar.c(bArr, i, i2);
                        } catch (InterruptedIOException e) {
                            throw new SocketTimeoutException(e.getMessage());
                        }
                    } else {
                        throw new ProtocolException("expected " + j + " bytes but received " + this.bytesReceived + i2);
                    }
                } else {
                    throw new IOException("closed");
                }
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
                if (!OutputStreamRequestBody.this.closed) {
                    dVar.flush();
                }
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                OutputStreamRequestBody.this.closed = true;
                long j = j;
                if (j == -1 || this.bytesReceived >= j) {
                    dVar.close();
                    return;
                }
                throw new ProtocolException("expected " + j + " bytes but received " + this.bytesReceived);
            }
        };
    }

    public final OutputStream outputStream() {
        return this.outputStream;
    }

    public final u timeout() {
        return this.timeout;
    }

    public final boolean isClosed() {
        return this.closed;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        return this.expectedContentLength;
    }
}
