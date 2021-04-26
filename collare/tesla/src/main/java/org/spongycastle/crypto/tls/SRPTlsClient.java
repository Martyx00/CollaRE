package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import org.spongycastle.util.Arrays;

public class SRPTlsClient extends AbstractTlsClient {
    protected TlsSRPGroupVerifier groupVerifier;
    protected byte[] identity;
    protected byte[] password;

    /* access modifiers changed from: protected */
    public boolean requireSRPServerExtension() {
        return false;
    }

    public SRPTlsClient(byte[] bArr, byte[] bArr2) {
        this(new DefaultTlsCipherFactory(), new DefaultTlsSRPGroupVerifier(), bArr, bArr2);
    }

    public SRPTlsClient(TlsCipherFactory tlsCipherFactory, byte[] bArr, byte[] bArr2) {
        this(tlsCipherFactory, new DefaultTlsSRPGroupVerifier(), bArr, bArr2);
    }

    public SRPTlsClient(TlsCipherFactory tlsCipherFactory, TlsSRPGroupVerifier tlsSRPGroupVerifier, byte[] bArr, byte[] bArr2) {
        super(tlsCipherFactory);
        this.groupVerifier = tlsSRPGroupVerifier;
        this.identity = Arrays.clone(bArr);
        this.password = Arrays.clone(bArr2);
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public int[] getCipherSuites() {
        return new int[]{49182};
    }

    @Override // org.spongycastle.crypto.tls.TlsClient, org.spongycastle.crypto.tls.AbstractTlsClient
    public Hashtable getClientExtensions() {
        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(super.getClientExtensions());
        TlsSRPUtils.addSRPExtension(ensureExtensionsInitialised, this.identity);
        return ensureExtensionsInitialised;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient, org.spongycastle.crypto.tls.AbstractTlsClient
    public void processServerExtensions(Hashtable hashtable) {
        if (TlsUtils.hasExpectedEmptyExtensionData(hashtable, TlsSRPUtils.EXT_SRP, 47) || !requireSRPServerExtension()) {
            super.processServerExtensions(hashtable);
            return;
        }
        throw new TlsFatalAlert(47);
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public TlsKeyExchange getKeyExchange() {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (keyExchangeAlgorithm) {
            case 21:
            case 22:
            case 23:
                return createSRPKeyExchange(keyExchangeAlgorithm);
            default:
                throw new TlsFatalAlert(80);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public TlsAuthentication getAuthentication() {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createSRPKeyExchange(int i) {
        return new TlsSRPKeyExchange(i, this.supportedSignatureAlgorithms, this.groupVerifier, this.identity, this.password);
    }
}
