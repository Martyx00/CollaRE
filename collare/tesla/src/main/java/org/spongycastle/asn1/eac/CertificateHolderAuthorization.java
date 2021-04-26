package org.spongycastle.asn1.eac;

import java.util.Hashtable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.util.Integers;

public class CertificateHolderAuthorization extends ASN1Object {
    static BidirectionalMap AuthorizationRole = new BidirectionalMap();
    public static final int CVCA = 192;
    public static final int DV_DOMESTIC = 128;
    public static final int DV_FOREIGN = 64;
    public static final int IS = 0;
    public static final int RADG3 = 1;
    public static final int RADG4 = 2;
    static Hashtable ReverseMap = new Hashtable();
    static Hashtable RightsDecodeMap = new Hashtable();
    public static final ASN1ObjectIdentifier id_role_EAC = EACObjectIdentifiers.bsi_de.branch("3.1.2.1");
    DERApplicationSpecific accessRights;
    ASN1ObjectIdentifier oid;

    static {
        RightsDecodeMap.put(Integers.valueOf(2), "RADG4");
        RightsDecodeMap.put(Integers.valueOf(1), "RADG3");
        AuthorizationRole.put(Integers.valueOf(192), "CVCA");
        AuthorizationRole.put(Integers.valueOf(128), "DV_DOMESTIC");
        AuthorizationRole.put(Integers.valueOf(64), "DV_FOREIGN");
        AuthorizationRole.put(Integers.valueOf(0), "IS");
    }

    public static String getRoleDescription(int i) {
        return (String) AuthorizationRole.get(Integers.valueOf(i));
    }

    public static int getFlag(String str) {
        Integer num = (Integer) AuthorizationRole.getReverse(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("Unknown value " + str);
    }

    private void setPrivateData(ASN1InputStream aSN1InputStream) {
        ASN1Primitive readObject = aSN1InputStream.readObject();
        if (readObject instanceof ASN1ObjectIdentifier) {
            this.oid = (ASN1ObjectIdentifier) readObject;
            ASN1Primitive readObject2 = aSN1InputStream.readObject();
            if (readObject2 instanceof DERApplicationSpecific) {
                this.accessRights = (DERApplicationSpecific) readObject2;
                return;
            }
            throw new IllegalArgumentException("No access rights in CerticateHolderAuthorization");
        }
        throw new IllegalArgumentException("no Oid in CerticateHolderAuthorization");
    }

    public CertificateHolderAuthorization(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        setOid(aSN1ObjectIdentifier);
        setAccessRights((byte) i);
    }

    public CertificateHolderAuthorization(DERApplicationSpecific dERApplicationSpecific) {
        if (dERApplicationSpecific.getApplicationTag() == 76) {
            setPrivateData(new ASN1InputStream(dERApplicationSpecific.getContents()));
        }
    }

    public int getAccessRights() {
        return this.accessRights.getContents()[0] & 255;
    }

    private void setAccessRights(byte b2) {
        this.accessRights = new DERApplicationSpecific(EACTags.getTag(83), new byte[]{b2});
    }

    public ASN1ObjectIdentifier getOid() {
        return this.oid;
    }

    private void setOid(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.oid = aSN1ObjectIdentifier;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.oid);
        aSN1EncodableVector.add(this.accessRights);
        return new DERApplicationSpecific(76, aSN1EncodableVector);
    }
}
