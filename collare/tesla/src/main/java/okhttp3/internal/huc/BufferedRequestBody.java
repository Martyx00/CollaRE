package okhttp3.internal.huc;

import c.c;
import c.d;
import okhttp3.Request;

/* access modifiers changed from: package-private */
public final class BufferedRequestBody extends OutputStreamRequestBody {
    final c buffer = new c();
    long contentLength = -1;

    BufferedRequestBody(long j) {
        initOutputStream(this.buffer, j);
    }

    @Override // okhttp3.internal.huc.OutputStreamRequestBody, okhttp3.RequestBody
    public long contentLength() {
        return this.contentLength;
    }

    @Override // okhttp3.internal.huc.OutputStreamRequestBody
    public Request prepareToSendRequest(Request request) {
        if (request.header("Content-Length") != null) {
            return request;
        }
        outputStream().close();
        this.contentLength = this.buffer.a();
        return request.newBuilder().removeHeader("Transfer-Encoding").header("Content-Length", Long.toString(this.buffer.a())).build();
    }

    @Override // okhttp3.RequestBody
    public void writeTo(d dVar) {
        this.buffer.a(dVar.b(), 0, this.buffer.a());
    }
}
