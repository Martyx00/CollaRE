package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class BEROctetString extends ASN1OctetString {
    private static final int MAX_LENGTH = 1000;
    private ASN1OctetString[] octs;

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return true;
    }

    private static byte[] toBytes(ASN1OctetString[] aSN1OctetStringArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != aSN1OctetStringArr.length; i++) {
            try {
                byteArrayOutputStream.write(((DEROctetString) aSN1OctetStringArr[i]).getOctets());
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException(aSN1OctetStringArr[i].getClass().getName() + " found in input should only contain DEROctetString");
            } catch (IOException e) {
                throw new IllegalArgumentException("exception converting octets " + e.toString());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public BEROctetString(byte[] bArr) {
        super(bArr);
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr) {
        super(toBytes(aSN1OctetStringArr));
        this.octs = aSN1OctetStringArr;
    }

    @Override // org.spongycastle.asn1.ASN1OctetString
    public byte[] getOctets() {
        return this.string;
    }

    public Enumeration getObjects() {
        if (this.octs == null) {
            return generateOcts().elements();
        }
        return new Enumeration() {
            /* class org.spongycastle.asn1.BEROctetString.AnonymousClass1 */
            int counter = 0;

            public boolean hasMoreElements() {
                return this.counter < BEROctetString.this.octs.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                ASN1OctetString[] aSN1OctetStringArr = BEROctetString.this.octs;
                int i = this.counter;
                this.counter = i + 1;
                return aSN1OctetStringArr[i];
            }
        };
    }

    private Vector generateOcts() {
        Vector vector = new Vector();
        int i = 0;
        while (i < this.string.length) {
            int i2 = i + MAX_LENGTH;
            byte[] bArr = new byte[((i2 > this.string.length ? this.string.length : i2) - i)];
            System.arraycopy(this.string, i, bArr, 0, bArr.length);
            vector.addElement(new DEROctetString(bArr));
            i = i2;
        }
        return vector;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        Enumeration objects = getObjects();
        int i = 0;
        while (objects.hasMoreElements()) {
            i += ((ASN1Encodable) objects.nextElement()).toASN1Primitive().encodedLength();
        }
        return i + 2 + 2;
    }

    @Override // org.spongycastle.asn1.ASN1OctetString, org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.write(36);
        aSN1OutputStream.write(128);
        Enumeration objects = getObjects();
        while (objects.hasMoreElements()) {
            aSN1OutputStream.writeObject((ASN1Encodable) objects.nextElement());
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    static BEROctetString fromSequence(ASN1Sequence aSN1Sequence) {
        ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[aSN1Sequence.size()];
        Enumeration objects = aSN1Sequence.getObjects();
        int i = 0;
        while (objects.hasMoreElements()) {
            aSN1OctetStringArr[i] = (ASN1OctetString) objects.nextElement();
            i++;
        }
        return new BEROctetString(aSN1OctetStringArr);
    }
}
