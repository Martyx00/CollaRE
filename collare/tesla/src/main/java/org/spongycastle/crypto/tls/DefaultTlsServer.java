package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.agreement.DHStandardGroups;
import org.spongycastle.crypto.params.DHParameters;

public abstract class DefaultTlsServer extends AbstractTlsServer {
    public DefaultTlsServer() {
    }

    public DefaultTlsServer(TlsCipherFactory tlsCipherFactory) {
        super(tlsCipherFactory);
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getDSASignerCredentials() {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getECDSASignerCredentials() {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsEncryptionCredentials getRSAEncryptionCredentials() {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getRSASignerCredentials() {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public DHParameters getDHParameters() {
        return DHStandardGroups.rfc3526_2048;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.tls.AbstractTlsServer
    public int[] getCipherSuites() {
        return new int[]{CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, 107, 103, 57, 51, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, 61, 60, 53, 47};
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public TlsCredentials getCredentials() {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        if (keyExchangeAlgorithm == 1) {
            return getRSAEncryptionCredentials();
        }
        if (keyExchangeAlgorithm == 3) {
            return getDSASignerCredentials();
        }
        if (keyExchangeAlgorithm != 5) {
            if (keyExchangeAlgorithm == 17) {
                return getECDSASignerCredentials();
            }
            switch (keyExchangeAlgorithm) {
                case 19:
                    break;
                case 20:
                    return null;
                default:
                    throw new TlsFatalAlert(80);
            }
        }
        return getRSASignerCredentials();
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public TlsKeyExchange getKeyExchange() {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        if (keyExchangeAlgorithm == 1) {
            return createRSAKeyExchange();
        }
        if (keyExchangeAlgorithm == 3 || keyExchangeAlgorithm == 5) {
            return createDHEKeyExchange(keyExchangeAlgorithm);
        }
        if (keyExchangeAlgorithm == 7 || keyExchangeAlgorithm == 9) {
            return createDHKeyExchange(keyExchangeAlgorithm);
        }
        switch (keyExchangeAlgorithm) {
            case 16:
            case 18:
            case 20:
                return createECDHKeyExchange(keyExchangeAlgorithm);
            case 17:
            case 19:
                return createECDHEKeyExchange(keyExchangeAlgorithm);
            default:
                throw new TlsFatalAlert(80);
        }
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createDHKeyExchange(int i) {
        return new TlsDHKeyExchange(i, this.supportedSignatureAlgorithms, getDHParameters());
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createDHEKeyExchange(int i) {
        return new TlsDHEKeyExchange(i, this.supportedSignatureAlgorithms, getDHParameters());
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createECDHKeyExchange(int i) {
        return new TlsECDHKeyExchange(i, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createECDHEKeyExchange(int i) {
        return new TlsECDHEKeyExchange(i, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createRSAKeyExchange() {
        return new TlsRSAKeyExchange(this.supportedSignatureAlgorithms);
    }
}
