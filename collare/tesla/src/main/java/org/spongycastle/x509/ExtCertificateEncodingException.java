package org.spongycastle.x509;

import java.security.cert.CertificateEncodingException;

/* access modifiers changed from: package-private */
public class ExtCertificateEncodingException extends CertificateEncodingException {
    Throwable cause;

    ExtCertificateEncodingException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
