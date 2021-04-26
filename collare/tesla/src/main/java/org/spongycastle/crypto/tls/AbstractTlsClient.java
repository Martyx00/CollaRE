package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import java.util.Vector;

public abstract class AbstractTlsClient extends AbstractTlsPeer implements TlsClient {
    protected TlsCipherFactory cipherFactory;
    protected short[] clientECPointFormats;
    protected TlsClientContext context;
    protected int[] namedCurves;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;
    protected short[] serverECPointFormats;
    protected Vector supportedSignatureAlgorithms;

    @Override // org.spongycastle.crypto.tls.TlsClient
    public Vector getClientSupplementalData() {
        return null;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public TlsSession getSessionToResume() {
        return null;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public boolean isFallback() {
        return false;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifyNewSessionTicket(NewSessionTicket newSessionTicket) {
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifySessionID(byte[] bArr) {
    }

    public AbstractTlsClient() {
        this(new DefaultTlsCipherFactory());
    }

    public AbstractTlsClient(TlsCipherFactory tlsCipherFactory) {
        this.cipherFactory = tlsCipherFactory;
    }

    /* access modifiers changed from: protected */
    public boolean allowUnexpectedServerExtension(Integer num, byte[] bArr) {
        if (num.intValue() != 10) {
            return false;
        }
        TlsECCUtils.readSupportedEllipticCurvesExtension(bArr);
        return true;
    }

    /* access modifiers changed from: protected */
    public void checkForUnexpectedServerExtension(Hashtable hashtable, Integer num) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, num);
        if (extensionData != null && !allowUnexpectedServerExtension(num, extensionData)) {
            throw new TlsFatalAlert(47);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void init(TlsClientContext tlsClientContext) {
        this.context = tlsClientContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public ProtocolVersion getClientHelloRecordLayerVersion() {
        return getClientVersion();
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public ProtocolVersion getClientVersion() {
        return ProtocolVersion.TLSv12;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public Hashtable getClientExtensions() {
        Hashtable hashtable = null;
        if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.context.getClientVersion())) {
            this.supportedSignatureAlgorithms = TlsUtils.getDefaultSupportedSignatureAlgorithms();
            hashtable = TlsExtensionsUtils.ensureExtensionsInitialised(null);
            TlsUtils.addSignatureAlgorithmsExtension(hashtable, this.supportedSignatureAlgorithms);
        }
        if (!TlsECCUtils.containsECCCipherSuites(getCipherSuites())) {
            return hashtable;
        }
        this.namedCurves = new int[]{23, 24};
        this.clientECPointFormats = new short[]{0, 1, 2};
        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(hashtable);
        TlsECCUtils.addSupportedEllipticCurvesExtension(ensureExtensionsInitialised, this.namedCurves);
        TlsECCUtils.addSupportedPointFormatsExtension(ensureExtensionsInitialised, this.clientECPointFormats);
        return ensureExtensionsInitialised;
    }

    public ProtocolVersion getMinimumVersion() {
        return ProtocolVersion.TLSv10;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifyServerVersion(ProtocolVersion protocolVersion) {
        if (!getMinimumVersion().isEqualOrEarlierVersionOf(protocolVersion)) {
            throw new TlsFatalAlert(70);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public short[] getCompressionMethods() {
        return new short[]{0};
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifySelectedCipherSuite(int i) {
        this.selectedCipherSuite = i;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifySelectedCompressionMethod(short s) {
        this.selectedCompressionMethod = s;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void processServerExtensions(Hashtable hashtable) {
        if (hashtable != null) {
            checkForUnexpectedServerExtension(hashtable, TlsUtils.EXT_signature_algorithms);
            checkForUnexpectedServerExtension(hashtable, TlsECCUtils.EXT_elliptic_curves);
            if (TlsECCUtils.isECCCipherSuite(this.selectedCipherSuite)) {
                this.serverECPointFormats = TlsECCUtils.getSupportedPointFormatsExtension(hashtable);
            } else {
                checkForUnexpectedServerExtension(hashtable, TlsECCUtils.EXT_ec_point_formats);
            }
            checkForUnexpectedServerExtension(hashtable, TlsExtensionsUtils.EXT_padding);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void processServerSupplementalData(Vector vector) {
        if (vector != null) {
            throw new TlsFatalAlert(10);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public TlsCompression getCompression() {
        if (this.selectedCompressionMethod == 0) {
            return new TlsNullCompression();
        }
        throw new TlsFatalAlert(80);
    }

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public TlsCipher getCipher() {
        return this.cipherFactory.createCipher(this.context, TlsUtils.getEncryptionAlgorithm(this.selectedCipherSuite), TlsUtils.getMACAlgorithm(this.selectedCipherSuite));
    }
}
