package org.spongycastle.asn1;

import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class ASN1Enumerated extends ASN1Primitive {
    private static ASN1Enumerated[] cache = new ASN1Enumerated[12];
    private final byte[] bytes;

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public static ASN1Enumerated getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Enumerated)) {
            return (ASN1Enumerated) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1Enumerated) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Enumerated getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof ASN1Enumerated)) {
            return getInstance(object);
        }
        return fromOctetString(((ASN1OctetString) object).getOctets());
    }

    public ASN1Enumerated(int i) {
        this.bytes = BigInteger.valueOf((long) i).toByteArray();
    }

    public ASN1Enumerated(BigInteger bigInteger) {
        this.bytes = bigInteger.toByteArray();
    }

    public ASN1Enumerated(byte[] bArr) {
        if (bArr.length > 1) {
            if (bArr[0] == 0 && (bArr[1] & 128) == 0) {
                throw new IllegalArgumentException("malformed enumerated");
            } else if (bArr[0] == -1 && (bArr[1] & 128) != 0) {
                throw new IllegalArgumentException("malformed enumerated");
            }
        }
        this.bytes = Arrays.clone(bArr);
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.bytes.length) + 1 + this.bytes.length;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.writeEncoded(10, this.bytes);
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Enumerated)) {
            return false;
        }
        return Arrays.areEqual(this.bytes, ((ASN1Enumerated) aSN1Primitive).bytes);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }

    static ASN1Enumerated fromOctetString(byte[] bArr) {
        if (bArr.length > 1) {
            return new ASN1Enumerated(bArr);
        }
        if (bArr.length != 0) {
            int i = bArr[0] & 255;
            ASN1Enumerated[] aSN1EnumeratedArr = cache;
            if (i >= aSN1EnumeratedArr.length) {
                return new ASN1Enumerated(Arrays.clone(bArr));
            }
            ASN1Enumerated aSN1Enumerated = aSN1EnumeratedArr[i];
            if (aSN1Enumerated != null) {
                return aSN1Enumerated;
            }
            ASN1Enumerated aSN1Enumerated2 = new ASN1Enumerated(Arrays.clone(bArr));
            aSN1EnumeratedArr[i] = aSN1Enumerated2;
            return aSN1Enumerated2;
        }
        throw new IllegalArgumentException("ENUMERATED has zero length");
    }
}
