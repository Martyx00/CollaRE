package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public interface ExchangePairGenerator {
    ExchangePair GenerateExchange(AsymmetricKeyParameter asymmetricKeyParameter);
}
