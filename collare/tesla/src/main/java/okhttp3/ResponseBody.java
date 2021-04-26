package okhttp3;

import c.c;
import c.e;
import c.f;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import okhttp3.internal.Util;

public abstract class ResponseBody implements Closeable {
    private Reader reader;

    public abstract long contentLength();

    public abstract MediaType contentType();

    public abstract e source();

    public final InputStream byteStream() {
        return source().g();
    }

    /* JADX INFO: finally extract failed */
    public final byte[] bytes() {
        long contentLength = contentLength();
        if (contentLength <= 2147483647L) {
            e source = source();
            try {
                byte[] t = source.t();
                Util.closeQuietly(source);
                if (contentLength == -1 || contentLength == ((long) t.length)) {
                    return t;
                }
                throw new IOException("Content-Length (" + contentLength + ") and stream length (" + t.length + ") disagree");
            } catch (Throwable th) {
                Util.closeQuietly(source);
                throw th;
            }
        } else {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
    }

    public final Reader charStream() {
        Reader reader2 = this.reader;
        if (reader2 != null) {
            return reader2;
        }
        BomAwareReader bomAwareReader = new BomAwareReader(source(), charset());
        this.reader = bomAwareReader;
        return bomAwareReader;
    }

    public final String string() {
        e source = source();
        try {
            return source.a(Util.bomAwareCharset(source, charset()));
        } finally {
            Util.closeQuietly(source);
        }
    }

    private Charset charset() {
        MediaType contentType = contentType();
        return contentType != null ? contentType.charset(Util.UTF_8) : Util.UTF_8;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Util.closeQuietly(source());
    }

    public static ResponseBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        c a2 = new c().a(str, charset);
        return create(mediaType, a2.a(), a2);
    }

    public static ResponseBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, (long) bArr.length, new c().c(bArr));
    }

    public static ResponseBody create(MediaType mediaType, f fVar) {
        return create(mediaType, (long) fVar.h(), new c().c(fVar));
    }

    public static ResponseBody create(final MediaType mediaType, final long j, final e eVar) {
        if (eVar != null) {
            return new ResponseBody() {
                /* class okhttp3.ResponseBody.AnonymousClass1 */

                @Override // okhttp3.ResponseBody
                public MediaType contentType() {
                    return MediaType.this;
                }

                @Override // okhttp3.ResponseBody
                public long contentLength() {
                    return j;
                }

                @Override // okhttp3.ResponseBody
                public e source() {
                    return eVar;
                }
            };
        }
        throw new NullPointerException("source == null");
    }

    static final class BomAwareReader extends Reader {
        private final Charset charset;
        private boolean closed;
        private Reader delegate;
        private final e source;

        BomAwareReader(e eVar, Charset charset2) {
            this.source = eVar;
            this.charset = charset2;
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) {
            if (!this.closed) {
                Reader reader = this.delegate;
                if (reader == null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(this.source.g(), Util.bomAwareCharset(this.source, this.charset));
                    this.delegate = inputStreamReader;
                    reader = inputStreamReader;
                }
                return reader.read(cArr, i, i2);
            }
            throw new IOException("Stream closed");
        }

        @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
        public void close() {
            this.closed = true;
            Reader reader = this.delegate;
            if (reader != null) {
                reader.close();
            } else {
                this.source.close();
            }
        }
    }
}
