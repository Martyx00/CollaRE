package org.spongycastle.asn1.bc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;

public class SecretKeyData extends ASN1Object {
    private final ASN1ObjectIdentifier keyAlgorithm;
    private final ASN1OctetString keyBytes;

    public SecretKeyData(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        this.keyAlgorithm = aSN1ObjectIdentifier;
        this.keyBytes = new DEROctetString(Arrays.clone(bArr));
    }

    private SecretKeyData(ASN1Sequence aSN1Sequence) {
        this.keyAlgorithm = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.keyBytes = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static SecretKeyData getInstance(Object obj) {
        if (obj instanceof SecretKeyData) {
            return (SecretKeyData) obj;
        }
        if (obj != null) {
            return new SecretKeyData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getKeyBytes() {
        return Arrays.clone(this.keyBytes.getOctets());
    }

    public ASN1ObjectIdentifier getKeyAlgorithm() {
        return this.keyAlgorithm;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.keyAlgorithm);
        aSN1EncodableVector.add(this.keyBytes);
        return new DERSequence(aSN1EncodableVector);
    }
}
