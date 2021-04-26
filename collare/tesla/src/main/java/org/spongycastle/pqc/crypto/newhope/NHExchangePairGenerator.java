package org.spongycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.ExchangePair;
import org.spongycastle.pqc.crypto.ExchangePairGenerator;

public class NHExchangePairGenerator implements ExchangePairGenerator {
    private final SecureRandom random;

    public NHExchangePairGenerator(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    @Override // org.spongycastle.pqc.crypto.ExchangePairGenerator
    public ExchangePair GenerateExchange(AsymmetricKeyParameter asymmetricKeyParameter) {
        byte[] bArr = new byte[32];
        byte[] bArr2 = new byte[2048];
        NewHope.sharedB(this.random, bArr, bArr2, ((NHPublicKeyParameters) asymmetricKeyParameter).pubData);
        return new ExchangePair(new NHPublicKeyParameters(bArr2), bArr);
    }
}
