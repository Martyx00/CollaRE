package org.spongycastle.asn1.x509.sigi;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.DirectoryString;

public class PersonalData extends ASN1Object {
    private ASN1GeneralizedTime dateOfBirth;
    private String gender;
    private BigInteger nameDistinguisher;
    private NameOrPseudonym nameOrPseudonym;
    private DirectoryString placeOfBirth;
    private DirectoryString postalAddress;

    public static PersonalData getInstance(Object obj) {
        if (obj == null || (obj instanceof PersonalData)) {
            return (PersonalData) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new PersonalData((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    private PersonalData(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() >= 1) {
            Enumeration objects = aSN1Sequence.getObjects();
            this.nameOrPseudonym = NameOrPseudonym.getInstance(objects.nextElement());
            while (objects.hasMoreElements()) {
                ASN1TaggedObject instance = ASN1TaggedObject.getInstance(objects.nextElement());
                switch (instance.getTagNo()) {
                    case 0:
                        this.nameDistinguisher = ASN1Integer.getInstance(instance, false).getValue();
                        break;
                    case 1:
                        this.dateOfBirth = ASN1GeneralizedTime.getInstance(instance, false);
                        break;
                    case 2:
                        this.placeOfBirth = DirectoryString.getInstance(instance, true);
                        break;
                    case 3:
                        this.gender = DERPrintableString.getInstance(instance, false).getString();
                        break;
                    case 4:
                        this.postalAddress = DirectoryString.getInstance(instance, true);
                        break;
                    default:
                        throw new IllegalArgumentException("Bad tag number: " + instance.getTagNo());
                }
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public PersonalData(NameOrPseudonym nameOrPseudonym2, BigInteger bigInteger, ASN1GeneralizedTime aSN1GeneralizedTime, DirectoryString directoryString, String str, DirectoryString directoryString2) {
        this.nameOrPseudonym = nameOrPseudonym2;
        this.dateOfBirth = aSN1GeneralizedTime;
        this.gender = str;
        this.nameDistinguisher = bigInteger;
        this.postalAddress = directoryString2;
        this.placeOfBirth = directoryString;
    }

    public NameOrPseudonym getNameOrPseudonym() {
        return this.nameOrPseudonym;
    }

    public BigInteger getNameDistinguisher() {
        return this.nameDistinguisher;
    }

    public ASN1GeneralizedTime getDateOfBirth() {
        return this.dateOfBirth;
    }

    public DirectoryString getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public String getGender() {
        return this.gender;
    }

    public DirectoryString getPostalAddress() {
        return this.postalAddress;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.nameOrPseudonym);
        BigInteger bigInteger = this.nameDistinguisher;
        if (bigInteger != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new ASN1Integer(bigInteger)));
        }
        ASN1GeneralizedTime aSN1GeneralizedTime = this.dateOfBirth;
        if (aSN1GeneralizedTime != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, aSN1GeneralizedTime));
        }
        DirectoryString directoryString = this.placeOfBirth;
        if (directoryString != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, directoryString));
        }
        String str = this.gender;
        if (str != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 3, new DERPrintableString(str, true)));
        }
        DirectoryString directoryString2 = this.postalAddress;
        if (directoryString2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 4, directoryString2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
