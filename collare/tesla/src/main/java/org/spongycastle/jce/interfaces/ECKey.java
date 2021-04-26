package org.spongycastle.jce.interfaces;

import org.spongycastle.jce.spec.ECParameterSpec;

public interface ECKey {
    ECParameterSpec getParameters();
}
