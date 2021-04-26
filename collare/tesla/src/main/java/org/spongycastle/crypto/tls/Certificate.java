package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encoding;

public class Certificate {
    public static final Certificate EMPTY_CHAIN = new Certificate(new org.spongycastle.asn1.x509.Certificate[0]);
    protected org.spongycastle.asn1.x509.Certificate[] certificateList;

    public Certificate(org.spongycastle.asn1.x509.Certificate[] certificateArr) {
        if (certificateArr != null) {
            this.certificateList = certificateArr;
            return;
        }
        throw new IllegalArgumentException("'certificateList' cannot be null");
    }

    public org.spongycastle.asn1.x509.Certificate[] getCertificateList() {
        return cloneCertificateList();
    }

    public org.spongycastle.asn1.x509.Certificate getCertificateAt(int i) {
        return this.certificateList[i];
    }

    public int getLength() {
        return this.certificateList.length;
    }

    public boolean isEmpty() {
        return this.certificateList.length == 0;
    }

    public void encode(OutputStream outputStream) {
        Vector vector = new Vector(this.certificateList.length);
        int i = 0;
        int i2 = 0;
        while (true) {
            org.spongycastle.asn1.x509.Certificate[] certificateArr = this.certificateList;
            if (i >= certificateArr.length) {
                break;
            }
            byte[] encoded = certificateArr[i].getEncoded(ASN1Encoding.DER);
            vector.addElement(encoded);
            i2 += encoded.length + 3;
            i++;
        }
        TlsUtils.checkUint24(i2);
        TlsUtils.writeUint24(i2, outputStream);
        for (int i3 = 0; i3 < vector.size(); i3++) {
            TlsUtils.writeOpaque24((byte[]) vector.elementAt(i3), outputStream);
        }
    }

    public static Certificate parse(InputStream inputStream) {
        int readUint24 = TlsUtils.readUint24(inputStream);
        if (readUint24 == 0) {
            return EMPTY_CHAIN;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(readUint24, inputStream));
        Vector vector = new Vector();
        while (byteArrayInputStream.available() > 0) {
            vector.addElement(org.spongycastle.asn1.x509.Certificate.getInstance(TlsUtils.readDERObject(TlsUtils.readOpaque24(byteArrayInputStream))));
        }
        org.spongycastle.asn1.x509.Certificate[] certificateArr = new org.spongycastle.asn1.x509.Certificate[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            certificateArr[i] = (org.spongycastle.asn1.x509.Certificate) vector.elementAt(i);
        }
        return new Certificate(certificateArr);
    }

    /* access modifiers changed from: protected */
    public org.spongycastle.asn1.x509.Certificate[] cloneCertificateList() {
        org.spongycastle.asn1.x509.Certificate[] certificateArr = this.certificateList;
        org.spongycastle.asn1.x509.Certificate[] certificateArr2 = new org.spongycastle.asn1.x509.Certificate[certificateArr.length];
        System.arraycopy(certificateArr, 0, certificateArr2, 0, certificateArr2.length);
        return certificateArr2;
    }
}
