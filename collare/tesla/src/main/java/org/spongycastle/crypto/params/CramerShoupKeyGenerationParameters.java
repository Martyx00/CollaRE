package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class CramerShoupKeyGenerationParameters extends KeyGenerationParameters {
    private CramerShoupParameters params;

    public CramerShoupKeyGenerationParameters(SecureRandom secureRandom, CramerShoupParameters cramerShoupParameters) {
        super(secureRandom, getStrength(cramerShoupParameters));
        this.params = cramerShoupParameters;
    }

    public CramerShoupParameters getParameters() {
        return this.params;
    }

    static int getStrength(CramerShoupParameters cramerShoupParameters) {
        return cramerShoupParameters.getP().bitLength();
    }
}
