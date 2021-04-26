package org.spongycastle.crypto.tls;

import java.io.OutputStream;

public class TlsNullCompression implements TlsCompression {
    @Override // org.spongycastle.crypto.tls.TlsCompression
    public OutputStream compress(OutputStream outputStream) {
        return outputStream;
    }

    @Override // org.spongycastle.crypto.tls.TlsCompression
    public OutputStream decompress(OutputStream outputStream) {
        return outputStream;
    }
}
