package org.spongycastle.crypto.tls;

import java.io.OutputStream;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.util.Arrays;

public class TlsRSAUtils {
    public static byte[] generateEncryptedPreMasterSecret(TlsContext tlsContext, RSAKeyParameters rSAKeyParameters, OutputStream outputStream) {
        byte[] bArr = new byte[48];
        tlsContext.getSecureRandom().nextBytes(bArr);
        TlsUtils.writeVersion(tlsContext.getClientVersion(), bArr, 0);
        PKCS1Encoding pKCS1Encoding = new PKCS1Encoding(new RSABlindedEngine());
        pKCS1Encoding.init(true, new ParametersWithRandom(rSAKeyParameters, tlsContext.getSecureRandom()));
        try {
            byte[] processBlock = pKCS1Encoding.processBlock(bArr, 0, bArr.length);
            if (TlsUtils.isSSL(tlsContext)) {
                outputStream.write(processBlock);
            } else {
                TlsUtils.writeOpaque16(processBlock, outputStream);
            }
            return bArr;
        } catch (InvalidCipherTextException e) {
            throw new TlsFatalAlert(80, e);
        }
    }

    public static byte[] safeDecryptPreMasterSecret(TlsContext tlsContext, RSAKeyParameters rSAKeyParameters, byte[] bArr) {
        ProtocolVersion clientVersion = tlsContext.getClientVersion();
        byte[] bArr2 = new byte[48];
        tlsContext.getSecureRandom().nextBytes(bArr2);
        byte[] clone = Arrays.clone(bArr2);
        try {
            PKCS1Encoding pKCS1Encoding = new PKCS1Encoding(new RSABlindedEngine(), bArr2);
            pKCS1Encoding.init(false, new ParametersWithRandom(rSAKeyParameters, tlsContext.getSecureRandom()));
            clone = pKCS1Encoding.processBlock(bArr, 0, bArr.length);
        } catch (Exception unused) {
        }
        int majorVersion = (clientVersion.getMajorVersion() ^ (clone[0] & 255)) | (clientVersion.getMinorVersion() ^ (clone[1] & 255));
        int i = majorVersion | (majorVersion >> 1);
        int i2 = i | (i >> 2);
        int i3 = ~(((i2 | (i2 >> 4)) & 1) - 1);
        for (int i4 = 0; i4 < 48; i4++) {
            clone[i4] = (byte) ((clone[i4] & (~i3)) | (bArr2[i4] & i3));
        }
        return clone;
    }
}
