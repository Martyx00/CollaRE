package org.spongycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Iterable;

public abstract class ASN1Set extends ASN1Primitive implements Iterable<ASN1Encodable> {
    private boolean isSorted;
    private Vector set;

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public abstract void encode(ASN1OutputStream aSN1OutputStream);

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return true;
    }

    public static ASN1Set getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Set)) {
            return (ASN1Set) obj;
        }
        if (obj instanceof ASN1SetParser) {
            return getInstance(((ASN1SetParser) obj).toASN1Primitive());
        }
        if (obj instanceof byte[]) {
            try {
                return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + e.getMessage());
            }
        } else {
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Set) {
                    return (ASN1Set) aSN1Primitive;
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Set getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            if (aSN1TaggedObject.isExplicit()) {
                return (ASN1Set) aSN1TaggedObject.getObject();
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        } else if (aSN1TaggedObject.isExplicit()) {
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSet(aSN1TaggedObject.getObject());
            }
            return new DLSet(aSN1TaggedObject.getObject());
        } else if (aSN1TaggedObject.getObject() instanceof ASN1Set) {
            return (ASN1Set) aSN1TaggedObject.getObject();
        } else {
            if (aSN1TaggedObject.getObject() instanceof ASN1Sequence) {
                ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1TaggedObject.getObject();
                if (aSN1TaggedObject instanceof BERTaggedObject) {
                    return new BERSet(aSN1Sequence.toArray());
                }
                return new DLSet(aSN1Sequence.toArray());
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
        }
    }

    protected ASN1Set() {
        this.set = new Vector();
        this.isSorted = false;
    }

    protected ASN1Set(ASN1Encodable aSN1Encodable) {
        this.set = new Vector();
        this.isSorted = false;
        this.set.addElement(aSN1Encodable);
    }

    protected ASN1Set(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        this.set = new Vector();
        this.isSorted = false;
        for (int i = 0; i != aSN1EncodableVector.size(); i++) {
            this.set.addElement(aSN1EncodableVector.get(i));
        }
        if (z) {
            sort();
        }
    }

    protected ASN1Set(ASN1Encodable[] aSN1EncodableArr, boolean z) {
        this.set = new Vector();
        this.isSorted = false;
        for (int i = 0; i != aSN1EncodableArr.length; i++) {
            this.set.addElement(aSN1EncodableArr[i]);
        }
        if (z) {
            sort();
        }
    }

    public Enumeration getObjects() {
        return this.set.elements();
    }

    public ASN1Encodable getObjectAt(int i) {
        return (ASN1Encodable) this.set.elementAt(i);
    }

    public int size() {
        return this.set.size();
    }

    public ASN1Encodable[] toArray() {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[size()];
        for (int i = 0; i != size(); i++) {
            aSN1EncodableArr[i] = getObjectAt(i);
        }
        return aSN1EncodableArr;
    }

    public ASN1SetParser parser() {
        return new ASN1SetParser() {
            /* class org.spongycastle.asn1.ASN1Set.AnonymousClass1 */
            private int index;
            private final int max = ASN1Set.this.size();

            @Override // org.spongycastle.asn1.ASN1SetParser
            public ASN1Encodable readObject() {
                int i = this.index;
                if (i == this.max) {
                    return null;
                }
                ASN1Set aSN1Set = ASN1Set.this;
                this.index = i + 1;
                ASN1Encodable objectAt = aSN1Set.getObjectAt(i);
                if (objectAt instanceof ASN1Sequence) {
                    return ((ASN1Sequence) objectAt).parser();
                }
                return objectAt instanceof ASN1Set ? ((ASN1Set) objectAt).parser() : objectAt;
            }

            @Override // org.spongycastle.asn1.InMemoryRepresentable
            public ASN1Primitive getLoadedObject() {
                return this;
            }

            @Override // org.spongycastle.asn1.ASN1Encodable
            public ASN1Primitive toASN1Primitive() {
                return this;
            }
        };
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        Enumeration objects = getObjects();
        int size = size();
        while (objects.hasMoreElements()) {
            size = (size * 17) ^ getNext(objects).hashCode();
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        if (this.isSorted) {
            DERSet dERSet = new DERSet();
            dERSet.set = this.set;
            return dERSet;
        }
        Vector vector = new Vector();
        for (int i = 0; i != this.set.size(); i++) {
            vector.addElement(this.set.elementAt(i));
        }
        DERSet dERSet2 = new DERSet();
        dERSet2.set = vector;
        dERSet2.sort();
        return dERSet2;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        DLSet dLSet = new DLSet();
        dLSet.set = this.set;
        return dLSet;
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Set)) {
            return false;
        }
        ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
        if (size() != aSN1Set.size()) {
            return false;
        }
        Enumeration objects = getObjects();
        Enumeration objects2 = aSN1Set.getObjects();
        while (objects.hasMoreElements()) {
            ASN1Encodable next = getNext(objects);
            ASN1Encodable next2 = getNext(objects2);
            ASN1Primitive aSN1Primitive2 = next.toASN1Primitive();
            ASN1Primitive aSN1Primitive3 = next2.toASN1Primitive();
            if (aSN1Primitive2 != aSN1Primitive3 && !aSN1Primitive2.equals(aSN1Primitive3)) {
                return false;
            }
        }
        return true;
    }

    private ASN1Encodable getNext(Enumeration enumeration) {
        ASN1Encodable aSN1Encodable = (ASN1Encodable) enumeration.nextElement();
        return aSN1Encodable == null ? DERNull.INSTANCE : aSN1Encodable;
    }

    private boolean lessThanOrEqual(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        for (int i = 0; i != min; i++) {
            if (bArr[i] != bArr2[i]) {
                if ((bArr[i] & 255) < (bArr2[i] & 255)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (min == bArr.length) {
            return true;
        }
        return false;
    }

    private byte[] getDEREncoded(ASN1Encodable aSN1Encodable) {
        try {
            return aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    /* access modifiers changed from: protected */
    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
        */
    public void sort() {
        /*
            r9 = this;
            boolean r0 = r9.isSorted
            if (r0 != 0) goto L_0x005d
            r0 = 1
            r9.isSorted = r0
            java.util.Vector r1 = r9.set
            int r1 = r1.size()
            if (r1 <= r0) goto L_0x005d
            java.util.Vector r1 = r9.set
            int r1 = r1.size()
            int r1 = r1 - r0
            r2 = r1
            r1 = 1
        L_0x0018:
            if (r1 == 0) goto L_0x005d
            java.util.Vector r1 = r9.set
            r3 = 0
            java.lang.Object r1 = r1.elementAt(r3)
            org.spongycastle.asn1.ASN1Encodable r1 = (org.spongycastle.asn1.ASN1Encodable) r1
            byte[] r1 = r9.getDEREncoded(r1)
            r5 = r1
            r1 = 0
            r4 = 0
        L_0x002a:
            if (r3 == r2) goto L_0x005a
            java.util.Vector r6 = r9.set
            int r7 = r3 + 1
            java.lang.Object r6 = r6.elementAt(r7)
            org.spongycastle.asn1.ASN1Encodable r6 = (org.spongycastle.asn1.ASN1Encodable) r6
            byte[] r6 = r9.getDEREncoded(r6)
            boolean r8 = r9.lessThanOrEqual(r5, r6)
            if (r8 == 0) goto L_0x0042
            r5 = r6
            goto L_0x0058
        L_0x0042:
            java.util.Vector r1 = r9.set
            java.lang.Object r1 = r1.elementAt(r3)
            java.util.Vector r4 = r9.set
            java.lang.Object r6 = r4.elementAt(r7)
            r4.setElementAt(r6, r3)
            java.util.Vector r4 = r9.set
            r4.setElementAt(r1, r7)
            r1 = r3
            r4 = 1
        L_0x0058:
            r3 = r7
            goto L_0x002a
        L_0x005a:
            r2 = r1
            r1 = r4
            goto L_0x0018
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.ASN1Set.sort():void");
    }

    public String toString() {
        return this.set.toString();
    }

    @Override // org.spongycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(toArray());
    }
}
