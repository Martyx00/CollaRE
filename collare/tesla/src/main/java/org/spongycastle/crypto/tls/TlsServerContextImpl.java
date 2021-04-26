package org.spongycastle.crypto.tls;

import java.security.SecureRandom;

class TlsServerContextImpl extends AbstractTlsContext implements TlsServerContext {
    @Override // org.spongycastle.crypto.tls.TlsContext
    public boolean isServer() {
        return true;
    }

    TlsServerContextImpl(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }
}
