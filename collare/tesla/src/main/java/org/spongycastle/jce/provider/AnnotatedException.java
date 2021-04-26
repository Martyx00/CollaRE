package org.spongycastle.jce.provider;

import org.spongycastle.jce.exception.ExtException;

public class AnnotatedException extends Exception implements ExtException {
    private Throwable _underlyingException;

    public AnnotatedException(String str, Throwable th) {
        super(str);
        this._underlyingException = th;
    }

    public AnnotatedException(String str) {
        this(str, null);
    }

    /* access modifiers changed from: package-private */
    public Throwable getUnderlyingException() {
        return this._underlyingException;
    }

    @Override // org.spongycastle.jce.exception.ExtException
    public Throwable getCause() {
        return this._underlyingException;
    }
}
