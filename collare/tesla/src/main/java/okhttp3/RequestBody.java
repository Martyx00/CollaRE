package okhttp3;

import c.d;
import c.f;
import c.l;
import c.t;
import java.io.File;
import java.nio.charset.Charset;
import okhttp3.internal.Util;

public abstract class RequestBody {
    public long contentLength() {
        return -1;
    }

    public abstract MediaType contentType();

    public abstract void writeTo(d dVar);

    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(final MediaType mediaType, final f fVar) {
        return new RequestBody() {
            /* class okhttp3.RequestBody.AnonymousClass1 */

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                return (long) fVar.h();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(d dVar) {
                dVar.c(fVar);
            }
        };
    }

    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr != null) {
            Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
            return new RequestBody() {
                /* class okhttp3.RequestBody.AnonymousClass2 */

                @Override // okhttp3.RequestBody
                public MediaType contentType() {
                    return MediaType.this;
                }

                @Override // okhttp3.RequestBody
                public long contentLength() {
                    return (long) i2;
                }

                @Override // okhttp3.RequestBody
                public void writeTo(d dVar) {
                    dVar.c(bArr, i, i2);
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() {
                /* class okhttp3.RequestBody.AnonymousClass3 */

                @Override // okhttp3.RequestBody
                public MediaType contentType() {
                    return MediaType.this;
                }

                @Override // okhttp3.RequestBody
                public long contentLength() {
                    return file.length();
                }

                @Override // okhttp3.RequestBody
                public void writeTo(d dVar) {
                    t tVar = null;
                    try {
                        tVar = l.a(file);
                        dVar.a(tVar);
                    } finally {
                        Util.closeQuietly(tVar);
                    }
                }
            };
        }
        throw new NullPointerException("file == null");
    }
}
