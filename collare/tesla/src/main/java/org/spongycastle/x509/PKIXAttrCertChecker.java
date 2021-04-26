package org.spongycastle.x509;

import java.security.cert.CertPath;
import java.util.Collection;
import java.util.Set;

public abstract class PKIXAttrCertChecker implements Cloneable {
    public abstract void check(X509AttributeCertificate x509AttributeCertificate, CertPath certPath, CertPath certPath2, Collection collection);

    @Override // java.lang.Object
    public abstract Object clone();

    public abstract Set getSupportedExtensions();
}
