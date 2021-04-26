package com.google.firebase.components;

import com.google.firebase.b.a;

/* access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public abstract class i implements b {
    i() {
    }

    @Override // com.google.firebase.components.b
    public <T> T a(Class<T> cls) {
        a<T> b2 = b(cls);
        if (b2 == null) {
            return null;
        }
        return b2.a();
    }
}
