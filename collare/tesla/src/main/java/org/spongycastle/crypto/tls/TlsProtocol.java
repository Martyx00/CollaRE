package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.prng.RandomGenerator;
import org.spongycastle.crypto.tls.SessionParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;

public abstract class TlsProtocol {
    protected static final short ADS_MODE_0_N = 1;
    protected static final short ADS_MODE_0_N_FIRSTONLY = 2;
    protected static final short ADS_MODE_1_Nsub1 = 0;
    protected static final short CS_CERTIFICATE_REQUEST = 7;
    protected static final short CS_CERTIFICATE_STATUS = 5;
    protected static final short CS_CERTIFICATE_VERIFY = 12;
    protected static final short CS_CLIENT_CERTIFICATE = 10;
    protected static final short CS_CLIENT_FINISHED = 13;
    protected static final short CS_CLIENT_HELLO = 1;
    protected static final short CS_CLIENT_KEY_EXCHANGE = 11;
    protected static final short CS_CLIENT_SUPPLEMENTAL_DATA = 9;
    protected static final short CS_END = 16;
    protected static final short CS_SERVER_CERTIFICATE = 4;
    protected static final short CS_SERVER_FINISHED = 15;
    protected static final short CS_SERVER_HELLO = 2;
    protected static final short CS_SERVER_HELLO_DONE = 8;
    protected static final short CS_SERVER_KEY_EXCHANGE = 6;
    protected static final short CS_SERVER_SESSION_TICKET = 14;
    protected static final short CS_SERVER_SUPPLEMENTAL_DATA = 3;
    protected static final short CS_START = 0;
    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    protected static final Integer EXT_SessionTicket = Integers.valueOf(35);
    private static final String TLS_ERROR_MESSAGE = "Internal TLS error, this could be an attack";
    private ByteQueue alertQueue;
    protected boolean allowCertificateStatus;
    private volatile boolean appDataReady;
    private volatile boolean appDataSplitEnabled;
    private volatile int appDataSplitMode;
    private ByteQueue applicationDataQueue;
    protected boolean blocking;
    protected Hashtable clientExtensions;
    private volatile boolean closed;
    protected short connection_state;
    protected boolean expectSessionTicket;
    private byte[] expected_verify_data;
    private volatile boolean failedWithError;
    private ByteQueue handshakeQueue;
    protected ByteQueueInputStream inputBuffers;
    protected int[] offeredCipherSuites;
    protected short[] offeredCompressionMethods;
    protected ByteQueueOutputStream outputBuffer;
    protected Certificate peerCertificate;
    protected boolean receivedChangeCipherSpec;
    RecordStream recordStream;
    protected boolean resumedSession;
    protected SecureRandom secureRandom;
    protected boolean secure_renegotiation;
    protected SecurityParameters securityParameters;
    protected Hashtable serverExtensions;
    protected SessionParameters sessionParameters;
    private TlsInputStream tlsInputStream;
    private TlsOutputStream tlsOutputStream;
    protected TlsSession tlsSession;

    private void processApplicationData() {
    }

    /* access modifiers changed from: protected */
    public abstract TlsContext getContext();

    /* access modifiers changed from: package-private */
    public abstract AbstractTlsContext getContextAdmin();

    /* access modifiers changed from: protected */
    public abstract TlsPeer getPeer();

    /* access modifiers changed from: protected */
    public void handleChangeCipherSpecMessage() {
    }

    /* access modifiers changed from: protected */
    public abstract void handleHandshakeMessage(short s, byte[] bArr);

    /* access modifiers changed from: protected */
    public void handleWarningMessage(short s) {
    }

    public TlsProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom2) {
        this.applicationDataQueue = new ByteQueue();
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue();
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.appDataSplitMode = 0;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = true;
        this.recordStream = new RecordStream(this, inputStream, outputStream);
        this.secureRandom = secureRandom2;
    }

    public TlsProtocol(SecureRandom secureRandom2) {
        this.applicationDataQueue = new ByteQueue();
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue();
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.appDataSplitMode = 0;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = false;
        this.inputBuffers = new ByteQueueInputStream();
        this.outputBuffer = new ByteQueueOutputStream();
        this.recordStream = new RecordStream(this, this.inputBuffers, this.outputBuffer);
        this.secureRandom = secureRandom2;
    }

    /* access modifiers changed from: protected */
    public void applyMaxFragmentLengthExtension() {
        if (this.securityParameters.maxFragmentLength < 0) {
            return;
        }
        if (MaxFragmentLength.isValid(this.securityParameters.maxFragmentLength)) {
            this.recordStream.setPlaintextLimit(1 << (this.securityParameters.maxFragmentLength + CS_SERVER_HELLO_DONE));
            return;
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public void checkReceivedChangeCipherSpec(boolean z) {
        if (z != this.receivedChangeCipherSpec) {
            throw new TlsFatalAlert(10);
        }
    }

    /* access modifiers changed from: protected */
    public void cleanupHandshake() {
        byte[] bArr = this.expected_verify_data;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.expected_verify_data = null;
        }
        this.securityParameters.clear();
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
    }

    /* access modifiers changed from: protected */
    public void blockForHandshake() {
        if (this.blocking) {
            while (this.connection_state != 16) {
                boolean z = this.closed;
                safeReadRecord();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void completeHandshake() {
        try {
            this.recordStream.finaliseHandshake();
            this.appDataSplitEnabled = !TlsUtils.isTLSv11(getContext());
            if (!this.appDataReady) {
                this.appDataReady = true;
                if (this.blocking) {
                    this.tlsInputStream = new TlsInputStream(this);
                    this.tlsOutputStream = new TlsOutputStream(this);
                }
            }
            if (this.tlsSession != null) {
                if (this.sessionParameters == null) {
                    this.sessionParameters = new SessionParameters.Builder().setCipherSuite(this.securityParameters.getCipherSuite()).setCompressionAlgorithm(this.securityParameters.getCompressionAlgorithm()).setMasterSecret(this.securityParameters.getMasterSecret()).setPeerCertificate(this.peerCertificate).setPSKIdentity(this.securityParameters.getPSKIdentity()).setSRPIdentity(this.securityParameters.getSRPIdentity()).setServerExtensions(this.serverExtensions).build();
                    this.tlsSession = new TlsSessionImpl(this.tlsSession.getSessionID(), this.sessionParameters);
                }
                getContextAdmin().setResumableSession(this.tlsSession);
            }
            getPeer().notifyHandshakeComplete();
        } finally {
            cleanupHandshake();
        }
    }

    /* access modifiers changed from: protected */
    public void processRecord(short s, byte[] bArr, int i, int i2) {
        switch (s) {
            case 20:
                processChangeCipherSpec(bArr, i, i2);
                return;
            case 21:
                this.alertQueue.addData(bArr, i, i2);
                processAlert();
                return;
            case 22:
                this.handshakeQueue.addData(bArr, i, i2);
                processHandshake();
                return;
            case 23:
                if (this.appDataReady) {
                    this.applicationDataQueue.addData(bArr, i, i2);
                    processApplicationData();
                    return;
                }
                throw new TlsFatalAlert(10);
            case 24:
                if (!this.appDataReady) {
                    throw new TlsFatalAlert(10);
                }
                return;
            default:
                return;
        }
    }

    private void processHandshake() {
        boolean z;
        do {
            z = true;
            if (this.handshakeQueue.available() >= 4) {
                byte[] bArr = new byte[4];
                this.handshakeQueue.read(bArr, 0, 4, 0);
                short readUint8 = TlsUtils.readUint8(bArr, 0);
                int readUint24 = TlsUtils.readUint24(bArr, 1);
                if (this.handshakeQueue.available() >= readUint24 + 4) {
                    byte[] removeData = this.handshakeQueue.removeData(readUint24, 4);
                    checkReceivedChangeCipherSpec(this.connection_state == 16 || readUint8 == 20);
                    if (readUint8 != 0) {
                        if (readUint8 == 20) {
                            TlsContext context = getContext();
                            if (this.expected_verify_data == null && context.getSecurityParameters().getMasterSecret() != null) {
                                this.expected_verify_data = createVerifyData(!context.isServer());
                            }
                        }
                        this.recordStream.updateHandshakeData(bArr, 0, 4);
                        this.recordStream.updateHandshakeData(removeData, 0, readUint24);
                    }
                    handleHandshakeMessage(readUint8, removeData);
                    continue;
                }
            }
            z = false;
            continue;
        } while (z);
    }

    private void processAlert() {
        while (this.alertQueue.available() >= 2) {
            byte[] removeData = this.alertQueue.removeData(2, 0);
            short s = (short) removeData[0];
            short s2 = (short) removeData[1];
            getPeer().notifyAlertReceived(s, s2);
            if (s != 2) {
                if (s2 == 0) {
                    handleClose(false);
                }
                handleWarningMessage(s2);
            } else {
                invalidateSession();
                this.failedWithError = true;
                this.closed = true;
                this.recordStream.safeClose();
                throw new IOException(TLS_ERROR_MESSAGE);
            }
        }
    }

    private void processChangeCipherSpec(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (TlsUtils.readUint8(bArr, i + i3) != 1) {
                throw new TlsFatalAlert(50);
            } else if (this.receivedChangeCipherSpec || this.alertQueue.available() > 0 || this.handshakeQueue.available() > 0) {
                throw new TlsFatalAlert(10);
            } else {
                this.recordStream.receivedReadCipherSpec();
                this.receivedChangeCipherSpec = true;
                handleChangeCipherSpecMessage();
            }
        }
    }

    /* access modifiers changed from: protected */
    public int applicationDataAvailable() {
        return this.applicationDataQueue.available();
    }

    /* access modifiers changed from: protected */
    public int readApplicationData(byte[] bArr, int i, int i2) {
        if (i2 < 1) {
            return 0;
        }
        while (this.applicationDataQueue.available() == 0) {
            if (!this.closed) {
                safeReadRecord();
            } else if (!this.failedWithError) {
                return -1;
            } else {
                throw new IOException(TLS_ERROR_MESSAGE);
            }
        }
        int min = Math.min(i2, this.applicationDataQueue.available());
        this.applicationDataQueue.removeData(bArr, i, min, 0);
        return min;
    }

    /* access modifiers changed from: protected */
    public void safeReadRecord() {
        try {
            if (!this.recordStream.readRecord()) {
                throw new EOFException();
            }
        } catch (TlsFatalAlert e) {
            if (!this.closed) {
                failWithError(2, e.getAlertDescription(), "Failed to read record", e);
            }
            throw e;
        } catch (IOException e2) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to read record", e2);
            }
            throw e2;
        } catch (RuntimeException e3) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to read record", e3);
            }
            throw e3;
        }
    }

    /* access modifiers changed from: protected */
    public void safeWriteRecord(short s, byte[] bArr, int i, int i2) {
        try {
            this.recordStream.writeRecord(s, bArr, i, i2);
        } catch (TlsFatalAlert e) {
            if (!this.closed) {
                failWithError(2, e.getAlertDescription(), "Failed to write record", e);
            }
            throw e;
        } catch (IOException e2) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to write record", e2);
            }
            throw e2;
        } catch (RuntimeException e3) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to write record", e3);
            }
            throw e3;
        }
    }

    /* access modifiers changed from: protected */
    public void writeData(byte[] bArr, int i, int i2) {
        if (!this.closed) {
            while (i2 > 0) {
                if (this.appDataSplitEnabled) {
                    switch (this.appDataSplitMode) {
                        case 2:
                            this.appDataSplitEnabled = false;
                        case 1:
                            safeWriteRecord(23, TlsUtils.EMPTY_BYTES, 0, 0);
                            break;
                        default:
                            safeWriteRecord(23, bArr, i, 1);
                            i++;
                            i2--;
                            break;
                    }
                }
                if (i2 > 0) {
                    int min = Math.min(i2, this.recordStream.getPlaintextLimit());
                    safeWriteRecord(23, bArr, i, min);
                    i += min;
                    i2 -= min;
                }
            }
        } else if (this.failedWithError) {
            throw new IOException(TLS_ERROR_MESSAGE);
        } else {
            throw new IOException("Sorry, connection has been closed, you cannot write more data");
        }
    }

    /* access modifiers changed from: protected */
    public void setAppDataSplitMode(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Illegal appDataSplitMode mode: " + i);
        }
        this.appDataSplitMode = i;
    }

    /* access modifiers changed from: protected */
    public void writeHandshakeMessage(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            int min = Math.min(i2, this.recordStream.getPlaintextLimit());
            safeWriteRecord(22, bArr, i, min);
            i += min;
            i2 -= min;
        }
    }

    public OutputStream getOutputStream() {
        if (this.blocking) {
            return this.tlsOutputStream;
        }
        throw new IllegalStateException("Cannot use OutputStream in non-blocking mode! Use offerOutput() instead.");
    }

    public InputStream getInputStream() {
        if (this.blocking) {
            return this.tlsInputStream;
        }
        throw new IllegalStateException("Cannot use InputStream in non-blocking mode! Use offerInput() instead.");
    }

    public void offerInput(byte[] bArr) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerInput() in blocking mode! Use getInputStream() instead.");
        } else if (!this.closed) {
            this.inputBuffers.addBytes(bArr);
            while (this.inputBuffers.available() >= 5) {
                byte[] bArr2 = new byte[5];
                this.inputBuffers.peek(bArr2);
                if (this.inputBuffers.available() >= TlsUtils.readUint16(bArr2, 3) + 5) {
                    safeReadRecord();
                } else {
                    return;
                }
            }
        } else {
            throw new IOException("Connection is closed, cannot accept any more input");
        }
    }

    public int getAvailableInputBytes() {
        if (!this.blocking) {
            return applicationDataAvailable();
        }
        throw new IllegalStateException("Cannot use getAvailableInputBytes() in blocking mode! Use getInputStream().available() instead.");
    }

    public int readInput(byte[] bArr, int i, int i2) {
        if (!this.blocking) {
            try {
                return readApplicationData(bArr, i, Math.min(i2, applicationDataAvailable()));
            } catch (IOException e) {
                throw new RuntimeException(e.toString());
            }
        } else {
            throw new IllegalStateException("Cannot use readInput() in blocking mode! Use getInputStream() instead.");
        }
    }

    public void offerOutput(byte[] bArr, int i, int i2) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerOutput() in blocking mode! Use getOutputStream() instead.");
        } else if (this.appDataReady) {
            writeData(bArr, i, i2);
        } else {
            throw new IOException("Application data cannot be sent until the handshake is complete!");
        }
    }

    public int getAvailableOutputBytes() {
        if (!this.blocking) {
            return this.outputBuffer.getBuffer().available();
        }
        throw new IllegalStateException("Cannot use getAvailableOutputBytes() in blocking mode! Use getOutputStream() instead.");
    }

    public int readOutput(byte[] bArr, int i, int i2) {
        if (!this.blocking) {
            int min = Math.min(getAvailableOutputBytes(), i2);
            this.outputBuffer.getBuffer().removeData(bArr, i, min, 0);
            return min;
        }
        throw new IllegalStateException("Cannot use readOutput() in blocking mode! Use getOutputStream() instead.");
    }

    /* access modifiers changed from: protected */
    public void failWithError(short s, short s2, String str, Throwable th) {
        if (!this.closed) {
            this.closed = true;
            if (s == 2) {
                invalidateSession();
                this.failedWithError = true;
            }
            raiseAlert(s, s2, str, th);
            this.recordStream.safeClose();
            if (s != 2) {
                return;
            }
        }
        throw new IOException(TLS_ERROR_MESSAGE);
    }

    /* access modifiers changed from: protected */
    public void invalidateSession() {
        SessionParameters sessionParameters2 = this.sessionParameters;
        if (sessionParameters2 != null) {
            sessionParameters2.clear();
            this.sessionParameters = null;
        }
        TlsSession tlsSession2 = this.tlsSession;
        if (tlsSession2 != null) {
            tlsSession2.invalidate();
            this.tlsSession = null;
        }
    }

    /* access modifiers changed from: protected */
    public void processFinishedMessage(ByteArrayInputStream byteArrayInputStream) {
        byte[] bArr = this.expected_verify_data;
        if (bArr != null) {
            byte[] readFully = TlsUtils.readFully(bArr.length, byteArrayInputStream);
            assertEmpty(byteArrayInputStream);
            if (!Arrays.constantTimeAreEqual(this.expected_verify_data, readFully)) {
                throw new TlsFatalAlert(51);
            }
            return;
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public void raiseAlert(short s, short s2, String str, Throwable th) {
        getPeer().notifyAlertRaised(s, s2, str, th);
        safeWriteRecord(21, new byte[]{(byte) s, (byte) s2}, 0, 2);
    }

    /* access modifiers changed from: protected */
    public void raiseWarning(short s, String str) {
        raiseAlert(1, s, str, null);
    }

    /* access modifiers changed from: protected */
    public void sendCertificateMessage(Certificate certificate) {
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        if (certificate.isEmpty() && !getContext().isServer()) {
            ProtocolVersion serverVersion = getContext().getServerVersion();
            if (serverVersion.isSSL()) {
                raiseWarning(41, serverVersion.toString() + " client didn't provide credentials");
                return;
            }
        }
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, 11);
        certificate.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendChangeCipherSpecMessage() {
        byte[] bArr = {1};
        safeWriteRecord(20, bArr, 0, bArr.length);
        this.recordStream.sentWriteCipherSpec();
    }

    /* access modifiers changed from: protected */
    public void sendFinishedMessage() {
        byte[] createVerifyData = createVerifyData(getContext().isServer());
        HandshakeMessage handshakeMessage = new HandshakeMessage(20, createVerifyData.length);
        handshakeMessage.write(createVerifyData);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendSupplementalDataMessage(Vector vector) {
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, 23);
        writeSupplementalData(handshakeMessage, vector);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public byte[] createVerifyData(boolean z) {
        TlsContext context = getContext();
        return TlsUtils.calculateVerifyData(context, z ? ExporterLabel.server_finished : ExporterLabel.client_finished, getCurrentPRFHash(context, this.recordStream.getHandshakeHash(), z ? TlsUtils.SSL_SERVER : TlsUtils.SSL_CLIENT));
    }

    public void close() {
        handleClose(true);
    }

    /* access modifiers changed from: protected */
    public void handleClose(boolean z) {
        if (!this.closed) {
            if (z && !this.appDataReady) {
                raiseWarning(90, "User canceled handshake");
            }
            failWithError(1, 0, "Connection closed", null);
        }
    }

    /* access modifiers changed from: protected */
    public void flush() {
        this.recordStream.flush();
    }

    public boolean isClosed() {
        return this.closed;
    }

    /* access modifiers changed from: protected */
    public short processMaxFragmentLengthExtension(Hashtable hashtable, Hashtable hashtable2, short s) {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (this.resumedSession || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s);
    }

    /* access modifiers changed from: protected */
    public void refuseRenegotiation() {
        if (!TlsUtils.isSSL(getContext())) {
            raiseWarning(100, "Renegotiation not supported");
            return;
        }
        throw new TlsFatalAlert(40);
    }

    protected static void assertEmpty(ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream.available() > 0) {
            throw new TlsFatalAlert(50);
        }
    }

    protected static byte[] createRandomBlock(boolean z, RandomGenerator randomGenerator) {
        byte[] bArr = new byte[32];
        randomGenerator.nextBytes(bArr);
        if (z) {
            TlsUtils.writeGMTUnixTime(bArr, 0);
        }
        return bArr;
    }

    protected static byte[] createRenegotiationInfo(byte[] bArr) {
        return TlsUtils.encodeOpaque8(bArr);
    }

    protected static void establishMasterSecret(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) {
        byte[] generatePremasterSecret = tlsKeyExchange.generatePremasterSecret();
        try {
            tlsContext.getSecurityParameters().masterSecret = TlsUtils.calculateMasterSecret(tlsContext, generatePremasterSecret);
        } finally {
            if (generatePremasterSecret != null) {
                Arrays.fill(generatePremasterSecret, (byte) 0);
            }
        }
    }

    protected static byte[] getCurrentPRFHash(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, byte[] bArr) {
        Digest forkPRFHash = tlsHandshakeHash.forkPRFHash();
        if (bArr != null && TlsUtils.isSSL(tlsContext)) {
            forkPRFHash.update(bArr, 0, bArr.length);
        }
        byte[] bArr2 = new byte[forkPRFHash.getDigestSize()];
        forkPRFHash.doFinal(bArr2, 0);
        return bArr2;
    }

    protected static Hashtable readExtensions(ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] readOpaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque16);
        Hashtable hashtable = new Hashtable();
        while (byteArrayInputStream2.available() > 0) {
            if (hashtable.put(Integers.valueOf(TlsUtils.readUint16(byteArrayInputStream2)), TlsUtils.readOpaque16(byteArrayInputStream2)) != null) {
                throw new TlsFatalAlert(47);
            }
        }
        return hashtable;
    }

    protected static Vector readSupplementalDataMessage(ByteArrayInputStream byteArrayInputStream) {
        byte[] readOpaque24 = TlsUtils.readOpaque24(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque24);
        Vector vector = new Vector();
        while (byteArrayInputStream2.available() > 0) {
            vector.addElement(new SupplementalDataEntry(TlsUtils.readUint16(byteArrayInputStream2), TlsUtils.readOpaque16(byteArrayInputStream2)));
        }
        return vector;
    }

    protected static void writeExtensions(OutputStream outputStream, Hashtable hashtable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeSelectedExtensions(byteArrayOutputStream, hashtable, true);
        writeSelectedExtensions(byteArrayOutputStream, hashtable, false);
        TlsUtils.writeOpaque16(byteArrayOutputStream.toByteArray(), outputStream);
    }

    protected static void writeSelectedExtensions(OutputStream outputStream, Hashtable hashtable, boolean z) {
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            int intValue = num.intValue();
            byte[] bArr = (byte[]) hashtable.get(num);
            if (z == (bArr.length == 0)) {
                TlsUtils.checkUint16(intValue);
                TlsUtils.writeUint16(intValue, outputStream);
                TlsUtils.writeOpaque16(bArr, outputStream);
            }
        }
    }

    protected static void writeSupplementalData(OutputStream outputStream, Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < vector.size(); i++) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry) vector.elementAt(i);
            int dataType = supplementalDataEntry.getDataType();
            TlsUtils.checkUint16(dataType);
            TlsUtils.writeUint16(dataType, byteArrayOutputStream);
            TlsUtils.writeOpaque16(supplementalDataEntry.getData(), byteArrayOutputStream);
        }
        TlsUtils.writeOpaque24(byteArrayOutputStream.toByteArray(), outputStream);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:786)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:825)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:130)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:88)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:50)
        */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0035  */
    protected static int getPRFAlgorithm(org.spongycastle.crypto.tls.TlsContext r4, int r5) {
        /*
        // Method dump skipped, instructions count: 418
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsProtocol.getPRFAlgorithm(org.spongycastle.crypto.tls.TlsContext, int):int");
    }

    class HandshakeMessage extends ByteArrayOutputStream {
        HandshakeMessage(TlsProtocol tlsProtocol, short s) {
            this(s, 60);
        }

        HandshakeMessage(short s, int i) {
            super(i + 4);
            TlsUtils.writeUint8(s, (OutputStream) this);
            this.count += 3;
        }

        /* access modifiers changed from: package-private */
        public void writeToRecordStream() {
            int i = this.count - 4;
            TlsUtils.checkUint24(i);
            TlsUtils.writeUint24(i, this.buf, 1);
            TlsProtocol.this.writeHandshakeMessage(this.buf, 0, this.count);
            this.buf = null;
        }
    }
}
