package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.ASN1UTCTime;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.X500Name;

public class TBSCertList extends ASN1Object {
    Extensions crlExtensions;
    X500Name issuer;
    Time nextUpdate;
    ASN1Sequence revokedCertificates;
    AlgorithmIdentifier signature;
    Time thisUpdate;
    ASN1Integer version;

    public static class CRLEntry extends ASN1Object {
        Extensions crlEntryExtensions;
        ASN1Sequence seq;

        private CRLEntry(ASN1Sequence aSN1Sequence) {
            if (aSN1Sequence.size() < 2 || aSN1Sequence.size() > 3) {
                throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
            }
            this.seq = aSN1Sequence;
        }

        public static CRLEntry getInstance(Object obj) {
            if (obj instanceof CRLEntry) {
                return (CRLEntry) obj;
            }
            if (obj != null) {
                return new CRLEntry(ASN1Sequence.getInstance(obj));
            }
            return null;
        }

        public ASN1Integer getUserCertificate() {
            return ASN1Integer.getInstance(this.seq.getObjectAt(0));
        }

        public Time getRevocationDate() {
            return Time.getInstance(this.seq.getObjectAt(1));
        }

        public Extensions getExtensions() {
            if (this.crlEntryExtensions == null && this.seq.size() == 3) {
                this.crlEntryExtensions = Extensions.getInstance(this.seq.getObjectAt(2));
            }
            return this.crlEntryExtensions;
        }

        @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
        public ASN1Primitive toASN1Primitive() {
            return this.seq;
        }

        public boolean hasExtensions() {
            return this.seq.size() == 3;
        }
    }

    /* access modifiers changed from: private */
    public class RevokedCertificatesEnumeration implements Enumeration {
        private final Enumeration en;

        RevokedCertificatesEnumeration(Enumeration enumeration) {
            this.en = enumeration;
        }

        public boolean hasMoreElements() {
            return this.en.hasMoreElements();
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return CRLEntry.getInstance(this.en.nextElement());
        }
    }

    /* access modifiers changed from: private */
    public class EmptyEnumeration implements Enumeration {
        public boolean hasMoreElements() {
            return false;
        }

        private EmptyEnumeration() {
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            throw new NoSuchElementException("Empty Enumeration");
        }
    }

    public static TBSCertList getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static TBSCertList getInstance(Object obj) {
        if (obj instanceof TBSCertList) {
            return (TBSCertList) obj;
        }
        if (obj != null) {
            return new TBSCertList(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public TBSCertList(ASN1Sequence aSN1Sequence) {
        int i;
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 7) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        int i2 = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            i2 = 1;
        } else {
            this.version = null;
        }
        int i3 = i2 + 1;
        this.signature = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i2));
        int i4 = i3 + 1;
        this.issuer = X500Name.getInstance(aSN1Sequence.getObjectAt(i3));
        int i5 = i4 + 1;
        this.thisUpdate = Time.getInstance(aSN1Sequence.getObjectAt(i4));
        if (i5 >= aSN1Sequence.size() || (!(aSN1Sequence.getObjectAt(i5) instanceof ASN1UTCTime) && !(aSN1Sequence.getObjectAt(i5) instanceof ASN1GeneralizedTime) && !(aSN1Sequence.getObjectAt(i5) instanceof Time))) {
            i = i5;
        } else {
            i = i5 + 1;
            this.nextUpdate = Time.getInstance(aSN1Sequence.getObjectAt(i5));
        }
        if (i < aSN1Sequence.size() && !(aSN1Sequence.getObjectAt(i) instanceof ASN1TaggedObject)) {
            this.revokedCertificates = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(i));
            i++;
        }
        if (i < aSN1Sequence.size() && (aSN1Sequence.getObjectAt(i) instanceof ASN1TaggedObject)) {
            this.crlExtensions = Extensions.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i), true));
        }
    }

    public int getVersionNumber() {
        ASN1Integer aSN1Integer = this.version;
        if (aSN1Integer == null) {
            return 1;
        }
        return aSN1Integer.getValue().intValue() + 1;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public Time getThisUpdate() {
        return this.thisUpdate;
    }

    public Time getNextUpdate() {
        return this.nextUpdate;
    }

    public CRLEntry[] getRevokedCertificates() {
        ASN1Sequence aSN1Sequence = this.revokedCertificates;
        if (aSN1Sequence == null) {
            return new CRLEntry[0];
        }
        CRLEntry[] cRLEntryArr = new CRLEntry[aSN1Sequence.size()];
        for (int i = 0; i < cRLEntryArr.length; i++) {
            cRLEntryArr[i] = CRLEntry.getInstance(this.revokedCertificates.getObjectAt(i));
        }
        return cRLEntryArr;
    }

    public Enumeration getRevokedCertificateEnumeration() {
        ASN1Sequence aSN1Sequence = this.revokedCertificates;
        if (aSN1Sequence == null) {
            return new EmptyEnumeration();
        }
        return new RevokedCertificatesEnumeration(aSN1Sequence.getObjects());
    }

    public Extensions getExtensions() {
        return this.crlExtensions;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Integer aSN1Integer = this.version;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        aSN1EncodableVector.add(this.signature);
        aSN1EncodableVector.add(this.issuer);
        aSN1EncodableVector.add(this.thisUpdate);
        Time time = this.nextUpdate;
        if (time != null) {
            aSN1EncodableVector.add(time);
        }
        ASN1Sequence aSN1Sequence = this.revokedCertificates;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        Extensions extensions = this.crlExtensions;
        if (extensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, extensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
