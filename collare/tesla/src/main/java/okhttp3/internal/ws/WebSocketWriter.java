package okhttp3.internal.ws;

import c.c;
import c.d;
import c.f;
import c.s;
import c.u;
import java.io.IOException;
import java.util.Random;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.asn1.eac.EACTags;

/* access modifiers changed from: package-private */
public final class WebSocketWriter {
    boolean activeWriter;
    final c buffer = new c();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    private final c.a maskCursor;
    private final byte[] maskKey;
    final Random random;
    final d sink;
    final c sinkBuffer;
    boolean writerClosed;

    WebSocketWriter(boolean z, d dVar, Random random2) {
        if (dVar == null) {
            throw new NullPointerException("sink == null");
        } else if (random2 != null) {
            this.isClient = z;
            this.sink = dVar;
            this.sinkBuffer = dVar.b();
            this.random = random2;
            c.a aVar = null;
            this.maskKey = z ? new byte[4] : null;
            this.maskCursor = z ? new c.a() : aVar;
        } else {
            throw new NullPointerException("random == null");
        }
    }

    /* access modifiers changed from: package-private */
    public void writePing(f fVar) {
        writeControlFrame(9, fVar);
    }

    /* access modifiers changed from: package-private */
    public void writePong(f fVar) {
        writeControlFrame(10, fVar);
    }

    /* access modifiers changed from: package-private */
    public void writeClose(int i, f fVar) {
        f fVar2 = f.f1330b;
        if (!(i == 0 && fVar == null)) {
            if (i != 0) {
                WebSocketProtocol.validateCloseCode(i);
            }
            c cVar = new c();
            cVar.h(i);
            if (fVar != null) {
                cVar.c(fVar);
            }
            fVar2 = cVar.q();
        }
        try {
            writeControlFrame(8, fVar2);
        } finally {
            this.writerClosed = true;
        }
    }

    private void writeControlFrame(int i, f fVar) {
        if (!this.writerClosed) {
            int h = fVar.h();
            if (((long) h) <= 125) {
                this.sinkBuffer.i(i | 128);
                if (this.isClient) {
                    this.sinkBuffer.i(h | 128);
                    this.random.nextBytes(this.maskKey);
                    this.sinkBuffer.c(this.maskKey);
                    if (h > 0) {
                        long a2 = this.sinkBuffer.a();
                        this.sinkBuffer.c(fVar);
                        this.sinkBuffer.a(this.maskCursor);
                        this.maskCursor.a(a2);
                        WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                        this.maskCursor.close();
                    }
                } else {
                    this.sinkBuffer.i(h);
                    this.sinkBuffer.c(fVar);
                }
                this.sink.flush();
                return;
            }
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        throw new IOException("closed");
    }

    /* access modifiers changed from: package-private */
    public s newMessageSink(int i, long j) {
        if (!this.activeWriter) {
            this.activeWriter = true;
            FrameSink frameSink2 = this.frameSink;
            frameSink2.formatOpcode = i;
            frameSink2.contentLength = j;
            frameSink2.isFirstFrame = true;
            frameSink2.closed = false;
            return frameSink2;
        }
        throw new IllegalStateException("Another message writer is active. Did you call close()?");
    }

    /* access modifiers changed from: package-private */
    public void writeMessageFrame(int i, long j, boolean z, boolean z2) {
        if (!this.writerClosed) {
            int i2 = 0;
            if (!z) {
                i = 0;
            }
            if (z2) {
                i |= 128;
            }
            this.sinkBuffer.i(i);
            if (this.isClient) {
                i2 = 128;
            }
            if (j <= 125) {
                this.sinkBuffer.i(((int) j) | i2);
            } else if (j <= 65535) {
                this.sinkBuffer.i(i2 | EACTags.NON_INTERINDUSTRY_DATA_OBJECT_NESTING_TEMPLATE);
                this.sinkBuffer.h((int) j);
            } else {
                this.sinkBuffer.i(i2 | CertificateBody.profileType);
                this.sinkBuffer.j(j);
            }
            if (this.isClient) {
                this.random.nextBytes(this.maskKey);
                this.sinkBuffer.c(this.maskKey);
                if (j > 0) {
                    long a2 = this.sinkBuffer.a();
                    this.sinkBuffer.write(this.buffer, j);
                    this.sinkBuffer.a(this.maskCursor);
                    this.maskCursor.a(a2);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            } else {
                this.sinkBuffer.write(this.buffer, j);
            }
            this.sink.e();
            return;
        }
        throw new IOException("closed");
    }

    /* access modifiers changed from: package-private */
    public final class FrameSink implements s {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        @Override // c.s
        public void write(c cVar, long j) {
            if (!this.closed) {
                WebSocketWriter.this.buffer.write(cVar, j);
                boolean z = this.isFirstFrame && this.contentLength != -1 && WebSocketWriter.this.buffer.a() > this.contentLength - 8192;
                long h = WebSocketWriter.this.buffer.h();
                if (h > 0 && !z) {
                    WebSocketWriter.this.writeMessageFrame(this.formatOpcode, h, this.isFirstFrame, false);
                    this.isFirstFrame = false;
                    return;
                }
                return;
            }
            throw new IOException("closed");
        }

        @Override // c.s, java.io.Flushable
        public void flush() {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.a(), this.isFirstFrame, false);
                this.isFirstFrame = false;
                return;
            }
            throw new IOException("closed");
        }

        @Override // c.s
        public u timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        @Override // java.io.Closeable, c.s, java.lang.AutoCloseable
        public void close() {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.a(), this.isFirstFrame, true);
                this.closed = true;
                WebSocketWriter.this.activeWriter = false;
                return;
            }
            throw new IOException("closed");
        }
    }
}
