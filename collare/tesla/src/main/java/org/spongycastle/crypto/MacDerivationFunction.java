package org.spongycastle.crypto;

public interface MacDerivationFunction extends DerivationFunction {
    Mac getMac();
}
