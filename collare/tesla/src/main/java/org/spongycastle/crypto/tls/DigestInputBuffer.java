package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Digest;

/* access modifiers changed from: package-private */
public class DigestInputBuffer extends ByteArrayOutputStream {
    DigestInputBuffer() {
    }

    /* access modifiers changed from: package-private */
    public void updateDigest(Digest digest) {
        digest.update(this.buf, 0, this.count);
    }
}
