package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x500.X500Name;

public class IssuerSerial extends ASN1Object {
    GeneralNames issuer;
    DERBitString issuerUID;
    ASN1Integer serial;

    public static IssuerSerial getInstance(Object obj) {
        if (obj instanceof IssuerSerial) {
            return (IssuerSerial) obj;
        }
        if (obj != null) {
            return new IssuerSerial(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static IssuerSerial getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    private IssuerSerial(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2 || aSN1Sequence.size() == 3) {
            this.issuer = GeneralNames.getInstance(aSN1Sequence.getObjectAt(0));
            this.serial = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
            if (aSN1Sequence.size() == 3) {
                this.issuerUID = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public IssuerSerial(X500Name x500Name, BigInteger bigInteger) {
        this(new GeneralNames(new GeneralName(x500Name)), new ASN1Integer(bigInteger));
    }

    public IssuerSerial(GeneralNames generalNames, BigInteger bigInteger) {
        this(generalNames, new ASN1Integer(bigInteger));
    }

    public IssuerSerial(GeneralNames generalNames, ASN1Integer aSN1Integer) {
        this.issuer = generalNames;
        this.serial = aSN1Integer;
    }

    public GeneralNames getIssuer() {
        return this.issuer;
    }

    public ASN1Integer getSerial() {
        return this.serial;
    }

    public DERBitString getIssuerUID() {
        return this.issuerUID;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.issuer);
        aSN1EncodableVector.add(this.serial);
        DERBitString dERBitString = this.issuerUID;
        if (dERBitString != null) {
            aSN1EncodableVector.add(dERBitString);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
