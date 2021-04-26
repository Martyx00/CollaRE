package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* access modifiers changed from: package-private */
public class RecordStream {
    private static int DEFAULT_PLAINTEXT_LIMIT = 16384;
    static final int TLS_HEADER_LENGTH_OFFSET = 3;
    static final int TLS_HEADER_SIZE = 5;
    static final int TLS_HEADER_TYPE_OFFSET = 0;
    static final int TLS_HEADER_VERSION_OFFSET = 1;
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private int ciphertextLimit;
    private int compressedLimit;
    private TlsProtocol handler;
    private TlsHandshakeHash handshakeHash = null;
    private InputStream input;
    private OutputStream output;
    private TlsCipher pendingCipher = null;
    private TlsCompression pendingCompression = null;
    private int plaintextLimit;
    private TlsCipher readCipher = null;
    private TlsCompression readCompression = null;
    private long readSeqNo = 0;
    private ProtocolVersion readVersion = null;
    private boolean restrictReadVersion = true;
    private TlsCipher writeCipher = null;
    private TlsCompression writeCompression = null;
    private long writeSeqNo = 0;
    private ProtocolVersion writeVersion = null;

    RecordStream(TlsProtocol tlsProtocol, InputStream inputStream, OutputStream outputStream) {
        this.handler = tlsProtocol;
        this.input = inputStream;
        this.output = outputStream;
        this.readCompression = new TlsNullCompression();
        this.writeCompression = this.readCompression;
    }

    /* access modifiers changed from: package-private */
    public void init(TlsContext tlsContext) {
        this.readCipher = new TlsNullCipher(tlsContext);
        this.writeCipher = this.readCipher;
        this.handshakeHash = new DeferredHash();
        this.handshakeHash.init(tlsContext);
        setPlaintextLimit(DEFAULT_PLAINTEXT_LIMIT);
    }

    /* access modifiers changed from: package-private */
    public int getPlaintextLimit() {
        return this.plaintextLimit;
    }

    /* access modifiers changed from: package-private */
    public void setPlaintextLimit(int i) {
        this.plaintextLimit = i;
        this.compressedLimit = this.plaintextLimit + 1024;
        this.ciphertextLimit = this.compressedLimit + 1024;
    }

    /* access modifiers changed from: package-private */
    public ProtocolVersion getReadVersion() {
        return this.readVersion;
    }

    /* access modifiers changed from: package-private */
    public void setReadVersion(ProtocolVersion protocolVersion) {
        this.readVersion = protocolVersion;
    }

    /* access modifiers changed from: package-private */
    public void setWriteVersion(ProtocolVersion protocolVersion) {
        this.writeVersion = protocolVersion;
    }

    /* access modifiers changed from: package-private */
    public void setRestrictReadVersion(boolean z) {
        this.restrictReadVersion = z;
    }

    /* access modifiers changed from: package-private */
    public void setPendingConnectionState(TlsCompression tlsCompression, TlsCipher tlsCipher) {
        this.pendingCompression = tlsCompression;
        this.pendingCipher = tlsCipher;
    }

    /* access modifiers changed from: package-private */
    public void sentWriteCipherSpec() {
        TlsCipher tlsCipher;
        TlsCompression tlsCompression = this.pendingCompression;
        if (tlsCompression == null || (tlsCipher = this.pendingCipher) == null) {
            throw new TlsFatalAlert(40);
        }
        this.writeCompression = tlsCompression;
        this.writeCipher = tlsCipher;
        this.writeSeqNo = 0;
    }

    /* access modifiers changed from: package-private */
    public void receivedReadCipherSpec() {
        TlsCipher tlsCipher;
        TlsCompression tlsCompression = this.pendingCompression;
        if (tlsCompression == null || (tlsCipher = this.pendingCipher) == null) {
            throw new TlsFatalAlert(40);
        }
        this.readCompression = tlsCompression;
        this.readCipher = tlsCipher;
        this.readSeqNo = 0;
    }

    /* access modifiers changed from: package-private */
    public void finaliseHandshake() {
        TlsCipher tlsCipher;
        TlsCompression tlsCompression = this.readCompression;
        TlsCompression tlsCompression2 = this.pendingCompression;
        if (tlsCompression == tlsCompression2 && this.writeCompression == tlsCompression2 && this.readCipher == (tlsCipher = this.pendingCipher) && this.writeCipher == tlsCipher) {
            this.pendingCompression = null;
            this.pendingCipher = null;
            return;
        }
        throw new TlsFatalAlert(40);
    }

    /* access modifiers changed from: package-private */
    public boolean readRecord() {
        byte[] readAllOrNothing = TlsUtils.readAllOrNothing(5, this.input);
        if (readAllOrNothing == null) {
            return false;
        }
        short readUint8 = TlsUtils.readUint8(readAllOrNothing, 0);
        checkType(readUint8, 10);
        if (this.restrictReadVersion) {
            ProtocolVersion readVersion2 = TlsUtils.readVersion(readAllOrNothing, 1);
            ProtocolVersion protocolVersion = this.readVersion;
            if (protocolVersion == null) {
                this.readVersion = readVersion2;
            } else if (!readVersion2.equals(protocolVersion)) {
                throw new TlsFatalAlert(47);
            }
        } else if ((TlsUtils.readVersionRaw(readAllOrNothing, 1) & -256) != 768) {
            throw new TlsFatalAlert(47);
        }
        byte[] decodeAndVerify = decodeAndVerify(readUint8, this.input, TlsUtils.readUint16(readAllOrNothing, 3));
        this.handler.processRecord(readUint8, decodeAndVerify, 0, decodeAndVerify.length);
        return true;
    }

    /* access modifiers changed from: package-private */
    public byte[] decodeAndVerify(short s, InputStream inputStream, int i) {
        checkLength(i, this.ciphertextLimit, 22);
        byte[] readFully = TlsUtils.readFully(i, inputStream);
        TlsCipher tlsCipher = this.readCipher;
        long j = this.readSeqNo;
        this.readSeqNo = 1 + j;
        byte[] decodeCiphertext = tlsCipher.decodeCiphertext(j, s, readFully, 0, readFully.length);
        checkLength(decodeCiphertext.length, this.compressedLimit, 22);
        OutputStream decompress = this.readCompression.decompress(this.buffer);
        if (decompress != this.buffer) {
            decompress.write(decodeCiphertext, 0, decodeCiphertext.length);
            decompress.flush();
            decodeCiphertext = getBufferContents();
        }
        checkLength(decodeCiphertext.length, this.plaintextLimit, 30);
        if (decodeCiphertext.length >= 1 || s == 23) {
            return decodeCiphertext;
        }
        throw new TlsFatalAlert(47);
    }

    /* access modifiers changed from: package-private */
    public void writeRecord(short s, byte[] bArr, int i, int i2) {
        byte[] bArr2;
        if (this.writeVersion != null) {
            checkType(s, 80);
            checkLength(i2, this.plaintextLimit, 80);
            if (i2 >= 1 || s == 23) {
                if (s == 22) {
                    updateHandshakeData(bArr, i, i2);
                }
                OutputStream compress = this.writeCompression.compress(this.buffer);
                if (compress == this.buffer) {
                    TlsCipher tlsCipher = this.writeCipher;
                    long j = this.writeSeqNo;
                    this.writeSeqNo = 1 + j;
                    bArr2 = tlsCipher.encodePlaintext(j, s, bArr, i, i2);
                } else {
                    compress.write(bArr, i, i2);
                    compress.flush();
                    byte[] bufferContents = getBufferContents();
                    checkLength(bufferContents.length, i2 + 1024, 80);
                    TlsCipher tlsCipher2 = this.writeCipher;
                    long j2 = this.writeSeqNo;
                    this.writeSeqNo = 1 + j2;
                    bArr2 = tlsCipher2.encodePlaintext(j2, s, bufferContents, 0, bufferContents.length);
                }
                checkLength(bArr2.length, this.ciphertextLimit, 80);
                byte[] bArr3 = new byte[(bArr2.length + 5)];
                TlsUtils.writeUint8(s, bArr3, 0);
                TlsUtils.writeVersion(this.writeVersion, bArr3, 1);
                TlsUtils.writeUint16(bArr2.length, bArr3, 3);
                System.arraycopy(bArr2, 0, bArr3, 5, bArr2.length);
                this.output.write(bArr3);
                this.output.flush();
                return;
            }
            throw new TlsFatalAlert(80);
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyHelloComplete() {
        this.handshakeHash = this.handshakeHash.notifyPRFDetermined();
    }

    /* access modifiers changed from: package-private */
    public TlsHandshakeHash getHandshakeHash() {
        return this.handshakeHash;
    }

    /* access modifiers changed from: package-private */
    public TlsHandshakeHash prepareToFinish() {
        TlsHandshakeHash tlsHandshakeHash = this.handshakeHash;
        this.handshakeHash = tlsHandshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    /* access modifiers changed from: package-private */
    public void updateHandshakeData(byte[] bArr, int i, int i2) {
        this.handshakeHash.update(bArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:0|1|2|3|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void safeClose() {
        /*
            r1 = this;
            java.io.InputStream r0 = r1.input     // Catch:{ IOException -> 0x0005 }
            r0.close()     // Catch:{ IOException -> 0x0005 }
        L_0x0005:
            java.io.OutputStream r0 = r1.output     // Catch:{ IOException -> 0x000a }
            r0.close()     // Catch:{ IOException -> 0x000a }
        L_0x000a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.RecordStream.safeClose():void");
    }

    /* access modifiers changed from: package-private */
    public void flush() {
        this.output.flush();
    }

    private byte[] getBufferContents() {
        byte[] byteArray = this.buffer.toByteArray();
        this.buffer.reset();
        return byteArray;
    }

    private static void checkType(short s, short s2) {
        switch (s) {
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                return;
            default:
                throw new TlsFatalAlert(s2);
        }
    }

    private static void checkLength(int i, int i2, short s) {
        if (i > i2) {
            throw new TlsFatalAlert(s);
        }
    }
}
