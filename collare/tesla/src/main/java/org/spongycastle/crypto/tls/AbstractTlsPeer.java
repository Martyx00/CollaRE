package org.spongycastle.crypto.tls;

public abstract class AbstractTlsPeer implements TlsPeer {
    @Override // org.spongycastle.crypto.tls.TlsPeer
    public void notifyAlertRaised(short s, short s2, String str, Throwable th) {
    }

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public void notifyAlertReceived(short s, short s2) {
    }

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public void notifyHandshakeComplete() {
    }

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public boolean shouldUseGMTUnixTime() {
        return false;
    }

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public void notifySecureRenegotiation(boolean z) {
        if (!z) {
            throw new TlsFatalAlert(40);
        }
    }
}
