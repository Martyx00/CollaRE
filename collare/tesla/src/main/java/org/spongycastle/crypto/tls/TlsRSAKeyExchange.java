package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.io.Streams;

public class TlsRSAKeyExchange extends AbstractTlsKeyExchange {
    protected byte[] premasterSecret;
    protected RSAKeyParameters rsaServerPublicKey = null;
    protected TlsEncryptionCredentials serverCredentials = null;
    protected AsymmetricKeyParameter serverPublicKey = null;

    public TlsRSAKeyExchange(Vector vector) {
        super(1, vector);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void skipServerCredentials() {
        throw new TlsFatalAlert(10);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsEncryptionCredentials) {
            processServerCertificate(tlsCredentials.getCertificate());
            this.serverCredentials = (TlsEncryptionCredentials) tlsCredentials;
            return;
        }
        throw new TlsFatalAlert(80);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
        if (!certificate.isEmpty()) {
            Certificate certificateAt = certificate.getCertificateAt(0);
            try {
                this.serverPublicKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
                if (!this.serverPublicKey.isPrivate()) {
                    this.rsaServerPublicKey = validateRSAPublicKey((RSAKeyParameters) this.serverPublicKey);
                    TlsUtils.validateKeyUsage(certificateAt, 32);
                    super.processServerCertificate(certificate);
                    return;
                }
                throw new TlsFatalAlert(80);
            } catch (RuntimeException e) {
                throw new TlsFatalAlert(43, e);
            }
        } else {
            throw new TlsFatalAlert(42);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void validateCertificateRequest(CertificateRequest certificateRequest) {
        short[] certificateTypes = certificateRequest.getCertificateTypes();
        for (short s : certificateTypes) {
            if (s != 64) {
                switch (s) {
                    default:
                        throw new TlsFatalAlert(47);
                    case 1:
                    case 2:
                        break;
                }
            }
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) {
        if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        this.premasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(this.context, this.rsaServerPublicKey, outputStream);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) {
        byte[] bArr;
        if (TlsUtils.isSSL(this.context)) {
            bArr = Streams.readAll(inputStream);
        } else {
            bArr = TlsUtils.readOpaque16(inputStream);
        }
        this.premasterSecret = this.serverCredentials.decryptPreMasterSecret(bArr);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generatePremasterSecret() {
        byte[] bArr = this.premasterSecret;
        if (bArr != null) {
            this.premasterSecret = null;
            return bArr;
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public RSAKeyParameters validateRSAPublicKey(RSAKeyParameters rSAKeyParameters) {
        if (rSAKeyParameters.getExponent().isProbablePrime(2)) {
            return rSAKeyParameters;
        }
        throw new TlsFatalAlert(47);
    }
}
