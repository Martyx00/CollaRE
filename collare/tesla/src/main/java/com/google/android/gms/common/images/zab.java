package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;

/* access modifiers changed from: package-private */
public final class zab {
    public final Uri uri;

    public zab(Uri uri2) {
        this.uri = uri2;
    }

    public final int hashCode() {
        return Objects.hashCode(this.uri);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zab)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return Objects.equal(((zab) obj).uri, this.uri);
    }
}
