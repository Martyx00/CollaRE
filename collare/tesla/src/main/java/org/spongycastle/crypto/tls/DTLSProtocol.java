package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Arrays;

public abstract class DTLSProtocol {
    protected final SecureRandom secureRandom;

    protected DTLSProtocol(SecureRandom secureRandom2) {
        if (secureRandom2 != null) {
            this.secureRandom = secureRandom2;
            return;
        }
        throw new IllegalArgumentException("'secureRandom' cannot be null");
    }

    /* access modifiers changed from: protected */
    public void processFinished(byte[] bArr, byte[] bArr2) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        byte[] readFully = TlsUtils.readFully(bArr2.length, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(bArr2, readFully)) {
            throw new TlsFatalAlert(40);
        }
    }

    protected static void applyMaxFragmentLengthExtension(DTLSRecordLayer dTLSRecordLayer, short s) {
        if (s < 0) {
            return;
        }
        if (MaxFragmentLength.isValid(s)) {
            dTLSRecordLayer.setPlaintextLimit(1 << (s + 8));
            return;
        }
        throw new TlsFatalAlert(80);
    }

    protected static short evaluateMaxFragmentLengthExtension(boolean z, Hashtable hashtable, Hashtable hashtable2, short s) {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (z || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s);
    }

    protected static byte[] generateCertificate(Certificate certificate) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificate.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected static byte[] generateSupplementalData(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsProtocol.writeSupplementalData(byteArrayOutputStream, vector);
        return byteArrayOutputStream.toByteArray();
    }

    protected static void validateSelectedCipherSuite(int i, short s) {
        switch (TlsUtils.getEncryptionAlgorithm(i)) {
            case 1:
            case 2:
                throw new TlsFatalAlert(s);
            default:
                return;
        }
    }
}
