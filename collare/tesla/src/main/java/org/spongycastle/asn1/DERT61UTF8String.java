package org.spongycastle.asn1;

import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class DERT61UTF8String extends ASN1Primitive implements ASN1String {
    private byte[] string;

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public static DERT61UTF8String getInstance(Object obj) {
        if (obj instanceof DERT61String) {
            return new DERT61UTF8String(((DERT61String) obj).getOctets());
        }
        if (obj == null || (obj instanceof DERT61UTF8String)) {
            return (DERT61UTF8String) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return new DERT61UTF8String(((DERT61String) fromByteArray((byte[]) obj)).getOctets());
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERT61UTF8String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof DERT61String) || (object instanceof DERT61UTF8String)) {
            return getInstance(object);
        }
        return new DERT61UTF8String(ASN1OctetString.getInstance(object).getOctets());
    }

    public DERT61UTF8String(byte[] bArr) {
        this.string = bArr;
    }

    public DERT61UTF8String(String str) {
        this(Strings.toUTF8ByteArray(str));
    }

    @Override // org.spongycastle.asn1.ASN1String
    public String getString() {
        return Strings.fromUTF8ByteArray(this.string);
    }

    public String toString() {
        return getString();
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.string.length) + 1 + this.string.length;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.writeEncoded(20, this.string);
    }

    public byte[] getOctets() {
        return Arrays.clone(this.string);
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERT61UTF8String)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((DERT61UTF8String) aSN1Primitive).string);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.string);
    }
}
