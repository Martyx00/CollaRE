package org.spongycastle.jce.exception;

import java.security.cert.CertificateEncodingException;

public class ExtCertificateEncodingException extends CertificateEncodingException implements ExtException {
    private Throwable cause;

    public ExtCertificateEncodingException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // org.spongycastle.jce.exception.ExtException
    public Throwable getCause() {
        return this.cause;
    }
}
