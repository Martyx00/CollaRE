package org.spongycastle.crypto.util;

import java.io.IOException;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.util.Arrays;

class DerUtil {
    DerUtil() {
    }

    static ASN1OctetString getOctetString(byte[] bArr) {
        if (bArr == null) {
            return new DEROctetString(new byte[0]);
        }
        return new DEROctetString(Arrays.clone(bArr));
    }

    static byte[] toByteArray(ASN1Primitive aSN1Primitive) {
        try {
            return aSN1Primitive.getEncoded();
        } catch (IOException e) {
            throw new IllegalStateException("Cannot get encoding: " + e.getMessage()) {
                /* class org.spongycastle.crypto.util.DerUtil.AnonymousClass1 */

                public Throwable getCause() {
                    return e;
                }
            };
        }
    }
}
