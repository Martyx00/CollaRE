package org.spongycastle.asn1.bc;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class ObjectStore extends ASN1Object {
    private final ObjectStoreIntegrityCheck integrityCheck;
    private final ASN1Encodable storeData;

    public ObjectStore(ObjectStoreData objectStoreData, ObjectStoreIntegrityCheck objectStoreIntegrityCheck) {
        this.storeData = objectStoreData;
        this.integrityCheck = objectStoreIntegrityCheck;
    }

    public ObjectStore(EncryptedObjectStoreData encryptedObjectStoreData, ObjectStoreIntegrityCheck objectStoreIntegrityCheck) {
        this.storeData = encryptedObjectStoreData;
        this.integrityCheck = objectStoreIntegrityCheck;
    }

    private ObjectStore(ASN1Sequence aSN1Sequence) {
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(0);
        if (objectAt instanceof EncryptedObjectStoreData) {
            this.storeData = objectAt;
        } else if (objectAt instanceof ObjectStoreData) {
            this.storeData = objectAt;
        } else {
            ASN1Sequence instance = ASN1Sequence.getInstance(objectAt);
            if (instance.size() == 2) {
                this.storeData = EncryptedObjectStoreData.getInstance(instance);
            } else {
                this.storeData = ObjectStoreData.getInstance(instance);
            }
        }
        this.integrityCheck = ObjectStoreIntegrityCheck.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static ObjectStore getInstance(Object obj) {
        if (obj instanceof ObjectStore) {
            return (ObjectStore) obj;
        }
        if (obj != null) {
            return new ObjectStore(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ObjectStoreIntegrityCheck getIntegrityCheck() {
        return this.integrityCheck;
    }

    public ASN1Encodable getStoreData() {
        return this.storeData;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.storeData);
        aSN1EncodableVector.add(this.integrityCheck);
        return new DERSequence(aSN1EncodableVector);
    }
}
