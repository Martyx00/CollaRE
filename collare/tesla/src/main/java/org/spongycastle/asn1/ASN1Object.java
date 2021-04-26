package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import org.spongycastle.util.Encodable;

public abstract class ASN1Object implements ASN1Encodable, Encodable {
    @Override // org.spongycastle.asn1.ASN1Encodable
    public abstract ASN1Primitive toASN1Primitive();

    @Override // org.spongycastle.util.Encodable
    public byte[] getEncoded() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ASN1OutputStream(byteArrayOutputStream).writeObject(this);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] getEncoded(String str) {
        if (str.equals(ASN1Encoding.DER)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new DEROutputStream(byteArrayOutputStream).writeObject(this);
            return byteArrayOutputStream.toByteArray();
        } else if (!str.equals(ASN1Encoding.DL)) {
            return getEncoded();
        } else {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            new DLOutputStream(byteArrayOutputStream2).writeObject(this);
            return byteArrayOutputStream2.toByteArray();
        }
    }

    public int hashCode() {
        return toASN1Primitive().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ASN1Encodable)) {
            return false;
        }
        return toASN1Primitive().equals(((ASN1Encodable) obj).toASN1Primitive());
    }

    public ASN1Primitive toASN1Object() {
        return toASN1Primitive();
    }

    protected static boolean hasEncodedTagValue(Object obj, int i) {
        return (obj instanceof byte[]) && ((byte[]) obj)[0] == i;
    }
}
