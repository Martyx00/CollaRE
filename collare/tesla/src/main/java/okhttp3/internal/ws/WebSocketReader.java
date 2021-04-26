package okhttp3.internal.ws;

import c.c;
import c.e;
import c.f;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import org.spongycastle.asn1.eac.CertificateBody;

/* access modifiers changed from: package-private */
public final class WebSocketReader {
    boolean closed;
    private final c controlFrameBuffer = new c();
    final FrameCallback frameCallback;
    long frameLength;
    final boolean isClient;
    boolean isControlFrame;
    boolean isFinalFrame;
    private final c.a maskCursor;
    private final byte[] maskKey;
    private final c messageFrameBuffer = new c();
    int opcode;
    final e source;

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(f fVar);

        void onReadMessage(String str);

        void onReadPing(f fVar);

        void onReadPong(f fVar);
    }

    WebSocketReader(boolean z, e eVar, FrameCallback frameCallback2) {
        byte[] bArr;
        if (eVar == null) {
            throw new NullPointerException("source == null");
        } else if (frameCallback2 != null) {
            this.isClient = z;
            this.source = eVar;
            this.frameCallback = frameCallback2;
            c.a aVar = null;
            if (z) {
                bArr = null;
            } else {
                bArr = new byte[4];
            }
            this.maskKey = bArr;
            this.maskCursor = !z ? new c.a() : aVar;
        } else {
            throw new NullPointerException("frameCallback == null");
        }
    }

    /* access modifiers changed from: package-private */
    public void processNextFrame() {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    /* JADX INFO: finally extract failed */
    private void readHeader() {
        if (!this.closed) {
            long timeoutNanos = this.source.timeout().timeoutNanos();
            this.source.timeout().clearTimeout();
            try {
                int i = this.source.i() & 255;
                this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                this.opcode = i & 15;
                boolean z = true;
                this.isFinalFrame = (i & 128) != 0;
                this.isControlFrame = (i & 8) != 0;
                if (!this.isControlFrame || this.isFinalFrame) {
                    boolean z2 = (i & 64) != 0;
                    boolean z3 = (i & 32) != 0;
                    boolean z4 = (i & 16) != 0;
                    if (z2 || z3 || z4) {
                        throw new ProtocolException("Reserved flags are unsupported.");
                    }
                    int i2 = this.source.i() & 255;
                    if ((i2 & 128) == 0) {
                        z = false;
                    }
                    boolean z5 = this.isClient;
                    if (z == z5) {
                        throw new ProtocolException(z5 ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
                    }
                    this.frameLength = (long) (i2 & CertificateBody.profileType);
                    long j = this.frameLength;
                    if (j == 126) {
                        this.frameLength = ((long) this.source.j()) & 65535;
                    } else if (j == 127) {
                        this.frameLength = this.source.l();
                        if (this.frameLength < 0) {
                            throw new ProtocolException("Frame length 0x" + Long.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
                        }
                    }
                    if (this.isControlFrame && this.frameLength > 125) {
                        throw new ProtocolException("Control frame must be less than 125B.");
                    } else if (z) {
                        this.source.a(this.maskKey);
                    }
                } else {
                    throw new ProtocolException("Control frames must be final.");
                }
            } catch (Throwable th) {
                this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                throw th;
            }
        } else {
            throw new IOException("closed");
        }
    }

    private void readControlFrame() {
        long j = this.frameLength;
        if (j > 0) {
            this.source.a(this.controlFrameBuffer, j);
            if (!this.isClient) {
                this.controlFrameBuffer.a(this.maskCursor);
                this.maskCursor.a(0);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 8:
                short s = 1005;
                String str = "";
                long a2 = this.controlFrameBuffer.a();
                if (a2 != 1) {
                    if (a2 != 0) {
                        s = this.controlFrameBuffer.j();
                        str = this.controlFrameBuffer.r();
                        String closeCodeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(s);
                        if (closeCodeExceptionMessage != null) {
                            throw new ProtocolException(closeCodeExceptionMessage);
                        }
                    }
                    this.frameCallback.onReadClose(s, str);
                    this.closed = true;
                    return;
                }
                throw new ProtocolException("Malformed close payload length of 1.");
            case 9:
                this.frameCallback.onReadPing(this.controlFrameBuffer.q());
                return;
            case 10:
                this.frameCallback.onReadPong(this.controlFrameBuffer.q());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.opcode));
        }
    }

    private void readMessageFrame() {
        int i = this.opcode;
        if (i == 1 || i == 2) {
            readMessage();
            if (i == 1) {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.r());
            } else {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.q());
            }
        } else {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(i));
        }
    }

    private void readUntilNonControlFrame() {
        while (!this.closed) {
            readHeader();
            if (this.isControlFrame) {
                readControlFrame();
            } else {
                return;
            }
        }
    }

    private void readMessage() {
        while (!this.closed) {
            long j = this.frameLength;
            if (j > 0) {
                this.source.a(this.messageFrameBuffer, j);
                if (!this.isClient) {
                    this.messageFrameBuffer.a(this.maskCursor);
                    this.maskCursor.a(this.messageFrameBuffer.a() - this.frameLength);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            if (!this.isFinalFrame) {
                readUntilNonControlFrame();
                if (this.opcode != 0) {
                    throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.opcode));
                }
            } else {
                return;
            }
        }
        throw new IOException("closed");
    }
}
