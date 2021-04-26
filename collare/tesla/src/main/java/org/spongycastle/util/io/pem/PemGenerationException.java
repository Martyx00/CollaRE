package org.spongycastle.util.io.pem;

import java.io.IOException;

public class PemGenerationException extends IOException {
    private Throwable cause;

    public PemGenerationException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public PemGenerationException(String str) {
        super(str);
    }

    public Throwable getCause() {
        return this.cause;
    }
}
