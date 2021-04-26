package org.spongycastle.pqc.jcajce.provider.util;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;

public class KeyUtil {
    public static byte[] getEncodedSubjectPublicKeyInfo(AlgorithmIdentifier algorithmIdentifier, ASN1Encodable aSN1Encodable) {
        try {
            return getEncodedSubjectPublicKeyInfo(new SubjectPublicKeyInfo(algorithmIdentifier, aSN1Encodable));
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        try {
            return getEncodedSubjectPublicKeyInfo(new SubjectPublicKeyInfo(algorithmIdentifier, bArr));
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        try {
            return subjectPublicKeyInfo.getEncoded(ASN1Encoding.DER);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getEncodedPrivateKeyInfo(AlgorithmIdentifier algorithmIdentifier, ASN1Encodable aSN1Encodable) {
        try {
            return getEncodedPrivateKeyInfo(new PrivateKeyInfo(algorithmIdentifier, aSN1Encodable.toASN1Primitive()));
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getEncodedPrivateKeyInfo(PrivateKeyInfo privateKeyInfo) {
        try {
            return privateKeyInfo.getEncoded(ASN1Encoding.DER);
        } catch (Exception unused) {
            return null;
        }
    }
}
