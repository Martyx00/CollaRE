package org.spongycastle.crypto.tls;

public class PSKTlsClient extends AbstractTlsClient {
    protected TlsPSKIdentity pskIdentity;

    public PSKTlsClient(TlsPSKIdentity tlsPSKIdentity) {
        this(new DefaultTlsCipherFactory(), tlsPSKIdentity);
    }

    public PSKTlsClient(TlsCipherFactory tlsCipherFactory, TlsPSKIdentity tlsPSKIdentity) {
        super(tlsCipherFactory);
        this.pskIdentity = tlsPSKIdentity;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public int[] getCipherSuites() {
        return new int[]{CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA};
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public TlsKeyExchange getKeyExchange() {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        if (keyExchangeAlgorithm != 24) {
            switch (keyExchangeAlgorithm) {
                case 13:
                case 14:
                case 15:
                    break;
                default:
                    throw new TlsFatalAlert(80);
            }
        }
        return createPSKKeyExchange(keyExchangeAlgorithm);
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public TlsAuthentication getAuthentication() {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createPSKKeyExchange(int i) {
        return new TlsPSKKeyExchange(i, this.supportedSignatureAlgorithms, this.pskIdentity, null, null, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }
}
