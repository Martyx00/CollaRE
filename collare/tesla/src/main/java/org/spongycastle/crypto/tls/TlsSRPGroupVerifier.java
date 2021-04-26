package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.params.SRP6GroupParameters;

public interface TlsSRPGroupVerifier {
    boolean accept(SRP6GroupParameters sRP6GroupParameters);
}
