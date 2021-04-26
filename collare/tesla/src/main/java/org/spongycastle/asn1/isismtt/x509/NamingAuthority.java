package org.spongycastle.asn1.isismtt.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.isismtt.ISISMTTObjectIdentifiers;
import org.spongycastle.asn1.x500.DirectoryString;

public class NamingAuthority extends ASN1Object {
    public static final ASN1ObjectIdentifier id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern = new ASN1ObjectIdentifier(ISISMTTObjectIdentifiers.id_isismtt_at_namingAuthorities + ".1");
    private ASN1ObjectIdentifier namingAuthorityId;
    private DirectoryString namingAuthorityText;
    private String namingAuthorityUrl;

    public static NamingAuthority getInstance(Object obj) {
        if (obj == null || (obj instanceof NamingAuthority)) {
            return (NamingAuthority) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new NamingAuthority((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static NamingAuthority getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    private NamingAuthority(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() <= 3) {
            Enumeration objects = aSN1Sequence.getObjects();
            if (objects.hasMoreElements()) {
                ASN1Encodable aSN1Encodable = (ASN1Encodable) objects.nextElement();
                if (aSN1Encodable instanceof ASN1ObjectIdentifier) {
                    this.namingAuthorityId = (ASN1ObjectIdentifier) aSN1Encodable;
                } else if (aSN1Encodable instanceof DERIA5String) {
                    this.namingAuthorityUrl = DERIA5String.getInstance(aSN1Encodable).getString();
                } else if (aSN1Encodable instanceof ASN1String) {
                    this.namingAuthorityText = DirectoryString.getInstance(aSN1Encodable);
                } else {
                    throw new IllegalArgumentException("Bad object encountered: " + aSN1Encodable.getClass());
                }
            }
            if (objects.hasMoreElements()) {
                ASN1Encodable aSN1Encodable2 = (ASN1Encodable) objects.nextElement();
                if (aSN1Encodable2 instanceof DERIA5String) {
                    this.namingAuthorityUrl = DERIA5String.getInstance(aSN1Encodable2).getString();
                } else if (aSN1Encodable2 instanceof ASN1String) {
                    this.namingAuthorityText = DirectoryString.getInstance(aSN1Encodable2);
                } else {
                    throw new IllegalArgumentException("Bad object encountered: " + aSN1Encodable2.getClass());
                }
            }
            if (objects.hasMoreElements()) {
                ASN1Encodable aSN1Encodable3 = (ASN1Encodable) objects.nextElement();
                if (aSN1Encodable3 instanceof ASN1String) {
                    this.namingAuthorityText = DirectoryString.getInstance(aSN1Encodable3);
                    return;
                }
                throw new IllegalArgumentException("Bad object encountered: " + aSN1Encodable3.getClass());
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public ASN1ObjectIdentifier getNamingAuthorityId() {
        return this.namingAuthorityId;
    }

    public DirectoryString getNamingAuthorityText() {
        return this.namingAuthorityText;
    }

    public String getNamingAuthorityUrl() {
        return this.namingAuthorityUrl;
    }

    public NamingAuthority(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, DirectoryString directoryString) {
        this.namingAuthorityId = aSN1ObjectIdentifier;
        this.namingAuthorityUrl = str;
        this.namingAuthorityText = directoryString;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.namingAuthorityId;
        if (aSN1ObjectIdentifier != null) {
            aSN1EncodableVector.add(aSN1ObjectIdentifier);
        }
        String str = this.namingAuthorityUrl;
        if (str != null) {
            aSN1EncodableVector.add(new DERIA5String(str, true));
        }
        DirectoryString directoryString = this.namingAuthorityText;
        if (directoryString != null) {
            aSN1EncodableVector.add(directoryString);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
