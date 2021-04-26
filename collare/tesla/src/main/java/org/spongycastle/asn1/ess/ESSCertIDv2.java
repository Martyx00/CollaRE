package org.spongycastle.asn1.ess;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.IssuerSerial;
import org.spongycastle.util.Arrays;

public class ESSCertIDv2 extends ASN1Object {
    private static final AlgorithmIdentifier DEFAULT_ALG_ID = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
    private byte[] certHash;
    private AlgorithmIdentifier hashAlgorithm;
    private IssuerSerial issuerSerial;

    public static ESSCertIDv2 getInstance(Object obj) {
        if (obj instanceof ESSCertIDv2) {
            return (ESSCertIDv2) obj;
        }
        if (obj != null) {
            return new ESSCertIDv2(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private ESSCertIDv2(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() <= 3) {
            int i = 0;
            if (aSN1Sequence.getObjectAt(0) instanceof ASN1OctetString) {
                this.hashAlgorithm = DEFAULT_ALG_ID;
            } else {
                this.hashAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0).toASN1Primitive());
                i = 1;
            }
            int i2 = i + 1;
            this.certHash = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i).toASN1Primitive()).getOctets();
            if (aSN1Sequence.size() > i2) {
                this.issuerSerial = IssuerSerial.getInstance(aSN1Sequence.getObjectAt(i2));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public ESSCertIDv2(byte[] bArr) {
        this(null, bArr, null);
    }

    public ESSCertIDv2(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this(algorithmIdentifier, bArr, null);
    }

    public ESSCertIDv2(byte[] bArr, IssuerSerial issuerSerial2) {
        this(null, bArr, issuerSerial2);
    }

    public ESSCertIDv2(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, IssuerSerial issuerSerial2) {
        if (algorithmIdentifier == null) {
            this.hashAlgorithm = DEFAULT_ALG_ID;
        } else {
            this.hashAlgorithm = algorithmIdentifier;
        }
        this.certHash = Arrays.clone(bArr);
        this.issuerSerial = issuerSerial2;
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public byte[] getCertHash() {
        return Arrays.clone(this.certHash);
    }

    public IssuerSerial getIssuerSerial() {
        return this.issuerSerial;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (!this.hashAlgorithm.equals(DEFAULT_ALG_ID)) {
            aSN1EncodableVector.add(this.hashAlgorithm);
        }
        aSN1EncodableVector.add(new DEROctetString(this.certHash).toASN1Primitive());
        IssuerSerial issuerSerial2 = this.issuerSerial;
        if (issuerSerial2 != null) {
            aSN1EncodableVector.add(issuerSerial2);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
