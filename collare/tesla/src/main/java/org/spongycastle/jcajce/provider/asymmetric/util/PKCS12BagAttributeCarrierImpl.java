package org.spongycastle.jcajce.provider.asymmetric.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OutputStream;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;

public class PKCS12BagAttributeCarrierImpl implements PKCS12BagAttributeCarrier {
    private Hashtable pkcs12Attributes;
    private Vector pkcs12Ordering;

    PKCS12BagAttributeCarrierImpl(Hashtable hashtable, Vector vector) {
        this.pkcs12Attributes = hashtable;
        this.pkcs12Ordering = vector;
    }

    public PKCS12BagAttributeCarrierImpl() {
        this(new Hashtable(), new Vector());
    }

    @Override // org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        if (this.pkcs12Attributes.containsKey(aSN1ObjectIdentifier)) {
            this.pkcs12Attributes.put(aSN1ObjectIdentifier, aSN1Encodable);
            return;
        }
        this.pkcs12Attributes.put(aSN1ObjectIdentifier, aSN1Encodable);
        this.pkcs12Ordering.addElement(aSN1ObjectIdentifier);
    }

    @Override // org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (ASN1Encodable) this.pkcs12Attributes.get(aSN1ObjectIdentifier);
    }

    @Override // org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public Enumeration getBagAttributeKeys() {
        return this.pkcs12Ordering.elements();
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.pkcs12Ordering.size();
    }

    /* access modifiers changed from: package-private */
    public Hashtable getAttributes() {
        return this.pkcs12Attributes;
    }

    /* access modifiers changed from: package-private */
    public Vector getOrdering() {
        return this.pkcs12Ordering;
    }

    public void writeObject(ObjectOutputStream objectOutputStream) {
        if (this.pkcs12Ordering.size() == 0) {
            objectOutputStream.writeObject(new Hashtable());
            objectOutputStream.writeObject(new Vector());
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
        Enumeration bagAttributeKeys = getBagAttributeKeys();
        while (bagAttributeKeys.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) bagAttributeKeys.nextElement();
            aSN1OutputStream.writeObject(aSN1ObjectIdentifier);
            aSN1OutputStream.writeObject((ASN1Encodable) this.pkcs12Attributes.get(aSN1ObjectIdentifier));
        }
        objectOutputStream.writeObject(byteArrayOutputStream.toByteArray());
    }

    public void readObject(ObjectInputStream objectInputStream) {
        Object readObject = objectInputStream.readObject();
        if (readObject instanceof Hashtable) {
            this.pkcs12Attributes = (Hashtable) readObject;
            this.pkcs12Ordering = (Vector) objectInputStream.readObject();
            return;
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream((byte[]) readObject);
        while (true) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1InputStream.readObject();
            if (aSN1ObjectIdentifier != null) {
                setBagAttribute(aSN1ObjectIdentifier, aSN1InputStream.readObject());
            } else {
                return;
            }
        }
    }
}
