package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class ECKeyGenerationParameters extends KeyGenerationParameters {
    private ECDomainParameters domainParams;

    public ECKeyGenerationParameters(ECDomainParameters eCDomainParameters, SecureRandom secureRandom) {
        super(secureRandom, eCDomainParameters.getN().bitLength());
        this.domainParams = eCDomainParameters;
    }

    public ECDomainParameters getDomainParameters() {
        return this.domainParams;
    }
}
