package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class DefaultTlsSignerCredentials extends AbstractTlsSignerCredentials {
    protected Certificate certificate;
    protected TlsContext context;
    protected AsymmetricKeyParameter privateKey;
    protected SignatureAndHashAlgorithm signatureAndHashAlgorithm;
    protected TlsSigner signer;

    public DefaultTlsSignerCredentials(TlsContext tlsContext, Certificate certificate2, AsymmetricKeyParameter asymmetricKeyParameter) {
        this(tlsContext, certificate2, asymmetricKeyParameter, null);
    }

    public DefaultTlsSignerCredentials(TlsContext tlsContext, Certificate certificate2, AsymmetricKeyParameter asymmetricKeyParameter, SignatureAndHashAlgorithm signatureAndHashAlgorithm2) {
        if (certificate2 == null) {
            throw new IllegalArgumentException("'certificate' cannot be null");
        } else if (certificate2.isEmpty()) {
            throw new IllegalArgumentException("'certificate' cannot be empty");
        } else if (asymmetricKeyParameter == null) {
            throw new IllegalArgumentException("'privateKey' cannot be null");
        } else if (!asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("'privateKey' must be private");
        } else if (!TlsUtils.isTLSv12(tlsContext) || signatureAndHashAlgorithm2 != null) {
            if (asymmetricKeyParameter instanceof RSAKeyParameters) {
                this.signer = new TlsRSASigner();
            } else if (asymmetricKeyParameter instanceof DSAPrivateKeyParameters) {
                this.signer = new TlsDSSSigner();
            } else if (asymmetricKeyParameter instanceof ECPrivateKeyParameters) {
                this.signer = new TlsECDSASigner();
            } else {
                throw new IllegalArgumentException("'privateKey' type not supported: " + asymmetricKeyParameter.getClass().getName());
            }
            this.signer.init(tlsContext);
            this.context = tlsContext;
            this.certificate = certificate2;
            this.privateKey = asymmetricKeyParameter;
            this.signatureAndHashAlgorithm = signatureAndHashAlgorithm2;
        } else {
            throw new IllegalArgumentException("'signatureAndHashAlgorithm' cannot be null for (D)TLS 1.2+");
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsCredentials
    public Certificate getCertificate() {
        return this.certificate;
    }

    @Override // org.spongycastle.crypto.tls.TlsSignerCredentials
    public byte[] generateCertificateSignature(byte[] bArr) {
        try {
            if (TlsUtils.isTLSv12(this.context)) {
                return this.signer.generateRawSignature(this.signatureAndHashAlgorithm, this.privateKey, bArr);
            }
            return this.signer.generateRawSignature(this.privateKey, bArr);
        } catch (CryptoException e) {
            throw new TlsFatalAlert(80, e);
        }
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsSignerCredentials, org.spongycastle.crypto.tls.TlsSignerCredentials
    public SignatureAndHashAlgorithm getSignatureAndHashAlgorithm() {
        return this.signatureAndHashAlgorithm;
    }
}
