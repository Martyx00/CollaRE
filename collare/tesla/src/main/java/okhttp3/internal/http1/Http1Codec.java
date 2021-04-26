package okhttp3.internal.http1;

import c.c;
import c.d;
import c.e;
import c.i;
import c.l;
import c.s;
import c.t;
import c.u;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;

public final class Http1Codec implements HttpCodec {
    private static final int HEADER_LIMIT = 262144;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    final OkHttpClient client;
    private long headerLimit = 262144;
    final d sink;
    final e source;
    int state = 0;
    final StreamAllocation streamAllocation;

    public Http1Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation2, e eVar, d dVar) {
        this.client = okHttpClient;
        this.streamAllocation = streamAllocation2;
        this.source = eVar;
        this.sink = dVar;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public s createRequestBody(Request request, long j) {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return newChunkedSink();
        }
        if (j != -1) {
            return newFixedLengthSink(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() {
        RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void writeRequestHeaders(Request request) {
        writeRequest(request.headers(), RequestLine.get(request, this.streamAllocation.connection().route().proxy().type()));
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody openResponseBody(Response response) {
        this.streamAllocation.eventListener.responseBodyStart(this.streamAllocation.call);
        String header = response.header("Content-Type");
        if (!HttpHeaders.hasBody(response)) {
            return new RealResponseBody(header, 0, l.a(newFixedLengthSource(0)));
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return new RealResponseBody(header, -1, l.a(newChunkedSource(response.request().url())));
        }
        long contentLength = HttpHeaders.contentLength(response);
        if (contentLength != -1) {
            return new RealResponseBody(header, contentLength, l.a(newFixedLengthSource(contentLength)));
        }
        return new RealResponseBody(header, -1, l.a(newUnknownLengthSource()));
    }

    public boolean isClosed() {
        return this.state == 6;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void flushRequest() {
        this.sink.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void finishRequest() {
        this.sink.flush();
    }

    public void writeRequest(Headers headers, String str) {
        if (this.state == 0) {
            this.sink.b(str).b("\r\n");
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                this.sink.b(headers.name(i)).b(": ").b(headers.value(i)).b("\r\n");
            }
            this.sink.b("\r\n");
            this.state = 1;
            return;
        }
        throw new IllegalStateException("state: " + this.state);
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder readResponseHeaders(boolean z) {
        int i = this.state;
        if (i == 1 || i == 3) {
            try {
                StatusLine parse = StatusLine.parse(readHeaderLine());
                Response.Builder headers = new Response.Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(readHeaders());
                if (z && parse.code == 100) {
                    return null;
                }
                if (parse.code == 100) {
                    this.state = 3;
                    return headers;
                }
                this.state = 4;
                return headers;
            } catch (EOFException e) {
                IOException iOException = new IOException("unexpected end of stream on " + this.streamAllocation);
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            throw new IllegalStateException("state: " + this.state);
        }
    }

    private String readHeaderLine() {
        String f = this.source.f(this.headerLimit);
        this.headerLimit -= (long) f.length();
        return f;
    }

    public Headers readHeaders() {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String readHeaderLine = readHeaderLine();
            if (readHeaderLine.length() == 0) {
                return builder.build();
            }
            Internal.instance.addLenient(builder, readHeaderLine);
        }
    }

    public s newChunkedSink() {
        if (this.state == 1) {
            this.state = 2;
            return new ChunkedSink();
        }
        throw new IllegalStateException("state: " + this.state);
    }

    public s newFixedLengthSink(long j) {
        if (this.state == 1) {
            this.state = 2;
            return new FixedLengthSink(j);
        }
        throw new IllegalStateException("state: " + this.state);
    }

    public t newFixedLengthSource(long j) {
        if (this.state == 4) {
            this.state = 5;
            return new FixedLengthSource(j);
        }
        throw new IllegalStateException("state: " + this.state);
    }

    public t newChunkedSource(HttpUrl httpUrl) {
        if (this.state == 4) {
            this.state = 5;
            return new ChunkedSource(httpUrl);
        }
        throw new IllegalStateException("state: " + this.state);
    }

    public t newUnknownLengthSource() {
        if (this.state == 4) {
            StreamAllocation streamAllocation2 = this.streamAllocation;
            if (streamAllocation2 != null) {
                this.state = 5;
                streamAllocation2.noNewStreams();
                return new UnknownLengthSource();
            }
            throw new IllegalStateException("streamAllocation == null");
        }
        throw new IllegalStateException("state: " + this.state);
    }

    /* access modifiers changed from: package-private */
    public void detachTimeout(i iVar) {
        u a2 = iVar.a();
        iVar.a(u.NONE);
        a2.clearDeadline();
        a2.clearTimeout();
    }

    /* access modifiers changed from: private */
    public final class FixedLengthSink implements s {
        private long bytesRemaining;
        private boolean closed;
        private final i timeout = new i(Http1Codec.this.sink.timeout());

        FixedLengthSink(long j) {
            this.bytesRemaining = j;
        }

        @Override // c.s
        public u timeout() {
            return this.timeout;
        }

        @Override // c.s
        public void write(c cVar, long j) {
            if (!this.closed) {
                Util.checkOffsetAndCount(cVar.a(), 0, j);
                if (j <= this.bytesRemaining) {
                    Http1Codec.this.sink.write(cVar, j);
                    this.bytesRemaining -= j;
                    return;
                }
                throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + j);
            }
            throw new IllegalStateException("closed");
        }

        @Override // c.s, java.io.Flushable
        public void flush() {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        @Override // java.io.Closeable, c.s, java.lang.AutoCloseable
        public void close() {
            if (!this.closed) {
                this.closed = true;
                if (this.bytesRemaining <= 0) {
                    Http1Codec.this.detachTimeout(this.timeout);
                    Http1Codec.this.state = 3;
                    return;
                }
                throw new ProtocolException("unexpected end of stream");
            }
        }
    }

    /* access modifiers changed from: private */
    public final class ChunkedSink implements s {
        private boolean closed;
        private final i timeout = new i(Http1Codec.this.sink.timeout());

        ChunkedSink() {
        }

        @Override // c.s
        public u timeout() {
            return this.timeout;
        }

        @Override // c.s
        public void write(c cVar, long j) {
            if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                Http1Codec.this.sink.m(j);
                Http1Codec.this.sink.b("\r\n");
                Http1Codec.this.sink.write(cVar, j);
                Http1Codec.this.sink.b("\r\n");
            }
        }

        @Override // c.s, java.io.Flushable
        public synchronized void flush() {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        @Override // java.io.Closeable, c.s, java.lang.AutoCloseable
        public synchronized void close() {
            if (!this.closed) {
                this.closed = true;
                Http1Codec.this.sink.b("0\r\n\r\n");
                Http1Codec.this.detachTimeout(this.timeout);
                Http1Codec.this.state = 3;
            }
        }
    }

    private abstract class AbstractSource implements t {
        protected long bytesRead;
        protected boolean closed;
        protected final i timeout;

        private AbstractSource() {
            this.timeout = new i(Http1Codec.this.source.timeout());
            this.bytesRead = 0;
        }

        @Override // c.t
        public u timeout() {
            return this.timeout;
        }

        @Override // c.t
        public long read(c cVar, long j) {
            try {
                long read = Http1Codec.this.source.read(cVar, j);
                if (read > 0) {
                    this.bytesRead += read;
                }
                return read;
            } catch (IOException e) {
                endOfInput(false, e);
                throw e;
            }
        }

        /* access modifiers changed from: protected */
        public final void endOfInput(boolean z, IOException iOException) {
            if (Http1Codec.this.state != 6) {
                if (Http1Codec.this.state == 5) {
                    Http1Codec.this.detachTimeout(this.timeout);
                    Http1Codec http1Codec = Http1Codec.this;
                    http1Codec.state = 6;
                    if (http1Codec.streamAllocation != null) {
                        Http1Codec.this.streamAllocation.streamFinished(!z, Http1Codec.this, this.bytesRead, iOException);
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("state: " + Http1Codec.this.state);
            }
        }
    }

    /* access modifiers changed from: private */
    public class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;

        FixedLengthSource(long j) {
            super();
            this.bytesRemaining = j;
            if (this.bytesRemaining == 0) {
                endOfInput(true, null);
            }
        }

        @Override // c.t, okhttp3.internal.http1.Http1Codec.AbstractSource
        public long read(c cVar, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (!this.closed) {
                long j2 = this.bytesRemaining;
                if (j2 == 0) {
                    return -1;
                }
                long read = super.read(cVar, Math.min(j2, j));
                if (read != -1) {
                    this.bytesRemaining -= read;
                    if (this.bytesRemaining == 0) {
                        endOfInput(true, null);
                    }
                    return read;
                }
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                endOfInput(false, protocolException);
                throw protocolException;
            } else {
                throw new IllegalStateException("closed");
            }
        }

        @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
        public void close() {
            if (!this.closed) {
                if (this.bytesRemaining != 0 && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    endOfInput(false, null);
                }
                this.closed = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public class ChunkedSource extends AbstractSource {
        private static final long NO_CHUNK_YET = -1;
        private long bytesRemainingInChunk = NO_CHUNK_YET;
        private boolean hasMoreChunks = true;
        private final HttpUrl url;

        ChunkedSource(HttpUrl httpUrl) {
            super();
            this.url = httpUrl;
        }

        @Override // c.t, okhttp3.internal.http1.Http1Codec.AbstractSource
        public long read(c cVar, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (!this.hasMoreChunks) {
                return NO_CHUNK_YET;
            } else {
                long j2 = this.bytesRemainingInChunk;
                if (j2 == 0 || j2 == NO_CHUNK_YET) {
                    readChunkSize();
                    if (!this.hasMoreChunks) {
                        return NO_CHUNK_YET;
                    }
                }
                long read = super.read(cVar, Math.min(j, this.bytesRemainingInChunk));
                if (read != NO_CHUNK_YET) {
                    this.bytesRemainingInChunk -= read;
                    return read;
                }
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                endOfInput(false, protocolException);
                throw protocolException;
            }
        }

        private void readChunkSize() {
            if (this.bytesRemainingInChunk != NO_CHUNK_YET) {
                Http1Codec.this.source.s();
            }
            try {
                this.bytesRemainingInChunk = Http1Codec.this.source.p();
                String trim = Http1Codec.this.source.s().trim();
                if (this.bytesRemainingInChunk < 0 || (!trim.isEmpty() && !trim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + trim + "\"");
                } else if (this.bytesRemainingInChunk == 0) {
                    this.hasMoreChunks = false;
                    HttpHeaders.receiveHeaders(Http1Codec.this.client.cookieJar(), this.url, Http1Codec.this.readHeaders());
                    endOfInput(true, null);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
        public void close() {
            if (!this.closed) {
                if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    endOfInput(false, null);
                }
                this.closed = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;

        UnknownLengthSource() {
            super();
        }

        @Override // c.t, okhttp3.internal.http1.Http1Codec.AbstractSource
        public long read(c cVar, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (this.inputExhausted) {
                return -1;
            } else {
                long read = super.read(cVar, j);
                if (read != -1) {
                    return read;
                }
                this.inputExhausted = true;
                endOfInput(true, null);
                return -1;
            }
        }

        @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
        public void close() {
            if (!this.closed) {
                if (!this.inputExhausted) {
                    endOfInput(false, null);
                }
                this.closed = true;
            }
        }
    }
}
