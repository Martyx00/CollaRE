package org.spongycastle.asn1.bc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.spongycastle.asn1.x509.Certificate;

public class EncryptedPrivateKeyData extends ASN1Object {
    private final Certificate[] certificateChain;
    private final EncryptedPrivateKeyInfo encryptedPrivateKeyInfo;

    public EncryptedPrivateKeyData(EncryptedPrivateKeyInfo encryptedPrivateKeyInfo2, Certificate[] certificateArr) {
        this.encryptedPrivateKeyInfo = encryptedPrivateKeyInfo2;
        this.certificateChain = new Certificate[certificateArr.length];
        System.arraycopy(certificateArr, 0, this.certificateChain, 0, certificateArr.length);
    }

    private EncryptedPrivateKeyData(ASN1Sequence aSN1Sequence) {
        int i = 0;
        this.encryptedPrivateKeyInfo = EncryptedPrivateKeyInfo.getInstance(aSN1Sequence.getObjectAt(0));
        ASN1Sequence instance = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        this.certificateChain = new Certificate[instance.size()];
        while (true) {
            Certificate[] certificateArr = this.certificateChain;
            if (i != certificateArr.length) {
                certificateArr[i] = Certificate.getInstance(instance.getObjectAt(i));
                i++;
            } else {
                return;
            }
        }
    }

    public static EncryptedPrivateKeyData getInstance(Object obj) {
        if (obj instanceof EncryptedPrivateKeyData) {
            return (EncryptedPrivateKeyData) obj;
        }
        if (obj != null) {
            return new EncryptedPrivateKeyData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public Certificate[] getCertificateChain() {
        Certificate[] certificateArr = this.certificateChain;
        Certificate[] certificateArr2 = new Certificate[certificateArr.length];
        System.arraycopy(certificateArr, 0, certificateArr2, 0, certificateArr.length);
        return certificateArr2;
    }

    public EncryptedPrivateKeyInfo getEncryptedPrivateKeyInfo() {
        return this.encryptedPrivateKeyInfo;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.encryptedPrivateKeyInfo);
        aSN1EncodableVector.add(new DERSequence(this.certificateChain));
        return new DERSequence(aSN1EncodableVector);
    }
}
