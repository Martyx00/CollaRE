package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.KeyUsage;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;
import org.spongycastle.util.io.Streams;

public class TlsUtils {
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    public static final short[] EMPTY_SHORTS = new short[0];
    public static final Integer EXT_signature_algorithms = Integers.valueOf(13);
    static final byte[][] SSL3_CONST = genSSL3Const();
    static final byte[] SSL_CLIENT = {67, 76, 78, 84};
    static final byte[] SSL_SERVER = {83, 82, 86, 82};

    public static boolean hasSigningCapability(short s) {
        if (s == 64) {
            return true;
        }
        switch (s) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    public static boolean isValidUint16(int i) {
        return (65535 & i) == i;
    }

    public static boolean isValidUint16(long j) {
        return (65535 & j) == j;
    }

    public static boolean isValidUint24(int i) {
        return (16777215 & i) == i;
    }

    public static boolean isValidUint24(long j) {
        return (16777215 & j) == j;
    }

    public static boolean isValidUint32(long j) {
        return (4294967295L & j) == j;
    }

    public static boolean isValidUint48(long j) {
        return (281474976710655L & j) == j;
    }

    public static boolean isValidUint64(long j) {
        return true;
    }

    public static boolean isValidUint8(int i) {
        return (i & 255) == i;
    }

    public static boolean isValidUint8(long j) {
        return (255 & j) == j;
    }

    public static boolean isValidUint8(short s) {
        return (s & 255) == s;
    }

    public static void checkUint8(short s) {
        if (!isValidUint8(s)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint8(int i) {
        if (!isValidUint8(i)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint8(long j) {
        if (!isValidUint8(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint16(int i) {
        if (!isValidUint16(i)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint16(long j) {
        if (!isValidUint16(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint24(int i) {
        if (!isValidUint24(i)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint24(long j) {
        if (!isValidUint24(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint32(long j) {
        if (!isValidUint32(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint48(long j) {
        if (!isValidUint48(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint64(long j) {
        if (!isValidUint64(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static boolean isSSL(TlsContext tlsContext) {
        return tlsContext.getServerVersion().isSSL();
    }

    public static boolean isTLSv11(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv11.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv11(TlsContext tlsContext) {
        return isTLSv11(tlsContext.getServerVersion());
    }

    public static boolean isTLSv12(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv12(TlsContext tlsContext) {
        return isTLSv12(tlsContext.getServerVersion());
    }

    public static void writeUint8(short s, OutputStream outputStream) {
        outputStream.write(s);
    }

    public static void writeUint8(int i, OutputStream outputStream) {
        outputStream.write(i);
    }

    public static void writeUint8(short s, byte[] bArr, int i) {
        bArr[i] = (byte) s;
    }

    public static void writeUint8(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
    }

    public static void writeUint16(int i, OutputStream outputStream) {
        outputStream.write(i >>> 8);
        outputStream.write(i);
    }

    public static void writeUint16(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
    }

    public static void writeUint24(int i, OutputStream outputStream) {
        outputStream.write((byte) (i >>> 16));
        outputStream.write((byte) (i >>> 8));
        outputStream.write((byte) i);
    }

    public static void writeUint24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) i;
    }

    public static void writeUint32(long j, OutputStream outputStream) {
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) j));
    }

    public static void writeUint32(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j >>> 24));
        bArr[i + 1] = (byte) ((int) (j >>> 16));
        bArr[i + 2] = (byte) ((int) (j >>> 8));
        bArr[i + 3] = (byte) ((int) j);
    }

    public static void writeUint48(long j, OutputStream outputStream) {
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) j));
    }

    public static void writeUint48(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j >>> 40));
        bArr[i + 1] = (byte) ((int) (j >>> 32));
        bArr[i + 2] = (byte) ((int) (j >>> 24));
        bArr[i + 3] = (byte) ((int) (j >>> 16));
        bArr[i + 4] = (byte) ((int) (j >>> 8));
        bArr[i + 5] = (byte) ((int) j);
    }

    public static void writeUint64(long j, OutputStream outputStream) {
        outputStream.write((byte) ((int) (j >>> 56)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) j));
    }

    public static void writeUint64(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j >>> 56));
        bArr[i + 1] = (byte) ((int) (j >>> 48));
        bArr[i + 2] = (byte) ((int) (j >>> 40));
        bArr[i + 3] = (byte) ((int) (j >>> 32));
        bArr[i + 4] = (byte) ((int) (j >>> 24));
        bArr[i + 5] = (byte) ((int) (j >>> 16));
        bArr[i + 6] = (byte) ((int) (j >>> 8));
        bArr[i + 7] = (byte) ((int) j);
    }

    public static void writeOpaque8(byte[] bArr, OutputStream outputStream) {
        checkUint8(bArr.length);
        writeUint8(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque16(byte[] bArr, OutputStream outputStream) {
        checkUint16(bArr.length);
        writeUint16(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque24(byte[] bArr, OutputStream outputStream) {
        checkUint24(bArr.length);
        writeUint24(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeUint8Array(short[] sArr, OutputStream outputStream) {
        for (short s : sArr) {
            writeUint8(s, outputStream);
        }
    }

    public static void writeUint8Array(short[] sArr, byte[] bArr, int i) {
        for (short s : sArr) {
            writeUint8(s, bArr, i);
            i++;
        }
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, OutputStream outputStream) {
        checkUint8(sArr.length);
        writeUint8(sArr.length, outputStream);
        writeUint8Array(sArr, outputStream);
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, byte[] bArr, int i) {
        checkUint8(sArr.length);
        writeUint8(sArr.length, bArr, i);
        writeUint8Array(sArr, bArr, i + 1);
    }

    public static void writeUint16Array(int[] iArr, OutputStream outputStream) {
        for (int i : iArr) {
            writeUint16(i, outputStream);
        }
    }

    public static void writeUint16Array(int[] iArr, byte[] bArr, int i) {
        for (int i2 : iArr) {
            writeUint16(i2, bArr, i);
            i += 2;
        }
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, OutputStream outputStream) {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, outputStream);
        writeUint16Array(iArr, outputStream);
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, byte[] bArr, int i) {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, bArr, i);
        writeUint16Array(iArr, bArr, i + 2);
    }

    public static byte[] encodeOpaque8(byte[] bArr) {
        checkUint8(bArr.length);
        return Arrays.prepend(bArr, (byte) bArr.length);
    }

    public static byte[] encodeUint8ArrayWithUint8Length(short[] sArr) {
        byte[] bArr = new byte[(sArr.length + 1)];
        writeUint8ArrayWithUint8Length(sArr, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint16ArrayWithUint16Length(int[] iArr) {
        byte[] bArr = new byte[((iArr.length * 2) + 2)];
        writeUint16ArrayWithUint16Length(iArr, bArr, 0);
        return bArr;
    }

    public static short readUint8(InputStream inputStream) {
        int read = inputStream.read();
        if (read >= 0) {
            return (short) read;
        }
        throw new EOFException();
    }

    public static short readUint8(byte[] bArr, int i) {
        return (short) (bArr[i] & 255);
    }

    public static int readUint16(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static int readUint16(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public static int readUint24(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        if (read3 >= 0) {
            return read3 | (read << 16) | (read2 << 8);
        }
        throw new EOFException();
    }

    public static int readUint24(byte[] bArr, int i) {
        int i2 = i + 1;
        return (bArr[i2 + 1] & 255) | ((bArr[i] & 255) << 16) | ((bArr[i2] & 255) << 8);
    }

    public static long readUint32(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        int read4 = inputStream.read();
        if (read4 >= 0) {
            return ((long) (read4 | (read << 24) | (read2 << 16) | (read3 << 8))) & 4294967295L;
        }
        throw new EOFException();
    }

    public static long readUint32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return ((long) ((bArr[i3 + 1] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8))) & 4294967295L;
    }

    public static long readUint48(InputStream inputStream) {
        return ((((long) readUint24(inputStream)) & 4294967295L) << 24) | (4294967295L & ((long) readUint24(inputStream)));
    }

    public static long readUint48(byte[] bArr, int i) {
        int readUint24 = readUint24(bArr, i);
        return (((long) readUint24(bArr, i + 3)) & 4294967295L) | ((((long) readUint24) & 4294967295L) << 24);
    }

    public static byte[] readAllOrNothing(int i, InputStream inputStream) {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        int readFully = Streams.readFully(inputStream, bArr);
        if (readFully == 0) {
            return null;
        }
        if (readFully == i) {
            return bArr;
        }
        throw new EOFException();
    }

    public static byte[] readFully(int i, InputStream inputStream) {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        if (i == Streams.readFully(inputStream, bArr)) {
            return bArr;
        }
        throw new EOFException();
    }

    public static void readFully(byte[] bArr, InputStream inputStream) {
        int length = bArr.length;
        if (length > 0 && length != Streams.readFully(inputStream, bArr)) {
            throw new EOFException();
        }
    }

    public static byte[] readOpaque8(InputStream inputStream) {
        return readFully(readUint8(inputStream), inputStream);
    }

    public static byte[] readOpaque16(InputStream inputStream) {
        return readFully(readUint16(inputStream), inputStream);
    }

    public static byte[] readOpaque24(InputStream inputStream) {
        return readFully(readUint24(inputStream), inputStream);
    }

    public static short[] readUint8Array(int i, InputStream inputStream) {
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = readUint8(inputStream);
        }
        return sArr;
    }

    public static int[] readUint16Array(int i, InputStream inputStream) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readUint16(inputStream);
        }
        return iArr;
    }

    public static ProtocolVersion readVersion(byte[] bArr, int i) {
        return ProtocolVersion.get(bArr[i] & 255, bArr[i + 1] & 255);
    }

    public static ProtocolVersion readVersion(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return ProtocolVersion.get(read, read2);
        }
        throw new EOFException();
    }

    public static int readVersionRaw(byte[] bArr, int i) {
        return bArr[i + 1] | (bArr[i] << 8);
    }

    public static int readVersionRaw(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static ASN1Primitive readASN1Object(byte[] bArr) {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        ASN1Primitive readObject = aSN1InputStream.readObject();
        if (readObject == null) {
            throw new TlsFatalAlert(50);
        } else if (aSN1InputStream.readObject() == null) {
            return readObject;
        } else {
            throw new TlsFatalAlert(50);
        }
    }

    public static ASN1Primitive readDERObject(byte[] bArr) {
        ASN1Primitive readASN1Object = readASN1Object(bArr);
        if (Arrays.areEqual(readASN1Object.getEncoded(ASN1Encoding.DER), bArr)) {
            return readASN1Object;
        }
        throw new TlsFatalAlert(50);
    }

    public static void writeGMTUnixTime(byte[] bArr, int i) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        bArr[i] = (byte) (currentTimeMillis >>> 24);
        bArr[i + 1] = (byte) (currentTimeMillis >>> 16);
        bArr[i + 2] = (byte) (currentTimeMillis >>> 8);
        bArr[i + 3] = (byte) currentTimeMillis;
    }

    public static void writeVersion(ProtocolVersion protocolVersion, OutputStream outputStream) {
        outputStream.write(protocolVersion.getMajorVersion());
        outputStream.write(protocolVersion.getMinorVersion());
    }

    public static void writeVersion(ProtocolVersion protocolVersion, byte[] bArr, int i) {
        bArr[i] = (byte) protocolVersion.getMajorVersion();
        bArr[i + 1] = (byte) protocolVersion.getMinorVersion();
    }

    public static Vector getDefaultDSSSignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm(2, 2));
    }

    public static Vector getDefaultECDSASignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm(2, 3));
    }

    public static Vector getDefaultRSASignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm(2, 1));
    }

    public static Vector getDefaultSupportedSignatureAlgorithms() {
        short[] sArr = {2, 3, 4, 5, 6};
        short[] sArr2 = {1, 2, 3};
        Vector vector = new Vector();
        for (int i = 0; i < sArr2.length; i++) {
            for (short s : sArr) {
                vector.addElement(new SignatureAndHashAlgorithm(s, sArr2[i]));
            }
        }
        return vector;
    }

    public static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(TlsContext tlsContext, TlsSignerCredentials tlsSignerCredentials) {
        if (!isTLSv12(tlsContext)) {
            return null;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = tlsSignerCredentials.getSignatureAndHashAlgorithm();
        if (signatureAndHashAlgorithm != null) {
            return signatureAndHashAlgorithm;
        }
        throw new TlsFatalAlert(80);
    }

    public static byte[] getExtensionData(Hashtable hashtable, Integer num) {
        if (hashtable == null) {
            return null;
        }
        return (byte[]) hashtable.get(num);
    }

    public static boolean hasExpectedEmptyExtensionData(Hashtable hashtable, Integer num, short s) {
        byte[] extensionData = getExtensionData(hashtable, num);
        if (extensionData == null) {
            return false;
        }
        if (extensionData.length == 0) {
            return true;
        }
        throw new TlsFatalAlert(s);
    }

    public static TlsSession importSession(byte[] bArr, SessionParameters sessionParameters) {
        return new TlsSessionImpl(bArr, sessionParameters);
    }

    public static boolean isSignatureAlgorithmsExtensionAllowed(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static void addSignatureAlgorithmsExtension(Hashtable hashtable, Vector vector) {
        hashtable.put(EXT_signature_algorithms, createSignatureAlgorithmsExtension(vector));
    }

    public static Vector getSignatureAlgorithmsExtension(Hashtable hashtable) {
        byte[] extensionData = getExtensionData(hashtable, EXT_signature_algorithms);
        if (extensionData == null) {
            return null;
        }
        return readSignatureAlgorithmsExtension(extensionData);
    }

    public static byte[] createSignatureAlgorithmsExtension(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encodeSupportedSignatureAlgorithms(vector, false, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Vector readSignatureAlgorithmsExtension(byte[] bArr) {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            Vector parseSupportedSignatureAlgorithms = parseSupportedSignatureAlgorithms(false, byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return parseSupportedSignatureAlgorithms;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static void encodeSupportedSignatureAlgorithms(Vector vector, boolean z, OutputStream outputStream) {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        int size = vector.size() * 2;
        checkUint16(size);
        writeUint16(size, outputStream);
        for (int i = 0; i < vector.size(); i++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i);
            if (z || signatureAndHashAlgorithm.getSignature() != 0) {
                signatureAndHashAlgorithm.encode(outputStream);
            } else {
                throw new IllegalArgumentException("SignatureAlgorithm.anonymous MUST NOT appear in the signature_algorithms extension");
            }
        }
    }

    public static Vector parseSupportedSignatureAlgorithms(boolean z, InputStream inputStream) {
        int readUint16 = readUint16(inputStream);
        if (readUint16 < 2 || (readUint16 & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        int i = readUint16 / 2;
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 < i; i2++) {
            SignatureAndHashAlgorithm parse = SignatureAndHashAlgorithm.parse(inputStream);
            if (z || parse.getSignature() != 0) {
                vector.addElement(parse);
            } else {
                throw new TlsFatalAlert(47);
            }
        }
        return vector;
    }

    public static void verifySupportedSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        } else if (signatureAndHashAlgorithm != null) {
            if (signatureAndHashAlgorithm.getSignature() != 0) {
                for (int i = 0; i < vector.size(); i++) {
                    SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = (SignatureAndHashAlgorithm) vector.elementAt(i);
                    if (signatureAndHashAlgorithm2.getHash() == signatureAndHashAlgorithm.getHash() && signatureAndHashAlgorithm2.getSignature() == signatureAndHashAlgorithm.getSignature()) {
                        return;
                    }
                }
            }
            throw new TlsFatalAlert(47);
        } else {
            throw new IllegalArgumentException("'signatureAlgorithm' cannot be null");
        }
    }

    public static byte[] PRF(TlsContext tlsContext, byte[] bArr, String str, byte[] bArr2, int i) {
        if (!tlsContext.getServerVersion().isSSL()) {
            byte[] byteArray = Strings.toByteArray(str);
            byte[] concat = concat(byteArray, bArr2);
            int prfAlgorithm = tlsContext.getSecurityParameters().getPrfAlgorithm();
            if (prfAlgorithm == 0) {
                return PRF_legacy(bArr, byteArray, concat, i);
            }
            byte[] bArr3 = new byte[i];
            hmac_hash(createPRFHash(prfAlgorithm), bArr, concat, bArr3);
            return bArr3;
        }
        throw new IllegalStateException("No PRF available for SSLv3 session");
    }

    public static byte[] PRF_legacy(byte[] bArr, String str, byte[] bArr2, int i) {
        byte[] byteArray = Strings.toByteArray(str);
        return PRF_legacy(bArr, byteArray, concat(byteArray, bArr2), i);
    }

    static byte[] PRF_legacy(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int length = (bArr.length + 1) / 2;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr, 0, bArr4, 0, length);
        System.arraycopy(bArr, bArr.length - length, bArr5, 0, length);
        byte[] bArr6 = new byte[i];
        byte[] bArr7 = new byte[i];
        hmac_hash(createHash((short) 1), bArr4, bArr3, bArr6);
        hmac_hash(createHash((short) 2), bArr5, bArr3, bArr7);
        for (int i2 = 0; i2 < i; i2++) {
            bArr6[i2] = (byte) (bArr6[i2] ^ bArr7[i2]);
        }
        return bArr6;
    }

    static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    static void hmac_hash(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        HMac hMac = new HMac(digest);
        hMac.init(new KeyParameter(bArr));
        int digestSize = digest.getDigestSize();
        int length = ((bArr3.length + digestSize) - 1) / digestSize;
        byte[] bArr4 = new byte[hMac.getMacSize()];
        byte[] bArr5 = new byte[hMac.getMacSize()];
        byte[] bArr6 = bArr2;
        int i = 0;
        while (i < length) {
            hMac.update(bArr6, 0, bArr6.length);
            hMac.doFinal(bArr4, 0);
            hMac.update(bArr4, 0, bArr4.length);
            hMac.update(bArr2, 0, bArr2.length);
            hMac.doFinal(bArr5, 0);
            int i2 = digestSize * i;
            System.arraycopy(bArr5, 0, bArr3, i2, Math.min(digestSize, bArr3.length - i2));
            i++;
            bArr6 = bArr4;
        }
    }

    static void validateKeyUsage(Certificate certificate, int i) {
        KeyUsage fromExtensions;
        Extensions extensions = certificate.getTBSCertificate().getExtensions();
        if (extensions != null && (fromExtensions = KeyUsage.fromExtensions(extensions)) != null && (fromExtensions.getBytes()[0] & 255 & i) != i) {
            throw new TlsFatalAlert(46);
        }
    }

    static byte[] calculateKeyBlock(TlsContext tlsContext, int i) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] masterSecret = securityParameters.getMasterSecret();
        byte[] concat = concat(securityParameters.getServerRandom(), securityParameters.getClientRandom());
        if (isSSL(tlsContext)) {
            return calculateKeyBlock_SSL(masterSecret, concat, i);
        }
        return PRF(tlsContext, masterSecret, "key expansion", concat, i);
    }

    static byte[] calculateKeyBlock_SSL(byte[] bArr, byte[] bArr2, int i) {
        Digest createHash = createHash((short) 1);
        Digest createHash2 = createHash((short) 2);
        int digestSize = createHash.getDigestSize();
        byte[] bArr3 = new byte[createHash2.getDigestSize()];
        byte[] bArr4 = new byte[(i + digestSize)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            byte[] bArr5 = SSL3_CONST[i3];
            createHash2.update(bArr5, 0, bArr5.length);
            createHash2.update(bArr, 0, bArr.length);
            createHash2.update(bArr2, 0, bArr2.length);
            createHash2.doFinal(bArr3, 0);
            createHash.update(bArr, 0, bArr.length);
            createHash.update(bArr3, 0, bArr3.length);
            createHash.doFinal(bArr4, i2);
            i2 += digestSize;
            i3++;
        }
        return Arrays.copyOfRange(bArr4, 0, i);
    }

    static byte[] calculateMasterSecret(TlsContext tlsContext, byte[] bArr) {
        byte[] bArr2;
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        if (securityParameters.extendedMasterSecret) {
            bArr2 = securityParameters.getSessionHash();
        } else {
            bArr2 = concat(securityParameters.getClientRandom(), securityParameters.getServerRandom());
        }
        if (isSSL(tlsContext)) {
            return calculateMasterSecret_SSL(bArr, bArr2);
        }
        return PRF(tlsContext, bArr, securityParameters.extendedMasterSecret ? ExporterLabel.extended_master_secret : "master secret", bArr2, 48);
    }

    static byte[] calculateMasterSecret_SSL(byte[] bArr, byte[] bArr2) {
        Digest createHash = createHash((short) 1);
        Digest createHash2 = createHash((short) 2);
        int digestSize = createHash.getDigestSize();
        byte[] bArr3 = new byte[createHash2.getDigestSize()];
        byte[] bArr4 = new byte[(digestSize * 3)];
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            byte[] bArr5 = SSL3_CONST[i2];
            createHash2.update(bArr5, 0, bArr5.length);
            createHash2.update(bArr, 0, bArr.length);
            createHash2.update(bArr2, 0, bArr2.length);
            createHash2.doFinal(bArr3, 0);
            createHash.update(bArr, 0, bArr.length);
            createHash.update(bArr3, 0, bArr3.length);
            createHash.doFinal(bArr4, i);
            i += digestSize;
        }
        return bArr4;
    }

    static byte[] calculateVerifyData(TlsContext tlsContext, String str, byte[] bArr) {
        if (isSSL(tlsContext)) {
            return bArr;
        }
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        return PRF(tlsContext, securityParameters.getMasterSecret(), str, bArr, securityParameters.getVerifyDataLength());
    }

    public static Digest createHash(short s) {
        switch (s) {
            case 1:
                return new MD5Digest();
            case 2:
                return new SHA1Digest();
            case 3:
                return new SHA224Digest();
            case 4:
                return new SHA256Digest();
            case 5:
                return new SHA384Digest();
            case 6:
                return new SHA512Digest();
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest createHash(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm == null) {
            return new CombinedHash();
        }
        return createHash(signatureAndHashAlgorithm.getHash());
    }

    public static Digest cloneHash(short s, Digest digest) {
        switch (s) {
            case 1:
                return new MD5Digest((MD5Digest) digest);
            case 2:
                return new SHA1Digest((SHA1Digest) digest);
            case 3:
                return new SHA224Digest((SHA224Digest) digest);
            case 4:
                return new SHA256Digest((SHA256Digest) digest);
            case 5:
                return new SHA384Digest((SHA384Digest) digest);
            case 6:
                return new SHA512Digest((SHA512Digest) digest);
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest createPRFHash(int i) {
        if (i != 0) {
            return createHash(getHashAlgorithmForPRFAlgorithm(i));
        }
        return new CombinedHash();
    }

    public static Digest clonePRFHash(int i, Digest digest) {
        if (i != 0) {
            return cloneHash(getHashAlgorithmForPRFAlgorithm(i), digest);
        }
        return new CombinedHash((CombinedHash) digest);
    }

    public static short getHashAlgorithmForPRFAlgorithm(int i) {
        switch (i) {
            case 0:
                throw new IllegalArgumentException("legacy PRF not a valid algorithm");
            case 1:
                return 4;
            case 2:
                return 5;
            default:
                throw new IllegalArgumentException("unknown PRFAlgorithm");
        }
    }

    public static ASN1ObjectIdentifier getOIDForHashAlgorithm(short s) {
        switch (s) {
            case 1:
                return PKCSObjectIdentifiers.md5;
            case 2:
                return X509ObjectIdentifiers.id_SHA1;
            case 3:
                return NISTObjectIdentifiers.id_sha224;
            case 4:
                return NISTObjectIdentifiers.id_sha256;
            case 5:
                return NISTObjectIdentifiers.id_sha384;
            case 6:
                return NISTObjectIdentifiers.id_sha512;
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    static short getClientCertificateType(Certificate certificate, Certificate certificate2) {
        if (certificate.isEmpty()) {
            return -1;
        }
        Certificate certificateAt = certificate.getCertificateAt(0);
        try {
            AsymmetricKeyParameter createKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
            if (createKey.isPrivate()) {
                throw new TlsFatalAlert(80);
            } else if (createKey instanceof RSAKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return 1;
            } else if (createKey instanceof DSAPublicKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return 2;
            } else if (createKey instanceof ECPublicKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return 64;
            } else {
                throw new TlsFatalAlert(43);
            }
        } catch (Exception e) {
            throw new TlsFatalAlert(43, e);
        }
    }

    static void trackHashAlgorithms(TlsHandshakeHash tlsHandshakeHash, Vector vector) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); i++) {
                short hash = ((SignatureAndHashAlgorithm) vector.elementAt(i)).getHash();
                if (!HashAlgorithm.isPrivate(hash)) {
                    tlsHandshakeHash.trackHashAlgorithm(hash);
                }
            }
        }
    }

    public static TlsSigner createTlsSigner(short s) {
        if (s == 64) {
            return new TlsECDSASigner();
        }
        switch (s) {
            case 1:
                return new TlsRSASigner();
            case 2:
                return new TlsDSSSigner();
            default:
                throw new IllegalArgumentException("'clientCertificateType' is not a type with signing capability");
        }
    }

    private static byte[][] genSSL3Const() {
        byte[][] bArr = new byte[10][];
        int i = 0;
        while (i < 10) {
            int i2 = i + 1;
            byte[] bArr2 = new byte[i2];
            Arrays.fill(bArr2, (byte) (i + 65));
            bArr[i] = bArr2;
            i = i2;
        }
        return bArr;
    }

    private static Vector vectorOfOne(Object obj) {
        Vector vector = new Vector(1);
        vector.addElement(obj);
        return vector;
    }

    public static int getCipherType(int i) {
        int encryptionAlgorithm = getEncryptionAlgorithm(i);
        switch (encryptionAlgorithm) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
            case 14:
                return 1;
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                return 2;
            default:
                switch (encryptionAlgorithm) {
                    case 102:
                    case 103:
                    case 104:
                        return 2;
                    default:
                        throw new TlsFatalAlert(80);
                }
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
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
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:50)
        */
    public static int getEncryptionAlgorithm(int r4) {
        /*
        // Method dump skipped, instructions count: 680
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsUtils.getEncryptionAlgorithm(int):int");
    }

    public static int getKeyExchangeAlgorithm(int i) {
        switch (i) {
            case 1:
            case 2:
                return 1;
            default:
                switch (i) {
                    case 4:
                    case 5:
                        return 1;
                    default:
                        switch (i) {
                            case 44:
                                return 13;
                            case 45:
                                return 14;
                            case 46:
                                return 15;
                            case 47:
                                return 1;
                            case 48:
                                return 7;
                            case 49:
                                return 9;
                            case 50:
                                return 3;
                            case 51:
                                return 5;
                            default:
                                switch (i) {
                                    case 53:
                                        return 1;
                                    case 54:
                                        return 7;
                                    case 55:
                                        return 9;
                                    case 56:
                                        return 3;
                                    case 57:
                                        return 5;
                                    default:
                                        switch (i) {
                                            case 59:
                                            case 60:
                                            case 61:
                                            case 65:
                                                return 1;
                                            case 62:
                                            case 66:
                                                return 7;
                                            case 63:
                                            case 67:
                                                return 9;
                                            case 64:
                                            case 68:
                                                return 3;
                                            case 69:
                                                return 5;
                                            default:
                                                switch (i) {
                                                    case 103:
                                                    case 107:
                                                        return 5;
                                                    case 104:
                                                        return 7;
                                                    case 105:
                                                        return 9;
                                                    case 106:
                                                        return 3;
                                                    default:
                                                        switch (i) {
                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA:
                                                                return 1;
                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA:
                                                                return 7;
                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA:
                                                                return 9;
                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA:
                                                                return 3;
                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA:
                                                                return 5;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.TLS_PSK_WITH_RC4_128_SHA:
                                                                    case CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA:
                                                                        return 13;
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA:
                                                                        return 14;
                                                                    case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA:
                                                                    case CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA:
                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA:
                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA:
                                                                        return 15;
                                                                    case CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA:
                                                                        return 1;
                                                                    case CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA:
                                                                        return 7;
                                                                    case CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA:
                                                                        return 9;
                                                                    case CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA:
                                                                        return 3;
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA:
                                                                        return 5;
                                                                    default:
                                                                        switch (i) {
                                                                            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384:
                                                                                return 1;
                                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384:
                                                                                return 5;
                                                                            case CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384:
                                                                                return 9;
                                                                            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384:
                                                                                return 3;
                                                                            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384:
                                                                                return 7;
                                                                            default:
                                                                                switch (i) {
                                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256:
                                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384:
                                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256:
                                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384:
                                                                                    case CipherSuite.TLS_PSK_WITH_NULL_SHA256:
                                                                                    case CipherSuite.TLS_PSK_WITH_NULL_SHA384:
                                                                                        return 13;
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384:
                                                                                        return 14;
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384:
                                                                                        return 15;
                                                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                        return 1;
                                                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                        return 7;
                                                                                    case 188:
                                                                                        return 9;
                                                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                        return 3;
                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                        return 5;
                                                                                    default:
                                                                                        switch (i) {
                                                                                            case 192:
                                                                                                return 1;
                                                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256:
                                                                                                return 7;
                                                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256:
                                                                                                return 9;
                                                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256:
                                                                                                return 3;
                                                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256:
                                                                                                return 5;
                                                                                            default:
                                                                                                switch (i) {
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384:
                                                                                                        return 16;
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384:
                                                                                                        return 17;
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384:
                                                                                                        return 18;
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384:
                                                                                                        return 19;
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA:
                                                                                                        return 20;
                                                                                                    case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA:
                                                                                                        return 21;
                                                                                                    case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA:
                                                                                                        return 23;
                                                                                                    case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA:
                                                                                                        return 22;
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384:
                                                                                                        return 24;
                                                                                                    default:
                                                                                                        switch (i) {
                                                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                return 17;
                                                                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                return 16;
                                                                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                return 19;
                                                                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                return 18;
                                                                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                return 1;
                                                                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                return 5;
                                                                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                return 9;
                                                                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                return 3;
                                                                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                return 7;
                                                                                                            default:
                                                                                                                switch (i) {
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8:
                                                                                                                        return 17;
                                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                        return 16;
                                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                        return 19;
                                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                        return 18;
                                                                                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8:
                                                                                                                        return 13;
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8:
                                                                                                                        return 14;
                                                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                        return 15;
                                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                        return 24;
                                                                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8:
                                                                                                                        return 1;
                                                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8:
                                                                                                                        return 5;
                                                                                                                    default:
                                                                                                                        switch (i) {
                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                                return 19;
                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                                return 17;
                                                                                                                            case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                                return 5;
                                                                                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                                return 13;
                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                                return 24;
                                                                                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                                return 14;
                                                                                                                            case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                                return 15;
                                                                                                                            default:
                                                                                                                                switch (i) {
                                                                                                                                    case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB:
                                                                                                                                    case 65281:
                                                                                                                                        return 5;
                                                                                                                                    case 65282:
                                                                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB:
                                                                                                                                        return 19;
                                                                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB:
                                                                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB:
                                                                                                                                        return 17;
                                                                                                                                    default:
                                                                                                                                        switch (i) {
                                                                                                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB:
                                                                                                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB:
                                                                                                                                                return 13;
                                                                                                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB:
                                                                                                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB:
                                                                                                                                                return 14;
                                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB:
                                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB:
                                                                                                                                                return 24;
                                                                                                                                            default:
                                                                                                                                                switch (i) {
                                                                                                                                                    case 10:
                                                                                                                                                        return 1;
                                                                                                                                                    case 13:
                                                                                                                                                        return 7;
                                                                                                                                                    case 16:
                                                                                                                                                        return 9;
                                                                                                                                                    case 19:
                                                                                                                                                        return 3;
                                                                                                                                                    case 22:
                                                                                                                                                        return 5;
                                                                                                                                                    default:
                                                                                                                                                        throw new TlsFatalAlert(80);
                                                                                                                                                }
                                                                                                                                        }
                                                                                                                                }
                                                                                                                        }
                                                                                                                }
                                                                                                        }
                                                                                                }
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static int getMACAlgorithm(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                switch (i) {
                    case 4:
                        return 1;
                    case 5:
                        return 2;
                    default:
                        switch (i) {
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                                return 2;
                            default:
                                switch (i) {
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                        return 2;
                                    default:
                                        switch (i) {
                                            case 59:
                                            case 60:
                                            case 61:
                                            case 62:
                                            case 63:
                                            case 64:
                                                return 3;
                                            case 65:
                                            case 66:
                                            case 67:
                                            case 68:
                                            case 69:
                                                return 2;
                                            default:
                                                switch (i) {
                                                    case 103:
                                                    case 104:
                                                    case 105:
                                                    case 106:
                                                    case 107:
                                                        return 3;
                                                    default:
                                                        switch (i) {
                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA:
                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA:
                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA:
                                                                return 2;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.TLS_PSK_WITH_RC4_128_SHA:
                                                                    case CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA:
                                                                    case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA:
                                                                    case CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA:
                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA:
                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA:
                                                                    case CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA:
                                                                    case CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA:
                                                                    case CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA:
                                                                    case CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA:
                                                                        return 2;
                                                                    default:
                                                                        switch (i) {
                                                                            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384:
                                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384:
                                                                            case CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384:
                                                                            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384:
                                                                            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256:
                                                                            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384:
                                                                                return 0;
                                                                            default:
                                                                                switch (i) {
                                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256:
                                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384:
                                                                                        return 0;
                                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256:
                                                                                    case CipherSuite.TLS_PSK_WITH_NULL_SHA256:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256:
                                                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                    case 188:
                                                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                        return 3;
                                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384:
                                                                                    case CipherSuite.TLS_PSK_WITH_NULL_SHA384:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384:
                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384:
                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384:
                                                                                        return 4;
                                                                                    default:
                                                                                        switch (i) {
                                                                                            case 192:
                                                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256:
                                                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256:
                                                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256:
                                                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256:
                                                                                                return 3;
                                                                                            default:
                                                                                                switch (i) {
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_RC4_128_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA:
                                                                                                        return 2;
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256:
                                                                                                        return 3;
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384:
                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384:
                                                                                                        return 4;
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256:
                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256:
                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256:
                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384:
                                                                                                        return 0;
                                                                                                    default:
                                                                                                        switch (i) {
                                                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                                return 3;
                                                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                return 4;
                                                                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                return 0;
                                                                                                            default:
                                                                                                                switch (i) {
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                                                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                                                                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8:
                                                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8:
                                                                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8:
                                                                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8:
                                                                                                                        return 0;
                                                                                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                                                                                                                        return 3;
                                                                                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                                                                                                                        return 4;
                                                                                                                    default:
                                                                                                                        switch (i) {
                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                            case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                            case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                                                                                return 0;
                                                                                                                            default:
                                                                                                                                switch (i) {
                                                                                                                                    case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB:
                                                                                                                                    case 65281:
                                                                                                                                    case 65282:
                                                                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB:
                                                                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB:
                                                                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB:
                                                                                                                                        return 0;
                                                                                                                                    default:
                                                                                                                                        switch (i) {
                                                                                                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB:
                                                                                                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB:
                                                                                                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB:
                                                                                                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB:
                                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB:
                                                                                                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB:
                                                                                                                                                return 0;
                                                                                                                                            default:
                                                                                                                                                switch (i) {
                                                                                                                                                    case 10:
                                                                                                                                                    case 13:
                                                                                                                                                    case 16:
                                                                                                                                                    case 19:
                                                                                                                                                    case 22:
                                                                                                                                                        return 2;
                                                                                                                                                    default:
                                                                                                                                                        throw new TlsFatalAlert(80);
                                                                                                                                                }
                                                                                                                                        }
                                                                                                                                }
                                                                                                                        }
                                                                                                                }
                                                                                                        }
                                                                                                }
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static ProtocolVersion getMinimumVersion(int i) {
        switch (i) {
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256:
            case 188:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256:
            case 192:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8:
            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256:
            case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
            case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256:
            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
            case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256:
            case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB:
            case 65281:
            case 65282:
            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB:
            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB:
            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB:
            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB:
            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB:
            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB:
            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB:
            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB:
            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB:
                return ProtocolVersion.TLSv12;
            default:
                return ProtocolVersion.SSLv3;
        }
    }

    public static boolean isAEADCipherSuite(int i) {
        return 2 == getCipherType(i);
    }

    public static boolean isBlockCipherSuite(int i) {
        return 1 == getCipherType(i);
    }

    public static boolean isStreamCipherSuite(int i) {
        return getCipherType(i) == 0;
    }

    public static boolean isValidCipherSuiteForVersion(int i, ProtocolVersion protocolVersion) {
        return getMinimumVersion(i).isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }
}
