package org.spongycastle.asn1.bc;

import java.util.Iterator;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Iterable;

public class ObjectDataSequence extends ASN1Object implements Iterable<ASN1Encodable> {
    private final ASN1Encodable[] dataSequence;

    public ObjectDataSequence(ObjectData[] objectDataArr) {
        this.dataSequence = new ASN1Encodable[objectDataArr.length];
        System.arraycopy(objectDataArr, 0, this.dataSequence, 0, objectDataArr.length);
    }

    private ObjectDataSequence(ASN1Sequence aSN1Sequence) {
        this.dataSequence = new ASN1Encodable[aSN1Sequence.size()];
        int i = 0;
        while (true) {
            ASN1Encodable[] aSN1EncodableArr = this.dataSequence;
            if (i != aSN1EncodableArr.length) {
                aSN1EncodableArr[i] = ObjectData.getInstance(aSN1Sequence.getObjectAt(i));
                i++;
            } else {
                return;
            }
        }
    }

    public static ObjectDataSequence getInstance(Object obj) {
        if (obj instanceof ObjectDataSequence) {
            return (ObjectDataSequence) obj;
        }
        if (obj != null) {
            return new ObjectDataSequence(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.dataSequence);
    }

    @Override // org.spongycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(this.dataSequence);
    }
}
