package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

class Utils {
    Utils() {
    }

    static Digest getDigest(String str) {
        if (str.equals(McElieceCCA2KeyGenParameterSpec.SHA1)) {
            return new SHA1Digest();
        }
        if (str.equals(McElieceCCA2KeyGenParameterSpec.SHA224)) {
            return new SHA224Digest();
        }
        if (str.equals(McElieceCCA2KeyGenParameterSpec.SHA256)) {
            return new SHA256Digest();
        }
        if (str.equals(McElieceCCA2KeyGenParameterSpec.SHA384)) {
            return new SHA384Digest();
        }
        if (str.equals(McElieceCCA2KeyGenParameterSpec.SHA512)) {
            return new SHA512Digest();
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + str);
    }
}
