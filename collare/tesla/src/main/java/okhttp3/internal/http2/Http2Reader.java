package okhttp3.internal.http2;

import c.c;
import c.e;
import c.f;
import c.t;
import c.u;
import com.google.android.gms.common.api.Api;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;

/* access modifiers changed from: package-private */
public final class Http2Reader implements Closeable {
    static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final boolean client;
    private final ContinuationSource continuation = new ContinuationSource(this.source);
    final Hpack.Reader hpackReader = new Hpack.Reader(4096, this.continuation);
    private final e source;

    /* access modifiers changed from: package-private */
    public interface Handler {
        void ackSettings();

        void alternateService(int i, String str, f fVar, String str2, int i2, long j);

        void data(boolean z, int i, e eVar, int i2);

        void goAway(int i, ErrorCode errorCode, f fVar);

        void headers(boolean z, int i, int i2, List<Header> list);

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, List<Header> list);

        void rstStream(int i, ErrorCode errorCode);

        void settings(boolean z, Settings settings);

        void windowUpdate(int i, long j);
    }

    Http2Reader(e eVar, boolean z) {
        this.source = eVar;
        this.client = z;
    }

    public void readConnectionPreface(Handler handler) {
        if (!this.client) {
            f d2 = this.source.d((long) Http2.CONNECTION_PREFACE.h());
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Util.format("<< CONNECTION %s", d2.f()));
            }
            if (!Http2.CONNECTION_PREFACE.equals(d2)) {
                throw Http2.ioException("Expected a connection header but was %s", d2.a());
            }
        } else if (!nextFrame(true, handler)) {
            throw Http2.ioException("Required SETTINGS preface not received", new Object[0]);
        }
    }

    public boolean nextFrame(boolean z, Handler handler) {
        try {
            this.source.a(9);
            int readMedium = readMedium(this.source);
            if (readMedium < 0 || readMedium > 16384) {
                throw Http2.ioException("FRAME_SIZE_ERROR: %s", Integer.valueOf(readMedium));
            }
            byte i = (byte) (this.source.i() & 255);
            if (!z || i == 4) {
                byte i2 = (byte) (this.source.i() & 255);
                int k = this.source.k() & Api.BaseClientBuilder.API_PRIORITY_OTHER;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(Http2.frameLog(true, k, readMedium, i, i2));
                }
                switch (i) {
                    case 0:
                        readData(handler, readMedium, i2, k);
                        break;
                    case 1:
                        readHeaders(handler, readMedium, i2, k);
                        break;
                    case 2:
                        readPriority(handler, readMedium, i2, k);
                        break;
                    case 3:
                        readRstStream(handler, readMedium, i2, k);
                        break;
                    case 4:
                        readSettings(handler, readMedium, i2, k);
                        break;
                    case 5:
                        readPushPromise(handler, readMedium, i2, k);
                        break;
                    case 6:
                        readPing(handler, readMedium, i2, k);
                        break;
                    case 7:
                        readGoAway(handler, readMedium, i2, k);
                        break;
                    case 8:
                        readWindowUpdate(handler, readMedium, i2, k);
                        break;
                    default:
                        this.source.i((long) readMedium);
                        break;
                }
                return true;
            }
            throw Http2.ioException("Expected a SETTINGS frame but was %s", Byte.valueOf(i));
        } catch (IOException unused) {
            return false;
        }
    }

    private void readHeaders(Handler handler, int i, byte b2, int i2) {
        short s = 0;
        if (i2 != 0) {
            boolean z = (b2 & 1) != 0;
            if ((b2 & 8) != 0) {
                s = (short) (this.source.i() & 255);
            }
            if ((b2 & 32) != 0) {
                readPriority(handler, i2);
                i -= 5;
            }
            handler.headers(z, i2, -1, readHeaderBlock(lengthWithoutPadding(i, b2, s), s, b2, i2));
            return;
        }
        throw Http2.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
    }

    private List<Header> readHeaderBlock(int i, short s, byte b2, int i2) {
        ContinuationSource continuationSource = this.continuation;
        continuationSource.left = i;
        continuationSource.length = i;
        continuationSource.padding = s;
        continuationSource.flags = b2;
        continuationSource.streamId = i2;
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private void readData(Handler handler, int i, byte b2, int i2) {
        short s = 0;
        if (i2 != 0) {
            boolean z = true;
            boolean z2 = (b2 & 1) != 0;
            if ((b2 & 32) == 0) {
                z = false;
            }
            if (!z) {
                if ((b2 & 8) != 0) {
                    s = (short) (this.source.i() & 255);
                }
                handler.data(z2, i2, this.source, lengthWithoutPadding(i, b2, s));
                this.source.i((long) s);
                return;
            }
            throw Http2.ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        throw Http2.ioException("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
    }

    private void readPriority(Handler handler, int i, byte b2, int i2) {
        if (i != 5) {
            throw Http2.ioException("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
        } else if (i2 != 0) {
            readPriority(handler, i2);
        } else {
            throw Http2.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
    }

    private void readPriority(Handler handler, int i) {
        int k = this.source.k();
        handler.priority(i, k & Api.BaseClientBuilder.API_PRIORITY_OTHER, (this.source.i() & 255) + 1, (Integer.MIN_VALUE & k) != 0);
    }

    private void readRstStream(Handler handler, int i, byte b2, int i2) {
        if (i != 4) {
            throw Http2.ioException("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
        } else if (i2 != 0) {
            int k = this.source.k();
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(k);
            if (fromHttp2 != null) {
                handler.rstStream(i2, fromHttp2);
            } else {
                throw Http2.ioException("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(k));
            }
        } else {
            throw Http2.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
    }

    private void readSettings(Handler handler, int i, byte b2, int i2) {
        if (i2 != 0) {
            throw Http2.ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
        } else if ((b2 & 1) != 0) {
            if (i == 0) {
                handler.ackSettings();
                return;
            }
            throw Http2.ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
        } else if (i % 6 == 0) {
            Settings settings = new Settings();
            for (int i3 = 0; i3 < i; i3 += 6) {
                int j = this.source.j() & 65535;
                int k = this.source.k();
                switch (j) {
                    case 2:
                        if (!(k == 0 || k == 1)) {
                            throw Http2.ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        }
                    case 3:
                        j = 4;
                        break;
                    case 4:
                        j = 7;
                        if (k >= 0) {
                            break;
                        } else {
                            throw Http2.ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        }
                    case 5:
                        if (k >= 16384 && k <= 16777215) {
                            break;
                        } else {
                            throw Http2.ioException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(k));
                        }
                        break;
                }
                settings.set(j, k);
            }
            handler.settings(false, settings);
        } else {
            throw Http2.ioException("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
        }
    }

    private void readPushPromise(Handler handler, int i, byte b2, int i2) {
        short s = 0;
        if (i2 != 0) {
            if ((b2 & 8) != 0) {
                s = (short) (this.source.i() & 255);
            }
            handler.pushPromise(i2, this.source.k() & Api.BaseClientBuilder.API_PRIORITY_OTHER, readHeaderBlock(lengthWithoutPadding(i - 4, b2, s), s, b2, i2));
            return;
        }
        throw Http2.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
    }

    private void readPing(Handler handler, int i, byte b2, int i2) {
        boolean z = false;
        if (i != 8) {
            throw Http2.ioException("TYPE_PING length != 8: %s", Integer.valueOf(i));
        } else if (i2 == 0) {
            int k = this.source.k();
            int k2 = this.source.k();
            if ((b2 & 1) != 0) {
                z = true;
            }
            handler.ping(z, k, k2);
        } else {
            throw Http2.ioException("TYPE_PING streamId != 0", new Object[0]);
        }
    }

    private void readGoAway(Handler handler, int i, byte b2, int i2) {
        if (i < 8) {
            throw Http2.ioException("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
        } else if (i2 == 0) {
            int k = this.source.k();
            int k2 = this.source.k();
            int i3 = i - 8;
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(k2);
            if (fromHttp2 != null) {
                f fVar = f.f1330b;
                if (i3 > 0) {
                    fVar = this.source.d((long) i3);
                }
                handler.goAway(k, fromHttp2, fVar);
                return;
            }
            throw Http2.ioException("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(k2));
        } else {
            throw Http2.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
    }

    private void readWindowUpdate(Handler handler, int i, byte b2, int i2) {
        if (i == 4) {
            long k = ((long) this.source.k()) & 2147483647L;
            if (k != 0) {
                handler.windowUpdate(i2, k);
            } else {
                throw Http2.ioException("windowSizeIncrement was 0", Long.valueOf(k));
            }
        } else {
            throw Http2.ioException("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.source.close();
    }

    /* access modifiers changed from: package-private */
    public static final class ContinuationSource implements t {
        byte flags;
        int left;
        int length;
        short padding;
        private final e source;
        int streamId;

        @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
        public void close() {
        }

        ContinuationSource(e eVar) {
            this.source = eVar;
        }

        @Override // c.t
        public long read(c cVar, long j) {
            while (true) {
                int i = this.left;
                if (i == 0) {
                    this.source.i((long) this.padding);
                    this.padding = 0;
                    if ((this.flags & 4) != 0) {
                        return -1;
                    }
                    readContinuationHeader();
                } else {
                    long read = this.source.read(cVar, Math.min(j, (long) i));
                    if (read == -1) {
                        return -1;
                    }
                    this.left = (int) (((long) this.left) - read);
                    return read;
                }
            }
        }

        @Override // c.t
        public u timeout() {
            return this.source.timeout();
        }

        private void readContinuationHeader() {
            int i = this.streamId;
            int readMedium = Http2Reader.readMedium(this.source);
            this.left = readMedium;
            this.length = readMedium;
            byte i2 = (byte) (this.source.i() & 255);
            this.flags = (byte) (this.source.i() & 255);
            if (Http2Reader.logger.isLoggable(Level.FINE)) {
                Http2Reader.logger.fine(Http2.frameLog(true, this.streamId, this.length, i2, this.flags));
            }
            this.streamId = this.source.k() & Api.BaseClientBuilder.API_PRIORITY_OTHER;
            if (i2 != 9) {
                throw Http2.ioException("%s != TYPE_CONTINUATION", Byte.valueOf(i2));
            } else if (this.streamId != i) {
                throw Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    static int readMedium(e eVar) {
        return (eVar.i() & 255) | ((eVar.i() & 255) << 16) | ((eVar.i() & 255) << 8);
    }

    static int lengthWithoutPadding(int i, byte b2, short s) {
        if ((b2 & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        throw Http2.ioException("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
    }
}
