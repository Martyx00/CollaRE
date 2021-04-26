package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import org.spongycastle.util.Integers;

public class TlsSRTPUtils {
    public static final Integer EXT_use_srtp = Integers.valueOf(14);

    public static void addUseSRTPExtension(Hashtable hashtable, UseSRTPData useSRTPData) {
        hashtable.put(EXT_use_srtp, createUseSRTPExtension(useSRTPData));
    }

    public static UseSRTPData getUseSRTPExtension(Hashtable hashtable) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_use_srtp);
        if (extensionData == null) {
            return null;
        }
        return readUseSRTPExtension(extensionData);
    }

    public static byte[] createUseSRTPExtension(UseSRTPData useSRTPData) {
        if (useSRTPData != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            TlsUtils.writeUint16ArrayWithUint16Length(useSRTPData.getProtectionProfiles(), byteArrayOutputStream);
            TlsUtils.writeOpaque8(useSRTPData.getMki(), byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalArgumentException("'useSRTPData' cannot be null");
    }

    public static UseSRTPData readUseSRTPExtension(byte[] bArr) {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
            if (readUint16 < 2 || (readUint16 & 1) != 0) {
                throw new TlsFatalAlert(50);
            }
            int[] readUint16Array = TlsUtils.readUint16Array(readUint16 / 2, byteArrayInputStream);
            byte[] readOpaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return new UseSRTPData(readUint16Array, readOpaque8);
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }
}
