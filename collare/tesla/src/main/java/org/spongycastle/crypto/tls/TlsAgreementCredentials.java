package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public interface TlsAgreementCredentials extends TlsCredentials {
    byte[] generateAgreement(AsymmetricKeyParameter asymmetricKeyParameter);
}
