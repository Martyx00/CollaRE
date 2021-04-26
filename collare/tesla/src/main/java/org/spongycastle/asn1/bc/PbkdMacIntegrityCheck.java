package org.spongycastle.asn1.bc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.pkcs.KeyDerivationFunc;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.util.Arrays;

public class PbkdMacIntegrityCheck extends ASN1Object {
    private final ASN1OctetString mac;
    private final AlgorithmIdentifier macAlgorithm;
    private final KeyDerivationFunc pbkdAlgorithm;

    public PbkdMacIntegrityCheck(AlgorithmIdentifier algorithmIdentifier, KeyDerivationFunc keyDerivationFunc, byte[] bArr) {
        this.macAlgorithm = algorithmIdentifier;
        this.pbkdAlgorithm = keyDerivationFunc;
        this.mac = new DEROctetString(Arrays.clone(bArr));
    }

    private PbkdMacIntegrityCheck(ASN1Sequence aSN1Sequence) {
        this.macAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.pbkdAlgorithm = KeyDerivationFunc.getInstance(aSN1Sequence.getObjectAt(1));
        this.mac = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static PbkdMacIntegrityCheck getInstance(Object obj) {
        if (obj instanceof PbkdMacIntegrityCheck) {
            return (PbkdMacIntegrityCheck) obj;
        }
        if (obj != null) {
            return new PbkdMacIntegrityCheck(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getMacAlgorithm() {
        return this.macAlgorithm;
    }

    public KeyDerivationFunc getPbkdAlgorithm() {
        return this.pbkdAlgorithm;
    }

    public byte[] getMac() {
        return Arrays.clone(this.mac.getOctets());
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.macAlgorithm);
        aSN1EncodableVector.add(this.pbkdAlgorithm);
        aSN1EncodableVector.add(this.mac);
        return new DERSequence(aSN1EncodableVector);
    }
}
