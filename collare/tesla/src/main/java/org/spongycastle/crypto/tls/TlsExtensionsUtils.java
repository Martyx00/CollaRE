package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import org.spongycastle.util.Integers;

public class TlsExtensionsUtils {
    public static final Integer EXT_encrypt_then_mac = Integers.valueOf(22);
    public static final Integer EXT_extended_master_secret = Integers.valueOf(23);
    public static final Integer EXT_heartbeat = Integers.valueOf(15);
    public static final Integer EXT_max_fragment_length = Integers.valueOf(1);
    public static final Integer EXT_padding = Integers.valueOf(21);
    public static final Integer EXT_server_name = Integers.valueOf(0);
    public static final Integer EXT_status_request = Integers.valueOf(5);
    public static final Integer EXT_truncated_hmac = Integers.valueOf(4);

    public static Hashtable ensureExtensionsInitialised(Hashtable hashtable) {
        return hashtable == null ? new Hashtable() : hashtable;
    }

    public static void addEncryptThenMACExtension(Hashtable hashtable) {
        hashtable.put(EXT_encrypt_then_mac, createEncryptThenMACExtension());
    }

    public static void addExtendedMasterSecretExtension(Hashtable hashtable) {
        hashtable.put(EXT_extended_master_secret, createExtendedMasterSecretExtension());
    }

    public static void addHeartbeatExtension(Hashtable hashtable, HeartbeatExtension heartbeatExtension) {
        hashtable.put(EXT_heartbeat, createHeartbeatExtension(heartbeatExtension));
    }

    public static void addMaxFragmentLengthExtension(Hashtable hashtable, short s) {
        hashtable.put(EXT_max_fragment_length, createMaxFragmentLengthExtension(s));
    }

    public static void addPaddingExtension(Hashtable hashtable, int i) {
        hashtable.put(EXT_padding, createPaddingExtension(i));
    }

    public static void addServerNameExtension(Hashtable hashtable, ServerNameList serverNameList) {
        hashtable.put(EXT_server_name, createServerNameExtension(serverNameList));
    }

    public static void addStatusRequestExtension(Hashtable hashtable, CertificateStatusRequest certificateStatusRequest) {
        hashtable.put(EXT_status_request, createStatusRequestExtension(certificateStatusRequest));
    }

    public static void addTruncatedHMacExtension(Hashtable hashtable) {
        hashtable.put(EXT_truncated_hmac, createTruncatedHMacExtension());
    }

    public static HeartbeatExtension getHeartbeatExtension(Hashtable hashtable) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_heartbeat);
        if (extensionData == null) {
            return null;
        }
        return readHeartbeatExtension(extensionData);
    }

    public static short getMaxFragmentLengthExtension(Hashtable hashtable) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_max_fragment_length);
        if (extensionData == null) {
            return -1;
        }
        return readMaxFragmentLengthExtension(extensionData);
    }

    public static int getPaddingExtension(Hashtable hashtable) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_padding);
        if (extensionData == null) {
            return -1;
        }
        return readPaddingExtension(extensionData);
    }

    public static ServerNameList getServerNameExtension(Hashtable hashtable) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_server_name);
        if (extensionData == null) {
            return null;
        }
        return readServerNameExtension(extensionData);
    }

    public static CertificateStatusRequest getStatusRequestExtension(Hashtable hashtable) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_status_request);
        if (extensionData == null) {
            return null;
        }
        return readStatusRequestExtension(extensionData);
    }

    public static boolean hasEncryptThenMACExtension(Hashtable hashtable) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_encrypt_then_mac);
        if (extensionData == null) {
            return false;
        }
        return readEncryptThenMACExtension(extensionData);
    }

    public static boolean hasExtendedMasterSecretExtension(Hashtable hashtable) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_extended_master_secret);
        if (extensionData == null) {
            return false;
        }
        return readExtendedMasterSecretExtension(extensionData);
    }

    public static boolean hasTruncatedHMacExtension(Hashtable hashtable) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_truncated_hmac);
        if (extensionData == null) {
            return false;
        }
        return readTruncatedHMacExtension(extensionData);
    }

    public static byte[] createEmptyExtensionData() {
        return TlsUtils.EMPTY_BYTES;
    }

    public static byte[] createEncryptThenMACExtension() {
        return createEmptyExtensionData();
    }

    public static byte[] createExtendedMasterSecretExtension() {
        return createEmptyExtensionData();
    }

    public static byte[] createHeartbeatExtension(HeartbeatExtension heartbeatExtension) {
        if (heartbeatExtension != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            heartbeatExtension.encode(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        throw new TlsFatalAlert(80);
    }

    public static byte[] createMaxFragmentLengthExtension(short s) {
        TlsUtils.checkUint8(s);
        byte[] bArr = new byte[1];
        TlsUtils.writeUint8(s, bArr, 0);
        return bArr;
    }

    public static byte[] createPaddingExtension(int i) {
        TlsUtils.checkUint16(i);
        return new byte[i];
    }

    public static byte[] createServerNameExtension(ServerNameList serverNameList) {
        if (serverNameList != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            serverNameList.encode(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        throw new TlsFatalAlert(80);
    }

    public static byte[] createStatusRequestExtension(CertificateStatusRequest certificateStatusRequest) {
        if (certificateStatusRequest != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            certificateStatusRequest.encode(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        throw new TlsFatalAlert(80);
    }

    public static byte[] createTruncatedHMacExtension() {
        return createEmptyExtensionData();
    }

    private static boolean readEmptyExtensionData(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        } else if (bArr.length == 0) {
            return true;
        } else {
            throw new TlsFatalAlert(47);
        }
    }

    public static boolean readEncryptThenMACExtension(byte[] bArr) {
        return readEmptyExtensionData(bArr);
    }

    public static boolean readExtendedMasterSecretExtension(byte[] bArr) {
        return readEmptyExtensionData(bArr);
    }

    public static HeartbeatExtension readHeartbeatExtension(byte[] bArr) {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            HeartbeatExtension parse = HeartbeatExtension.parse(byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return parse;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static short readMaxFragmentLengthExtension(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        } else if (bArr.length == 1) {
            return TlsUtils.readUint8(bArr, 0);
        } else {
            throw new TlsFatalAlert(50);
        }
    }

    public static int readPaddingExtension(byte[] bArr) {
        if (bArr != null) {
            for (byte b2 : bArr) {
                if (b2 != 0) {
                    throw new TlsFatalAlert(47);
                }
            }
            return bArr.length;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static ServerNameList readServerNameExtension(byte[] bArr) {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ServerNameList parse = ServerNameList.parse(byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return parse;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static CertificateStatusRequest readStatusRequestExtension(byte[] bArr) {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            CertificateStatusRequest parse = CertificateStatusRequest.parse(byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return parse;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static boolean readTruncatedHMacExtension(byte[] bArr) {
        return readEmptyExtensionData(bArr);
    }
}
