package org.spongycastle.jce.exception;

import java.io.IOException;

public class ExtIOException extends IOException implements ExtException {
    private Throwable cause;

    public ExtIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // org.spongycastle.jce.exception.ExtException
    public Throwable getCause() {
        return this.cause;
    }
}
