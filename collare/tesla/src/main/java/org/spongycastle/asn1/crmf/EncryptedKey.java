package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.cms.EnvelopedData;

public class EncryptedKey extends ASN1Object implements ASN1Choice {
    private EncryptedValue encryptedValue;
    private EnvelopedData envelopedData;

    public static EncryptedKey getInstance(Object obj) {
        if (obj instanceof EncryptedKey) {
            return (EncryptedKey) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new EncryptedKey(EnvelopedData.getInstance((ASN1TaggedObject) obj, false));
        }
        if (obj instanceof EncryptedValue) {
            return new EncryptedKey((EncryptedValue) obj);
        }
        return new EncryptedKey(EncryptedValue.getInstance(obj));
    }

    public EncryptedKey(EnvelopedData envelopedData2) {
        this.envelopedData = envelopedData2;
    }

    public EncryptedKey(EncryptedValue encryptedValue2) {
        this.encryptedValue = encryptedValue2;
    }

    public boolean isEncryptedValue() {
        return this.encryptedValue != null;
    }

    public ASN1Encodable getValue() {
        EncryptedValue encryptedValue2 = this.encryptedValue;
        if (encryptedValue2 != null) {
            return encryptedValue2;
        }
        return this.envelopedData;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        EncryptedValue encryptedValue2 = this.encryptedValue;
        if (encryptedValue2 != null) {
            return encryptedValue2.toASN1Primitive();
        }
        return new DERTaggedObject(false, 0, this.envelopedData);
    }
}
