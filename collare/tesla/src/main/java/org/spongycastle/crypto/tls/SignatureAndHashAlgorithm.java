package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;

public class SignatureAndHashAlgorithm {
    protected short hash;
    protected short signature;

    public SignatureAndHashAlgorithm(short s, short s2) {
        if (!TlsUtils.isValidUint8(s)) {
            throw new IllegalArgumentException("'hash' should be a uint8");
        } else if (!TlsUtils.isValidUint8(s2)) {
            throw new IllegalArgumentException("'signature' should be a uint8");
        } else if (s2 != 0) {
            this.hash = s;
            this.signature = s2;
        } else {
            throw new IllegalArgumentException("'signature' MUST NOT be \"anonymous\"");
        }
    }

    public short getHash() {
        return this.hash;
    }

    public short getSignature() {
        return this.signature;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignatureAndHashAlgorithm)) {
            return false;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) obj;
        if (signatureAndHashAlgorithm.getHash() == getHash() && signatureAndHashAlgorithm.getSignature() == getSignature()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (getHash() << 16) | getSignature();
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(getHash(), outputStream);
        TlsUtils.writeUint8(getSignature(), outputStream);
    }

    public static SignatureAndHashAlgorithm parse(InputStream inputStream) {
        return new SignatureAndHashAlgorithm(TlsUtils.readUint8(inputStream), TlsUtils.readUint8(inputStream));
    }
}
