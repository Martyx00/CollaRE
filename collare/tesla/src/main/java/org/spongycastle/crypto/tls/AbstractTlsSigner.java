package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public abstract class AbstractTlsSigner implements TlsSigner {
    protected TlsContext context;

    @Override // org.spongycastle.crypto.tls.TlsSigner
    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsSigner
    public byte[] generateRawSignature(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr) {
        return generateRawSignature(null, asymmetricKeyParameter, bArr);
    }

    @Override // org.spongycastle.crypto.tls.TlsSigner
    public boolean verifyRawSignature(byte[] bArr, AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr2) {
        return verifyRawSignature(null, bArr, asymmetricKeyParameter, bArr2);
    }

    @Override // org.spongycastle.crypto.tls.TlsSigner
    public Signer createSigner(AsymmetricKeyParameter asymmetricKeyParameter) {
        return createSigner(null, asymmetricKeyParameter);
    }

    @Override // org.spongycastle.crypto.tls.TlsSigner
    public Signer createVerifyer(AsymmetricKeyParameter asymmetricKeyParameter) {
        return createVerifyer(null, asymmetricKeyParameter);
    }
}
