package org.spongycastle.x509;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509Extension;
import java.util.Date;

public interface X509AttributeCertificate extends X509Extension {
    void checkValidity();

    void checkValidity(Date date);

    X509Attribute[] getAttributes();

    X509Attribute[] getAttributes(String str);

    byte[] getEncoded();

    AttributeCertificateHolder getHolder();

    AttributeCertificateIssuer getIssuer();

    boolean[] getIssuerUniqueID();

    Date getNotAfter();

    Date getNotBefore();

    BigInteger getSerialNumber();

    byte[] getSignature();

    int getVersion();

    void verify(PublicKey publicKey, String str);
}
