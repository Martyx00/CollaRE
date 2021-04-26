package org.spongycastle.asn1.icao;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.x509.Certificate;

public class CscaMasterList extends ASN1Object {
    private Certificate[] certList;
    private ASN1Integer version = new ASN1Integer(0);

    public static CscaMasterList getInstance(Object obj) {
        if (obj instanceof CscaMasterList) {
            return (CscaMasterList) obj;
        }
        if (obj != null) {
            return new CscaMasterList(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private CscaMasterList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence == null || aSN1Sequence.size() == 0) {
            throw new IllegalArgumentException("null or empty sequence passed.");
        } else if (aSN1Sequence.size() == 2) {
            int i = 0;
            this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            ASN1Set instance = ASN1Set.getInstance(aSN1Sequence.getObjectAt(1));
            this.certList = new Certificate[instance.size()];
            while (true) {
                Certificate[] certificateArr = this.certList;
                if (i < certificateArr.length) {
                    certificateArr[i] = Certificate.getInstance(instance.getObjectAt(i));
                    i++;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Incorrect sequence size: " + aSN1Sequence.size());
        }
    }

    public CscaMasterList(Certificate[] certificateArr) {
        this.certList = copyCertList(certificateArr);
    }

    public int getVersion() {
        return this.version.getValue().intValue();
    }

    public Certificate[] getCertStructs() {
        return copyCertList(this.certList);
    }

    private Certificate[] copyCertList(Certificate[] certificateArr) {
        Certificate[] certificateArr2 = new Certificate[certificateArr.length];
        for (int i = 0; i != certificateArr2.length; i++) {
            certificateArr2[i] = certificateArr[i];
        }
        return certificateArr2;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            Certificate[] certificateArr = this.certList;
            if (i < certificateArr.length) {
                aSN1EncodableVector2.add(certificateArr[i]);
                i++;
            } else {
                aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
                return new DERSequence(aSN1EncodableVector);
            }
        }
    }
}
