package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.tls.TlsProtocol;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;

public class TlsServerProtocol extends TlsProtocol {
    protected CertificateRequest certificateRequest = null;
    protected short clientCertificateType = -1;
    protected TlsKeyExchange keyExchange = null;
    protected TlsHandshakeHash prepareFinishHash = null;
    protected TlsCredentials serverCredentials = null;
    protected TlsServer tlsServer = null;
    TlsServerContextImpl tlsServerContext = null;

    public TlsServerProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
    }

    public TlsServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public void accept(TlsServer tlsServer2) {
        if (tlsServer2 == null) {
            throw new IllegalArgumentException("'tlsServer' cannot be null");
        } else if (this.tlsServer == null) {
            this.tlsServer = tlsServer2;
            this.securityParameters = new SecurityParameters();
            this.securityParameters.entity = 0;
            this.tlsServerContext = new TlsServerContextImpl(this.secureRandom, this.securityParameters);
            this.securityParameters.serverRandom = createRandomBlock(tlsServer2.shouldUseGMTUnixTime(), this.tlsServerContext.getNonceRandomGenerator());
            this.tlsServer.init(this.tlsServerContext);
            this.recordStream.init(this.tlsServerContext);
            this.recordStream.setRestrictReadVersion(false);
            blockForHandshake();
        } else {
            throw new IllegalStateException("'accept' can only be called once");
        }
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.prepareFinishHash = null;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public TlsContext getContext() {
        return this.tlsServerContext;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public AbstractTlsContext getContextAdmin() {
        return this.tlsServerContext;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public TlsPeer getPeer() {
        return this.tlsServer;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003e  */
    @Override // org.spongycastle.crypto.tls.TlsProtocol
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleHandshakeMessage(short r7, byte[] r8) {
        /*
        // Method dump skipped, instructions count: 530
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsServerProtocol.handleHandshakeMessage(short, byte[]):void");
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public void handleWarningMessage(short s) {
        if (s != 41) {
            super.handleWarningMessage(s);
        } else if (TlsUtils.isSSL(getContext()) && this.certificateRequest != null) {
            notifyClientCertificate(Certificate.EMPTY_CHAIN);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyClientCertificate(Certificate certificate) {
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        } else if (this.peerCertificate == null) {
            this.peerCertificate = certificate;
            if (certificate.isEmpty()) {
                this.keyExchange.skipClientCredentials();
            } else {
                this.clientCertificateType = TlsUtils.getClientCertificateType(certificate, this.serverCredentials.getCertificate());
                this.keyExchange.processClientCertificate(certificate);
            }
            this.tlsServer.notifyClientCertificate(certificate);
        } else {
            throw new TlsFatalAlert(10);
        }
    }

    /* access modifiers changed from: protected */
    public void receiveCertificateMessage(ByteArrayInputStream byteArrayInputStream) {
        Certificate parse = Certificate.parse(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        notifyClientCertificate(parse);
    }

    /* access modifiers changed from: protected */
    public void receiveCertificateVerifyMessage(ByteArrayInputStream byteArrayInputStream) {
        byte[] bArr;
        if (this.certificateRequest != null) {
            DigitallySigned parse = DigitallySigned.parse(getContext(), byteArrayInputStream);
            assertEmpty(byteArrayInputStream);
            try {
                SignatureAndHashAlgorithm algorithm = parse.getAlgorithm();
                if (TlsUtils.isTLSv12(getContext())) {
                    TlsUtils.verifySupportedSignatureAlgorithm(this.certificateRequest.getSupportedSignatureAlgorithms(), algorithm);
                    bArr = this.prepareFinishHash.getFinalHash(algorithm.getHash());
                } else {
                    bArr = this.securityParameters.getSessionHash();
                }
                AsymmetricKeyParameter createKey = PublicKeyFactory.createKey(this.peerCertificate.getCertificateAt(0).getSubjectPublicKeyInfo());
                TlsSigner createTlsSigner = TlsUtils.createTlsSigner(this.clientCertificateType);
                createTlsSigner.init(getContext());
                if (!createTlsSigner.verifyRawSignature(algorithm, parse.getSignature(), createKey, bArr)) {
                    throw new TlsFatalAlert(51);
                }
            } catch (TlsFatalAlert e) {
                throw e;
            } catch (Exception e2) {
                throw new TlsFatalAlert(51, e2);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: protected */
    public void receiveClientHelloMessage(ByteArrayInputStream byteArrayInputStream) {
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        this.recordStream.setWriteVersion(readVersion);
        if (!readVersion.isDTLS()) {
            byte[] readFully = TlsUtils.readFully(32, byteArrayInputStream);
            if (TlsUtils.readOpaque8(byteArrayInputStream).length <= 32) {
                int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
                if (readUint16 < 2 || (readUint16 & 1) != 0) {
                    throw new TlsFatalAlert(50);
                }
                this.offeredCipherSuites = TlsUtils.readUint16Array(readUint16 / 2, byteArrayInputStream);
                short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
                if (readUint8 >= 1) {
                    this.offeredCompressionMethods = TlsUtils.readUint8Array(readUint8, byteArrayInputStream);
                    this.clientExtensions = readExtensions(byteArrayInputStream);
                    this.securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(this.clientExtensions);
                    getContextAdmin().setClientVersion(readVersion);
                    this.tlsServer.notifyClientVersion(readVersion);
                    this.tlsServer.notifyFallback(Arrays.contains(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV));
                    this.securityParameters.clientRandom = readFully;
                    this.tlsServer.notifyOfferedCipherSuites(this.offeredCipherSuites);
                    this.tlsServer.notifyOfferedCompressionMethods(this.offeredCompressionMethods);
                    if (Arrays.contains(this.offeredCipherSuites, 255)) {
                        this.secure_renegotiation = true;
                    }
                    byte[] extensionData = TlsUtils.getExtensionData(this.clientExtensions, EXT_RenegotiationInfo);
                    if (extensionData != null) {
                        this.secure_renegotiation = true;
                        if (!Arrays.constantTimeAreEqual(extensionData, createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                            throw new TlsFatalAlert(40);
                        }
                    }
                    this.tlsServer.notifySecureRenegotiation(this.secure_renegotiation);
                    if (this.clientExtensions != null) {
                        TlsExtensionsUtils.getPaddingExtension(this.clientExtensions);
                        this.tlsServer.processClientExtensions(this.clientExtensions);
                        return;
                    }
                    return;
                }
                throw new TlsFatalAlert(47);
            }
            throw new TlsFatalAlert(47);
        }
        throw new TlsFatalAlert(47);
    }

    /* access modifiers changed from: protected */
    public void receiveClientKeyExchangeMessage(ByteArrayInputStream byteArrayInputStream) {
        this.keyExchange.processClientKeyExchange(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        this.prepareFinishHash = this.recordStream.prepareToFinish();
        this.securityParameters.sessionHash = getCurrentPRFHash(getContext(), this.prepareFinishHash, null);
        establishMasterSecret(getContext(), this.keyExchange);
        this.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
        if (!this.expectSessionTicket) {
            sendChangeCipherSpecMessage();
        }
    }

    /* access modifiers changed from: protected */
    public void sendCertificateRequestMessage(CertificateRequest certificateRequest2) {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 13);
        certificateRequest2.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendCertificateStatusMessage(CertificateStatus certificateStatus) {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 22);
        certificateStatus.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendNewSessionTicketMessage(NewSessionTicket newSessionTicket) {
        if (newSessionTicket != null) {
            TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 4);
            newSessionTicket.encode(handshakeMessage);
            handshakeMessage.writeToRecordStream();
            return;
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public void sendServerHelloMessage() {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 2);
        ProtocolVersion serverVersion = this.tlsServer.getServerVersion();
        if (serverVersion.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            this.recordStream.setReadVersion(serverVersion);
            this.recordStream.setWriteVersion(serverVersion);
            this.recordStream.setRestrictReadVersion(true);
            getContextAdmin().setServerVersion(serverVersion);
            TlsUtils.writeVersion(serverVersion, handshakeMessage);
            handshakeMessage.write(this.securityParameters.serverRandom);
            TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, handshakeMessage);
            int selectedCipherSuite = this.tlsServer.getSelectedCipherSuite();
            if (!Arrays.contains(this.offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, getContext().getServerVersion())) {
                throw new TlsFatalAlert(80);
            }
            this.securityParameters.cipherSuite = selectedCipherSuite;
            short selectedCompressionMethod = this.tlsServer.getSelectedCompressionMethod();
            if (Arrays.contains(this.offeredCompressionMethods, selectedCompressionMethod)) {
                this.securityParameters.compressionAlgorithm = selectedCompressionMethod;
                TlsUtils.writeUint16(selectedCipherSuite, handshakeMessage);
                TlsUtils.writeUint8(selectedCompressionMethod, (OutputStream) handshakeMessage);
                this.serverExtensions = this.tlsServer.getServerExtensions();
                boolean z = false;
                if (this.secure_renegotiation) {
                    if (TlsUtils.getExtensionData(this.serverExtensions, EXT_RenegotiationInfo) == null) {
                        this.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
                        this.serverExtensions.put(EXT_RenegotiationInfo, createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
                    }
                }
                if (this.securityParameters.extendedMasterSecret) {
                    this.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
                    TlsExtensionsUtils.addExtendedMasterSecretExtension(this.serverExtensions);
                }
                if (this.serverExtensions != null) {
                    this.securityParameters.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(this.serverExtensions);
                    this.securityParameters.maxFragmentLength = processMaxFragmentLengthExtension(this.clientExtensions, this.serverExtensions, 80);
                    this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(this.serverExtensions);
                    this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsExtensionsUtils.EXT_status_request, 80);
                    if (!this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsProtocol.EXT_SessionTicket, 80)) {
                        z = true;
                    }
                    this.expectSessionTicket = z;
                    writeExtensions(handshakeMessage, this.serverExtensions);
                }
                this.securityParameters.prfAlgorithm = getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
                this.securityParameters.verifyDataLength = 12;
                applyMaxFragmentLengthExtension();
                handshakeMessage.writeToRecordStream();
                return;
            }
            throw new TlsFatalAlert(80);
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public void sendServerHelloDoneMessage() {
        byte[] bArr = new byte[4];
        TlsUtils.writeUint8((short) 14, bArr, 0);
        TlsUtils.writeUint24(0, bArr, 1);
        writeHandshakeMessage(bArr, 0, bArr.length);
    }

    /* access modifiers changed from: protected */
    public void sendServerKeyExchangeMessage(byte[] bArr) {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(12, bArr.length);
        handshakeMessage.write(bArr);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public boolean expectCertificateVerifyMessage() {
        short s = this.clientCertificateType;
        return s >= 0 && TlsUtils.hasSigningCapability(s);
    }
}
