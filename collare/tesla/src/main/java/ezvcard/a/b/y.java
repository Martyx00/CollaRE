package ezvcard.a.b;

import ezvcard.parameter.KeyType;
import ezvcard.property.Key;

/* compiled from: KeyScribe */
public class y extends d<Key, KeyType> {
    public y() {
        super(Key.class, "KEY");
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public KeyType b(String str) {
        return KeyType.b(str, null, null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public KeyType a(String str) {
        return KeyType.b(null, str, null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public KeyType c(String str) {
        return KeyType.a(null, null, str);
    }

    /* access modifiers changed from: protected */
    public Key a(String str, KeyType keyType) {
        return new Key(str, keyType);
    }

    /* access modifiers changed from: protected */
    public Key a(byte[] bArr, KeyType keyType) {
        return new Key(bArr, keyType);
    }
}
