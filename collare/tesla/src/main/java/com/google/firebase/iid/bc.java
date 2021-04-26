package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;

/* access modifiers changed from: package-private */
public final class bc {

    /* renamed from: a  reason: collision with root package name */
    private final KeyPair f3918a;

    /* renamed from: b  reason: collision with root package name */
    private final long f3919b;

    @VisibleForTesting
    bc(KeyPair keyPair, long j) {
        this.f3918a = keyPair;
        this.f3919b = j;
    }

    /* access modifiers changed from: package-private */
    public final KeyPair a() {
        return this.f3918a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof bc)) {
            return false;
        }
        bc bcVar = (bc) obj;
        if (this.f3919b != bcVar.f3919b || !this.f3918a.getPublic().equals(bcVar.f3918a.getPublic()) || !this.f3918a.getPrivate().equals(bcVar.f3918a.getPrivate())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f3918a.getPublic(), this.f3918a.getPrivate(), Long.valueOf(this.f3919b));
    }

    /* access modifiers changed from: private */
    public final String b() {
        return Base64.encodeToString(this.f3918a.getPublic().getEncoded(), 11);
    }

    /* access modifiers changed from: private */
    public final String c() {
        return Base64.encodeToString(this.f3918a.getPrivate().getEncoded(), 11);
    }
}
